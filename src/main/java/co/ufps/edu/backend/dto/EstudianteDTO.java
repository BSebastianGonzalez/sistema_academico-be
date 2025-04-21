package co.ufps.edu.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstudianteDTO {

    private String correo;
    private String contrasenia;
    private Long idUsuario;
    private Long idCarrera;
}
