package frsf.isi.died.dao;

import java.util.List;

import frsf.isi.died.dominio.Insumo;

public interface InsumoGeneralDao {
	public List<Insumo> buscarTodos() ;
	public Insumo save(Insumo p) ;
	public void update(Insumo ig);
	public void delete(Integer id);
	public List<Integer> obtenerIdsAllInsumos();
}
