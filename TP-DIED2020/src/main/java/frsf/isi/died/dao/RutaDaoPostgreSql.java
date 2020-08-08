package frsf.isi.died.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import frsf.isi.died.dao.utils.DB;
import frsf.isi.died.dominio.Ruta;

public class RutaDaoPostgreSql implements RutaDao{

	private static final String SELECT_ALL_RUTA=
			"SELECT idRuta,idPlantaOrigen,idPlantaDestino,distanciaEnKm,duracionEnMin,cantMaxPermitidaEnKilos"
			+ " FROM trabajoPractico.RUTA";
	
	private static final String SELECT_ALL_IDRUTA=
			"SELECT idRuta FROM trabajoPractico.RUTA";
	
	private static final String INSERT_RUTA=
			"INSERT INTO trabajoPractico.RUTA (idRuta,idPlantaOrigen,idPlantaDestino,distanciaEnKm,duracionEnMin,cantMaxPermitidaEnKilos)"
			+ " VALUES (?,?,?,?,?,?)";
	
	private static final String DELETE_RUTA =
			"DELETE FROM trabajoPractico.RUTA WHERE idRuta = ?";
	
	
	@Override
	public List<Ruta> buscarTodos() {
		List<Ruta> lista = new ArrayList<Ruta>();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt= conn.prepareStatement(SELECT_ALL_RUTA);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				Ruta r = new Ruta(
				(rs.getInt("idRuta")),
				(rs.getInt("idPlantaOrigen")),
				(rs.getInt("idPlantaDestino")),
				(rs.getDouble("distanciaEnKm")),
				(rs.getInt("duracionEnMin")),
				(rs.getInt("cantMaxPermitidaEnKilos")));
				
				lista.add(r);

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
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt= conn.prepareStatement(SELECT_ALL_IDRUTA);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				lista.add(rs.getInt("idRuta"));
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
	public void save(Ruta r) {

		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			
			System.out.println("EJECUTA INSERT");
			pstmt= conn.prepareStatement(INSERT_RUTA);
			
			pstmt.setInt(1,r.getIdRuta());
			pstmt.setInt(2, r.getIdPlantaOrigen());
			pstmt.setInt(3, r.getIdPlantaDestino());
			pstmt.setDouble(4, r.getDistancia());
			pstmt.setInt(5, r.getduracionEstimadaHoras());
			pstmt.setInt(6, r.getPesoMaximo());
			
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

	public void delete(Integer id) {
		
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			
			System.out.println("EJECUTA DELETE");
			pstmt= conn.prepareStatement(DELETE_RUTA);
			pstmt.setInt(1,id);
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
