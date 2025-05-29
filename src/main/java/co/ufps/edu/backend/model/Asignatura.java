package co.ufps.edu.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Asignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;//  1155401 es uno por ejemplo
    private String codigoCarrera;
    private String nombre;
    private String descripcion;
    private int creditos;
    private int semestre;
    private int secuenciaAsignatura;
    private int pensum;
    private int creditosRequeridos;

    @ElementCollection
    @CollectionTable(name = "prerrequisitos_codigos", joinColumns = @JoinColumn(name = "asignatura_id"))
    @Column(name = "codigo_prerrequisito")
    private Set<String> codigosPrerrequisitos = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "id_carrera")
    private Carrera carrera;


    @ManyToMany
    @JoinTable(
            name = "asignatura_prerequisitos",
            joinColumns = @JoinColumn(name = "asignatura_id"),
            inverseJoinColumns = @JoinColumn(name = "prerequisito_id")
    )
    private Set<Asignatura> prerequisitos = new HashSet<>();



}
