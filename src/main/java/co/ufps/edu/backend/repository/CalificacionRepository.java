package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {
}
