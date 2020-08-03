package frsf.isi.died.dominio;



public class Ruta {

	private Double distancia;
	private Integer duracionEstimadaMinutos;
	private Integer pesoMaximo;
	
	private Planta plantaOrigen;
	private Planta plantaDestino;
	public Double getDistancia() {
		return distancia;
	}
	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}
	public Integer getDuracionEstimadaMinutos() {
		return duracionEstimadaMinutos;
	}
	public void setDuracionEstimadaMinutos(Integer duracionEstimadaMinutos) {
		this.duracionEstimadaMinutos = duracionEstimadaMinutos;
	}
	public Integer getPesoMaximo() {
		return pesoMaximo;
	}
	public void setPesoMaximo(Integer pesoMaximo) {
		this.pesoMaximo = pesoMaximo;
	}
	public Planta getPlantaOrigen() {
		return plantaOrigen;
	}
	public void setPlantaOrigen(Planta plantaOrigen) {
		this.plantaOrigen = plantaOrigen;
	}
	public Planta getPlantaDestino() {
		return plantaDestino;
	}
	public void setPlantaDestino(Planta plantaDestino) {
		this.plantaDestino = plantaDestino;
	}
	
	
}
