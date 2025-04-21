package co.ufps.edu.backend.dto;

import co.ufps.edu.backend.model.Administrador;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminLoginResponseDTO {
    private boolean success;
    private String message;
    private String horaAcceso;
}
