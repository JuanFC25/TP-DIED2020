package frsf.isi.died.dominio;

import java.time.LocalDateTime;

public class Camion {

	private String patente;
	private String marca;
	private String modelo;
	private Double kmRecorridos;
	private Double costoHora;
	private Double costoKm;
	private LocalDateTime fechaDeCompra;
	
	
	public Camion(String patente, String marca, String modelo, Double kmRecorridos, Double costoHora, Double costoKm,
			LocalDateTime fechaDeCompra) {
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


	public LocalDateTime getFechaDeCompra() {
		return fechaDeCompra;
	}


	public void setFechaDeCompra(LocalDateTime fechaDeCompra) {
		this.fechaDeCompra = fechaDeCompra;
	}
	
	
} 
