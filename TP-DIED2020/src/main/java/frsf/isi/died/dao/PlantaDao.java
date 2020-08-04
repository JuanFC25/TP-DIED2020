package frsf.isi.died.dao;

import java.util.List;


import frsf.isi.died.dominio.Planta;

public interface PlantaDao {

	public List<Planta> buscarTodos() ;
	public Planta save(Planta p) ;
	public Planta update(Planta p) ;
	public List<Integer> obtenerIds();
}

