package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
    Optional<Asistencia> findByPresente(boolean presente);
}
