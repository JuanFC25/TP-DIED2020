package frsf.isi.died.controller;

import frsf.isi.died.dominio.Insumo;
import frsf.isi.died.dominio.InsumoLiquido;
import frsf.isi.died.services.InsumoLiquidoService;

public class InsumoLiquidoController {
	
	
	public void agregarInsumoLiquido(String id, String descripcion, String unidad, String costoXinsumo, String densidad) {
		
		
		Insumo il = new InsumoLiquido(
				Integer.parseInt(id),
				descripcion,
				unidad,
				Double.parseDouble(costoXinsumo),
				Double.parseDouble(densidad));
		
		InsumoLiquidoService ils= new InsumoLiquidoService();
		ils.agregarInsumoLiquido(il);
	}

}
