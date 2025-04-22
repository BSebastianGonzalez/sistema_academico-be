package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarreraRepository extends JpaRepository<Carrera, Long> {

    Optional<Carrera> findByCodigo(String codigo);
    Optional<Carrera> findByNombre(String nombre);
}
