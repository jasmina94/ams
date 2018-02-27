package upp.project.model.dto;

import org.activiti.engine.task.Task;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskDTO {

	private String id;
	
	private String name;
	
	private String assigne;
	
	private String processInstanceId;
	
	public TaskDTO(Task task){
		this.id = task.getId();
		this.name = task.getName();
		this.assigne = task.getAssignee();
		this.processInstanceId = task.getProcessInstanceId();
	}
}
