package negocio;

import java.util.ArrayList;

import dao.ProfesorDAO;
import dto.MateriaDTO;
import dto.ProfesorDTO;

public class Profesor {
	private int legajo;
	private String nombre;
	private Direccion direccion;
	private ArrayList<Materia> materias;
	private ArrayList<Curso> cursos;
	
	public void agregarMateria(Materia materia) {
		materias.add(materia);
	}
	
	public void eliminarMateria(Materia materia) {
		ArrayList<Materia> ms = new ArrayList<Materia>();
		for (Materia m : materias) {
			if (! m.getCodigo().equals(materia.getCodigo())) {
				ms.add(m);
			}
		}
		materias = ms;
	}
	
	public boolean disponible(String dia, String turno) {
		for (Curso c : cursos) {
			if (c.getDia().equals(dia) && c.getTurno().equals(turno)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean soyElProfesor(int legajo) {
		return (this.legajo == legajo);
	}
	
	public void agregarCurso(Curso curso) {
		cursos.add(curso);
	}
	
	public void removerCurso(Curso curso) {
		ArrayList<Curso> cs = new ArrayList<Curso>();
		for (Curso c : cursos) {
			if (c.getNumero() != curso.getNumero()) {
				cs.add(c);
			}
		}
		cursos = cs;
	}
	
	public ProfesorDTO toView(){
		ArrayList<MateriaDTO> msv = new ArrayList<MateriaDTO>();
		for (Materia m : materias) {
			msv.add(m.toView());
		}
		ProfesorDTO pv = new ProfesorDTO(legajo, nombre, direccion.getCalle(), direccion.getNumero(), direccion.getCodigoPostal(), direccion.getLocalidad(), msv);
		return pv;
	}

	public int getLegajo() {
		return legajo;
	}

	public String getNombre() {
		return nombre;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public ArrayList<Materia> getMaterias() {
		return materias;
	}

	public Profesor(int legajo, String nombre, String calle, int numero, String codigoPostal, String localidad) {
		this.legajo = legajo;
		this.nombre = nombre;
		Direccion d = new Direccion(calle, numero, codigoPostal, localidad);
		this.direccion = d;
		this.materias = new ArrayList<Materia>();
		this.cursos = new ArrayList<Curso>();
	}

	public Profesor(String nombre, String calle, int numero, String codigoPostal, String localidad) {
		this.nombre = nombre;
		Direccion d = new Direccion(calle, numero, codigoPostal, localidad);
		this.direccion = d;
		this.materias = new ArrayList<Materia>();
		this.cursos = new ArrayList<Curso>();
	}

	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public void setMaterias(ArrayList<Materia> materias) {
		this.materias = materias;
	}

	public void setCursos(ArrayList<Curso> cursos) {
		this.cursos = cursos;
	}

	public ArrayList<Curso> getCursos() {
		return cursos;
	}
	
	public Integer insert() {
		return ProfesorDAO.getInstancia().insert(this);
	}
	
	public void grabar() {
		ProfesorDAO.getInstancia().update(this);
	}
}
