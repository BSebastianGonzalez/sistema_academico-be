package co.ufps.edu.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdministradorDTO {
    private String departamento;
    private byte nivelAcceso;
    private String contrasenia;
    private Long idUsuario;
}
