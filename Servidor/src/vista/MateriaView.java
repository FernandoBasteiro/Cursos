package vista;

public class MateriaView {
	private String codigo;
	private String descripcion;
	private boolean habilitada;
	public MateriaView(String codigo, String descripcion, boolean habilitada) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.habilitada = habilitada;
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
	
	
}
