package co.ufps.edu.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String departamento;
    private byte nivelAcceso;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}

