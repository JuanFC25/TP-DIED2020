package frsf.isi.died.services;

import java.util.List;

import frsf.isi.died.dao.RutaDao;
import frsf.isi.died.dao.StockDao;
import frsf.isi.died.dao.StockDaoPostgreSql;
import frsf.isi.died.dominio.Ruta;
import frsf.isi.died.dominio.Stock;
import frsf.isi.died.exceptions.IdUtilizadoException;

public class StockService {

	public void agregarStock(Stock s) throws IdUtilizadoException {
		
		StockDao sd=new StockDaoPostgreSql();
		verificarIdUnico(s, sd);
		sd.save(s);
	}
	
	private void verificarIdUnico(Stock s,StockDao sd) throws IdUtilizadoException {
		Integer id=s.getIdRegistroStock();

		List<Integer> listaIds = sd.obtenerIds();
		for(Integer unId : listaIds) {
			if(unId.equals(id)) throw new IdUtilizadoException();
		}
	}
}
