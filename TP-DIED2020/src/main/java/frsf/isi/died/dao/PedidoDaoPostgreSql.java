package frsf.isi.died.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import frsf.isi.died.dao.utils.DB;
import frsf.isi.died.dominio.Pedido;

public class PedidoDaoPostgreSql implements PedidoDao{
	
	private static final String SELECT_ALL_PEDIDO =
			"SELECT idPedido, idPlantaOrigen, idPlantaDestino, fechaSolicitud, fechaEntrega, estado"
			+ " FROM trabajoPractico.PEDIDO";
	
	private static final String INSERT_PEDIDO =
			"INSERT INTO trabajoPractico.PEDIDO (idPedido,idPlantaOrigen,idPlantaDestino,fechaSolicitud,fechaEntrega,estado)"
			+ " VALUES (?,?,?,?,?,?)";
	
	
	private static final String UPDATE_PEDIDO =
			" UPDATE trabajoPractico.PEDIDO SET idPlantaOrigen =? ,idPlantaDestino = ? , fechaSolicitud =?, fechaEntrega =?,"
			+ "  estado =?"
			+ " WHERE idPedido = ?";
	
	@Override
	public List<Pedido> buscarTodos() {
		List<Pedido> lista = new ArrayList<Pedido>();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt= conn.prepareStatement(SELECT_ALL_PEDIDO);
			rs = pstmt.executeQuery();
			while(rs.next()) {	
				Pedido p = new Pedido(
						(rs.getInt("idPedido")),
						(rs.getInt("idPlantaOrigen")),
						(rs.getInt("idPlantaDestino")),
						(rs.getDate("fechaSolicitud")),
						(rs.getDate("fechaEntrega")),
						(rs.getString("estado"))
						);

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
	public void save(Pedido p) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			
			System.out.println("EJECUTA INSERT");
			pstmt= conn.prepareStatement(INSERT_PEDIDO);
			
			pstmt.setInt(1, p.getIdPedido());
			pstmt.setNull(2, Types.INTEGER);
			pstmt.setInt(3, p.getIdPlantaDestino());
			pstmt.setDate(4,new Date(p.getFechaSolicitud().getTime()));
			pstmt.setDate(5, new Date(p.getFechaEntrega().getTime()));
			pstmt.setString(6, p.getEstado());
			
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
	public void update(Pedido p) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			System.out.println("EJECUTA UPDATE");
			pstmt= conn.prepareStatement(UPDATE_PEDIDO);
			
			pstmt.setInt(1, p.getIdPlantaOrigen());
			pstmt.setInt(2, p.getIdPlantaDestino());
			pstmt.setDate(3,new Date(p.getFechaSolicitud().getTime()));
			pstmt.setDate(4, new Date(p.getFechaEntrega().getTime()));
			pstmt.setString(5, p.getEstado());
			pstmt.setInt(6, p.getIdPedido());
			
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
