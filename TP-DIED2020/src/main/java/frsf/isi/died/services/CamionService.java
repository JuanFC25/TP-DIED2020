package frsf.isi.died.services;

import java.util.List;

import frsf.isi.died.dao.CamionDao;
import frsf.isi.died.dao.CamionDaoPostgreSql;
import frsf.isi.died.dao.PlantaDao;
import frsf.isi.died.dao.PlantaDaoPostgreSql;
import frsf.isi.died.dominio.Camion;
import frsf.isi.died.dominio.Planta;
import frsf.isi.died.exceptions.IdUtilizadoException;

public class CamionService {

	public void agregarCamion(Camion c) throws IdUtilizadoException {
		
		CamionDao cd=new CamionDaoPostgreSql();
		verificarIdUnico(c, cd);
		cd.save(c);
		
	}
	
	private void verificarIdUnico(Camion c,CamionDao cd) throws IdUtilizadoException {
		Integer id=c.getIdCamion();
		
		List<Integer> listaIds = cd.obtenerIds();
		for(Integer unId : listaIds) {
			if(unId.equals(id)) throw new IdUtilizadoException();
		}
	}
	
	
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

	public void modificarCamion(Camion c) {
		CamionDao cd=new CamionDaoPostgreSql();
		cd.update(c);
	}
}
