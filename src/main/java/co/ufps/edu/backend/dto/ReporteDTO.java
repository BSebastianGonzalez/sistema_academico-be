package co.ufps.edu.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteDTO {

    private long id;
    private String titulo;
    private String descripcion;
    private Date fechaGeneracion;
    private String tipo;
    private long generadoPorId;
    private String formato;
    private String contenido;
}
