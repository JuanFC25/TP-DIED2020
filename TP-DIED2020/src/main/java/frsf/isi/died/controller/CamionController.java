package frsf.isi.died.controller;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import frsf.isi.died.dominio.Camion;
import frsf.isi.died.exceptions.CampoVacioException;
import frsf.isi.died.exceptions.FormatoFechaException;
import frsf.isi.died.exceptions.FormatoNumericoException;
import frsf.isi.died.exceptions.IdUtilizadoException;
import frsf.isi.died.exceptions.LongitudException;
import frsf.isi.died.services.CamionService;


public class CamionController {

	
	public void modificarCamion(Integer idCamion,String patente,String marca,String modelo,String kmRecorridos,
			String costoXhora,String costoXkm,String fechaDeCompra) throws FormatoNumericoException, 
			LongitudException, CampoVacioException, FormatoFechaException, IdUtilizadoException {
		
		this.verificarPatente(patente);
		this.verificarModelo(modelo);
		this.verificarKmRecorridos(kmRecorridos);
		this.verificarCostoHora(costoXhora);
		this.verificarCostoKm(costoXkm);
		this.verificarFecha(fechaDeCompra);
		
        Date fecha = this.verificarFecha(fechaDeCompra);
		
		Camion c = new Camion(
				idCamion,
				patente,marca,modelo,
				Double.parseDouble(kmRecorridos),
				Double.parseDouble(costoXhora),
				Double.parseDouble(costoXkm),
				fecha
				);
		
		CamionService cs=new CamionService();
		cs.modificarCamion(c);
	}
	
	
	public void agregarCamion(String idCamion,String patente,String marca,String modelo,
			String kmRecorridos,String costoXhora,String costoXkm,String fechaDeCompra) throws
			FormatoNumericoException, LongitudException, CampoVacioException, FormatoFechaException, IdUtilizadoException {
		
		this.verificarId(idCamion);
		this.verificarPatente(patente);
		this.verificarModelo(modelo);
		this.verificarKmRecorridos(kmRecorridos);
		this.verificarCostoHora(costoXhora);
		this.verificarCostoKm(costoXkm);
		this.verificarFecha(fechaDeCompra);
		
        Date fecha = this.verificarFecha(fechaDeCompra);
		
		Camion c = new Camion(
				Integer.parseInt(idCamion),
				patente,marca,modelo,
				Double.parseDouble(kmRecorridos),
				Double.parseDouble(costoXhora),
				Double.parseDouble(costoXkm),
				fecha
				);
		
		CamionService cs=new CamionService();
		cs.agregarCamion(c);
	}
	
	
	
	
	private void verificarCostoKm(String cadena) throws CampoVacioException, FormatoNumericoException {
		if(cadena.length()==0) throw new CampoVacioException("Costo por KM");
		try {
			Double.parseDouble(cadena);
        } catch (NumberFormatException excepcion) {
            throw new FormatoNumericoException("Costo por KM");
        }
		
	}
	
	private void verificarCostoHora(String cadena) throws CampoVacioException, FormatoNumericoException {
	
		if(cadena.length()==0) throw new CampoVacioException("Costo por Hora");
		try {
			Double.parseDouble(cadena);
        } catch (NumberFormatException excepcion) {
            throw new FormatoNumericoException("Costo por Hora");
        }
		
	}

	private void verificarModelo(String cadena) throws LongitudException, CampoVacioException {
		if(cadena.length() == 0) throw new CampoVacioException("Modelo");
		else if(cadena.length() >100) throw new LongitudException("Modelo");
	}

	private void verificarPatente(String cadena) throws LongitudException, CampoVacioException {
		if(cadena.length() == 0) throw new CampoVacioException("Modelo");
		else if(cadena.length() >20) throw new LongitudException("Modelo");
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
	
	private void verificarKmRecorridos(String cadena) throws CampoVacioException, FormatoNumericoException {
		if(cadena.length()==0) throw new CampoVacioException("KM Recorridos");
		try {
			Double.parseDouble(cadena);
        } catch (NumberFormatException excepcion) {
            throw new FormatoNumericoException("KM Recorridos");
        }
		
	}
	
}