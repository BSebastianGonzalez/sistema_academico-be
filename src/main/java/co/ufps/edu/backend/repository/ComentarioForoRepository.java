package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.ComentarioForo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioForoRepository extends JpaRepository<ComentarioForo, Long> {

    List<ComentarioForo> findByForoId(Long idForo);
}
