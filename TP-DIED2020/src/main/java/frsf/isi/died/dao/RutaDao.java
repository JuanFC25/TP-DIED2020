package frsf.isi.died.dao;

import java.util.List;

import frsf.isi.died.dominio.Ruta;

public interface RutaDao {

	public List<Ruta> buscarTodos();
	public List<Integer> obtenerIds();
	public void save(Ruta r);
	public void delete(Integer id);
	public void update(Ruta r);
}
