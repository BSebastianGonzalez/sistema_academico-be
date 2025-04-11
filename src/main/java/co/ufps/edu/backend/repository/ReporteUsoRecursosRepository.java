package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.ReporteAsistencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReporteUsoRecursosRepository extends JpaRepository<ReporteAsistencia, Long> {
}
