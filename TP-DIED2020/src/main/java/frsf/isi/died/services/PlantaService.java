package frsf.isi.died.services;

import java.util.List;

import frsf.isi.died.dao.PlantaDao;
import frsf.isi.died.dao.PlantaDaoPostgreSql;
import frsf.isi.died.dominio.Planta;
import frsf.isi.died.exceptions.IdUtilizadoException;

public class PlantaService {

	public void agregarPlanta (Planta p) throws IdUtilizadoException{
		PlantaDao pd=new PlantaDaoPostgreSql();
		verificarIdUnico(p, pd);
		pd.save(p);
	}
	
	
	private void verificarIdUnico(Planta p,PlantaDao pd) throws IdUtilizadoException {
		Integer id=p.getIdPlanta();
		
		List<Integer> listaIds = pd.obtenerIds();
		for(Integer unId : listaIds) {
			if(unId.equals(id)) throw new IdUtilizadoException();
		}
	}


	public void modificarPlanta(Planta p) {
		PlantaDao pd=new PlantaDaoPostgreSql();
		pd.update(p);
		
	}
}
