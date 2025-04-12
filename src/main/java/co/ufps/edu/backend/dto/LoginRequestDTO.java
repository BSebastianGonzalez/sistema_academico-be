package co.ufps.edu.backend.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String correo;
    private String contrasenia;
}
