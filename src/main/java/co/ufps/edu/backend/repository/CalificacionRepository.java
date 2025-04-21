package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {
    List<Calificacion> findByEstudiante_Id(int estudianteId);
}
