package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.Asistencia;
import co.ufps.edu.backend.model.Curso;
import co.ufps.edu.backend.model.PeriodoAcademico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {

    @Query("SELECT a FROM Asistencia a " +
            "WHERE a.sesion.curso = :curso " +
            "AND a.sesion.curso.periodoAcademico = :periodo")
    List<Asistencia> findByCursoAndPeriodo(
            @Param("curso") Curso curso,
            @Param("periodo") PeriodoAcademico periodo
    );
}
