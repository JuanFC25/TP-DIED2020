package frsf.isi.died.dominio;

import java.time.LocalDateTime;
import java.util.List;
import frsf.isi.died.dominio.util.EstadoPedido;

public class Pedido {

	private Double idPedido;
	private Planta plantaDestino;
	private Planta plantaOrigen;
	private LocalDateTime fechaSolicitud; 
	private LocalDateTime fechaEntrega;
	private List<Item> listaItems;
	private Envio detalleEnvio;
	private EstadoPedido estado;
	
}
