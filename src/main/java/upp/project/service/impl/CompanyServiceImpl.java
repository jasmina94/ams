package upp.project.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upp.project.model.CustomUser;
import upp.project.model.dto.CustomUserDTO;
import upp.project.model.dto.RequestDTO;
import upp.project.repository.CustomUserRepo;
import upp.project.service.CompanyService;

@Service("companyService")
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	private CustomUserRepo customUserRepo;
	
	@Autowired
	private RuntimeService runtimeService;

	@Override
	public List<CustomUserDTO> findCandidates(RequestDTO requestDTO, String processInstanceId) {
		List<CustomUser> all = customUserRepo.findByTip(CustomUser.Type.PRAVNO);  //sve firme
		List<CustomUserDTO> firme = new ArrayList<CustomUserDTO>();
		for(CustomUser c : all){
			if(c.getJobCategory().getName().equals(requestDTO.getKategorijaPosla())){
				CustomUserDTO cc = new CustomUserDTO(c, true);
				firme.add(cc);
				System.out.println(c.getEmail());
			}
		}
		
		HashMap<String, Object> variables = (HashMap<String, Object>) runtimeService.getVariables(processInstanceId);
		variables.put("firme", firme);
		runtimeService.setVariables(processInstanceId, variables);
		
		return firme;
	}
	
	

}
