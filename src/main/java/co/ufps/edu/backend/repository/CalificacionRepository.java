package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.Calificacion;
import co.ufps.edu.backend.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {
    static Optional<Calificacion> findByEstudiante(Estudiante estudiante) {
        return null;
    }
}
