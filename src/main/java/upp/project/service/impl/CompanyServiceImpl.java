package upp.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import upp.project.model.Company;
import upp.project.model.dto.RequestDTO;
import upp.project.repository.CompanyRepo;
import upp.project.service.CompanyService;

public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	private CompanyRepo companyRepo;

	@Override
	public List<Company> findCandidates(RequestDTO requestDTO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
