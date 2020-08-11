package frsf.isi.died.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import frsf.isi.died.app.App;
import frsf.isi.died.controller.ItemController;
import frsf.isi.died.controller.PedidoController;
import frsf.isi.died.dao.InsumoDaoPostgreSql;
import frsf.isi.died.dao.InsumoGeneralDao;
import frsf.isi.died.dao.InsumoGeneralDaoPostgreSql;
import frsf.isi.died.dao.InsumoLiquidoDao;
import frsf.isi.died.dao.InsumoLiquidoDaoPostgreSql;
import frsf.isi.died.dao.ItemDao;
import frsf.isi.died.dao.ItemDaoPostgreSql;
import frsf.isi.died.dao.PedidoDao;
import frsf.isi.died.dao.PedidoDaoPostgreSql;
import frsf.isi.died.dao.PlantaDao;
import frsf.isi.died.dao.PlantaDaoPostgreSql;
import frsf.isi.died.dominio.Insumo;
import frsf.isi.died.dominio.InsumoLiquido;
import frsf.isi.died.dominio.Item;
import frsf.isi.died.dominio.Pedido;
import frsf.isi.died.dominio.Planta;
import frsf.isi.died.exceptions.CampoVacioException;
import frsf.isi.died.exceptions.FormatoFechaException;
import frsf.isi.died.exceptions.FormatoNumericoException;
import frsf.isi.died.exceptions.LongitudException;
import frsf.isi.died.gui.util.MiModelo;

public class PedidoGui {
	
	
	Double precio;
	List<Item> listaItems;
	List<Integer> idInsumos;
	Boolean pedidoSeleccionado = false;
	Integer idPedido;
	
