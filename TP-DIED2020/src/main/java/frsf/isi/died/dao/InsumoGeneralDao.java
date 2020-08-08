package frsf.isi.died.dao;

import java.util.List;

import frsf.isi.died.dominio.Insumo;
import frsf.isi.died.dominio.InsumoGeneral;

public interface InsumoGeneralDao {
	public List<Insumo> buscarTodos() ;
	public Insumo save(Insumo p) ;
//	public InsumoGeneral update(InsumoGeneral p) ;
//	public List<Integer> obtenerIds();
}
