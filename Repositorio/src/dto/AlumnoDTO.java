package dto;

import java.io.Serializable;
import java.util.ArrayList;


public class AlumnoDTO implements Serializable {

	private static final long serialVersionUID = -6001994651737313716L;
	private int legajo;
	private String nombre;
	private ArrayList<CursoDTO> cursos;
	public AlumnoDTO(int legajo, String nombre) {
		super();
		this.legajo = legajo;
		this.nombre = nombre;
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
	public ArrayList<CursoDTO> getCursos() {
		return cursos;
	}
	public void setCursos(ArrayList<CursoDTO> cursos) {
		this.cursos = cursos;
	}
	
	
}
