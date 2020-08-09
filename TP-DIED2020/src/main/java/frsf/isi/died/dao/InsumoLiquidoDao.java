package frsf.isi.died.dao;

import java.util.List;

import frsf.isi.died.dominio.Insumo;

public interface InsumoLiquidoDao {
	public List<Insumo> buscarTodos() ;
	public Insumo save(Insumo p) ;
//	public InsumoLiquido update(InsumoGeneral p) ;
//	public List<Integer> obtenerIds();
}
