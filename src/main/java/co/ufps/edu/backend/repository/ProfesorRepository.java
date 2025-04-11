package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
}
