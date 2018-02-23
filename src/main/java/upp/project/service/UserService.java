package upp.project.service;


import upp.project.model.dto.UserDTO;

public interface UserService {

	UserDTO read();
	
	void notifyUserWrongRegistrationData();
	
	UserDTO activate();
	
	void deactivate();
}
