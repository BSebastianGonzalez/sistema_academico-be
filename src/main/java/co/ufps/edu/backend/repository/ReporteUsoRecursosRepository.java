package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.ReporteAsistencia;
import co.ufps.edu.backend.model.ReporteUsoRecursos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReporteUsoRecursosRepository extends JpaRepository<ReporteAsistencia, Long> {

    List<ReporteUsoRecursos> findByRecursoId(Long recursoId);
    List<ReporteUsoRecursos> findByPeriodoId(Long periodoId);

    @Query("SELECT r FROM ReporteUsoRecursos r WHERE r.tasaUtilizacion > :tasaMinima ORDER BY r.tasaUtilizacion DESC")
    List<ReporteUsoRecursos> findRecursosMasUtilizados(float tasaMinima);

    @Query("SELECT SUM(r.totalUsos) FROM ReporteUsoRecursos r WHERE r.periodo.id = :periodoId")
    Integer findTotalUsosPorPeriodo(Long periodoId);

}
