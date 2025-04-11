package co.ufps.edu.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComentarioForo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2000)
    private String contenido;

    private LocalDateTime fechaPublicacion;

    @ManyToOne
    @JoinColumn(name = "id_foro")
    private Foro foro;

    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Usuario autor;
}
