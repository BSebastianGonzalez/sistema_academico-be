package co.ufps.edu.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante {

    @Id
    private int id;

    private byte codigoCarrera;
    private byte semestre;
    private boolean estado;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

}
