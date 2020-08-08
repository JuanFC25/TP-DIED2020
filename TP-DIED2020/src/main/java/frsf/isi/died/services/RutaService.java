package frsf.isi.died.services;

import java.util.List;


import frsf.isi.died.dao.RutaDao;
import frsf.isi.died.dao.RutaDaoPostgreSql;
import frsf.isi.died.dominio.Ruta;
import frsf.isi.died.exceptions.IdUtilizadoException;

public class RutaService {

	public void agregarRuta(Ruta r) throws IdUtilizadoException {
		RutaDao rd = new RutaDaoPostgreSql();
		verificarIdUnico(r,rd);
		rd.save(r);
		
	}

	
	private void verificarIdUnico(Ruta r,RutaDao rd) throws IdUtilizadoException {
		Integer id=r.getIdRuta();

		List<Integer> listaIds = rd.obtenerIds();
		for(Integer unId : listaIds) {
			if(unId.equals(id)) throw new IdUtilizadoException();
		}
	}


	public void borrarRuta(Integer id) {
		
		RutaDao rd = new RutaDaoPostgreSql();
		
		rd.delete(id);
		
	}
	
}
