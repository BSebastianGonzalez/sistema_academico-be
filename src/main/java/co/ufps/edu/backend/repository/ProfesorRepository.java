package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

    Optional<Profesor> findByNombre(String nombre);
}
