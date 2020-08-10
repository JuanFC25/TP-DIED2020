package frsf.isi.died.dao;

import java.util.List;

import frsf.isi.died.dominio.Item;

public interface ItemDao {

	public void save(Item i);

	public int obtenerIdMasAlto();
	
	public List<Item> obtenerItemsPedido(Integer id);
}
