package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.Calificacion;
import co.ufps.edu.backend.model.Curso;
import co.ufps.edu.backend.model.PeriodoAcademico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {
    List<Calificacion> findByEstudiante_Id(int estudianteId);

    @Query("SELECT c FROM Calificacion c " +
            "WHERE c.evaluacion.curso = :curso " +
            "AND c.evaluacion.curso.periodoAcademico = :periodo")
    List<Calificacion> findByCursoAndPeriodo(
            @Param("curso") Curso curso,
            @Param("periodo") PeriodoAcademico periodo
    );
}
