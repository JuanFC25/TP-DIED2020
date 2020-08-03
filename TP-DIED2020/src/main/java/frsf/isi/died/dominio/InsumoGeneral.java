package frsf.isi.died.dominio;

import frsf.isi.died.dominio.util.UnidadDeMedida;

public class InsumoGeneral extends Insumo {


	private Double pesoPorUnidadMedida;
	

	public InsumoGeneral(Double costoUnidadMedida, UnidadDeMedida unidadDeMedida, String descripcion) {
		super(costoUnidadMedida, unidadDeMedida, descripcion);
		// TODO Auto-generated constructor stub
	}

	public Double pesoPorUnidad() {
		return pesoPorUnidadMedida;
	}
	
	public Double pesoParaXCantidad(Double cantidadDeUnidades) {	
		return pesoPorUnidadMedida * cantidadDeUnidades;
	}
	
	
	
}
