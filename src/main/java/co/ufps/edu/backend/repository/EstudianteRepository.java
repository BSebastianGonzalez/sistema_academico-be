package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    @Query("SELECT MAX(e.id) FROM Estudiante e WHERE e.id >= :codigoCarrera * 10000")
    Integer findUltimoIdByCarrera(@Param("codigoCarrera") int codigoCarrera);

}
