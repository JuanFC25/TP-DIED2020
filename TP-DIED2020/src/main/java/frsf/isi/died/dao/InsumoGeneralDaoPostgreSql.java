package frsf.isi.died.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import frsf.isi.died.dao.utils.DB;
import frsf.isi.died.dominio.InsumoGeneral;
import frsf.isi.died.dominio.Planta;

public class InsumoGeneralDaoPostgreSql implements InsumoGeneralDao{

	
	private static final String SELECT_ALL_PLANTA =
			"SELECT idPlanta,nombrePlanta,direccion,telefono FROM trabajoPractico.PLANTA";
	private static final String INSERT_PLANTA =
			"INSERT INTO trabajoPractico.PLANTA (idPlanta,nombrePlanta,direccion,telefono) VALUES (?,?,?,?)";
	private static final String UPDATE_PLANTA =
			" UPDATE trabajoPractico.PLANTA SET nombrePlanta =? ,direccion = ? , telefono =? "
			+ " WHERE idPlanta = ?";
	private static final String SELECT_ALL_IDPLANTA =
			"SELECT idPlanta FROM trabajoPractico.PLANTA";
	
	public List<InsumoGeneral> buscarTodos() {
		List<InsumoGeneral> lista = new ArrayList<InsumoGeneral>();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try { 
			pstmt= conn.prepareStatement(SELECT_ALL_PLANTA);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				InsumoGeneral ig = new InsumoGeneral(
				(ig.getInt("idPlanta")),
				(ig.getString("nombrePlanta")),
				(ig.getString("direccion")),
				(ig.getInt("telefono")));
				
				lista.add(ig);

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
	public InsumoGeneral save(InsumoGeneral ig) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			
			System.out.println("EJECUTA INSERT");
			pstmt= conn.prepareStatement(INSERT_PLANTA);
			
			pstmt.setInt(1,ig.getIDInsumo());
			pstmt.setString(2, ig.getDescripcion());
			pstmt.setString(3, ig.getUnidadDeMedida());
			pstmt.setInt(4, ig.getCostoUnidadMedida());
			
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
	public InsumoGeneral update(InsumoGeneral p) {
	
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

//
//	@Override
//	public InsumoGeneral save(InsumoGeneral p) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	@Override
//	public InsumoGeneral update(InsumoGeneral p) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	
	
	
}

