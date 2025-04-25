package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReporteRepository extends JpaRepository<Reporte, Long> {

    List<Reporte> findByTipo(String tipo);
    List<Reporte> findByFechaGeneracionBetween(LocalDateTime inicio, LocalDateTime fin);
    List<Reporte> findByGeneradoPorId(Long administradorId);
}
