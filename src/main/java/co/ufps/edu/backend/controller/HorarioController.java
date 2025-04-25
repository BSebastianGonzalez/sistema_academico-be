package co.ufps.edu.backend.controller;

import co.ufps.edu.backend.dto.HorarioDTO;
import co.ufps.edu.backend.model.Horario;
import co.ufps.edu.backend.service.HorarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/horarios")
@RequiredArgsConstructor
public class HorarioController {

    private final HorarioService horarioService;

    @PostMapping("/crear")
    public ResponseEntity<Horario> crearHorario(@RequestBody HorarioDTO horarioDTO) {
        Horario creado = horarioService.crearHorario(horarioDTO);
        return ResponseEntity.ok(creado);
    }
}
