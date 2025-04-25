package co.ufps.edu.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComparativoHistoricoDTO {

    private String asignatura;
    private Map<Integer, Double> promediosPorAnio;
}
