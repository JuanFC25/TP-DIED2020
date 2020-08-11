package frsf.isi.died.controller;

import frsf.isi.died.dominio.Insumo;
import frsf.isi.died.dominio.Stock;
import frsf.isi.died.exceptions.IdUtilizadoException;
import frsf.isi.died.services.StockService;

public class StockController {

	public void agregarStock(String id, Integer idPlanta, Insumo i, String cantidad, String puntoPedido) throws IdUtilizadoException {
		
		
		Stock s = new Stock(Integer.parseInt(cantidad),
				Integer.parseInt(id),
				i.getIdInsumo(),
				Integer.parseInt(puntoPedido),
				idPlanta);
				
		StockService ss = new StockService();
		
		ss.agregarStock(s);
		
	}

	
}
