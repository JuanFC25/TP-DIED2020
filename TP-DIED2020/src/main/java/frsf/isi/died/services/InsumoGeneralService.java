package frsf.isi.died.services;

import frsf.isi.died.dao.InsumoGeneralDao;
import frsf.isi.died.dao.InsumoGeneralDaoPostgreSql;
import frsf.isi.died.dominio.Insumo;

public class InsumoGeneralService {

	public void agregarInsumoGeneral(Insumo ig) {
		
		InsumoGeneralDao igd=new InsumoGeneralDaoPostgreSql();
		
		igd.save(ig);
	}
	
}
