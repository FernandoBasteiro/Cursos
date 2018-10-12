package vista;

import java.util.ArrayList;


public class AlumnoView {
	private int legajo;
	private String nombre;
	private ArrayList<CursoView> cursos;
	
	public AlumnoView(int legajo, String nombre, ArrayList<CursoView> cursos) {
		this.legajo = legajo;
		this.nombre = nombre;
		this.cursos = cursos;
	}
	
	public AlumnoView(int legajo, String nombre) {
		this.legajo = legajo;
		this.nombre = nombre;
	}

	public int getLegajo() {
		return legajo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public ArrayList<CursoView> getCursos() {
		return cursos;
	}
}
