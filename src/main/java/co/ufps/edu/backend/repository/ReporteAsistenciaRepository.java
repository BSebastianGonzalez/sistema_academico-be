package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.Curso;
import co.ufps.edu.backend.model.PeriodoAcademico;
import co.ufps.edu.backend.model.ReporteAsistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReporteAsistenciaRepository extends JpaRepository<ReporteAsistencia, Long> {

    List<ReporteAsistencia> findByCurso(Curso curso);

    List<ReporteAsistencia> findByPeriodo(PeriodoAcademico periodo);

    List<ReporteAsistencia> findByCursoAndPeriodo(Curso curso, PeriodoAcademico periodo);

    Float obtenerAsistenciaPromedioPorPeriodo(Long periodoId);

    List<ReporteAsistencia> findByPorcentajeAsistenciaPromedioGreaterThanEqual(Float porcentaje);
}
