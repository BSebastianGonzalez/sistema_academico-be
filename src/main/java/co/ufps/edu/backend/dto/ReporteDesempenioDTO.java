package co.ufps.edu.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReporteDesempenioDTO {
    private Long estudianteId;
    private String nombreEstudiante;
    private float promedioActual;
    private float proyeccionRendimiento;
    private int cantidadEvaluaciones;
}
