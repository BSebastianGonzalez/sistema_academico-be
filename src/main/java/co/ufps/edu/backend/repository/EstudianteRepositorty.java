package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepositorty extends JpaRepository<Estudiante, Long> {
}
