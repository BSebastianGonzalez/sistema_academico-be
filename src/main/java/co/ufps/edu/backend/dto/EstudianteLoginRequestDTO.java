package co.ufps.edu.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstudianteLoginRequestDTO {
    private Long id;
    private String correo;
    private String contrasenia;
}
