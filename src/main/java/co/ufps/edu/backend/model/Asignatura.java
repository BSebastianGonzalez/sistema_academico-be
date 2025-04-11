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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String codigo;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Lob
    private String descripcion;

    @Positive(message = "Los créditos deben ser un valor positivo")
    private byte creditos;

}
