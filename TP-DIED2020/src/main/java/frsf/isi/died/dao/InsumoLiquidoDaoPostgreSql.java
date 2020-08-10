package frsf.isi.died.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import frsf.isi.died.dao.utils.DB;
import frsf.isi.died.dominio.Insumo;

import frsf.isi.died.dominio.InsumoLiquido;

public class InsumoLiquidoDaoPostgreSql implements InsumoLiquidoDao{

	private static final String SELECT_ALL_INSUMO_LIQUIDO =
			"SELECT INSUMO.idInsumo,INSUMO.descripcion,INSUMO.undidad,INSUMO.costoXinsumo,LIQUIDO.densidad"
			+ " FROM trabajoPractico.INSUMO, trabajoPractico.LIQUIDO"
			+ " WHERE INSUMO.idInsumo = LIQUIDO.idInsumoLiquido";
	private static final String INSERT_INSUMO_LIQUIDO  =
			"INSERT INTO trabajoPractico.INSUMO (IdInsumo,descripcion,undidad,costoXinsumo) VALUES (?,?,?,?);"
			+ " INSERT INTO trabajoPractico.LIQUIDO (idInsumoLiquido,densidad) VALUES (?,?)";
	private static final String UPDATE_INSUMO_LIQUIDO  =
			" UPDATE trabajoPractico.INSUMO SET descripcion = ? , undidad =? , costoXinsumo= =?"
			+ " WHERE IdInsumo = ? ;"
			+ "UPDATE trabajoPractico.LIQUIDO SET  densidad =? "
			+ " WHERE idInsumoLiquido=?";
	private static final String DELETE_INSUMO_LIQUIDO  =
			"DELETE FROM trabajoPractico.INSUMO WHERE idInsumo= ? DELETE ON CASCADE";
	private static final String SELECT_ALL_ID_INSUMO =
			"SELECT INSUMO.idInsumo FROM trabajoPractico.INSUMO";
	
	
	
	
	@Override
	public List<Insumo> buscarTodos() {
		List<Insumo> lista = new ArrayList<Insumo>();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try { 

			pstmt= conn.prepareStatement(SELECT_ALL_INSUMO_LIQUIDO);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				Insumo il = new InsumoLiquido(
						(rs.getInt("idInsumo")),
						(rs.getString("descripcion")),
						(rs.getString("undidad")),
						(rs.getDouble("costoXinsumo")),
						(rs.getDouble("densidad"))
						);

				lista.add(il);

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

	public Insumo save(Insumo il) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			
			System.out.println("EJECUTA INSERT");

			pstmt= conn.prepareStatement(INSERT_INSUMO_LIQUIDO);

			
			pstmt.setInt(1,il.getIdInsumo());
			pstmt.setString(2, il.getDescripcion());
			pstmt.setString(3, il.getUnidadDeMedida());
			pstmt.setDouble(4, il.getCostoUnidadMedida());
			pstmt.setInt(5, il.getIdInsumo());
			pstmt.setDouble(6, il.getDensidad());
			
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
	
		return  il;
	}


	@Override
	public void update(Insumo il) {
	
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			System.out.println("EJECUTA UPDATE");
			
			pstmt= conn.prepareStatement(UPDATE_INSUMO_LIQUIDO);
			
			
			pstmt.setString(1, il.getDescripcion());
			pstmt.setString(2, il.getUnidadDeMedida());
			pstmt.setDouble(3, il.getCostoUnidadMedida());
			pstmt.setInt(4, il.getIdInsumo());
			pstmt.setDouble(6, il.getDensidad());
			pstmt.setInt(1,il.getIdInsumo());
			
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
	public void delete(Integer id) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			
			System.out.println("EJECUTA DELETE");
			pstmt= conn.prepareStatement(DELETE_INSUMO_LIQUIDO);
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

