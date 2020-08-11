package frsf.isi.died.controller;

import frsf.isi.died.dominio.Planta;
import frsf.isi.died.dominio.Ruta;
import frsf.isi.died.exceptions.CampoVacioException;
import frsf.isi.died.exceptions.FormatoNumericoException;
import frsf.isi.died.exceptions.IdUtilizadoException;
import frsf.isi.died.exceptions.LongitudException;
import frsf.isi.died.exceptions.MismaPlantaException;
import frsf.isi.died.services.RutaService;

public class RutaController {

	public void agregarRuta(String idRuta, Planta pOrigen, Planta pDestino, String distancia, String duracion,
			String peso) throws FormatoNumericoException, LongitudException, CampoVacioException,
			MismaPlantaException, IdUtilizadoException {
		
		verificarId(idRuta);
		verificarPlantas(pOrigen, pDestino);
		verificarDistanciaEnKm(distancia);
		verificarDuracion(duracion);
		verificarPeso(peso);
	
		Ruta r = new Ruta(Integer.parseInt(idRuta),
				pOrigen.getIdPlanta(),
				pDestino.getIdPlanta(),
				Integer.parseInt(distancia),
				Integer.parseInt(duracion),
				Integer.parseInt(peso));
	
		RutaService rs = new RutaService();
		rs.agregarRuta(r);
		
	}
	
	

	public void modificarRuta(Integer idRuta, Planta pOrigen, Planta pDestino,
			String distancia, String duracion,String peso) throws MismaPlantaException, 
			CampoVacioException, FormatoNumericoException {
		
		verificarPlantas(pOrigen, pDestino);
		verificarDistanciaEnKm(distancia);
		verificarDuracion(duracion);
		verificarPeso(peso);
	
		Ruta r = new Ruta(idRuta,
				pOrigen.getIdPlanta(),
				pDestino.getIdPlanta(),
				Integer.parseInt(distancia),
				Integer.parseInt(duracion),
				Integer.parseInt(peso));
	
		RutaService rs = new RutaService();
		rs.modificarRuta(r);
	}

	public void borrarRuta() {
		
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
	
	private void verificarPlantas(Planta pUno,Planta pDos) throws MismaPlantaException {
		if(pUno==pDos) throw new MismaPlantaException();
	}
	
	private void verificarDistanciaEnKm(String cadena) throws CampoVacioException, FormatoNumericoException {
		if(cadena.length()==0) throw new CampoVacioException("Distancia(KM)");
		try {
			Double.parseDouble(cadena);
        } catch (NumberFormatException excepcion) {
            throw new FormatoNumericoException("Distancia(KM)");
        }
		
	}
	
	private void verificarDuracion(String cadena) throws CampoVacioException, FormatoNumericoException {
		if(cadena.length()==0) throw new CampoVacioException("Duracion(hs);");
		try {
			Integer.parseInt(cadena);
        } catch (NumberFormatException excepcion) {
            throw new FormatoNumericoException("Duracion(hs);");
        }
		
	}
	
	private void verificarPeso(String cadena) throws CampoVacioException, FormatoNumericoException {
		if(cadena.length()==0) throw new CampoVacioException("Peso maximo(Kg):");
		try {
			Integer.parseInt(cadena);
        } catch (NumberFormatException excepcion) {
            throw new FormatoNumericoException("Peso maximo(Kg):");
        }
		
	}

	public void borrarRuta(Integer id) {
		
		RutaService cs = new RutaService();
		cs.borrarRuta(id);
		
	}

	
}
