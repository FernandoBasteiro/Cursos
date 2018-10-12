package dto;

import java.io.Serializable;

public class MateriaDTO  implements Serializable {
	private static final long serialVersionUID = 39591395691461055L;
	private String codigo;
	private String descripcion;
	private boolean habilitada;
	public MateriaDTO(String codigo, String descripcion, boolean habilitada) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.habilitada = habilitada;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public boolean isHabilitada() {
		return habilitada;
	}
	public void setHabilitada(boolean habilitada) {
		this.habilitada = habilitada;
	}
	
	
}
