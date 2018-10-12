package negocio;

import java.util.ArrayList;

import dao.AlumnoDAO;
import dto.AlumnoDTO;
import dto.CursoDTO;

public class Alumno {
	private int legajo;
	private String nombre;
	private ArrayList<Curso> cursos;
	
	
	public Alumno(int legajo, String nombre) {
		this.legajo = legajo;
		this.nombre = nombre;
		this.cursos = new ArrayList<Curso>();
	}

	public Alumno(String nombre) {
		this.nombre = nombre;
		this.cursos = new ArrayList<Curso>();
	}

	public boolean sePuedeInscribir(Materia materia) {
		//TODO Como se define si un alumno puede inscribirse a una materia?
		return true;
	}
	
	public boolean soyElAlummno(int legajo) {
		return (this.legajo == legajo);
	}
	
	public void inscripto(Curso curso) {
		this.cursos.add(curso);
	}
	
	public void desinscripto(Curso curso) {
		ArrayList<Curso> cs = new ArrayList<Curso>();
		for (Curso c : cursos) {
			if (c.getNumero() != curso.getNumero()) {
				cs.add(c);
			}
		}
		cursos = cs;
	}
	
	public AlumnoDTO toView() {
		ArrayList<CursoDTO> cdto = new ArrayList<CursoDTO>();
		for (Curso c : cursos) {
			cdto.add(c.toViewReducida());
		}
		AlumnoDTO adto = new AlumnoDTO(this.legajo, this.nombre);
		adto.setCursos(cdto);
		return adto;
	}

	public int getLegajo() {
		return legajo;
	}

	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(ArrayList<Curso> cursos) {
		this.cursos = cursos;
	}

	public AlumnoDTO toViewReducida() {
		AlumnoDTO adto = new AlumnoDTO(this.legajo, this.nombre);
		return adto;
	}
	
	public Integer insert(){
		return AlumnoDAO.getInstancia().insert(this);
	}
	public void update(){
		AlumnoDAO.getInstancia().update(this);
	}
}
