package frsf.isi.died.dominio;



public class Ruta {

	private Integer idRuta;
	private Integer distancia;
	private Integer duracionEstimadaHoras;
	private Integer pesoMaximo;
	
	private Integer IdPlantaOrigen;
	private Integer IdPlantaDestino;

	
	public Ruta(Integer idRuta,Integer idPlantaOrigen,Integer idPlantaDestino, Integer distancia,
			Integer duracionEstimadaHoras, Integer pesoMaximo ) {
		this.idRuta = idRuta;
		this.distancia = distancia;
		this.duracionEstimadaHoras = duracionEstimadaHoras;
		this.pesoMaximo = pesoMaximo;
		IdPlantaOrigen = idPlantaOrigen;
		IdPlantaDestino = idPlantaDestino;
	}
	public Integer getDistancia() {
		return distancia;
	}
	public void setDistancia(Integer distancia) {
		this.distancia = distancia;
	}
	public Integer getduracionEstimadaHoras() {
		return duracionEstimadaHoras;
	}
	public void setduracionEstimadaHoras(Integer duracionEstimadaHoras) {
		this.duracionEstimadaHoras = duracionEstimadaHoras;
	}
	public Integer getPesoMaximo() {
		return pesoMaximo;
	}
	public void setPesoMaximo(Integer pesoMaximo) {
		this.pesoMaximo = pesoMaximo;
	}
	public Integer getIdPlantaOrigen() {
		return IdPlantaOrigen;
	}
	public void setIdPlantaOrigen(Integer IdPlantaOrigen) {
		this.IdPlantaOrigen = IdPlantaOrigen;
	}
	public Integer getIdPlantaDestino() {
		return IdPlantaDestino;
	}
	public void setIdPlantaDestino(Integer IdPlantaDestino) {
		this.IdPlantaDestino = IdPlantaDestino;
	}
	public Integer getIdRuta() {
		return idRuta;
	}
	public void setIdRuta(Integer idRuta) {
		this.idRuta = idRuta;
	}
	
	
	
	
}
