package frsf.isi.died.controller;

import frsf.isi.died.dominio.Insumo;
import frsf.isi.died.dominio.InsumoGeneral;
import frsf.isi.died.exceptions.CampoVacioException;
import frsf.isi.died.exceptions.FormatoNumericoException;
import frsf.isi.died.exceptions.LongitudException;
import frsf.isi.died.services.InsumoGeneralService;

public class InsumoGeneralController {

	
	public void agregarInsumoGeneral(String id, String descripcion, String unidad, String costoXinsumo, String peso) throws FormatoNumericoException, LongitudException, CampoVacioException {
		//asd
		this.verificarId(id);
		//this.verificarDescripcion(descripcion);
		//this.verificarUnidad(unidad);
		this.verificarCostoXInsumo(costoXinsumo);
		this.verificarPeso(peso);
		
		
		Insumo ig = new InsumoGeneral(
				Integer.parseInt(id),
				descripcion,
				unidad,
				Double.parseDouble(costoXinsumo),
				Double.parseDouble(peso));
		
		InsumoGeneralService igs= new InsumoGeneralService();
		igs.agregarInsumoGeneral(ig);
	}
	
	//private void verificarDescripcion(String descripcion) {}
	
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
	
	private void verificarPeso(String p) throws CampoVacioException, FormatoNumericoException {
		
		if(p.length() == 0) throw new CampoVacioException("PESO");
		try {
			Double.parseDouble(p);
		} catch (NumberFormatException e) {
			 throw new FormatoNumericoException("PESO");
		}
		
	}

	private void verificarCostoXInsumo(String costoXinsumo) throws CampoVacioException, FormatoNumericoException {
		if(costoXinsumo.length() == 0) throw new CampoVacioException("COSTO");
		try {
			Double.parseDouble(costoXinsumo);
		} catch (NumberFormatException e) {
			 throw new FormatoNumericoException("COSTO");
		}
	}
	
	//private void verificarUnidad(unidad) {};
	
}
