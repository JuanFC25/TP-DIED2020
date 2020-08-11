package frsf.isi.died.dao.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	private static final String url ="jdbc:postgresql://tpdied.cquiwsbyjbxy.sa-east-1.rds.amazonaws.com/DIED-Joa-Juan-Nico";
	private static final String user="root";
	private static final String pass="trabajopracticodied";
	

	private DB(){	
	}

	private static Connection crearConexion(){
		Connection conn=null;
		try {
			Class.forName("org.postgresql.Driver");
			conn= DriverManager.getConnection(url,user,pass);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return conn;
	}
	
	public static Connection getConexion() {
		return crearConexion();
	}
	
}
