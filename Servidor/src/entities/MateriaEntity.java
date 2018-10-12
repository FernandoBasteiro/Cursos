package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="materias")
public class MateriaEntity {
	@Id
	private String codigo;
	private String descripcion;
	private boolean habilitada;
	
	public MateriaEntity() { }
	
	public MateriaEntity(String codigo, String descripcion, boolean habilitada) {
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
