package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
}
