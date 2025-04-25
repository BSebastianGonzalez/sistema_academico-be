package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.Reporte;
import co.ufps.edu.backend.model.ReporteRendimientoAcademico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface ReporteRepository extends JpaRepository<Reporte, Long> {

    List<Reporte> findByTipo(String tipo);

    List<Reporte> findByFechaGeneracionBetween(Date fechaInicio, Date fechaFin);

    List<Reporte> findByGeneradoPorId(Long administradorId);

    List<Reporte> buscarPorTitulo(String titulo);

    List<Reporte> findTop10ByOrderByFechaGeneracionDesc();

    List<ReporteRendimientoAcademico> findByCurso_Asignatura_IdAndPeriodo_NombreContaining(long asignaturaId, String año);
}
