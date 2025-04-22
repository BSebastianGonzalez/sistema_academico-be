package co.ufps.edu.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoDTO {

    private Long id;
    private Long periodoAcademicoId;
    private Long asignaturaId;
    private Integer cupoMaximo;
    private List<Long> profesoresIds;
}
