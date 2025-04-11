package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.Curso;
import co.ufps.edu.backend.model.PeriodoAcademico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeriodoAcademicoRepository extends JpaRepository<PeriodoAcademico, Long> {
}
