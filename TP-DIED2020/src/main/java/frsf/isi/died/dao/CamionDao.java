package frsf.isi.died.dao;

import java.util.List;

import frsf.isi.died.dominio.Camion;



public interface CamionDao {

	
	public Camion buscarPorId(Integer id);
	public void borrar(Integer id);
	public List<Camion> buscarTodos();
	public Camion save(Camion c);
	public List<Integer> obtenerIds();
	public Camion update(Camion c);
}
