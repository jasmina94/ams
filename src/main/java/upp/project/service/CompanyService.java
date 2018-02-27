package upp.project.service;

import java.util.List;

import upp.project.model.Company;
import upp.project.model.dto.RequestDTO;

public interface CompanyService {

	List<Company> findCandidates(RequestDTO requestDTO);
}
