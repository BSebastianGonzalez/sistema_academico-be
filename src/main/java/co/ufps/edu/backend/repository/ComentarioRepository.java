package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByEvaluacionId(Long evaluacionId);
}
