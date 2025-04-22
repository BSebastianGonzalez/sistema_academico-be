package co.ufps.edu.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstudianteLoginResponseDTO {
    private boolean success;
    private String message;
    private String horaAcceso;
}
