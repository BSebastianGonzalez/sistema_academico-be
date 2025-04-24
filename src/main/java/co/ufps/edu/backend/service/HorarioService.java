package co.ufps.edu.backend.service;

import co.ufps.edu.backend.dto.HorarioDTO;
import co.ufps.edu.backend.exception.ConflictException;
import co.ufps.edu.backend.model.*;
import co.ufps.edu.backend.repository.AulaRepository;
import co.ufps.edu.backend.repository.CursoRepository;
import co.ufps.edu.backend.repository.HorarioRepository;
import co.ufps.edu.backend.repository.ProfesorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
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

    @Autowired
    private ProfesorRepository profesorRepository;


    public Horario crearHorario(HorarioDTO horarioDTO) {

        Curso curso = cursoRepository.findById(horarioDTO.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        Aula aula = aulaRepository.findById(horarioDTO.getAulaId())
                .orElseThrow(() -> new RuntimeException("Aula no encontrada"));

        Profesor profesor = null;
        if (horarioDTO.getProfesorId() != null) {
            profesor = profesorRepository.findById(horarioDTO.getProfesorId())
                    .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
        }

        if (horarioDTO.getTipo() == TipoHorario.EXAMEN) {
            if (aula.getCapacidad() < curso.getCupoMaximo()) {
                throw new ConflictException("El aula no tiene capacidad suficiente para el examen");
            }
        }

        if (horarioDTO.getTipo() == TipoHorario.EXAMEN) {
            List<Horario> horariosCurso = horarioRepository.findByCursoIdAndDia(horarioDTO.getCursoId(), horarioDTO.getDia());
            if (!horariosCurso.isEmpty()) {
                throw new ConflictException("El curso tiene clases programadas el mismo día del examen");
            }
        }



        // Validar solapamientos
        validarDisponibilidad(aula, profesor, horarioDTO);

        Horario horario = new Horario();
        horario.setTipo(horarioDTO.getTipo());
        horario.setDia(horarioDTO.getDia());
        horario.setHoraInicio(horarioDTO.getHoraInicio());
        horario.setHoraFin(horarioDTO.getHoraFin());
        horario.setCurso(curso);
        horario.setAula(aula);
        horario.setProfesor(profesor);

        return horarioRepository.save(horario);
    }

    private void validarDisponibilidad(Aula aula, Profesor profesor, HorarioDTO horarioDTO) {
        // Validar aula
        List<Horario> conflictosAula = horarioRepository.findConflictsByAula(
                aula.getId(),
                horarioDTO.getDia(),
                horarioDTO.getHoraInicio(),
                horarioDTO.getHoraFin()
        );

        if (!conflictosAula.isEmpty()) {
            throw new ConflictException("El aula está ocupada en este horario");
        }

        // Validar profesor (solo para tutorías)
        if (horarioDTO.getTipo() == TipoHorario.TUTORIA && profesor != null) {
            List<Horario> conflictosProfesor = horarioRepository.findConflictsByProfesor(
                    profesor.getId(),
                    horarioDTO.getDia(),
                    horarioDTO.getHoraInicio(),
                    horarioDTO.getHoraFin()
            );

            if (!conflictosProfesor.isEmpty()) {
                throw new ConflictException("El profesor tiene otro compromiso en este horario");
            }
        }
    }

    public List<Horario> sugerirHorarioOptimo(Long aulaId, DiaSemana dia) {
        Aula aula = aulaRepository.findById(aulaId)
                .orElseThrow(() -> new RuntimeException("Aula no encontrada"));

        List<Horario> horarios = horarioRepository.findByAulaAndDiaOrderByHoraInicioAsc(aula, dia);

        List<Horario> sugerencias = new ArrayList<>();
        LocalTime ultimaHoraFin = LocalTime.of(6, 0); // Hora de apertura del aula

        for (Horario h : horarios) {
            if (h.getHoraInicio().isAfter(ultimaHoraFin.plusMinutes(30))) { // 30 mins entre eventos
                Horario sugerencia = new Horario();
                sugerencia.setHoraInicio(ultimaHoraFin.plusMinutes(30));
                sugerencia.setHoraFin(sugerencia.getHoraInicio().plusHours(2)); // Bloques de 2 horas
                sugerencias.add(sugerencia);
            }
            ultimaHoraFin = h.getHoraFin();
        }

        return sugerencias;
    }













}
