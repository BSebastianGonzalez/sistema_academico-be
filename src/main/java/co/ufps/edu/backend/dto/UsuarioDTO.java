package co.ufps.edu.backend.dto;

import co.ufps.edu.backend.model.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private TipoDocumento tipoDocumento;
    private Long documento;
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    private String correo;
    private Date fechaNacimiento;
    private String direccion;
    private long telefono;
}
