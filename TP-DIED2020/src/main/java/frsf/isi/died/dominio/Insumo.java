package frsf.isi.died.dominio;

import frsf.isi.died.dominio.util.UnidadDeMedida;

public abstract class Insumo {

	private Double costoUnidadMedida;
	private UnidadDeMedida unidadDeMedida;
	private String descripcion;
	
	public Double getCostoUnidadMedida() {
		return costoUnidadMedida;
	}
	public void setCostoUnidadMedida(Double costoUnidadMedida) {
		this.costoUnidadMedida = costoUnidadMedida;
	}
	public UnidadDeMedida getUnidadDeMedida() {
		return unidadDeMedida;
	}
	public void setUnidadDeMedida(UnidadDeMedida unidadDeMedida) {
		this.unidadDeMedida = unidadDeMedida;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Insumo(Double costoUnidadMedida, UnidadDeMedida unidadDeMedida, String descripcion) {
		super();
		this.costoUnidadMedida = costoUnidadMedida;
		this.unidadDeMedida = unidadDeMedida;
		this.descripcion = descripcion;
	}
	
	
}
