package co.ufps.edu.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoDisponibleDTO {

    private long id;
    private String nombreAsignatura;
    private String codigoAsignatura;
    private int cuposDisponibles;
    private Set<String> prerrequisitos;
}
