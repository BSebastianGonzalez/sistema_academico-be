package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {

    List<Notificacion> findByDestinatario_Id(Long destinatarioId);
}
