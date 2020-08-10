package frsf.isi.died.controller;

import java.util.Date;

import frsf.isi.died.dominio.Planta;
import frsf.isi.died.exceptions.CampoVacioException;
import frsf.isi.died.exceptions.FormatoNumericoException;
import frsf.isi.died.exceptions.LongitudException;

public class PedidoController {
	
	public void agregarPedido(String id, Planta plantaOrigen, Planta plantaDestino, Date fechaActual,
			String fechaEntrega, String estado) {
		
		
		
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
	
	
	
	
	
}
