package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="alumnos")
public class AlumnoEntity {
	@Id
	@GeneratedValue
	private int legajo;
	private String nombre;

	@ManyToMany()
    private List<CursoEntity> cursos;
	
	public AlumnoEntity() { }
	
	public AlumnoEntity(int legajo, String nombre) {
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
	
	public List<CursoEntity> getCursos() {
		return cursos;
	}
	public void setCursos(ArrayList<CursoEntity> cursos) {
		this.cursos = cursos;
	}
}
