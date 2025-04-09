package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AsignaturaRepository extends JpaRepository<Asignatura, Integer> {

    List<Asignatura> findByNombreContainingIgnoreCase(String nombre);

    Optional<Asignatura> findByNombreIgnoreCase(String nombre);

    List<Asignatura> findByCreditos(byte creditos);

    boolean existsByNombreIgnoreCase(String nombre);
}
