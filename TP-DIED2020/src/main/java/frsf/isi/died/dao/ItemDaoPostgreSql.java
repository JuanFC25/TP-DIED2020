package frsf.isi.died.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import frsf.isi.died.dao.utils.DB;
import frsf.isi.died.dominio.Item;
import frsf.isi.died.dominio.Pedido;
import frsf.isi.died.dominio.Planta;

public class ItemDaoPostgreSql implements ItemDao{
	private static final String SELECT_ALL_ITEM_ID=
			"SELECT idItem,idPedido,idInsumo,cantidad,costoItem"
			+ " FROM trabajoPractico.ITEM "
			+ " WHERE idPedido = ?";
	
	private static final String SELECT_MAX_ID_ITEM=
			"SELECT MAX(idItem) AS max"
			+ " FROM trabajoPractico.ITEM";
	
	private static final String INSERT_ITEM=
			"INSERT INTO trabajoPractico.ITEM (idItem,idPedido,idInsumo,cantidad,costoItem)"
			+ " VALUES (?,?,?,?,?)";
	
	private static final String DELETE_ITEM =
			"DELETE FROM trabajoPractico.ITEM WHERE idItem = ?";
	
	
	@Override
	public void save(Item i) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			
			System.out.println("EJECUTA INSERT");
			pstmt= conn.prepareStatement(INSERT_ITEM);
			
			pstmt.setInt(1, i.getIdItem());
			pstmt.setInt(2, i.getIdPedido());
			pstmt.setInt(3, i.getIdInsumo());
			pstmt.setDouble(4, i.getCantidad());
			pstmt.setDouble(5, i.getCostoItem());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}	
	}


	@Override
	public int obtenerIdMasAlto() {
		Integer id=0;
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			System.out.println("EJECUTA SELECT");
			pstmt= conn.prepareStatement(SELECT_MAX_ID_ITEM);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				id=rs.getInt("max");
			}		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
			
		return id;

	}
	
	public List<Item> obtenerItemsPedido(Integer id){
		
		List<Item> lista = new ArrayList<Item>();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt= conn.prepareStatement(SELECT_ALL_ITEM_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				Item i = new Item(rs.getInt("idItem"),
						rs.getInt("idInsumo"),
						rs.getInt("idPedido"),
						rs.getDouble("cantidad"),
						rs.getDouble("costoItem"));
				
				lista.add(i);

			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}	
		
		return lista;
	}
	
}
