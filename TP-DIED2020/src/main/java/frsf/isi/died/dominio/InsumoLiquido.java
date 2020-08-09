package frsf.isi.died.dominio;

public class InsumoLiquido extends Insumo{

	private Double densidadLiquido;
	
	public InsumoLiquido(Integer idInsumoLiquido, String descripcion, String unidadDeMedida, Double costoUnidadMedida,Double densidad) {
		super(idInsumoLiquido, costoUnidadMedida, unidadDeMedida, descripcion);
		this.densidadLiquido=densidad;
	}

	public Double pesoPorUnidad() {
		return densidadLiquido;
	}
	
	public Double pesoParaXCantidad(Double volumenEnLitros) {	
		return densidadLiquido * volumenEnLitros;
	}

	@Override
	public double getDensidad() {
		return densidadLiquido;
	}
}
