package co.ufps.edu.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoResponseDTO {

    private Long id;
    private String nombreAsignatura;
    private int cupoMaximo;
    private int cupoActual;
    private List<ProfesorInfoDTO> profesores;
}


