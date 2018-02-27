package upp.project.service;

public interface MailService {

	public void send(String executionId, String processInstanceId, String emailTo);
}
