package frsf.isi.died.dominio;

import java.util.Date;


public class Camion {

	private Integer idCamion;
	private String patente;
	private String marca;
	private String modelo;
	private Double kmRecorridos;
	private Double costoHora;
	private Double costoKm;
	private Date fechaDeCompra;
	
	
	public Camion(Integer idCamion,String patente, String marca, String modelo, Double kmRecorridos, Double costoHora, Double costoKm,
			Date fechaDeCompra) {
		this.idCamion=idCamion;
		this.patente = patente;
		this.marca = marca;
		this.modelo = modelo;
		this.kmRecorridos = kmRecorridos;
		this.costoHora = costoHora;
		this.costoKm = costoKm;
		this.fechaDeCompra = fechaDeCompra;
	}


	public Camion() {
		// TODO Auto-generated constructor stub
	}

	

	public Integer getIdCamion() {
		return idCamion;
	}


	public void setIdCamion(Integer idCamion) {
		this.idCamion = idCamion;
	}


	public String getPatente() {
		return patente;
	}


	public void setPatente(String catente) {
		this.patente = catente;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public Double getKmRecorridos() {
		return kmRecorridos;
	}


	public void setKmRecorridos(Double kmRecorridos) {
		this.kmRecorridos = kmRecorridos;
	}


	public Double getCostoHora() {
		return costoHora;
	}


	public void setCostoHora(Double costoHora) {
		this.costoHora = costoHora;
	}


	public Double getCostoKm() {
		return costoKm;
	}


	public void setCostoKm(Double costoKm) {
		this.costoKm = costoKm;
	}


	public Date getFechaDeCompra() {
		return fechaDeCompra;
	}


	public void setFechaDeCompra(Date fechaDeCompra) {
		this.fechaDeCompra = fechaDeCompra;
	}
	
	
} 
