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
public class ReporteUsoRecursos extends Reporte {

    @ManyToOne
    @JoinColumn(name = "periodo_id")
    private PeriodoAcademico periodo;

    @ManyToOne
    @JoinColumn(name = "recurso_id")
    private RecursoAcademico recurso;

    private float tasaUtilizacion;

    private int totalUsos;

    @ElementCollection
    @MapKeyColumn(name = "recurso_nombre")
    @Column(name = "total_usos")
    @CollectionTable(name = "uso_recursos_top", joinColumns = @JoinColumn(name = "reporte_id"))
    private Map<String, Integer> topRecursosUtilizados;
}
