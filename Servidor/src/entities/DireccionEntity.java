package entities;

import javax.persistence.Embeddable;

@Embeddable
public class DireccionEntity {

	private String calle;
	private Integer numero;
	private String codigoPostal;
	private String localidad;
	
	public DireccionEntity() { }
	
	public DireccionEntity(String calle, Integer numero, String codigoPostal, String localidad) {
		super();
		this.calle = calle;
		this.numero = numero;
		this.codigoPostal = codigoPostal;
		this.localidad = localidad;
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
}
