package frsf.isi.died.services;

import java.util.List;

import frsf.isi.died.dao.InsumoLiquidoDao;
import frsf.isi.died.dao.InsumoLiquidoDaoPostgreSql;
import frsf.isi.died.dao.PlantaDao;
import frsf.isi.died.dominio.Insumo;
import frsf.isi.died.dominio.InsumoLiquido;
import frsf.isi.died.dominio.Planta;
import frsf.isi.died.exceptions.IdUtilizadoException;

public class InsumoLiquidoService {

	public void agregarInsumoLiquido(Insumo il) throws IdUtilizadoException {
		
		InsumoLiquidoDao ild=new InsumoLiquidoDaoPostgreSql();
		
		verificarIdUnico(il, ild);
		ild.save(il);		
	}
	
	
	private void verificarIdUnico(Insumo il,InsumoLiquidoDao ild) throws IdUtilizadoException {
		Integer id=il.getIdInsumo();
		
		
		List<Integer> listaIds = ild.obtenerIdsAllInsumos();
		
		for(Integer unId : listaIds) {
			if(unId.equals(id)) throw new IdUtilizadoException();
		}
	}


	public void modificarInsumoLiquido(InsumoLiquido il) {
		InsumoLiquidoDao ild = new InsumoLiquidoDaoPostgreSql();
		ild.update(il);
		
	}
}
