package upp.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import upp.project.model.Company;

public interface CompanyRepo extends JpaRepository<Company, Long> {

	Company findByName(String name);
}
