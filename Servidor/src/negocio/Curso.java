package negocio;

import java.util.ArrayList;

import dao.CursoDAO;
import dto.AlumnoDTO;
import dto.CursoDTO;


public class Curso {
	
	static private int numerador;
	private int numero;
	private Profesor profesor;
	private Materia materia;
	private String dia;
	private String turno;
	private ArrayList<Alumno> alumnos;
	private int maximo;
	
	public Curso(int numero, Materia materia, Profesor profesor, String dia, String turno, int maximo) {
		this.numero = numero;
		this.profesor = profesor;
		this.materia = materia;
		this.dia = dia;
		this.turno = turno;
		this.maximo = maximo;
		this.alumnos = new ArrayList<Alumno>();
	}
	
	public Curso(int numero, String dia, String turno, int maximo) {
		this.numero = numero;
		this.dia = dia;
		this.turno = turno;
		this.maximo = maximo;
		this.alumnos = new ArrayList<Alumno>();
	}

	public Curso(Materia materia, Profesor profesor, String dia, String turno, int maximo) {
		this.profesor = profesor;
		this.materia = materia;
		this.dia = dia;
		this.turno = turno;
		this.maximo = maximo;
		this.alumnos = new ArrayList<Alumno>();
	}
	public static int getNumerador() {
		return ++numerador;
	}

	public void anotarAlumno(Alumno alumno) {
		this.alumnos.add(alumno);
		alumno.inscripto(this);
	}
	
	public void eliminarAlumno(Alumno alumno) {
		ArrayList<Alumno> as = new ArrayList<Alumno>();
		for (Alumno a : alumnos) {
			if (a.getLegajo() != alumno.getLegajo()) {
				as.add(a);
			}
		}
		alumnos = as;
		alumno.desinscripto(this); //TODO Genera el metodo "Desinscripto".
	}
	
	public boolean hayVacantes() {
		return (this.maximo > this.alumnos.size());
	}
	
	public void reasignarProfesor(Profesor profesor) {
		if (this.profesor != null) this.profesor.removerCurso(this);
		this.profesor = profesor;
		profesor.agregarCurso(this);
	}
	
	public ArrayList<Alumno> obtenerAlummnos() {
		return alumnos;
	}
	
	public boolean soyElCurso(int numero) {
		return (this.numero == numero);
	}
	
	public CursoDTO toView() {
		ArrayList<AlumnoDTO> asdto = new ArrayList<AlumnoDTO>();
		for (Alumno a : alumnos) {
			asdto.add(a.toViewReducida());
		}
		CursoDTO cdto = new CursoDTO(numero, materia.toView(), dia, turno);
		if (profesor != null) cdto.setProfesor(profesor.toView());
		cdto.setAlumnos(asdto);
		cdto.setMaximo(maximo);
		return cdto;
	}

	public int getNumero() {
		return numero;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public Materia getMateria() {
		return materia;
	}

	public String getDia() {
		return dia;
	}

	public String getTurno() {
		return turno;
	}

	public ArrayList<Alumno> getAlumnos() {
		return alumnos;
	}

	public int getMaximo() {
		return maximo;
	}

	public CursoDTO toViewReducida() {
		CursoDTO cdv = new CursoDTO(this.numero, this.materia.toView(), this.dia, this.turno);
		return cdv;
	}

	public static void setNumerador(int numerador) {
		Curso.numerador = numerador;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public void setAlumnos(ArrayList<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public void setMaximo(int maximo) {
		this.maximo = maximo;
	}

	public Integer insert() {
		return CursoDAO.getInstancia().insert(this);
	}
	
	public void update() {
		CursoDAO.getInstancia().update(this);
	}
}
