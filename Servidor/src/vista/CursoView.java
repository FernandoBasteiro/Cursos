package vista;

import java.util.ArrayList;

public class CursoView {
	private int numero;
	private ProfesorView profesor;
	private MateriaView materia;
	private String dia;
	private String turno;
	private ArrayList<AlumnoView> alumnos;
	private int maximo;
	public CursoView(int numero, ProfesorView profesor, MateriaView materia, String dia, String turno,
			ArrayList<AlumnoView> alumnos, int maximo) {
		this.numero = numero;
		this.profesor = profesor;
		this.materia = materia;
		this.dia = dia;
		this.turno = turno;
		this.alumnos = alumnos;
		this.maximo = maximo;
	}

	public CursoView(int numero, MateriaView materia, String dia, String turno) {
		this.numero = numero;
		this.materia = materia;
		this.dia = dia;
		this.turno = turno;
	}
	
	public int getNumero() {
		return numero;
	}
	public ProfesorView getProfesor() {
		return profesor;
	}
	public MateriaView getMateria() {
		return materia;
	}
	public String getDia() {
		return dia;
	}
	public String getTurno() {
		return turno;
	}
	public ArrayList<AlumnoView> getAlumnos() {
		return alumnos;
	}
	public int getMaximo() {
		return maximo;
	}
}

