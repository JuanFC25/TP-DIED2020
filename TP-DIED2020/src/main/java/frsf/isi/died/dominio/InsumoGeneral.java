package frsf.isi.died.dominio;

import frsf.isi.died.dominio.util.UnidadDeMedida;

public class InsumoGeneral extends Insumo {


	private Double pesoPorUnidadMedida;
	

	public InsumoGeneral(Integer idInsumoGeneral, String descripcion, String unidadDeMedida, Double costoUnidadMedida,Double peso) {
		super(idInsumoGeneral, costoUnidadMedida, unidadDeMedida, descripcion);
		this.pesoPorUnidadMedida=peso;
	}

	@Override
	public Double pesoPorUnidad() {
		return pesoPorUnidadMedida;
	}
	
	public Double pesoParaXCantidad(Double cantidadDeUnidades) {	
		return pesoPorUnidadMedida * cantidadDeUnidades;
	}
	
	
	
}
