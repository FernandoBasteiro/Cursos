package dto;

import java.io.Serializable;
import java.util.ArrayList;

public class CursoDTO  implements Serializable {
	private static final long serialVersionUID = -5095076309125169836L;
	private int numero;
	private ProfesorDTO profesor;
	private MateriaDTO materia;
	private String dia;
	private String turno;
	private ArrayList<AlumnoDTO> alumnos;
	private int maximo;
	
	public CursoDTO(int numero, MateriaDTO materia, String dia, String turno) {
		super();
		this.numero = numero;
		this.materia = materia;
		this.dia = dia;
		this.turno = turno;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public ProfesorDTO getProfesor() {
		return profesor;
	}

	public void setProfesor(ProfesorDTO profesor) {
		this.profesor = profesor;
	}

	public MateriaDTO getMateria() {
		return materia;
	}

	public void setMateria(MateriaDTO materia) {
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

	public ArrayList<AlumnoDTO> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(ArrayList<AlumnoDTO> alumnos) {
		this.alumnos = alumnos;
	}

	public int getMaximo() {
		return maximo;
	}

	public void setMaximo(int maximo) {
		this.maximo = maximo;
	}
	
	
}
