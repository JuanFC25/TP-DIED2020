package frsf.isi.died.controller;

import frsf.isi.died.dominio.Insumo;
import frsf.isi.died.dominio.InsumoGeneral;
import frsf.isi.died.services.InsumoGeneralService;

public class InsumoGeneralController {

	
	public void agregarInsumoGeneral(String id, String descripcion, String unidad, String costoXinsumo, String peso) {
		
		
		Insumo ig = new InsumoGeneral(
				Integer.parseInt(id),
				descripcion,
				unidad,
				Double.parseDouble(costoXinsumo),
				Double.parseDouble(peso));
		
		InsumoGeneralService igs= new InsumoGeneralService();
		igs.agregarInsumoGeneral(ig);
	}
	
	
}
