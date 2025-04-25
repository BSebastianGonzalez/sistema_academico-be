package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.ReporteAsistencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReporteAsistenciaRepository extends JpaRepository<ReporteAsistencia, Long> {

    List<ReporteAsistencia> findByCursoId(Long cursoId);
    List<ReporteAsistencia> findByPeriodoId(Long periodoId);
}
