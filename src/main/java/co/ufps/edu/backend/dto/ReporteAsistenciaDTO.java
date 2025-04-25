package co.ufps.edu.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteAsistenciaDTO extends ReporteDTO{

    private String nombreAsignatura;
    private String periodo;
    private float porcentajeAsistenciaPromedio;
    private int totalEstudiantes;
}
