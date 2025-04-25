package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.RecursoAcademico;
import co.ufps.edu.backend.model.UsoRecurso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UsoRecursoRepository extends JpaRepository<UsoRecurso, Long> {
    List<UsoRecurso> findByRecursoAndFechaUsoBetween(RecursoAcademico recurso, LocalDateTime inicio, LocalDateTime fin);
}
