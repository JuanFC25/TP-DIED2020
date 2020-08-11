package frsf.isi.died.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import frsf.isi.died.app.App;
import frsf.isi.died.controller.InsumoLiquidoController;
import frsf.isi.died.controller.StockController;
import frsf.isi.died.dao.InsumoGeneralDao;
import frsf.isi.died.dao.InsumoGeneralDaoPostgreSql;
import frsf.isi.died.dao.InsumoLiquidoDao;
import frsf.isi.died.dao.InsumoLiquidoDaoPostgreSql;
import frsf.isi.died.dao.PlantaDao;
import frsf.isi.died.dao.PlantaDaoPostgreSql;
import frsf.isi.died.dao.RutaDao;
import frsf.isi.died.dao.RutaDaoPostgreSql;
import frsf.isi.died.dao.StockDao;
import frsf.isi.died.dao.StockDaoPostgreSql;
import frsf.isi.died.dominio.Insumo;
import frsf.isi.died.dominio.Planta;
import frsf.isi.died.dominio.Ruta;
import frsf.isi.died.dominio.Stock;
import frsf.isi.died.exceptions.CampoVacioException;
import frsf.isi.died.exceptions.FormatoNumericoException;
import frsf.isi.died.exceptions.IdUtilizadoException;
import frsf.isi.died.exceptions.LongitudException;
import frsf.isi.died.gui.util.MiModelo;

public class StockGui {

