package frsf.isi.died.services;

import frsf.isi.died.dao.InsumoLiquidoDao;
import frsf.isi.died.dao.InsumoLiquidoDaoPostgreSql;
import frsf.isi.died.dominio.Insumo;

public class InsumoLiquidoService {

	public void agregarInsumoLiquido(Insumo il) {
		
		InsumoLiquidoDao ild=new InsumoLiquidoDaoPostgreSql();
		
		ild.save(il);
	}
}
