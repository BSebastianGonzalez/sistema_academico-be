package co.ufps.edu.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarreraDTO {

    private Long id;
    private String codigo;  // Campo requerido para el método findByCodigo()
    private String nombre;

    // Opcional: incluir estos si necesitas manejar las relaciones en el DTO
    private List<Long> estudiantesIds;
    private List<Long> profesoresIds;
}
