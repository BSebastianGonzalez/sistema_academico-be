package co.ufps.edu.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante {

    @Id
    private Long id;

    private byte semestre;
    private short creditos;
    private boolean estado;
    private String correo;
    private String contrasenia;
    private Date ultimoAcceso;
    private boolean activo;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    @JsonManagedReference
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_carrera")
    @JsonManagedReference
    private Carrera carrera;

}
