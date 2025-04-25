package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.ReporteRendimientoAcademico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReporteRendimientoAcademicoRepository extends JpaRepository<ReporteRendimientoAcademico, Long> {

    List<ReporteRendimientoAcademico> findByCursoId(Long cursoId);
    List<ReporteRendimientoAcademico> findByPeriodoId(Long periodoId);

}
