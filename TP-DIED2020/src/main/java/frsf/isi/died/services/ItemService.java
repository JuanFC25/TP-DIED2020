package frsf.isi.died.services;

import frsf.isi.died.dao.ItemDao;
import frsf.isi.died.dao.ItemDaoPostgreSql;
import frsf.isi.died.dominio.Item;

public class ItemService {

	public void agregarItem(Item unItem) {
		
		ItemDao id = new ItemDaoPostgreSql();
		id.save(unItem);
	}

	public int obtenerIdMasAlto() {
		ItemDao id = new ItemDaoPostgreSql();
		
		return id.obtenerIdMasAlto();
		
	}
	
}
