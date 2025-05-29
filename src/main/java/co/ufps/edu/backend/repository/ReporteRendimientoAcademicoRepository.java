package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.ReporteRendimientoAcademico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReporteRendimientoAcademicoRepository extends JpaRepository<ReporteRendimientoAcademico, Long> {


    @Query("SELECT AVG(r.promedioGeneral) FROM ReporteRendimientoAcademico r WHERE r.periodo.id = :periodoId")
    Float findPromedioGeneralByPeriodo(@Param("periodoId") Long periodoId);

    List<ReporteRendimientoAcademico> findByCurso_Asignatura_IdAndPeriodo_NombreContaining(long asignaturaId, String año);

}
