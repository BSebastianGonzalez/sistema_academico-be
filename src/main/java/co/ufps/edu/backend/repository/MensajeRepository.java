package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {

    List<Mensaje> findByEmisorId(Long id_emisor);
    List<Mensaje> findByReceptorId(Long id_receptor);

}
