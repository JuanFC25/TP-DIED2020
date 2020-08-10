package frsf.isi.died.dao;

import java.util.List;

import frsf.isi.died.dominio.Insumo;

public interface InsumoLiquidoDao {

	public Insumo save(Insumo il);
	public void update(Insumo il);
	public void delete(Integer id);
	public List<Insumo> buscarTodos();
	public List<Integer> obtenerIdsAllInsumos();


}
