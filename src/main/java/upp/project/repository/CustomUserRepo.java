package upp.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import upp.project.model.CustomUser;

public interface CustomUserRepo extends JpaRepository<CustomUser, Long>{

	CustomUser findByUsername(String name);
}
