package frsf.isi.died.controller;



import frsf.isi.died.dominio.Planta;
import frsf.isi.died.exceptions.CampoVacioException;
import frsf.isi.died.exceptions.FormatoNumericoException;
import frsf.isi.died.exceptions.IdUtilizadoException;
import frsf.isi.died.exceptions.LongitudException;
import frsf.isi.died.services.PlantaService;

public class PlantaController {

	
	
	
	
	public void agregarPlanta(String id,String nombre,String direccion,String telefono) 
			throws FormatoNumericoException, LongitudException, CampoVacioException, IdUtilizadoException {
		verificarId(id);
		verificarNombre(nombre);
		verificarDireccion(direccion);
		verificarTelefono(telefono);
	
		Planta p = new Planta(
				Integer.parseInt(id),
				nombre, direccion,
				Integer.parseInt(telefono));
		
		PlantaService ps=new PlantaService();
		ps.agregarPlanta(p);
	
		}
	
	
	private void verificarId(String cadena) throws FormatoNumericoException, LongitudException, CampoVacioException {
	
		if(cadena.length()==0) throw new CampoVacioException("ID");
		Integer id;
		 try {
	            id=Integer.parseInt(cadena);
	        
	        } catch (NumberFormatException excepcion) {
	            throw new FormatoNumericoException("ID");
	        }
		
		Integer cantidad=0;
		while (id/10 != 0) {
			cantidad+=1;
			id=id/10;
		}
		if (cantidad != 4) throw new LongitudException("ID");
	}
	

	private void verificarNombre(String cadena) throws LongitudException, CampoVacioException {
		if(cadena.length() == 0) throw new CampoVacioException("Nombre");
		else if(cadena.length() >100) throw new LongitudException("Nombre");
	}
	
	private void verificarDireccion(String cadena) throws LongitudException, CampoVacioException {
		if(cadena.length() == 0) throw new CampoVacioException("Direccion");
		else if(cadena.length() >100) throw new LongitudException("Direccion");
	}
	
private void verificarTelefono(String cadena) throws FormatoNumericoException, CampoVacioException {
		
		if(cadena.length()==0) throw new CampoVacioException("Telefono");
		try {
			Integer.parseInt(cadena);
        } catch (NumberFormatException excepcion) {
            throw new FormatoNumericoException("Telefono");
        }
		
	}

public void modificarPlanta(Integer id,String nombre,String direccion,String telefono) 
		throws LongitudException, CampoVacioException, FormatoNumericoException {
	
	verificarNombre(nombre);
	verificarDireccion(direccion);
	verificarTelefono(telefono);
	Planta p = new Planta(
			id,nombre, direccion,
			Integer.parseInt(telefono));
	
	PlantaService ps=new PlantaService();
	ps.modificarPlanta(p);
}


}
