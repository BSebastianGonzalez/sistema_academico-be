package co.ufps.edu.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteRendimientoAcademico extends Reporte {

    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "id_periodo")
    private PeriodoAcademico periodo;

    private float promedioGeneral;
    private int aprobado;
    private int reprobado;
    private int totalEstudiantes;

    @ElementCollection
    @MapKeyColumn(name = "rango")
    @Column(name = "cantidad_estudiantes")
    @CollectionTable(name = "rendimiento_distribucion_notas", joinColumns = @JoinColumn(name = "reporte_id"))
    private Map<String, Integer> distribucionNotas;

    private float promedioHombres;
    private float promedioMujeres;

}
