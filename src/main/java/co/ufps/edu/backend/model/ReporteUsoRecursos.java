package co.ufps.edu.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "reporte_id")
public class ReporteUsoRecursos extends Reporte {

    @ManyToOne
    @JoinColumn(name = "periodo_id")
    private PeriodoAcademico periodo;

    @ManyToOne
    @JoinColumn(name = "recurso_id")
    private RecursoAcademico recurso;

    private float tasaUtilizacion;

    private int totalUsos;
}
