package negocio;

import dto.MateriaDTO;
import dao.MateriaDAO;

public class Materia {
	private String codigo;
	private String descripcion;
	private boolean habilitada;
	
	public boolean soyLaMateria(String codigo){
		return (this.codigo.equals(codigo));
	}
	
	public MateriaDTO toView() {
		MateriaDTO mv = new MateriaDTO(this.codigo, this.descripcion, this.habilitada);
		return mv;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public boolean isHabilitada() {
		return habilitada;
	}

	public Materia(String codigo, String descripcion, boolean habilitada) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.habilitada = habilitada;
	}
	
	public void grabar() {
		MateriaDAO.getInstancia().grabar(this);
	}
	
}
