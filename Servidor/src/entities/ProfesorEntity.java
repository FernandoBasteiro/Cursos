package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="profesores")
public class ProfesorEntity {
	@Id
	@GeneratedValue
	private Integer legajo;
	private String nombre;
	@Embedded
	private DireccionEntity direccion;
	@ManyToMany(cascade=CascadeType.ALL)
	private List<MateriaEntity> materias;
	@OneToMany(cascade = CascadeType.ALL)
	private List<CursoEntity> cursos;
	
	public ProfesorEntity() { }
	
	public ProfesorEntity(Integer legajo, String nombre, DireccionEntity direccion, ArrayList<MateriaEntity> materias,
			ArrayList<CursoEntity> cursos) {
		this.legajo = legajo;
		this.nombre = nombre;
		this.direccion = direccion;
		this.materias = materias;
		this.cursos = cursos;
	}
	
	public ProfesorEntity(Integer legajo, String nombre, DireccionEntity direccion) {
		this.legajo = legajo;
		this.nombre = nombre;
		this.direccion = direccion;
	}
	
	public ProfesorEntity(String nombre, DireccionEntity direccion) {
		this.nombre = nombre;
		this.direccion = direccion;
	}
	
	public Integer getLegajo() {
		return legajo;
	}
	
	public void setLegajo(Integer legajo) {
		this.legajo = legajo;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public DireccionEntity getDireccion() {
		return direccion;
	}
	public void setDireccion(DireccionEntity direccion) {
		this.direccion = direccion;
	}
	public List<MateriaEntity> getMaterias() {
		return materias;
	}
	public void setMaterias(ArrayList<MateriaEntity> materias) {
		this.materias = materias;
	}
	public List<CursoEntity> getCursos() {
		return cursos;
	}
	public void setCursos(ArrayList<CursoEntity> cursos) {
		this.cursos = cursos;
	}
}
