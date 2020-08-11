package frsf.isi.died.services;

import frsf.isi.died.dao.InsumoGeneralDao;
import frsf.isi.died.dao.InsumoGeneralDaoPostgreSql;
import frsf.isi.died.dao.InsumoLiquidoDao;
import frsf.isi.died.dao.InsumoLiquidoDaoPostgreSql;
import frsf.isi.died.dominio.Insumo;
import frsf.isi.died.dominio.InsumoGeneral;

public class InsumoGeneralService {

	public void agregarInsumoGeneral(Insumo ig) {
		
		InsumoGeneralDao igd=new InsumoGeneralDaoPostgreSql();
		
		igd.save(ig);
	}

	public void modificarInsumoGeneral(InsumoGeneral ig) {
		InsumoGeneralDao igd = new InsumoGeneralDaoPostgreSql();
		igd.update(ig);
	}
	
}
