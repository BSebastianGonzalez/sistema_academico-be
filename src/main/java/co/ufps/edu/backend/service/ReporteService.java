package co.ufps.edu.backend.service;

import co.ufps.edu.backend.dto.*;
import co.ufps.edu.backend.model.*;
import co.ufps.edu.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.time.Year;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;


@Service
@RequiredArgsConstructor
public class ReporteService {

    @Autowired
    private final CalificacionRepository calificacionRepository;

    @Autowired
    private final AsistenciaRepository asistenciaRepository;

    @Autowired
    private final UsoRecursoRepository usoRecursoRepository;

    @Autowired
    private ReporteRendimientoAcademicoRepository reporteRendimientoAcademicoRepository;

    @Autowired
    private AsignaturaService asignaturaService;

    public ReporteRendimientoAcademicoDTO generarReporteRendimiento(Curso curso, PeriodoAcademico periodo) {

        List<Calificacion> calificaciones = calificacionRepository.findByCursoAndPeriodo(curso, periodo);

        float promedio = (float) calificaciones.stream()
                .mapToDouble(Calificacion::getNota)
                .average()
                .orElse(0.0);

        long aprobados = calificaciones.stream()
                .filter(c -> c.getNota() >= 3.0)
                .count();

        Map<String, Integer> distribucion = new HashMap<>();
        distribucion.put("0-2.9", (int) calificaciones.stream().filter(c -> c.getNota() <= 2.9).count());
        distribucion.put("3-3.9", (int) calificaciones.stream().filter(c -> c.getNota() > 2.9 && c.getNota() <= 3.9).count());
        distribucion.put("4-5", (int) calificaciones.stream().filter(c -> c.getNota() > 3.9).count());

        double avgHombres = calificaciones.stream()
                .filter(c -> GeneroEstudiante.MASCULINO.equals(c.getEstudiante().getGenero()))
                .mapToDouble(Calificacion::getNota)
                .average().orElse(0.0);

        double avgMujeres = calificaciones.stream()
                .filter(c -> GeneroEstudiante.FEMENINO.equals(c.getEstudiante().getGenero()))
                .mapToDouble(Calificacion::getNota)
                .average().orElse(0.0);

        return new ReporteRendimientoAcademicoDTO(
                curso.getAsignatura().getNombre(),
                periodo.getNombre(),
                promedio,
                (int) aprobados,
                calificaciones.size() - (int) aprobados,
                calificaciones.size(),
                distribucion,
                (float) avgHombres,
                (float) avgMujeres
        );
    }

    public ReporteAsistenciaDTO generarReporteAsistencia(Curso curso, PeriodoAcademico periodo) {
        List<Asistencia> asistencias = asistenciaRepository.findByCursoAndPeriodo(curso, periodo);

        long totalAsistencias = asistencias.stream()
                .filter(Asistencia::isPresente).count();

        float porcentaje = asistencias.isEmpty() ? 0 : (totalAsistencias * 100.0f) / asistencias.size();

        return new ReporteAsistenciaDTO(
                curso.getAsignatura().getNombre(),
                periodo.getNombre(),
                porcentaje,
                (int) asistencias.stream().map(Asistencia::getEstudiante).distinct().count()
        );
    }

    public ReporteUsoRecursosDTO generarReporteUsoRecursos(RecursoAcademico recurso, PeriodoAcademico periodo) {
        List<UsoRecurso> usos = usoRecursoRepository.findByRecursoAndFechaUsoBetween(
                recurso,
                periodo.getFechaInicio().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
                periodo.getFechaFin().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
        );

        long diasPeriodo = ChronoUnit.DAYS.between(
                periodo.getFechaInicio().toInstant(),
                periodo.getFechaFin().toInstant()
        );

        float tasa = diasPeriodo == 0 ? 0 : (float) usos.size() / diasPeriodo;

        return new ReporteUsoRecursosDTO(
                recurso.getNombre(),
                recurso.getTipo(),
                periodo.getNombre(),
                usos.size(),
                (int) tasa
        );
    }

    public ComparativoHistoricoDTO obtenerComparativoHistorico(Long asignaturaId, int años) {
        int añoActual = Year.now().getValue();
        Map<Integer, Double> comparativo = new LinkedHashMap<>();

        for (int i = años; i > 0; i--) {
            int añoConsulta = añoActual - i;

            List<ReporteRendimientoAcademico> reportes = reporteRendimientoAcademicoRepository
                    .findByCurso_Asignatura_IdAndPeriodo_NombreContaining(asignaturaId, String.valueOf(añoConsulta));

            double promedio = reportes.stream()
                    .mapToDouble(ReporteRendimientoAcademico::getPromedioGeneral)
                    .average().orElse(0.0);

            comparativo.put(añoConsulta, promedio);
        }

        return new ComparativoHistoricoDTO(
                asignaturaService.obtenerAsignaturaPorId(asignaturaId).orElseThrow().getNombre(),
                comparativo
        );
    }

    public Float obtenerPromedioGeneralPorPeriodo(Long periodoId) {
        return reporteRendimientoAcademicoRepository.findPromedioGeneralByPeriodo(periodoId);
    }

}