	Boolean stockSeleccionado=false;
	 Integer idRegistroStock;
	 Integer idInsumoAsociado;
	 Integer cantidad;
	 Integer puntoDePedido;
	
	
	public void pantallaPrincipalStock(App app,Integer idPlanta) {
		app.activarMenu();
		
		JPanel panel=new JPanel(new GridBagLayout());
		
		JScrollPane scrollStock=new JScrollPane();
		
		JLabel tituloPlantas=new JLabel("LISTA DE Stock - Empresa x");
		JLabel filtrar = new JLabel("Filtar:");
		
		JTextField campoTexto = new JTextField(15);
		
		JButton botonBuscar = new JButton ("Buscar");
		JButton botonAgregarStock = new JButton("Agregar Stock"); //esta
		JButton botonEliminarStock = new JButton("Eliminar Stock"); 
		JButton botonEditar = new JButton("Editar Stock"); //esta

		
		JTable tablaStock=this.dibujarTablaStock(idPlanta);
		
		tablaStock.addMouseListener(new MouseAdapter() 
		   {
		      public void mouseClicked(MouseEvent e) 
		      {
		         int fila = tablaStock.rowAtPoint(e.getPoint());
		         int columna = tablaStock.columnAtPoint(e.getPoint());
		         if ((fila > -1) && (columna > -1)) {
		        	stockSeleccionado=true;
		        	idRegistroStock = (Integer) tablaStock.getValueAt(fila,0);
		       	    idInsumoAsociado= (Integer) tablaStock.getValueAt(fila,1);
		       	    cantidad= (Integer) tablaStock.getValueAt(fila,3);
		       	    puntoDePedido= (Integer) tablaStock.getValueAt(fila,4);
		         }
		      }
		   });
	
		botonAgregarStock.addActionListener( e-> {
			pantallaAgregarStock(app,idPlanta);
		});
		
		botonEditar.addActionListener( e-> {
			if(stockSeleccionado==false) {
				JOptionPane.showMessageDialog(panel,"Seleccione un stock", "Error", JOptionPane.ERROR_MESSAGE);	
			}
			else {
//				pantallaModificarStock(app,valorId,nombre,direccion,telefono);
			}
		});
		
		app.gbc.gridx = 0;
		app.gbc.gridy = 0;
		app.gbc.gridwidth=6;
		app.gbc.gridheight=1;
		panel.add(tituloPlantas,app.gbc);
		
		app.gbc.gridx = 0;
		app.gbc.gridy = 1;
		app.gbc.gridwidth=1;
		app.gbc.gridheight=1;
		panel.add(filtrar,app.gbc);
		
		app.gbc.gridx = 1;
		app.gbc.gridy = 1;
		app.gbc.gridwidth=2;
		app.gbc.gridheight=1;
		panel.add(campoTexto,app.gbc);
		
		app.gbc.gridx = 4;
		app.gbc.gridy = 1;
		app.gbc.gridwidth=1;
		app.gbc.gridheight=1;
		panel.add(botonBuscar,app.gbc);
			
		app.gbc.gridx = 0;
		app.gbc.gridy = 2;
		app.gbc.gridwidth=7;
		app.gbc.gridheight=1;
		app.gbc.weightx=0.1;
		app.gbc.fill=GridBagConstraints.HORIZONTAL;
		scrollStock.setViewportView(tablaStock);
		panel.add(scrollStock,app.gbc);
		app.gbc.weightx=0;

	
		app.gbc.gridx=0;
		app.gbc.gridy = 4;
		app.gbc.gridwidth=1;
		app.gbc.gridheight=1;
		app.gbc.fill=GridBagConstraints.NONE;
		app.gbc.anchor=GridBagConstraints.FIRST_LINE_END;
		
		panel.add(botonAgregarStock,app.gbc);
		
		app.gbc.gridx = 1;
		app.gbc.gridy = 4;
		app.gbc.gridwidth = 1;
		app.gbc.gridheight = 1;
		app.gbc.fill=GridBagConstraints.NONE;
		panel.add(botonEditar,app.gbc);
		
		app.gbc.gridx=2;
		app.gbc.gridy = 4;
		app.gbc.gridwidth=1;
		app.gbc.gridheight=1;
		app.gbc.fill=GridBagConstraints.NONE;
		panel.add(botonEliminarStock,app.gbc);

		app.resetGbc();
		app.setVerPlantasFalse();
		app.setVerCamionesTrue();
		app.setContentPane(panel);
		app.revalidate();
		app.repaint();
	}
	
//	Integer idRegistroStock;
//	 Integer idInsumoAsociado;
//	 Integer cantidad;
//	 Integer puntoDePedido;
	public void pantallaModificarStock(App app,Integer valorId, Integer IdInsumo, Integer cant, Integer puntoP, Integer idPlantaAsociada) {
		app.desactivarMenu();
		
		JPanel panel=new JPanel(new GridBagLayout());
		JLabel etiquetaId = new JLabel("ID: ");
		//JLabel etiquetaDescripcion=new JLabel("ID Registro: ");
		JLabel etiquetaIdInsumo=new JLabel("ID Insumo: ");
		JLabel etiquetaCantidad=new JLabel("Cantidad: ");
		JLabel etiquetaPuntoPedido=new JLabel("Punto Pedido: ");
		JTextField ingresarId = new JTextField(valorId.toString());
		ingresarId.setEditable(false);
		//JTextField ingresarIdRegistro=new JTextField(idRegistroStock);
		JTextField ingresarIdInsumo = new JTextField(IdInsumo);
		JTextField ingresarCantidad = new JTextField(cant);
		JTextField ingresarPuntoP = new JTextField(puntoP);
		JButton cancelar = new JButton("Cancelar");
		JButton agregar = new JButton("Modificar");
		
		
		
		cancelar.addActionListener( e -> {
			this.pantallaPrincipalStock(app, idPlantaAsociada);
		});
		
		agregar.addActionListener( e -> {
	
			
			String idInsumo = ingresarIdInsumo.getText();
			String iCantidad = ingresarCantidad.getText();
			String iPuntoPedido= ingresarPuntoP.getText();
			
			StockController sc = new StockController();
			sc.modificarStock(valorId.toString(), idInsumo, iCantidad, iPuntoPedido, idPlantaAsociada);
			JOptionPane.showMessageDialog(panel,"El stock fue modificado correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			this.pantallaPrincipalStock(app, idPlantaAsociada);
			
			this.pantallaPrincipalStock(app, idPlantaAsociada);
		});
		
		
		
		app.gbc.gridx=0;
		app.gbc.gridy=0;
		app.gbc.gridwidth=1;
		app.gbc.gridheight=1;
		panel.add(etiquetaId,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		app.gbc.fill=GridBagConstraints.HORIZONTAL;
		panel.add(ingresarId,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=1;
		app.gbc.gridwidth=1;
		app.gbc.gridheight=1;
		app.gbc.fill=GridBagConstraints.NONE;
		panel.add(etiquetaIdInsumo,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		app.gbc.fill=GridBagConstraints.HORIZONTAL;
		panel.add(ingresarIdInsumo,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=2;
		app.gbc.gridwidth=1;
		app.gbc.fill=GridBagConstraints.NONE;
		panel.add(etiquetaCantidad,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		app.gbc.fill=GridBagConstraints.HORIZONTAL;
		panel.add(ingresarCantidad,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=3;
		app.gbc.gridwidth=1;
		app.gbc.fill=GridBagConstraints.NONE;
		panel.add(etiquetaPuntoPedido,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		app.gbc.fill=GridBagConstraints.HORIZONTAL;
		panel.add(ingresarPuntoP,app.gbc);
		
		app.gbc.gridx=2;
		app.gbc.gridy=5;
		app.gbc.gridwidth=1;
		app.gbc.gridheight=1;
		app.gbc.fill=GridBagConstraints.NONE;
		panel.add(cancelar,app.gbc);
		
		app.gbc.gridx=3;
		panel.add(agregar,app.gbc);
		
		
		app.setContentPane(panel);
		app.revalidate();
		app.repaint();
		
	}
	
	private void pantallaAgregarStock(App app,Integer idPlanta) {
		
		JPanel panel = new JPanel(new GridBagLayout());
		
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
		
		JLabel etiquetaIDstock = new JLabel("ID registro:");
		JLabel etiquetaCantidad = new JLabel("Cantidad:");
		JLabel etiquetaPuntoPedido = new JLabel("Punto de pedido:");
		JLabel etiquetaInsumoAsociado = new JLabel("Insumo:");
		JLabel etiquetaPlantaAsociada = new JLabel("ID Planta:");
		
		JTextField ingresarIDstock = new JTextField(30);
		JTextField ingresarCantidad = new JTextField(30);
		JTextField ingresarPuntoPedido = new JTextField(30);
		JTextField ingresarPlantaAsociada = new JTextField(idPlanta.toString());
		ingresarPlantaAsociada.setEnabled(false);
		
		JButton botonAgregar=new JButton("Agregar");
		JButton botonCancelar= new JButton("Cancelar");
		
		botonCancelar.addActionListener( e-> {
			pantallaPrincipalStock(app, idPlanta);
		});
		
		botonAgregar.addActionListener(e -> {
			StockController sc = new StockController();
			
			Insumo i= (Insumo) listaInsumos.getSelectedItem();
		
			try {
				sc.agregarStock(ingresarIDstock.getText(),
						idPlanta,i,ingresarCantidad.getText(),
						ingresarPuntoPedido.getText()
						);
				JOptionPane.showMessageDialog(panel,"El stock fue agregado correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				pantallaPrincipalStock(app, idPlanta);
			} catch (IdUtilizadoException e1) {
				JOptionPane.showMessageDialog(panel,e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		});
		
		app.gbc.gridx=0;
		app.gbc.gridy=0;
		app.gbc.gridwidth=1;
		app.gbc.gridheight=1;
		panel.add(etiquetaIDstock,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		app.gbc.fill=GridBagConstraints.BOTH;
		panel.add(ingresarIDstock,app.gbc);
		
		
		app.gbc.gridx=0;
		app.gbc.gridy=1;
		app.gbc.gridwidth=1;
		app.gbc.fill=GridBagConstraints.NONE;
		panel.add(etiquetaPlantaAsociada,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		app.gbc.fill=GridBagConstraints.BOTH;
		panel.add(ingresarPlantaAsociada,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=2;
		app.gbc.gridwidth=1;
		app.gbc.fill=GridBagConstraints.NONE;
		panel.add(etiquetaInsumoAsociado,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		app.gbc.fill=GridBagConstraints.BOTH;
		panel.add(listaInsumos,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=3;
		app.gbc.gridwidth=1;
		app.gbc.fill=GridBagConstraints.NONE;
		panel.add(etiquetaCantidad,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		app.gbc.fill=GridBagConstraints.BOTH;
		panel.add(ingresarCantidad,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=4;
		app.gbc.gridwidth=1;
		app.gbc.fill=GridBagConstraints.NONE;
		panel.add(etiquetaPuntoPedido,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		app.gbc.fill=GridBagConstraints.BOTH;
		panel.add(ingresarPuntoPedido,app.gbc);

		app.gbc.gridx=2;
		app.gbc.gridy=7;
		app.gbc.gridwidth=1;
		app.gbc.gridheight=1;
		panel.add(botonCancelar,app.gbc);
		
		app.gbc.gridx=3;
		panel.add(botonAgregar,app.gbc);
		
		app.resetGbc();
		app.setContentPane(panel);
		app.revalidate();
		app.repaint();
		

		
	}
	
	private JTable dibujarTablaStock(Integer idPlanta) {
		

		MiModelo modelo = new MiModelo();	
		
		modelo.addColumn("ID Stock");
		modelo.addColumn("ID Insumo");
		modelo.addColumn("Cantidad");
		modelo.addColumn("Punto de Pedido");
	
	
		JTable tablaPlantas=new JTable(modelo);
		TableRowSorter<TableModel> ordenador=new TableRowSorter<TableModel>(modelo);
		tablaPlantas.setRowSorter(ordenador);
		
		TableColumnModel modeloColumna = tablaPlantas.getColumnModel();
		modeloColumna.getColumn(0).setPreferredWidth(166);
		modeloColumna.getColumn(1).setPreferredWidth(166);
		modeloColumna.getColumn(2).setPreferredWidth(166);
		modeloColumna.getColumn(3).setPreferredWidth(166);
		
		
		StockDao sd = new StockDaoPostgreSql();
		//PlantaDao pd =new PlantaDaoPostgreSql();
		
		
		List<Stock> stock = sd.buscarStockPlanta(idPlanta);
		
		for(Stock unStock : stock) {
			Object fila[]= new Object [6];
			fila[0]=unStock.getIdRegistroStock();
			fila[1]=unStock.getIdInsumoAsociado();
			fila[2]=unStock.getCantidad();
			fila[3]=unStock.getPuntoDePedido();
//			fila[4]=unaRuta.getduracionEstimadaHoras();
//			fila[5]=unaRuta.getPesoMaximo();
			
			modelo.addRow(fila);
		}
		
		return tablaPlantas;
	}
	
	
}
