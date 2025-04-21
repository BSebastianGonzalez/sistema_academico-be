package co.ufps.edu.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Administrador{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String departamento;
    private byte nivelAcceso;
    private String correo;
    private String contrasenia;
    private Date ultimoAcceso;
    private boolean activo;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    @JsonManagedReference
    private Usuario usuario;
}

