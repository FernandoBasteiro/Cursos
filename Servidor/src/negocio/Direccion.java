package negocio;

public class Direccion {
	private String calle;
	private Integer numero;
	private String codigoPostal;
	private String localidad;
	
	public Direccion(String calle, Integer numero, String codigoPostal, String localidad) {
		this.calle = calle;
		this.numero = numero;
		this.codigoPostal = codigoPostal;
		this.localidad = localidad;
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
}
