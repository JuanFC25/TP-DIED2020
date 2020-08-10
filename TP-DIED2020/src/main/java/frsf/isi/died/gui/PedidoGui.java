package frsf.isi.died.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import frsf.isi.died.app.App;
import frsf.isi.died.controller.PedidoController;
import frsf.isi.died.dao.PedidoDao;
import frsf.isi.died.dao.PedidoDaoPostgreSql;
import frsf.isi.died.dao.PlantaDao;
import frsf.isi.died.dao.PlantaDaoPostgreSql;
import frsf.isi.died.dominio.Pedido;
import frsf.isi.died.dominio.Planta;
import frsf.isi.died.gui.util.MiModelo;

public class PedidoGui {

	public void pantallaPrincipalPedido(App app,String nombre) {
		
		JPanel panel=new JPanel(new GridBagLayout());
		
		JLabel tituloPedidos=new JLabel("LISTA DE PEDIDOS - " + nombre);
	
		JScrollPane scrollPedidos=new JScrollPane();
		
		JTable tablaPedidos = new JTable();
		scrollPedidos.setViewportView(tablaPedidos);
		
		JButton botonAgregar = new JButton("Agregar Pedido");
		JButton botonCancelar = new JButton("Cancelar Pedido");
		
		
	}
	
	public void agregarPedido(App app, Integer valorId, String nombre,PlantaGui pg) {
		JPanel panel=new JPanel(new GridBagLayout());
		
		JLabel etiquetaIdPedido = new JLabel("ID Pedido:");
		JLabel etiquetaPlantaOrigen = new JLabel("Planta Origen:");
		JLabel etiquetaPlantaDestino = new JLabel("Planta Destino:");
		JLabel etiquetaFechaEntrega = new JLabel("Fecha Entrega:");
		
		JComboBox<Planta> listaPlantasOrigen = new JComboBox<Planta>();
		
		JTextField ingresarIdPedido = new JTextField(30);
		JTextField ingresarPlantaDestino = new JTextField("ID:"+ valorId + "  Nombre:" + nombre);
		ingresarPlantaDestino.setEditable(false);
		JTextField ingresarFechaEntrega = new JTextField(30);
		
		JButton cancelar = new JButton("Cancelar");
		JButton agregar = new JButton("Agregar Pedido");

		PlantaDao pd = new PlantaDaoPostgreSql();
		List<Planta> listaAux = pd.buscarTodos();
		
		for(Planta unaPlanta : listaAux) {
			listaPlantasOrigen.addItem(unaPlanta);
		}
		
		cancelar.addActionListener( e-> {
			pg.pantallaPrincipalPlantas(app);
		});
		
		agregar.addActionListener( e-> {
		
			Date fechaActual=new Date();
			Planta plantaDestino = pd.obtenerPlanta(valorId);
			Planta plantaOrigen = (Planta) listaPlantasOrigen.getSelectedItem();
			String id = ingresarIdPedido.getText();
			String fechaEntrega = ingresarFechaEntrega.getText();
			String estado = "CREADA";
			PedidoController pc = new PedidoController();
			
			pc.agregarPedido(id,plantaOrigen,plantaDestino,fechaActual,fechaEntrega,estado);
		});
		
		
		
		app.gbc.gridx=0;
		app.gbc.gridy=0;
		app.gbc.gridwidth=1;
		app.gbc.gridheight=1;
		panel.add(etiquetaIdPedido,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		panel.add(ingresarIdPedido,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=1;
		app.gbc.gridwidth=1;
		app.gbc.gridheight=1;
		panel.add(etiquetaPlantaOrigen,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		app.gbc.fill=GridBagConstraints.BOTH;
		panel.add(listaPlantasOrigen,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=2;
		app.gbc.gridwidth=1;
		app.gbc.fill=GridBagConstraints.NONE;
		panel.add(etiquetaPlantaDestino,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		app.gbc.fill=GridBagConstraints.BOTH;
		panel.add(ingresarPlantaDestino,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=3;
		app.gbc.gridwidth=1;
		app.gbc.fill=GridBagConstraints.NONE;
		panel.add(etiquetaFechaEntrega,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		panel.add(ingresarFechaEntrega,app.gbc);
		
		
		app.gbc.gridx=2;
		app.gbc.gridy=6;
		app.gbc.gridwidth=1;
		app.gbc.gridheight=1;
		panel.add(cancelar,app.gbc);
		
		app.gbc.gridx=3;
		panel.add(agregar,app.gbc);
		
		
		app.resetGbc();
		app.setContentPane(panel);
		app.revalidate();
		app.repaint();
	}
	
	private JTable dibujarTablaPedidos() {
		
		MiModelo modelo = new MiModelo();	
		
		modelo.addColumn("ID Pedido");
		modelo.addColumn("ID Planta Origen");
		modelo.addColumn("ID Planta Destino");
		modelo.addColumn("Fecha Solicitud");
		modelo.addColumn("Fecha Entrega");
		modelo.addColumn("Estado");
	
		JTable tablaPlantas=new JTable(modelo);
		TableRowSorter<TableModel> ordenador=new TableRowSorter<TableModel>(modelo);
		tablaPlantas.setRowSorter(ordenador);
		
		TableColumnModel modeloColumna = tablaPlantas.getColumnModel();
		modeloColumna.getColumn(0).setPreferredWidth(145);
		modeloColumna.getColumn(1).setPreferredWidth(145);
		modeloColumna.getColumn(2).setPreferredWidth(145);
		modeloColumna.getColumn(3).setPreferredWidth(145);
		modeloColumna.getColumn(4).setPreferredWidth(145);
		modeloColumna.getColumn(5).setPreferredWidth(145);
		
		
		PedidoDao pd = new PedidoDaoPostgreSql();
		
		List<Pedido> pedidos = pd.buscarTodos();
		
		for(Pedido unPedido : pedidos) {
			Object fila[]= new Object [6];
			fila[0]=unPedido.getIdPedido();
			fila[1]=unPedido.getIdPlantaOrigen();
			fila[2]=unPedido.getIdPlantaDestino();
			fila[3]=unPedido.getFechaSolicitud();
			fila[4]=unPedido.getFechaEntrega();
			fila[5]=unPedido.getEstado();
			
			modelo.addRow(fila);
		}
		
	
		return tablaPlantas;
	}




}