	public void pantallaPrincipalPedido(App app) {
		app.activarMenu();
		
		JPanel panel=new JPanel(new GridBagLayout());
		
		JLabel etiquetaPedidos = new JLabel("PEDIDOS");
		
		JScrollPane scrollPedidos=new JScrollPane();
		
		JTable tablaPedidos = dibujarTablaPedidos();
		scrollPedidos.setViewportView(tablaPedidos);
		
		JButton botonModificar = new JButton("Modificar Pedido");
		JButton botonCancelar = new JButton("Cancelar Pedido");
		JButton botonPlantasPosibles = new JButton("Plantas posibles");
		
		tablaPedidos.addMouseListener(new MouseAdapter() 
		   {
		      public void mouseClicked(MouseEvent e) 
		      {
		         int fila = tablaPedidos.rowAtPoint(e.getPoint());
		         int columna = tablaPedidos.columnAtPoint(e.getPoint());
		         if ((fila > -1) && (columna > -1)) {
		        	pedidoSeleccionado=true;
		            idPedido = (Integer) tablaPedidos.getValueAt(fila,0);

		         }
		      }
		   });
		
		botonPlantasPosibles.addActionListener( e-> {
			if(pedidoSeleccionado==false) {
				JOptionPane.showMessageDialog(panel,"Seleccione un pedido", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else {
				pantallaPlantasPosibles(app,idPedido);
			}
		});
		
		
		
		
		
		app.gbc.gridx = 0;
		app.gbc.gridy = 0;
		app.gbc.gridwidth=6;
		app.gbc.gridheight=1;
		app.gbc.fill=GridBagConstraints.NONE;
		panel.add(etiquetaPedidos,app.gbc);
		
		app.gbc.gridx = 0;
		app.gbc.gridy = 1;
		app.gbc.gridwidth=7;
		app.gbc.gridheight=1;
		app.gbc.weightx=0.1;
		app.gbc.fill=GridBagConstraints.HORIZONTAL;
		panel.add(scrollPedidos,app.gbc);
		
		app.gbc.gridx = 0;
		app.gbc.gridy = 2;
		app.gbc.gridwidth=1;
		app.gbc.gridheight=1;
		app.gbc.weightx=0;
		app.gbc.fill=GridBagConstraints.NONE;
		panel.add(botonModificar,app.gbc);
		
		app.gbc.gridx = 1;
		app.gbc.gridy = 2;
		app.gbc.gridwidth=1;
		app.gbc.gridheight=1;
		panel.add(botonCancelar,app.gbc);
		
		app.gbc.gridx = 2;
		app.gbc.gridy = 2;
		app.gbc.gridwidth=1;
		app.gbc.gridheight=1;
		panel.add(botonPlantasPosibles,app.gbc);
		
		
		app.resetGbc();
		app.setContentPane(panel);
		app.revalidate();
		app.repaint();

	}
	
	
	
	




	public void agregarPedido(App app, Integer valorId, String nombre,PlantaGui pg) {
		app.desactivarMenu();
		
		listaItems = new ArrayList<Item>();
		idInsumos = new ArrayList<Integer>();
		
		JPanel panel=new JPanel(new GridBagLayout());
	
		JLabel etiquetaCantidad = new JLabel("Ingrese Cantidad");
		JLabel etiquetaIdPedido = new JLabel("ID Pedido:");
		JLabel etiquetaPlantaDestino = new JLabel("Planta Destino:");
		JLabel etiquetaFechaEntrega = new JLabel("Fecha Entrega:");
		JLabel etiquetaInsumos = new JLabel("Insumos:");
		JLabel etiquetaPrecio = new JLabel("Precio");
		
		JComboBox<Insumo> listaInsumos = new JComboBox<Insumo>();
	
		InsumoGeneralDao igd = new InsumoGeneralDaoPostgreSql();
		InsumoLiquidoDao ild = new InsumoLiquidoDaoPostgreSql();
		
		List<Insumo> listaAux = igd.buscarTodos();
		for(Insumo unInsumo : listaAux) {
			listaInsumos.addItem(unInsumo);
		}
		listaAux = ild.buscarTodos();
		for(Insumo unInsumo : listaAux) {
			listaInsumos.addItem(unInsumo);
		}

		JTextField ingresarCantidad = new JTextField(30);
		JTextField ingresarIdPedido = new JTextField(30);
		JTextField ingresarPlantaDestino = new JTextField("ID:"+ valorId + "  Nombre:" + nombre);
		ingresarPlantaDestino.setEditable(false);
		JTextField ingresarFechaEntrega = new JTextField(30);
		JTextField mostrarPrecio = new JTextField(30);
		
		if(!ingresarCantidad.getText().isBlank()) {
			precio = (((Insumo) listaInsumos.getSelectedItem()).getCostoUnidadMedida()
					* Double.parseDouble(ingresarCantidad.getText()));
		}
		
		mostrarPrecio.addActionListener( e -> {
			mostrarPrecio.setText("");
		});
		
		
		JButton añadirItem = new JButton("Añadir Item"); 
		JButton botonCalcularPrecio = new JButton("Calcular Precio"); 
		JButton cancelar = new JButton("Cancelar");
		JButton agregar = new JButton("Agregar Pedido");

		
		botonCalcularPrecio.addActionListener( e-> {
			
			if(!ingresarCantidad.getText().isBlank()) {
				
				precio= (((Insumo) listaInsumos.getSelectedItem()).getCostoUnidadMedida()
						* Double.parseDouble(ingresarCantidad.getText()));
				mostrarPrecio.setText(precio.toString());
			}
			else {
				mostrarPrecio.setText(precio.toString());
			}
		});
		
		
		
		cancelar.addActionListener( e-> {
			pg.pantallaPrincipalPlantas(app);
		});
		
		
		añadirItem.addActionListener( e-> {
			
			Insumo ins =(Insumo) listaInsumos.getSelectedItem();
			
			if(idInsumos.contains(ins.getIdInsumo())){
				JOptionPane.showMessageDialog(panel,"Este insumo ya fue agregado al pedido", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else {
				Item i = new Item(
						((Insumo)listaInsumos.getSelectedItem()).getIdInsumo(),
						Double.parseDouble(ingresarCantidad.getText()),
						(ins.getCostoUnidadMedida() * Double.parseDouble(ingresarCantidad.getText()))
						);
				
				idInsumos.add(ins.getIdInsumo());
				listaItems.add(i);
				JOptionPane.showMessageDialog(panel,"Item agregado correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				mostrarPrecio.setText("");
			}
			

		});
		
		agregar.addActionListener( e-> {
		
			Date fechaActual=new Date();
			Integer idPlantaDestino = valorId;
			String id = ingresarIdPedido.getText();
			String fechaEntrega = ingresarFechaEntrega.getText();
			String estado = "CREADA";
			PedidoController pc = new PedidoController();
			ItemController ic = new ItemController();
			Integer maxIdItem = ic.obtenerIdMasAlto() + 1;
			try {
				pc.agregarPedido(id,idPlantaDestino,fechaActual,fechaEntrega,estado);

				
				for(Item unItem : listaItems) {
					ic.agregarItem(maxIdItem,unItem,Integer.parseInt(id));
					maxIdItem++;
				}
				JOptionPane.showMessageDialog(panel,"El pedido fue agregado correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				pg.pantallaPrincipalPlantas(app);
			} catch (FormatoNumericoException | LongitudException | CampoVacioException | FormatoFechaException e1) {
				JOptionPane.showMessageDialog(panel,e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		
		
		app.gbc.gridx=0;
		app.gbc.gridy=0;
		app.gbc.gridwidth=1;
		app.gbc.gridheight=1;
		panel.add(etiquetaIdPedido,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		app.gbc.fill=GridBagConstraints.BOTH;
		panel.add(ingresarIdPedido,app.gbc);
		
		
		app.gbc.gridx=0;
		app.gbc.gridy=1;
		app.gbc.gridwidth=1;
		app.gbc.fill=GridBagConstraints.NONE;
		panel.add(etiquetaPlantaDestino,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		app.gbc.fill=GridBagConstraints.BOTH;
		panel.add(ingresarPlantaDestino,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=2;
		app.gbc.gridwidth=1;
		app.gbc.fill=GridBagConstraints.NONE;
		panel.add(etiquetaFechaEntrega,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		app.gbc.fill=GridBagConstraints.BOTH;
		panel.add(ingresarFechaEntrega,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=3;
		app.gbc.gridwidth=1;
		app.gbc.fill=GridBagConstraints.NONE;
		panel.add(etiquetaInsumos,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		app.gbc.fill=GridBagConstraints.BOTH;
		panel.add(listaInsumos,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=4;
		app.gbc.gridwidth=1;
		app.gbc.fill=GridBagConstraints.NONE;
		panel.add(etiquetaCantidad,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		app.gbc.fill=GridBagConstraints.BOTH;
		panel.add(ingresarCantidad,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=5;
		app.gbc.gridwidth=1;
		app.gbc.fill=GridBagConstraints.NONE;
		panel.add(etiquetaPrecio,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		app.gbc.fill=GridBagConstraints.BOTH;
		panel.add(mostrarPrecio,app.gbc);
		
		
		app.gbc.gridx=2;
		app.gbc.gridy=6;
		app.gbc.gridwidth=1;
		app.gbc.fill=GridBagConstraints.BOTH;
		panel.add(botonCalcularPrecio,app.gbc);
		
		app.gbc.gridx=3;
		app.gbc.gridwidth=3;
		panel.add(añadirItem,app.gbc);
		
		app.gbc.gridx=2;
		app.gbc.gridy=7;
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
	
	

	private void pantallaPlantasPosibles(App app, Integer idPedido) {
		app.desactivarMenu();
		
		JPanel panel=new JPanel(new GridBagLayout());
		ItemDao id = new ItemDaoPostgreSql();
		
		List<Item> listaItems = id.obtenerItemsPedido(idPedido);
		
		PedidoController pc = new PedidoController();
		
		List<Planta> listaPlantasConStock = pc.obtenerPlantasConStock(listaItems);
		
		JComboBox<Planta> listaPlantas = new JComboBox<Planta>();
		
		for(Planta unaPlanta : listaPlantasConStock ) {
			listaPlantas.addItem(unaPlanta);
		}
		
		app.gbc.gridx=3;
		app.gbc.gridwidth=3;
		panel.add(listaPlantas,app.gbc);
		
		
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
	
		JTable tablaPedidos=new JTable(modelo);
		TableRowSorter<TableModel> ordenador=new TableRowSorter<TableModel>(modelo);
		tablaPedidos.setRowSorter(ordenador);
		
		TableColumnModel modeloColumna = tablaPedidos.getColumnModel();
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
			if(unPedido.getIdPlantaDestino() == 0) fila[2]="No tiene";
			else fila[2]=unPedido.getIdPlantaDestino();
			fila[3]=unPedido.getFechaSolicitud();
			fila[4]=unPedido.getFechaEntrega();
			fila[5]=unPedido.getEstado();
			
			modelo.addRow(fila);
		}
		
	
		return tablaPedidos;
	}



}
