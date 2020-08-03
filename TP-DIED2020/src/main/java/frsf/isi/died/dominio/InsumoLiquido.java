package frsf.isi.died.dominio;

public class InsumoLiquido {

	private Double densidadLiquido;
	
	
	
	
	public Double pesoPorUnidad() {
		return densidadLiquido;
	}
	
	public Double pesoParaXCantidad(Double volumenEnLitros) {	
		return densidadLiquido * volumenEnLitros;
	}
}
