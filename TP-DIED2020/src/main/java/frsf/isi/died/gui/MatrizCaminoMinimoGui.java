package frsf.isi.died.gui;

import java.util.List;

import frfs.isi.died.estructuras.grafos.Grafo;
import frsf.isi.died.app.App;
import frsf.isi.died.controller.PlantaController;
import frsf.isi.died.dao.PlantaDao;
import frsf.isi.died.dao.PlantaDaoPostgreSql;
import frsf.isi.died.dao.RutaDao;
import frsf.isi.died.dao.RutaDaoPostgreSql;
import frsf.isi.died.dominio.Planta;
import frsf.isi.died.dominio.Ruta;

public class MatrizCaminoMinimoGui {

	Grafo<Planta> grafoPlantas;
	
	public void pantallaPrincipalMatriz (App app) {
		
		
	}
	
	private void armarGrafo () {
		grafoPlantas = new Grafo<Planta>();
		PlantaDao pd = new PlantaDaoPostgreSql();
		List<Planta> listaPlanta = pd.buscarTodos();
		
		for (Planta unaPlanta : listaPlanta) {
			grafoPlantas.addNodo(unaPlanta);
		}
		
		RutaDao rd = new RutaDaoPostgreSql();
		List<Ruta> listaRuta = rd.buscarTodos();
		
		for (Ruta unaRuta : listaRuta) {
			grafoPlantas.conectar(pd.obtenerPlanta(unaRuta.getIdPlantaOrigen()),
					pd.obtenerPlanta(unaRuta.getIdPlantaDestino()),
					unaRuta.getDistancia());
		}
	}
}
