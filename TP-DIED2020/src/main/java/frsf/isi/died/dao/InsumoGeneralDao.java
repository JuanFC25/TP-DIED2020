package frsf.isi.died.dao;

import java.util.List;

import frsf.isi.died.dominio.InsumoGeneral;

public interface InsumoGeneralDao {
	public List<InsumoGeneral> buscarTodos() ;
	public InsumoGeneral save(InsumoGeneral p) ;
	public InsumoGeneral update(InsumoGeneral p) ;
	public List<Integer> obtenerIds();
}
