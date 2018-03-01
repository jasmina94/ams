package upp.project.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import upp.project.model.dto.FormDTO;
import upp.project.model.dto.RequestDTO;
import upp.project.model.dto.TaskDTO;
import upp.project.model.dto.UserDTO;
import upp.project.service.UserService;
import upp.project.util.Messages;
import upp.project.util.Validator;

@RestController
@RequestMapping("/auction")
public class AuctionController {
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private FormService formService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping(value="/start")
	public void newInstance(HttpServletResponse response) throws IOException{
		
		//Proveri ulogovanog
		UserDTO userDTO = userService.read();
		if(userDTO == null){
			response.sendRedirect("/index.html");
		}else {		
			ProcessDefinition procDef = repositoryService.createProcessDefinitionQuery().processDefinitionKey("aukcije").latestVersion().singleResult();
			HashMap<String, Object> variables = new HashMap<>();
			variables.put("klijent", userDTO);
			
			runtimeService.startProcessInstanceByKey("aukcije", variables);
			String message = "Nova instanca je uspešno pokrenuta";
			System.out.println(message);	
		}
	}
	
	@GetMapping(value="/tasks")
	public ResponseEntity<List<TaskDTO>> getTasks(HttpServletResponse response) throws IOException{
		//Proveri ulogovanog
		UserDTO userDTO = userService.read();
		if(userDTO == null){
			response.sendRedirect("/index.html");
			return null;
		}else{
			List<Task> myTasks = taskService.createTaskQuery().taskCandidateOrAssigned(userDTO.getUsername()).list();
			List<TaskDTO> taskDTOs = new ArrayList<>();
			for (Task task : myTasks) {
				TaskDTO taskDTO = new TaskDTO(task);
				taskDTOs.add(taskDTO);
			}
			return new ResponseEntity<List<TaskDTO>>(taskDTOs, HttpStatus.OK); 
		}
	}
	
	@GetMapping("/task/{id}")
	public ResponseEntity<FormDTO> test(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Task task= taskService.createTaskQuery().taskId(id).singleResult(); //koji task treba da se izvrsi
		String instanceId = task.getProcessInstanceId(); //instanca
		FormDTO formDTO = new FormDTO(); //forma
		UserDTO userDTO = userService.read();  //ko je ulogovan
		HashMap<String, Object> variables=(HashMap<String, Object>) runtimeService.getVariables(instanceId);
		if(variables.get("klijent").equals(userDTO)){
			TaskFormData taskFormData = formService.getTaskFormData(id);
			List<FormProperty> formProperties = taskFormData.getFormProperties();
			if (formProperties.size() == 0){
				taskService.complete(id);
				formDTO.setMessage(Messages.SUCCESSFUL_TASK);
				System.out.println("Zavrsio je task");
			}else {
				formDTO.setFormKey(taskFormData.getFormKey());
				formDTO.setFormProperties(formProperties);
				for(FormProperty formProperty : formProperties){
					if(formProperty.getType().getName().equals("enum")){
						LinkedHashMap<String, String> information = (LinkedHashMap<String, String>) formProperty.getType().getInformation("values");
						formDTO.setEnumMap(information);
					}
				}
			}
		}else {
			response.sendRedirect("/index.html");
		}
		return new ResponseEntity<FormDTO>(formDTO, HttpStatus.OK);
	}
	
	//Potvrdjuje formu ovde treba da validiram i kreiram objekat RequestDTO i zakacim ga kao zahtev
	@PostMapping(value="/confirm/{taskId}")
	public ResponseEntity<String> confirmData(@PathVariable String taskId, @RequestBody RequestDTO requestDTO, HttpServletRequest request){
		Task task= taskService.createTaskQuery().taskId(taskId).singleResult();
		String message = "";
		TaskFormData formData = formService.getTaskFormData(taskId); //za validaciju ostavi
		
		requestDTO = changeDates(requestDTO);
		
		HashMap<String, Object> variables=(HashMap<String, Object>) runtimeService.getVariables(task.getProcessInstanceId());
		UserDTO klijent = (UserDTO) variables.get("klijent");
		String userId = klijent.getUsername();
		requestDTO.setRequestMaker(userId);
		if(canExecute(taskId, userId)){
			variables.put("zahtev", requestDTO);
			variables.put("minPonuda", requestDTO.getMinPonuda());
			variables.put("maxPonuda", requestDTO.getMaxPonuda());
			taskService.complete(taskId,variables);
			message = "ok";
		}else {
			message = "fail";
		}
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	@Transactional
	@PostMapping(value="/confirmGen/{taskId}")
	public ResponseEntity<String> confirmGenData(@PathVariable String taskId, @RequestBody Map<String, String> params, HttpServletRequest request){
		String message = "";
		Task task= taskService.createTaskQuery().taskId(taskId).singleResult();
		TaskFormData formData = formService.getTaskFormData(taskId); //za validaciju ostavi
		boolean valid = Validator.validateForm(formData, params);
		HashMap<String, Object> variables=(HashMap<String, Object>) runtimeService.getVariables(task.getProcessInstanceId());
		UserDTO klijent = (UserDTO) variables.get("klijent");
		String userId = klijent.getUsername();
		if(canExecute(taskId, userId) && valid){
			formService.submitTaskFormData(taskId, params);
			message = "ok";
		}else{
			message = "fail";
		}
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	private RequestDTO changeDates(RequestDTO requestDTO){
		Calendar c = Calendar.getInstance(); 
		c.setTime(requestDTO.getRokZaPonude()); 
		c.add(Calendar.HOUR, -1);
		requestDTO.setRokZaPonude(c.getTime());
		
		c = Calendar.getInstance();
		c.setTime(requestDTO.getRokIzvrsenja()); 
		c.add(Calendar.HOUR, -1);
		requestDTO.setRokIzvrsenja(c.getTime());
		
		return requestDTO;
	}
	
	@Transactional
	@GetMapping(value="/acceptLower/{processInstanceId}/{executionId}")
	public void activeProfile(@PathVariable String processInstanceId, @PathVariable String executionId, HttpServletResponse response) throws IOException{
		Execution execution = runtimeService.createExecutionQuery().processInstanceId(processInstanceId).executionId(executionId).singleResult();
		if(execution != null){
			execution = runtimeService.createExecutionQuery().processInstanceId(processInstanceId).signalEventSubscriptionName("prihvataManjePonuda").singleResult();
			runtimeService.signalEventReceived("prihvataManjePonuda", execution.getId());
			System.out.println("Potvrdjen manji broj redirektuj na indeks.");
			response.sendRedirect("/index.html");
		}else {
			System.out.println("iseklo vreme vrati ga na neku drugu stranicu.");
			response.sendRedirect("/timeout.html");
		}	
	}
	
	
	
	private boolean canExecute(String taskId, String userId){
		for (Task t : taskService.createTaskQuery().taskAssignee(userId).list())
			if (t.getId().equals(taskId))
				return true;
		return false;
	}
	
	
	private boolean canClaim(String taskId, String userId){
		for (Task t : taskService.createTaskQuery().taskCandidateUser(userId).list())
			if (t.getId().equals(taskId))
				return true;
		return false;
	}
	

	
}
