package dto;

import java.io.Serializable;
import java.util.ArrayList;


public class ProfesorDTO  implements Serializable {
	private static final long serialVersionUID = 5103924100768208848L;
	private int legajo;
	private String nombre;
	private String calle;
	private Integer numero;
	private String codigoPostal;
	private String localidad;
	private ArrayList<MateriaDTO> materias;
	public ProfesorDTO(int legajo, String nombre, String calle, Integer numero, String codigoPostal, String localidad,
			ArrayList<MateriaDTO> materias) {
		super();
		this.legajo = legajo;
		this.nombre = nombre;
		this.calle = calle;
		this.numero = numero;
		this.codigoPostal = codigoPostal;
		this.localidad = localidad;
		this.materias = materias;
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
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public ArrayList<MateriaDTO> getMaterias() {
		return materias;
	}
	public void setMaterias(ArrayList<MateriaDTO> materias) {
		this.materias = materias;
	}
	
	
}
