package frsf.isi.died.dominio;

public class Item {

	
	private Integer idItem;
	private Integer idInsumo;
	private Integer idPedido;
    private Double cantidad;
    private Double costoItem;
	
    
    public Item(Integer idItem, Integer idInsumo, Integer idPedido, Double cantidad, Double costoItem) {
		this.idItem = idItem;
		this.idInsumo = idInsumo;
		this.idPedido = idPedido;
		this.cantidad = cantidad;
		this.costoItem = costoItem;
	}

	public Item(Integer idInsumo, Double cantidad, Double costoItem) {
    	this.setIdPedido(null);
		this.idItem = null;
    	this.idInsumo = idInsumo;
		this.cantidad = cantidad;
		this.costoItem = costoItem;
	}

	public Integer getIdInsumo() {
		return idInsumo;
	}

	public void setIdInsumo(Integer idInsumo) {
		this.idInsumo = idInsumo;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Double getCostoItem() {
		return costoItem;
	}

	public void setCostoItem(Double costoItem) {
		this.costoItem = costoItem;
	}

	public Integer getIdItem() {
		return idItem;
	}

	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}
    
    
    
}
