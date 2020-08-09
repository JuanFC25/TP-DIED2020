package frsf.isi.died.dominio;


import java.util.Date;
import java.util.List;
import frsf.isi.died.dominio.util.EstadoPedido;

public class Pedido {

	private Integer idPedido;
	private Integer idPlantaDestino;
	private Integer idPlantaOrigen;
	private Date fechaSolicitud; 
	private Date fechaEntrega;
	private String estado;
	
	public Pedido(Integer idPedido, Integer idPlantaDestino, Integer idPlantaOrigen, Date fechaSolicitud,
			Date fechaEntrega, String estado) {
		this.idPedido = idPedido;
		this.idPlantaDestino = idPlantaDestino;
		this.idPlantaOrigen = idPlantaOrigen;
		this.fechaSolicitud = fechaSolicitud;
		this.fechaEntrega = fechaEntrega;
		this.estado = estado;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Integer getIdPlantaDestino() {
		return idPlantaDestino;
	}

	public void setIdPlantaDestino(Integer idPlantaDestino) {
		this.idPlantaDestino = idPlantaDestino;
	}

	public Integer getIdPlantaOrigen() {
		return idPlantaOrigen;
	}

	public void setIdPlantaOrigen(Integer idPlantaOrigen) {
		this.idPlantaOrigen = idPlantaOrigen;
	}

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
}
