package upp.project.controllers;

import com.google.gson.Gson;
import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import upp.project.model.dto.FormDTO;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

	@Autowired
	private TaskService taskService;

	@Autowired
	private FormService formService;
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	private static final Gson gson = new Gson();
	
	@Transactional
	@GetMapping(value="/start")
	public ResponseEntity newInstance(){
		ProcessDefinition procDef = repositoryService.createProcessDefinitionQuery().processDefinitionKey("myProcess").latestVersion().singleResult();
		StartFormData formData = formService.getStartFormData(procDef.getId());
		List<FormProperty> formProperties = formData.getFormProperties();

		FormDTO formDTO = new FormDTO();
		formDTO.setFormKey(formData.getFormKey());

		if (formProperties.size() == 0){
			runtimeService.startProcessInstanceByKey("myProcess");
			String message = "Nova instanca procesa je uspesno pokrenuta. Nema forme na startu.";
			formDTO.setMessage(message);
		}else{
			formDTO.setFormProperties(formProperties);
			formDTO.setMessage("Forma na startu.");
		}

		return new ResponseEntity(formDTO, HttpStatus.OK);
	}
}
