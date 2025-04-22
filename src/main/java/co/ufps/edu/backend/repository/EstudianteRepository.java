package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.Carrera;
import co.ufps.edu.backend.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    Long countByCarrera(Carrera carrera);
}
