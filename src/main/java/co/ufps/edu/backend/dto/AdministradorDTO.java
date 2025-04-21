package co.ufps.edu.backend.dto;

import co.ufps.edu.backend.model.Usuario;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdministradorDTO {
    private String departamento;
    private byte nivelAcceso;
    private String contrasenia;
    private Long idUsuario;
}
