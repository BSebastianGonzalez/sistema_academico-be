package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AsignaturaRepository extends JpaRepository<Asignatura, Long> {

    Optional<Asignatura> findByCodigo(String codigo);
    List<Asignatura> findByCodigoContaining(String codigoParcial);
    List<Asignatura> findByNombreContainingIgnoreCase(String nombre);
    List<Asignatura> findByCreditos(byte creditos);
    boolean existsByNombreIgnoreCase(String nombre);

    void deleteByCodigo(String codigo);
}
