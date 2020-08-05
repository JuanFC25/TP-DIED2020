package frsf.isi.died.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import frsf.isi.died.dao.utils.DB;
import frsf.isi.died.dominio.Camion;



public class CamionDaoPostgreSql implements CamionDao{

	private static final String SELECT_ALL_CAMION =
			"SELECT idCamion, patente, marca, modelo, kmRecorridos,costoXkm,costoXhora,fechaCompra"
			+ " FROM trabajoPractico.CAMION";
	
	private static final String INSERT_CAMION =
			"INSERT INTO trabajoPractico.CAMION (idCamion,patente,marca,modelo,kmRecorridos,costoXkm,costoXhora,fechaCompra)"
			+ " VALUES (?,?,?,?,?,?,?,?)";
	
	private static final String SELECT_ALL_IDCAMION =
			"SELECT idCamion FROM trabajoPractico.CAMION";
	
	private static final String UPDATE_CAMION =
			" UPDATE trabajoPractico.CAMION SET patente =? ,marca = ? , modelo =?, kmRecorridos =?,"
			+ "  costoXkm =?, costoXhora =?, fechaCompra =?"
			+ " WHERE idCamion = ?";
	
	
	
	public List<Camion> buscarTodos() {
		List<Camion> lista = new ArrayList<Camion>();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt= conn.prepareStatement(SELECT_ALL_CAMION);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Camion c = new Camion();
				c.setIdCamion(rs.getInt("idCamion"));
				c.setPatente(rs.getString("patente"));
				c.setMarca(rs.getString("marca"));
				c.setModelo(rs.getString("modelo"));
				c.setKmRecorridos(rs.getDouble("kmRecorridos"));
				c.setCostoKm(rs.getDouble("costoXkm"));
				c.setCostoHora(rs.getDouble("costoXhora"));
				c.setFechaDeCompra(rs.getDate("fechaCompra"));
				
				lista.add(c);
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

	
	public Camion save(Camion c) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			
			System.out.println("EJECUTA INSERT");
			pstmt= conn.prepareStatement(INSERT_CAMION);
			
			pstmt.setInt(1,c.getIdCamion());
			pstmt.setString(2, c.getPatente());
			pstmt.setString(3, c.getMarca());
			pstmt.setString(4, c.getModelo());
			pstmt.setDouble(5, c.getKmRecorridos());
			pstmt.setDouble(6, c.getCostoHora());
			pstmt.setDouble(7, c.getCostoKm());
			pstmt.setDate(8, ( new Date(c.getFechaDeCompra().getTime()))); 
			
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
	
		return c;
	}

	
	public List<Integer> obtenerIds() {
		List<Integer> lista = new ArrayList<Integer>();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt= conn.prepareStatement(SELECT_ALL_IDCAMION);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				lista.add(rs.getInt("idCamion"));
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
	public Camion buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void borrar(Integer id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Camion update(Camion c) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			System.out.println("EJECUTA UPDATE");
			pstmt= conn.prepareStatement(UPDATE_CAMION);
			
			pstmt.setString(1, c.getPatente());
			pstmt.setString(2, c.getMarca());
			pstmt.setString(3, c.getModelo());
			pstmt.setDouble(4, c.getKmRecorridos());
			pstmt.setDouble(5, c.getCostoHora());
			pstmt.setDouble(6, c.getCostoKm());
			pstmt.setDate(7, ( new Date(c.getFechaDeCompra().getTime())));
			pstmt.setInt(8,c.getIdCamion());
			
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
			
		return c;
	}

	
	
}
