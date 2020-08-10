package frsf.isi.died.dao;

import java.util.List;

import frsf.isi.died.dominio.Stock;

public interface StockDao {
	public List<Stock> buscarStockPlanta(Integer id);
	public List<Integer> obtenerIds();
	public void save(Stock s);
}
