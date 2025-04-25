package co.ufps.edu.backend.controller;

import co.ufps.edu.backend.dto.ComparativoHistoricoDTO;
import co.ufps.edu.backend.dto.ReporteAsistenciaDTO;
import co.ufps.edu.backend.dto.ReporteRendimientoAcademicoDTO;
import co.ufps.edu.backend.dto.ReporteUsoRecursosDTO;
import co.ufps.edu.backend.model.*;
import co.ufps.edu.backend.service.CursoService;
import co.ufps.edu.backend.service.PeriodoAcademicoService;
import co.ufps.edu.backend.service.RecursoAcademicoService;
import co.ufps.edu.backend.service.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReporteController {

    private final ReporteService reporteService;
    private final CursoService cursoService;
    private final PeriodoAcademicoService periodoService;
    private final RecursoAcademicoService recursoService;

    @GetMapping("/rendimiento")
    public ResponseEntity<ReporteRendimientoAcademicoDTO> getReporteRendimiento(
            @RequestParam Long cursoId,
            @RequestParam Long periodoId) {

        Curso curso = cursoService.obtenerCursoPorId(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        PeriodoAcademico periodo = periodoService.findById(periodoId)
                .orElseThrow(() -> new RuntimeException("Período no encontrado"));

        return ResponseEntity.ok(reporteService.generarReporteRendimiento(curso, periodo));
    }

    @GetMapping("/asistencia")
    public ResponseEntity<ReporteAsistenciaDTO> getReporteAsistencia(
            @RequestParam Long cursoId,
            @RequestParam Long periodoId) {

        Curso curso = cursoService.obtenerCursoPorId(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        PeriodoAcademico periodo = periodoService.findById(periodoId)
                .orElseThrow(() -> new RuntimeException("Período no encontrado"));

        return ResponseEntity.ok(reporteService.generarReporteAsistencia(curso, periodo));
    }

    @GetMapping("/uso-recursos")
    public ResponseEntity<ReporteUsoRecursosDTO> getReporteUsoRecursos(
            @RequestParam Long recursoId,
            @RequestParam Long periodoId) {

        RecursoAcademico recurso = recursoService.findById(recursoId)
                .orElseThrow(() -> new RuntimeException("Recurso no encontrado"));

        PeriodoAcademico periodo = periodoService.findById(periodoId)
                .orElseThrow(() -> new RuntimeException("Período no encontrado"));

        return ResponseEntity.ok(reporteService.generarReporteUsoRecursos(recurso, periodo));
    }

    @GetMapping("/comparativo")
    public ResponseEntity<ComparativoHistoricoDTO> getComparativoHistorico(
            @RequestParam Long asignaturaId,
            @RequestParam(defaultValue = "5") int años) {

        return ResponseEntity.ok(reporteService.obtenerComparativoHistorico(asignaturaId, años));
    }


}
