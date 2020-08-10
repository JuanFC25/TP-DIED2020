package frsf.isi.died.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import frsf.isi.died.dao.utils.DB;
import frsf.isi.died.dominio.Ruta;
import frsf.isi.died.dominio.Stock;

public class StockDaoPostgreSql implements StockDao{

	private static final String SELECT_ALL_STOCK=
			"SELECT idRegistro,cantidad,puntoDePedido,idInsumoAsociado,idPlantaAsociada"
			+ " FROM trabajoPractico.STOCK "
			+ " WHERE idPlantaAsociada = ?";
	
	private static final String SELECT_ALL_ID_STOCK=
			"SELECT idRegistro"
			+ " FROM trabajoPractico.STOCK ";
	
	private static final String SELECT_MAX_IDSTOCK=
			"SELECT MAX(idStock) FROM trabajoPractico.RUTA";
	
	private static final String INSERT_STOCK=
			"INSERT INTO trabajoPractico.STOCK (idRegistro,cantidad,puntoDePedido,idInsumoAsociado,idPlantaAsociada)"
			+ " VALUES (?,?,?,?,?)";
	
	private static final String DELETE_STOCK =
			"DELETE FROM trabajoPractico.STOCK WHERE idStock = ?";
	
	public List<Stock> buscarStockPlanta(Integer id) {
		List<Stock> lista = new ArrayList<Stock>();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt= conn.prepareStatement(SELECT_ALL_STOCK);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Stock s = new Stock(rs.getInt("cantidad"),
						rs.getInt("idRegistro"),
						rs.getInt("idInsumoAsociado"),
						rs.getInt("puntoDePedido"),
						rs.getInt("idPlantaAsociada"));
						
				
				lista.add(s);

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
		System.out.println("Resultado "+lista);
		return lista;
	}

	@Override
	public List<Integer> obtenerIds() {
		List<Integer> lista = new ArrayList<Integer>();
		Integer id;
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt= conn.prepareStatement(SELECT_ALL_ID_STOCK);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				id=rs.getInt("idRegistro");
				lista.add(id);
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
		System.out.println("Resultado "+lista);
		return lista;
	}

	@Override
	public void save(Stock s) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			
			System.out.println("EJECUTA INSERT");
			pstmt= conn.prepareStatement(INSERT_STOCK);
			
			pstmt.setInt(1, s.getIdRegistroStock());
			pstmt.setInt(2, s.getCantidad());
			pstmt.setInt(3, s.getPuntoDePedido());
			pstmt.setDouble(4, s.getIdInsumoAsociado());
			pstmt.setInt(5,s.getIdPlantaAsociada());
			
			
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
	
	
}
