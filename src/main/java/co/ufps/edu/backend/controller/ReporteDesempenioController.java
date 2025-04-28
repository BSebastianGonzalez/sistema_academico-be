package co.ufps.edu.backend.controller;

import co.ufps.edu.backend.dto.ReporteDesempenioDTO;
import co.ufps.edu.backend.service.ReporteDesempenioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReporteDesempenioController {

    private final ReporteDesempenioService reporteDesempenoService;

    @GetMapping("/{estudianteId}")
    public ReporteDesempenioDTO obtenerReporte(@PathVariable Long estudianteId) {
        return reporteDesempenoService.generarReporte(estudianteId);
    }
}
