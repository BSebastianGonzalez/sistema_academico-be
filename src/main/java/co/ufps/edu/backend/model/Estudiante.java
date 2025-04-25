package co.ufps.edu.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante {

    @Id
    private Long id;
    private byte semestre;
    private int creditos;
    private boolean activo;
    private String correo;
    private String contrasenia;
    private Date ultimoAcceso;
    private GeneroEstudiante genero;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    @JsonManagedReference
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_carrera")
    @JsonManagedReference
    private Carrera carrera;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Inscripcion> inscripciones = new ArrayList<>();

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Calificacion> calificaciones = new ArrayList<>();

}
