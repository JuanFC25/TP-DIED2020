package frsf.isi.died.dao;

import java.util.List;

import frsf.isi.died.dominio.Camion;



public interface CamionDao {

	public Camion saveOrUpdate(Camion c);
	public Camion buscarPorId(Integer id);
	public void borrar(Integer id);
	public List<Camion> buscarTodos();
}
