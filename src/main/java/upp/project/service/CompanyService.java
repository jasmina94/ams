package upp.project.service;

import java.util.List;

import upp.project.model.CustomUser;
import upp.project.model.dto.CustomUserDTO;
import upp.project.model.dto.RequestDTO;

public interface CompanyService {

	List<CustomUserDTO> findCandidates(RequestDTO requestDTO, String processInstanceId);
}
