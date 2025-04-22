package co.ufps.edu.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorLoginRequestDTO {
    private String correo;
    private String contrasenia;
}
