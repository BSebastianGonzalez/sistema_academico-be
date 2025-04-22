package co.ufps.edu.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsignaturaDTO {

    private Long id;
    private String codigoCarrera;
    private int pensum;
    private String nombre;
    private String descripcion;
    private int creditos;
    private int semestre;
    private int secuenciaAsignatura = -1;
    private Long carreraId;
    private Set<String> codigosPrerrequisitos;
    private int creditosRequeridos;


}
