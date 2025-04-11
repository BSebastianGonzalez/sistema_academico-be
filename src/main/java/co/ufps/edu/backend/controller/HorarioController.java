package co.ufps.edu.backend.controller;

import co.ufps.edu.backend.model.Horario;
import co.ufps.edu.backend.service.HorarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/horarios")
@RequiredArgsConstructor
public class HorarioController {

    private HorarioService horarioService;

    @PostMapping("/crear")
    public ResponseEntity<Horario> crearHorario(@RequestBody Horario horario,
                                                @RequestParam Long idCurso,
                                                @RequestParam Long idAula) {
        Horario creado = horarioService.crearHorario(horario, idCurso, idAula);
        return ResponseEntity.ok(creado);
    }
}
