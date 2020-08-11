package frsf.isi.died.controller;

import frsf.isi.died.dominio.Insumo;
import frsf.isi.died.dominio.InsumoGeneral;
import frsf.isi.died.dominio.InsumoLiquido;
import frsf.isi.died.dominio.Planta;
import frsf.isi.died.exceptions.CampoVacioException;
import frsf.isi.died.exceptions.FormatoNumericoException;
import frsf.isi.died.exceptions.IdUtilizadoException;
import frsf.isi.died.exceptions.LongitudException;
import frsf.isi.died.services.InsumoGeneralService;
import frsf.isi.died.services.InsumoLiquidoService;
import frsf.isi.died.services.PlantaService;

public class InsumoLiquidoController {
	
	
	public void agregarInsumoLiquido(String id, String descripcion, String unidad, String costoXinsumo, String densidad) 
			throws CampoVacioException, FormatoNumericoException, LongitudException, IdUtilizadoException {

		this.verificarId(id);
		//this.verificarDescripcion(descripcion);
		//this.verificarUnidad(unidad);
		this.verificarCostoXInsumo(costoXinsumo);
		this.verificarDensidad(densidad);
		
		Insumo il = new InsumoLiquido(
				Integer.parseInt(id),
				descripcion,
				unidad,
				Double.parseDouble(costoXinsumo),
				Double.parseDouble(densidad));
		
		InsumoLiquidoService ils= new InsumoLiquidoService();
		ils.agregarInsumoLiquido(il);
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
	
	private void verificarDensidad(String d) throws CampoVacioException, FormatoNumericoException {
		
		if(d.length() == 0) throw new CampoVacioException("DENSIDAD");
		try {
			Double.parseDouble(d);
		} catch (NumberFormatException e) {
			 throw new FormatoNumericoException("DENSIDAD");
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
	
	public void modificarInsumoLiquido(String id, String descripcion, String unidad, String costoXinsumo, String densidad) 
			throws LongitudException, CampoVacioException, FormatoNumericoException {
		
		verificarId(id);
		verificarDensidad(densidad);
		verificarCostoXInsumo(costoXinsumo);
		InsumoLiquido il = new InsumoLiquido(
				Integer.parseInt(id)
				,descripcion, unidad,
				Double.parseDouble(costoXinsumo),
				Double.parseDouble(densidad));
		
		InsumoLiquidoService ils = new InsumoLiquidoService();

		ils.modificarInsumoLiquido(il);
	}


}
