package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.ReporteAsistencia;
import co.ufps.edu.backend.model.ReporteUsoRecursos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReporteUsoRecursosRepository extends JpaRepository<ReporteAsistencia, Long> {

    List<ReporteUsoRecursos> findByRecursoId(Long recursoId);
    List<ReporteUsoRecursos> findByPeriodoId(Long periodoId);

}
