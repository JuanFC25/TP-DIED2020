package frsf.isi.died.dominio;

public class Planta {
	
	private Integer idPlanta;
	private String nombrePlanta;
	private String direccion;
	private Integer telefono;

	public Planta(Integer id,String nombre,String direccion,Integer telefono) {
		this.idPlanta=id;
		this.nombrePlanta=nombre;
		this.direccion=direccion;
		this.telefono=telefono;
	}
	
	
	public String getNombrePlanta() {
		return nombrePlanta;
	}

	public void setNombrePlanta(String nombrePlanta) {
		this.nombrePlanta = nombrePlanta;
	}

	public Integer getIdPlanta() {
		return idPlanta;
	}

	public void setIdPlanta(Integer idPlanta) {
		this.idPlanta = idPlanta;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}
	
	
	
}
