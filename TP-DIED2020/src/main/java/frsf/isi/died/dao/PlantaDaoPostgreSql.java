package frsf.isi.died.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import frsf.isi.died.dao.utils.DB;
import frsf.isi.died.dominio.Planta;

public class PlantaDaoPostgreSql implements PlantaDao{

	
	private static final String SELECT_ALL_PLANTA =
			"SELECT idPlanta,nombrePlanta,direccion,telefono FROM trabajoPractico.PLANTA";
	private static final String INSERT_PLANTA =
			"INSERT INTO trabajoPractico.PLANTA (idPlanta,nombrePlanta,direccion,telefono) VALUES (?,?,?,?)";
	private static final String UPDATE_PLANTA =
			" UPDATE trabajoPractico.PLANTA SET nombrePlanta =? ,direccion = ? , telefono =? "
			+ " WHERE idPlanta = ?";
	private static final String SELECT_ALL_IDPLANTA =
			"SELECT idPlanta FROM trabajoPractico.PLANTA";
	
	private static final String SELECT_PLANTA =
			"SELECT idPlanta,nombrePlanta,direccion,telefono FROM trabajoPractico.PLANTA"
			+ " WHERE idPlanta = ?";
	
	private static final String DELETE_PLANTA =
			"DELETE FROM trabajoPractico.PLANTA WHERE idPlanta = ?";
	
	public List<Planta> buscarTodos() {
		List<Planta> lista = new ArrayList<Planta>();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt= conn.prepareStatement(SELECT_ALL_PLANTA);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				Planta p = new Planta(
				(rs.getInt("idPlanta")),
				(rs.getString("nombrePlanta")),
				(rs.getString("direccion")),
				(rs.getInt("telefono")));
				
				lista.add(p);

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
	public Planta save(Planta p) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			
			System.out.println("EJECUTA INSERT");
			pstmt= conn.prepareStatement(INSERT_PLANTA);
			
			pstmt.setInt(1,p.getIdPlanta());
			pstmt.setString(2, p.getNombrePlanta());
			pstmt.setString(3, p.getDireccion());
			pstmt.setInt(4, p.getTelefono());
			
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
	
		return p;
	}


	@Override
	public Planta update(Planta p) {
	
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			System.out.println("EJECUTA UPDATE");
			pstmt= conn.prepareStatement(UPDATE_PLANTA);
			pstmt.setString(1, p.getNombrePlanta());
			pstmt.setString(2, p.getDireccion());
			pstmt.setInt(3, p.getTelefono());
			pstmt.setInt(4, p.getIdPlanta());
	
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
			
		return p;
	}


	@Override
	public List<Integer> obtenerIds() {
		List<Integer> lista = new ArrayList<Integer>();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt= conn.prepareStatement(SELECT_ALL_IDPLANTA);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				lista.add(rs.getInt("idPlanta"));
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
	public Planta obtenerPlanta(Integer id) {
		
		Planta p = new Planta();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			System.out.println("EJECUTA SELECT PLANTA");
			pstmt= conn.prepareStatement(SELECT_PLANTA);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				p.setIdPlanta((rs.getInt("idPlanta")));
				p.setNombrePlanta((rs.getString("nombrePlanta")));
				p.setDireccion((rs.getString("direccion")));
				p.setTelefono((rs.getInt("telefono")));
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
			
		return p;

	}
	
	
}
