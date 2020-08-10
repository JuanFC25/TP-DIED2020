package frsf.isi.died.dominio;

import frsf.isi.died.dominio.util.UnidadDeMedida;

public abstract class Insumo {

	private Double costoUnidadMedida;
	private String unidadDeMedida;
	private String descripcion;
	private Integer IdInsumo;
	
	public Double getCostoUnidadMedida() {
		return costoUnidadMedida;
	}
	public void setCostoUnidadMedida(Double costoUnidadMedida) {
		this.costoUnidadMedida = costoUnidadMedida;
	}
	public String getUnidadDeMedida() {
		return unidadDeMedida;
	}
	public void setUnidadDeMedida(String unidadDeMedida) {
		this.unidadDeMedida = unidadDeMedida;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Insumo(Integer idInsumo,Double costoUnidadMedida, String unidadDeMedida, String descripcion) {
		
		this.IdInsumo=idInsumo;
		this.costoUnidadMedida = costoUnidadMedida;
		this.unidadDeMedida = unidadDeMedida;
		this.descripcion = descripcion;
	}
	public Integer getIdInsumo() {
		return IdInsumo;
	}
	public void setIDInsumo(Integer iDInsumo) {
		IdInsumo = iDInsumo;
	}
	
	 public Double pesoPorUnidad() {
		return 0.0;
	}
	public double getDensidad() {
	
		return 0;
	}
	
	
	
	
}
