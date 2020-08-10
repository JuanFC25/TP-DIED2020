package frsf.isi.died.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import frsf.isi.died.dao.utils.DB;
import frsf.isi.died.dominio.Insumo;
import frsf.isi.died.dominio.InsumoGeneral;


public class InsumoGeneralDaoPostgreSql implements InsumoGeneralDao{

	
	private static final String SELECT_ALL_INSUMO_GENERAL =
			"SELECT INSUMO.idInsumo,INSUMO.descripcion,INSUMO.undidad,INSUMO.costoXinsumo,GENERAL.peso"
			+ " FROM trabajoPractico.INSUMO, trabajoPractico.GENERAL"
			+ " WHERE INSUMO.idInsumo = GENERAL.idInsumoGeneral";
	private static final String INSERT_INSUMO_GENERAL =
			"INSERT INTO trabajoPractico.INSUMO (IdInsumo,descripcion,undidad,costoXinsumo) VALUES (?,?,?,?);"
			+ " INSERT INTO trabajoPractico.GENERAL (idInsumoGeneral,peso) VALUES (?,?)";
	private static final String UPDATE_INSUMO_GENERAL =
			" UPDATE trabajoPractico.INSUMO SET descripcion = ? , undidad =? , costoXinsumo= =?"
			+ " WHERE IdInsumo = ? ;"
			+ "UPDATE trabajoPractico.GENERAL SET  peso =? "
			+ " WHERE idInsumoGeneral=?";
	private static final String DELETE_INSUMO_GENERAL =
			"DELETE FROM trabajoPractico.INSUMO WHERE idInsumo= ? DELETE ON CASCADE";
	
	private static final String SELECT_ALL_ID_INSUMO =
			"SELECT INSUMO.idInsumo FROM trabajoPractico.INSUMO";
	
	public List<Insumo> buscarTodos() {
		List<Insumo> lista = new ArrayList<Insumo>();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try { 
			pstmt= conn.prepareStatement(SELECT_ALL_INSUMO_GENERAL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				Insumo ig = new InsumoGeneral(
						(rs.getInt("idInsumo")),
						(rs.getString("descripcion")),
						(rs.getString("undidad")),
						(rs.getDouble("costoXinsumo")),
						(rs.getDouble("peso"))
						);

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
	public Insumo save(Insumo ig) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			
			System.out.println("EJECUTA INSERT");
			pstmt= conn.prepareStatement(INSERT_INSUMO_GENERAL);
			
			pstmt.setInt(1,ig.getIdInsumo());
			pstmt.setString(2, ig.getDescripcion());
			pstmt.setString(3, ig.getUnidadDeMedida());
			pstmt.setDouble(4, ig.getCostoUnidadMedida());
			pstmt.setInt(5, ig.getIdInsumo());
			pstmt.setDouble(6, ig.pesoPorUnidad());
			
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
	
		return  ig;
	}



	@Override
	public void update(Insumo ig) {
	
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			System.out.println("EJECUTA UPDATE");
			
			pstmt= conn.prepareStatement(UPDATE_INSUMO_GENERAL);
			
			
			pstmt.setString(1, ig.getDescripcion());
			pstmt.setString(2, ig.getUnidadDeMedida());
			pstmt.setDouble(3, ig.getCostoUnidadMedida());
			pstmt.setInt(4, ig.getIdInsumo());
			pstmt.setDouble(6, ig.pesoPorUnidad());
			pstmt.setInt(1,ig.getIdInsumo());
			
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
			pstmt= conn.prepareStatement(DELETE_INSUMO_GENERAL);
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
	
	@Override
	public List<Integer> obtenerIdsAllInsumos() {
		List<Integer> lista = new ArrayList<Integer>();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try { 

			pstmt= conn.prepareStatement(SELECT_ALL_ID_INSUMO);
			rs = pstmt.executeQuery();
			while(rs.next()) {	

				lista.add(rs.getInt("idInsumo"));

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
	
}

