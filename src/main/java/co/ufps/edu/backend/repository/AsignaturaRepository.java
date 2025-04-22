package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.Asignatura;
import co.ufps.edu.backend.model.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AsignaturaRepository extends JpaRepository<Asignatura, Long> {

    Optional<Asignatura> findByCodigo(String codigo);
    List<Asignatura> findByCreditos(byte creditos);
    Long countByCarreraAndPensumAndSemestre(Carrera carrera, int pensum, int semestre);

    void deleteByCodigo(String codigo);
}
