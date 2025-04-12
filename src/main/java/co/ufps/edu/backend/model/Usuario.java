package co.ufps.edu.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cedula;
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasenia;
    private Date fechaNacimiento;
    private String direccion;
    private long telefono;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;

    private Date ultimoAcceso;
    private boolean activo;

}
