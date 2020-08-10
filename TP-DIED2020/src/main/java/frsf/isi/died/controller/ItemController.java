package frsf.isi.died.controller;

import java.awt.ItemSelectable;

import frsf.isi.died.dominio.Item;
import frsf.isi.died.services.ItemService;

public class ItemController {

	public void agregarItem(Integer maxIdItem, Item unItem,Integer idPedido) {
		unItem.setIdItem(maxIdItem);
		unItem.setIdPedido(idPedido);
		ItemService is = new ItemService();
		
		is.agregarItem(unItem);
	}

	public int obtenerIdMasAlto() {

		ItemService is = new ItemService();
		return is.obtenerIdMasAlto();
	}

}
