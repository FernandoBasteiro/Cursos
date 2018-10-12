package vista;

import java.util.ArrayList;


public class ProfesorView {
	private int legajo;
	private String nombre;
	private String calle;
	private Integer numero;
	private String codigoPostal;
	private String localidad;
	private ArrayList<MateriaView> materias;
	public ProfesorView(int legajo, String nombre, String calle, Integer numero, String codigoPostal, String localidad,
			ArrayList<MateriaView> materias) {
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
	public String getNombre() {
		return nombre;
	}
	public String getCalle() {
		return calle;
	}
	public Integer getNumero() {
		return numero;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public String getLocalidad() {
		return localidad;
	}
	public ArrayList<MateriaView> getMaterias() {
		return materias;
	}
}
