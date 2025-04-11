package co.ufps.edu.backend.service;

import co.ufps.edu.backend.model.Aula;
import co.ufps.edu.backend.model.Curso;
import co.ufps.edu.backend.model.Horario;
import co.ufps.edu.backend.repository.AulaRepository;
import co.ufps.edu.backend.repository.CursoRepository;
import co.ufps.edu.backend.repository.HorarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private AulaRepository aulaRepository;


    public Horario crearHorario(Horario horario, Long cursoId, Long aulaId) {
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new EntityNotFoundException("Curso no encontrado"));

        Aula aula = aulaRepository.findById(aulaId)
                .orElseThrow(() -> new EntityNotFoundException("Aula no encontrada"));

        horario.setCurso(curso);
        horario.setAula(aula);

        // Validación simple de solapamiento
        List<Horario> existentes = horarioRepository.findByAulaAndDia(aula.getNombre(), horario.getDia());
        for (Horario h : existentes) {
            if (h.getHoraInicio().isBefore(horario.getHoraFin()) && horario.getHoraInicio().isBefore(h.getHoraFin())) {
                throw new IllegalArgumentException("Conflicto de horario detectado.");
            }
        }

        return horarioRepository.save(horario);
    }












}
