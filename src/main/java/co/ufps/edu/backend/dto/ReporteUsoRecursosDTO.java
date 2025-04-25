package co.ufps.edu.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteUsoRecursosDTO extends ReporteDTO{

    private String nombreRecurso;
    private String tipoRecurso;
    private String periodo;
    private float tasaUtilizacion;
    private int totalUsos;
}
