package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cursos")
public class CursoEntity {
	@Id
	@GeneratedValue
	private int numero;
	@ManyToOne()
	private ProfesorEntity profesor;
	@ManyToOne()
	private MateriaEntity materia;
	private String dia;
	private String turno;
	@ManyToMany(mappedBy="cursos")
	private List<AlumnoEntity> alumnos;
	private int maximo;
	
	public CursoEntity(int numero, String dia, String turno, int maximo) {
		super();
		this.numero = numero;
		this.dia = dia;
		this.turno = turno;
		this.maximo = maximo;
	}

	public CursoEntity() { }

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public ProfesorEntity getProfesor() {
		return profesor;
	}

	public void setProfesor(ProfesorEntity profesor) {
		this.profesor = profesor;
	}

	public MateriaEntity getMateria() {
		return materia;
	}

	public void setMateria(MateriaEntity materia) {
		this.materia = materia;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public List<AlumnoEntity> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<AlumnoEntity> alumnos) {
		this.alumnos = alumnos;
	}

	public int getMaximo() {
		return maximo;
	}

	public void setMaximo(int maximo) {
		this.maximo = maximo;
	}
}
