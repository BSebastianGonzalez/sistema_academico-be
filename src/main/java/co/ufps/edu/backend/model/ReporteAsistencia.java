package co.ufps.edu.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteAsistencia extends Reporte {

    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "id_periodo")
    private PeriodoAcademico periodo;

    private float porcentajeAsistenciaPromedio;

    private int totalEstudiantes;

    @ElementCollection
    @CollectionTable(name = "asistencia_semanas", joinColumns = @JoinColumn(name = "reporte_id"))
    private List<Float> porcentajePorSemana;

}
