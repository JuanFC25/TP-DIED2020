package frsf.isi.died.dao;

import java.util.List;

import frsf.isi.died.dominio.Pedido;

public interface PedidoDao {

	public List<Pedido> buscarTodos();

    public void update(Pedido p);

	public void save(Pedido p);

}
