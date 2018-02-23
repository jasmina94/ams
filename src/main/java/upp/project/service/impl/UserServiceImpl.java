package upp.project.service.impl;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import upp.project.model.dto.UserDTO;
import upp.project.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private IdentityService identityService;
	
	@Override
	public UserDTO read() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		List<User> users = identityService.createUserQuery().userId(username).list();
		User user = users.get(0);
        return new UserDTO(user);
	}

	@Override
	public void notifyUserWrongRegistrationData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserDTO activate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub
		
	}

}
