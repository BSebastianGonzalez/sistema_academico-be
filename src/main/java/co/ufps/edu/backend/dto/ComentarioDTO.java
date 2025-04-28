package co.ufps.edu.backend.dto;

import lombok.Data;

@Data
public class ComentarioDTO {
    private Long remitenteId;
    private Long destinatarioId;
    private Long evaluacionId;
    private String contenido;

}
