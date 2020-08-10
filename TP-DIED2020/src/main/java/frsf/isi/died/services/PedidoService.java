package frsf.isi.died.services;

import frsf.isi.died.dao.PedidoDao;
import frsf.isi.died.dao.PedidoDaoPostgreSql;
import frsf.isi.died.dominio.Pedido;

public class PedidoService {

	public void agregarPedido(Pedido p) {
		PedidoDao pd = new PedidoDaoPostgreSql();
		pd.save(p);
	}

}
