package co.ufps.edu.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoDTO {

    private long id;
    private long periodoAcademicoId;
    private long asignaturaId;

    private int cupoMaximo;
    private List<Long> profesoresIds;
    private List<String> nombresCompletosProfesores;

    private String nombreCompletoProfesor; // Opcional si se usa profesorId
    private Long profesorId;

}
