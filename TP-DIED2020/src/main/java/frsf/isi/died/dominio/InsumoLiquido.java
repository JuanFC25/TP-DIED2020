package frsf.isi.died.dominio;

public class InsumoLiquido extends Insumo {

	private Double densidadLiquido;
	
	public Double pesoPorUnidad() {
		return densidadLiquido;
	}
	
	public Double pesoParaXCantidad(Double volumenEnLitros) {	
		return densidadLiquido * volumenEnLitros;
	}

	public InsumoLiquido(Integer idInsumoGeneral, String descripcion, String unidadDeMedida, Double costoUnidadMedida,Double densidad) {
		super(idInsumoGeneral, costoUnidadMedida, unidadDeMedida, descripcion);
		this.densidadLiquido=densidad;
	}
	
	
	
}
