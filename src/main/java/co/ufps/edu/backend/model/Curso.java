package co.ufps.edu.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.Objects;

public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "periodo_academico_id", nullable = false)
    private PeriodoAcademico periodoAcademico;

    @Positive(message = "El cupo máximo debe ser un valor positivo")
    private short cupoMaximo;

    private short cupoActual;

    @ManyToOne
    @JoinColumn(name = "asignatura_id", nullable = false)
    private Asignatura asignatura;

    @ManyToOne
    @JoinColumn(name = "clase_id", nullable = false)
    private SesionClase clase;

    @ManyToOne
    @JoinColumn(name = "profesor_id")
    private Profesor profesor;

    @ManyToOne
    @JoinColumn(name = "inscripcion_id")
    private Inscripcion inscripcion;

    @ManyToOne
    @JoinColumn(name = "evaluacion_id")
    private Evaluacion evaluacion;

    @ManyToOne
    @JoinColumn(name = "recurso_id")
    private RecursoAcademico recurso;

    public Curso() {
        this.cupoActual = 0;
    }

    public Curso(Long id, PeriodoAcademico periodoAcademico, short cupoMaximo, short cupoActual, Asignatura asignatura, SesionClase clase, Profesor profesor, Inscripcion inscripcion, Evaluacion evaluacion, RecursoAcademico recurso) {
        this.id = id;
        this.periodoAcademico = periodoAcademico;
        this.cupoMaximo = cupoMaximo;
        this.cupoActual = cupoActual;
        this.asignatura = asignatura;
        this.clase = clase;
        this.profesor = profesor;
        this.inscripcion = inscripcion;
        this.evaluacion = evaluacion;
        this.recurso = recurso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PeriodoAcademico getPeriodoAcademico() {
        return periodoAcademico;
    }

    public void setPeriodoAcademico(PeriodoAcademico periodoAcademico) {
        this.periodoAcademico = periodoAcademico;
    }

    public short getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(short cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public short getCupoActual() {
        return cupoActual;
    }

    public void setCupoActual(short cupoActual) {
        this.cupoActual = cupoActual;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public SesionClase getClase() {
        return clase;
    }

    public void setClase(SesionClase clase) {
        this.clase = clase;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Inscripcion getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(Inscripcion inscripcion) {
        this.inscripcion = inscripcion;
    }

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    public RecursoAcademico getRecurso() {
        return recurso;
    }

    public void setRecurso(RecursoAcademico recurso) {
        this.recurso = recurso;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return cupoMaximo == curso.cupoMaximo && cupoActual == curso.cupoActual && Objects.equals(id, curso.id) && Objects.equals(periodoAcademico, curso.periodoAcademico) && Objects.equals(asignatura, curso.asignatura) && Objects.equals(clase, curso.clase) && Objects.equals(profesor, curso.profesor) && Objects.equals(inscripcion, curso.inscripcion) && Objects.equals(evaluacion, curso.evaluacion) && Objects.equals(recurso, curso.recurso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, periodoAcademico, cupoMaximo, cupoActual, asignatura, clase, profesor, inscripcion, evaluacion, recurso);
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", periodoAcademico=" + periodoAcademico +
                ", cupoMaximo=" + cupoMaximo +
                ", cupoActual=" + cupoActual +
                ", asignatura=" + asignatura +
                ", clase=" + clase +
                ", profesor=" + profesor +
                ", inscripcion=" + inscripcion +
                ", evaluacion=" + evaluacion +
                ", recurso=" + recurso +
                '}';
    }
}
