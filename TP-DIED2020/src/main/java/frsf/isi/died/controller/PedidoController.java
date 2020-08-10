package frsf.isi.died.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import frsf.isi.died.dao.PlantaDao;
import frsf.isi.died.dao.PlantaDaoPostgreSql;
import frsf.isi.died.dao.StockDao;
import frsf.isi.died.dao.StockDaoPostgreSql;
import frsf.isi.died.dominio.Item;
import frsf.isi.died.dominio.Pedido;
import frsf.isi.died.dominio.Planta;
import frsf.isi.died.dominio.Stock;
import frsf.isi.died.exceptions.CampoVacioException;
import frsf.isi.died.exceptions.FormatoFechaException;
import frsf.isi.died.exceptions.FormatoNumericoException;
import frsf.isi.died.exceptions.LongitudException;
import frsf.isi.died.services.PedidoService;

public class PedidoController {
	
	public void agregarPedido(String id, Integer plantaDestino, Date fechaActual,
			String fechaEntrega, String estado) throws FormatoNumericoException, LongitudException,
			CampoVacioException, FormatoFechaException {
		
		verificarId(id);
		Date fecha = this.verificarFecha(fechaEntrega);
		
		Pedido p = new Pedido(Integer.parseInt(id),
				plantaDestino,
				fechaActual,
				fecha);
		
		PedidoService pd = new PedidoService();
		pd.agregarPedido(p);
	}
	
	private void verificarId(String cadena) throws FormatoNumericoException, LongitudException, CampoVacioException {
		
		if(cadena.length()==0) throw new CampoVacioException("ID");
		Integer id;
		 try {
	            id=Integer.parseInt(cadena);
	        
	        } catch (NumberFormatException excepcion) {
	            throw new FormatoNumericoException("ID");
	        }
		
		Integer cantidad=0;
		while (id/10 != 0) {
			cantidad+=1;
			id=id/10;
		}
		if (cantidad != 4) throw new LongitudException("ID");
	}
	
	private Date verificarFecha(String cadena) throws FormatoFechaException {
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaDate = null;
		try {
			fechaDate = formato.parse(cadena);
		} catch (ParseException e) {
			throw new FormatoFechaException();
		}
	
		return fechaDate;
	}

	public List<Planta> obtenerPlantasConStock(List<Item> listaItems) {
		Boolean cumple=true;
		
		PlantaDao pd = new PlantaDaoPostgreSql();
		StockDao sd = new StockDaoPostgreSql();
		List<Planta> listaPlantas = pd.buscarTodos();
		List<Planta> resultado = new ArrayList<Planta>();
		for(Planta unaPlanta : listaPlantas) {
			
			List<Stock> listaStock = sd.buscarStockPlanta(unaPlanta.getIdPlanta());
			
			for(Item unItem : listaItems) {
				Integer idInsumoItem = unItem.getIdInsumo();
				Stock s= estaEnStock(idInsumoItem, listaStock);
				if(s!=null) {
					if(s.getCantidad()<unItem.getCantidad()) cumple=false;
				}
				else cumple=false;
			}
			
			if(cumple==true) resultado.add(unaPlanta);
			
			cumple=true;
		}
		
		return resultado;
	}
	
	private Stock estaEnStock(Integer idInsumoItem,List<Stock> listaStock) {
		for(Stock unStock : listaStock) {
			if(unStock.getIdInsumoAsociado().equals(idInsumoItem)) return unStock;
		}
		return null;
	}
	
}
