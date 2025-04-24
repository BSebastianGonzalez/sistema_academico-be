package co.ufps.edu.backend.dto;

import co.ufps.edu.backend.model.DiaSemana;
import co.ufps.edu.backend.model.TipoHorario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HorarioDTO {

    private TipoHorario tipo;
    private DiaSemana dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Long cursoId;
    private Long aulaId;
    private Long profesorId;
}
