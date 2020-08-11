package frsf.isi.died.controller;

import frsf.isi.died.dominio.Insumo;
import frsf.isi.died.dominio.Stock;
import frsf.isi.died.exceptions.CampoVacioException;
import frsf.isi.died.exceptions.FormatoNumericoException;
import frsf.isi.died.exceptions.IdUtilizadoException;
import frsf.isi.died.exceptions.LongitudException;
import frsf.isi.died.services.InsumoLiquidoService;
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
	
	private void verificarCantidad(String c) {
		Integer x = Integer.parseInt(c);
		try {
			if (x > 0) {
			}
			else {
				throw new FormatoNumericoException("CANTIDAD");
			}
		} catch (Exception e) {
		}
	}
	
	public void modificarStock(String idReg, String idInsumo, String iCantidad, String iPuntoPedido, Integer idPlanta) {
	
		Stock nuevoStock = new Stock(
				Integer.parseInt(iCantidad),
				Integer.parseInt(idReg),
				Integer.parseInt(idInsumo), 
				Integer.parseInt(iPuntoPedido), 
				idPlanta);
		StockService ss = new StockService();

		ss.modificarStock(nuevoStock);
		
	}

	
}
