package co.ufps.edu.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteRendimientoAcademicoDTO extends ReporteDTO {

    private String nombreAsignatura;
    private String periodo;
    private float promedioGeneral;
    private int aprobados;
    private int reprobados;
    private int totalEstudiantes;
    private Map<String, Integer> distribucionNotas;
    private float promedioHombres;
    private float promedioMujeres;
}
