package co.ufps.edu.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Asignatura {

    @Id
    private Long id; //La id es el codigo de la materia como tal. Para hacerlo toca que sea personalizada.
                        // 1155401 es uno por ejemplo

    private String nombre;
    private String descripcion;
    private byte creditos;
    private byte semestre;

    @ManyToOne
    @JoinColumn(name = "id_carrera")
    private Carrera carrera;

}
