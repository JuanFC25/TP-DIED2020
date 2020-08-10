package frsf.isi.died.dominio;

public class Stock {

	private Integer cantidad;
	private Integer idRegistroStock;
	private Integer idInsumoAsociado;
	private Integer puntoDePedido;
	private Integer idPlantaAsociada;

	public Stock(Integer cantidad, Integer idRegistroStock, Integer idInsumoAsociado, Integer puntoDePedido,
			Integer idPlantaAsociada) {
		this.cantidad = cantidad;
		this.idRegistroStock = idRegistroStock;
		this.idInsumoAsociado = idInsumoAsociado;
		this.puntoDePedido = puntoDePedido;
		this.idPlantaAsociada = idPlantaAsociada;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getIdRegistroStock() {
		return idRegistroStock;
	}

	public void setIdRegistroStock(Integer idRegistroStock) {
		this.idRegistroStock = idRegistroStock;
	}

	public Integer getIdInsumoAsociado() {
		return idInsumoAsociado;
	}

	public void setIdInsumoAsociado(Integer idInsumoAsociado) {
		this.idInsumoAsociado = idInsumoAsociado;
	}

	public Integer getPuntoDePedido() {
		return puntoDePedido;
	}

	public void setPuntoDePedido(Integer puntoDePedido) {
		this.puntoDePedido = puntoDePedido;
	}

	public Integer getIdPlantaAsociada() {
		return idPlantaAsociada;
	}

	public void setIdPlantaAsociada(Integer idPlantaAsociada) {
		this.idPlantaAsociada = idPlantaAsociada;
	}

}
