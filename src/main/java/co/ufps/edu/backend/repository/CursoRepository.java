package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.Asignatura;
import co.ufps.edu.backend.model.Curso;
import co.ufps.edu.backend.model.PeriodoAcademico;
import co.ufps.edu.backend.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    List<Curso> findByAsignatura(Asignatura asignatura);
    List<Curso> findByPeriodoAcademico(PeriodoAcademico periodoAcademico);
    List<Curso> findByProfesores(Profesor profesor);
    List<Curso> findByAsignaturaAndPeriodoAcademico(Asignatura asignatura, PeriodoAcademico periodoAcademico);
    List<Curso> findByCupoActualLessThan(int cupoMaximo);

    @Query("SELECT c FROM Curso c WHERE c.cupoActual < c.cupoMaximo")
    List<Curso> findCursosConCuposDisponibles();
}