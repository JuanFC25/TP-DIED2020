package frsf.isi.died.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import frsf.isi.died.dao.utils.DB;
import frsf.isi.died.dominio.Camion;


public class CamionDaoPostgreSql implements CamionDao{

	private static final String SELECT_ALL_CAMION =
			"SELECT ID,PATENTE,MARCA,MODELO,KM FROM CAMION";
	
	
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
//				c.setId(rs.getInt("ID"));
//				c.setMarca(rs.getString("MARCA"));
//				c.setModelo(rs.getString("MODELO"));
//				c.setPatente(rs.getString("PATENTE"));
//				c.setKm(rs.getInt("KM"));
//				lista.add(c);
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
	public Camion saveOrUpdate(Camion c) {
		// TODO Auto-generated method stub
		return null;
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
	
	
}
