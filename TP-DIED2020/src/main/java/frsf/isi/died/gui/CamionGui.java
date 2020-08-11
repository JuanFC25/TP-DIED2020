package frsf.isi.died.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.RowFilter;
import javax.swing.RowFilter.ComparisonType;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import frsf.isi.died.app.App;
import frsf.isi.died.controller.CamionController;
import frsf.isi.died.dao.CamionDao;
import frsf.isi.died.dao.CamionDaoPostgreSql;
import frsf.isi.died.dominio.Camion;
import frsf.isi.died.exceptions.CampoVacioException;
import frsf.isi.died.exceptions.FormatoFechaException;
import frsf.isi.died.exceptions.FormatoNumericoException;
import frsf.isi.died.exceptions.IdUtilizadoException;
import frsf.isi.died.exceptions.LongitudException;
import frsf.isi.died.gui.util.MiModelo;


public class CamionGui {

	private TableRowSorter<TableModel> ordenador;
	
	public void pantallaPrincipalCamiones(App app) {
		app.activarMenu();
		
		Object [] atributos = new Object[8];
		atributos[0]=-1;
		
		JComboBox<String> listaBusqueda= new JComboBox<String>();
		
		listaBusqueda.addItem("ID");
		listaBusqueda.addItem("Patente");
		listaBusqueda.addItem("Marca");
		listaBusqueda.addItem("Modelo");
		listaBusqueda.addItem("KM Recorridos");
		listaBusqueda.addItem("Costo x hora");
		listaBusqueda.addItem("Costo x km");
		listaBusqueda.addItem("Fecha de compra");

		JPanel panel = new JPanel(new GridBagLayout());
		JScrollPane scrollCamiones=new JScrollPane();
	    JTable tablaCamiones = this.dibujarTablaCamiones();
	    scrollCamiones.setViewportView(tablaCamiones);
	    
	    JLabel etiqueta1 = new JLabel("Lista de Camiones - Empresa X");
		JLabel etiquetaFiltrar = new JLabel("Filtar:");
		
		JTextField ingresarFiltrar = new JTextField(20);
		
		JButton botonBuscar = new JButton ("Buscar");
		JButton botonAgregarCamion = new JButton("Agregar Camion");
		JButton botonEditarCamion = new JButton("Editar");
		
		
		botonAgregarCamion.addActionListener( e -> {
			pantallaAgregarCamion(app);
		});
		
		
		tablaCamiones.addMouseListener(new MouseAdapter() 
		   {  
			public void mouseClicked(MouseEvent e) 
		      {
		         int fila = tablaCamiones.rowAtPoint(e.getPoint());
		         int columna = tablaCamiones.columnAtPoint(e.getPoint());
		        
				if ((fila > -1) && (columna > -1)) 
		         
		         	atributos[0] = tablaCamiones.getValueAt(fila,0);
					atributos[1] = tablaCamiones.getValueAt(fila,1);
					atributos[2] = tablaCamiones.getValueAt(fila,2);
					atributos[3] = tablaCamiones.getValueAt(fila,3);
					atributos[4] = tablaCamiones.getValueAt(fila,4);
					atributos[5] = tablaCamiones.getValueAt(fila,5);
					atributos[6] = tablaCamiones.getValueAt(fila,6);
					atributos[7] = tablaCamiones.getValueAt(fila,7);
		      
		      }
		   });
		
		
		botonEditarCamion.addActionListener( e-> {
			if((Integer) atributos[0] == -1) {
				JOptionPane.showMessageDialog(panel,"Seleccione un camion", "Error", JOptionPane.ERROR_MESSAGE);	
			}
			else {
				Camion c = new Camion((Integer) atributos[0],
						(String) atributos[1],
						(String) atributos[2],
						(String) atributos[3],
						(Double) atributos[4],
						(Double) atributos[5],
						(Double) atributos[6],
						(Date) atributos[7]);
				
				pantallaModificarCamion(app,c);
			}
		});
		
		botonBuscar.addActionListener( e -> {
			
			switch ((String) listaBusqueda.getSelectedItem()) {
			case "ID":
				ordenador.setRowFilter(RowFilter.regexFilter(ingresarFiltrar.getText(), 0));
				break;
			case "Patente":
				ordenador.setRowFilter(RowFilter.regexFilter(ingresarFiltrar.getText(), 1));
				break;
			case "Marca":
				ordenador.setRowFilter(RowFilter.regexFilter(ingresarFiltrar.getText(), 2));
				break;
			case "Modelo":
				ordenador.setRowFilter(RowFilter.regexFilter(ingresarFiltrar.getText(), 3));
				break;
			case "KM Recorridos":
				ordenador.setRowFilter(RowFilter.regexFilter(ingresarFiltrar.getText(), 4));
				break;
			case "Costo x hora":
				ordenador.setRowFilter(RowFilter.regexFilter(ingresarFiltrar.getText(), 5));
				break;
			case "Costo x km":
				ordenador.setRowFilter(RowFilter.regexFilter(ingresarFiltrar.getText(), 6));
				break;
			case "Fecha de compra":
				ordenador.setRowFilter(RowFilter.regexFilter(ingresarFiltrar.getText(), 7));
				break;
			}
			
		});
		
		
		
		app.gbc.gridx=0;
		app.gbc.gridwidth=2;
		app.gbc.gridy=0;
		panel.add(etiqueta1,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridwidth=1;
		app.gbc.gridy=1;
		panel.add(etiquetaFiltrar,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=2;
		app.gbc.gridy=1;
		panel.add(ingresarFiltrar,app.gbc);
		
		
		app.gbc.gridx=3;
		app.gbc.gridwidth=1;
		app.gbc.gridy=1;
		panel.add(listaBusqueda,app.gbc);
		
		
		app.gbc.gridx=4;
		app.gbc.gridwidth=1;
		app.gbc.gridy=1;
		panel.add(botonBuscar,app.gbc);
		
		
		
		
		
		
		app.gbc.gridx=0;
		app.gbc.gridy=2;
		app.gbc.gridwidth=6;
		app.gbc.weightx=0.1;
		app.gbc.fill=GridBagConstraints.BOTH;
		panel.add(scrollCamiones,app.gbc);
		app.gbc.weightx=0;
		
		app.gbc.gridx=0;
		app.gbc.gridwidth=1;
		app.gbc.gridy=3;
		panel.add(botonAgregarCamion,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=1;
		app.gbc.gridy=3;
		panel.add(botonEditarCamion,app.gbc);
		
		
		app.resetGbc();
		app.setContentPane(panel);
		app.revalidate();
		app.repaint();
	}
	

	private void pantallaAgregarCamion(App app) {
		app.desactivarMenu();
		
		JPanel panel=new JPanel(new GridBagLayout());
		
		JLabel etiquetaIdCamion = new JLabel("ID: ");
		JLabel etiquetaPatente=new JLabel("Patente: ");
		JLabel etiquetaMarca=new JLabel("Marca: ");
		JLabel etiquetaModelo=new JLabel("Modelo: ");
		JLabel etiquetaKmRecorridos=new JLabel("KM Recorridos: ");
		JLabel etiquetaCostoxHora=new JLabel("Costo por Hora: ");
		JLabel etiquetaCostoxKm=new JLabel("Costo por KM: ");
		JLabel etiquetaFechaDeCompra=new JLabel("Fecha de compra: ");
		
		JTextField ingresarIdCamion = new JTextField(30);
		JTextField ingresarPatente=new JTextField(30);
		JTextField ingresarMarca=new JTextField(30);
		JTextField ingresarModelo = new JTextField(30);
		JTextField ingresarKmRecorridos = new JTextField(30);
		JTextField ingresarCostoxHora=new JTextField(30);
		JTextField ingresarCostoxKm=new JTextField(30);
		JTextField ingresarFechaDeCompra = new JTextField(30);
		
		JButton cancelar = new JButton("Cancelar");
		JButton agregar = new JButton("Agregar Camion");
		
		cancelar.addActionListener( e-> {
			this.pantallaPrincipalCamiones(app);
		});
		
		
		agregar.addActionListener( e -> {
	
			String idCamion = ingresarIdCamion.getText();
			String patente = ingresarPatente.getText();
			String marca = ingresarMarca.getText();
			String modelo= ingresarModelo.getText();
			String kmRecorridos = ingresarKmRecorridos.getText();
			String costoXhora = ingresarCostoxHora.getText();
			String costoXkm = ingresarCostoxKm.getText();
			String fechaDeCompra= ingresarFechaDeCompra.getText();
			
			
			CamionController cc = new CamionController();
			
			try {
				cc.agregarCamion(idCamion, patente, marca, modelo,kmRecorridos,costoXhora,costoXkm,fechaDeCompra);
				JOptionPane.showMessageDialog(panel,"El camion fue agregado correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				this.pantallaPrincipalCamiones(app);
			} catch (CampoVacioException e1) {
				JOptionPane.showMessageDialog(panel,e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} catch (FormatoNumericoException e1) {
				JOptionPane.showMessageDialog(panel,e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} catch (LongitudException e1) {
				JOptionPane.showMessageDialog(panel,e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} catch (FormatoFechaException e1) {
				JOptionPane.showMessageDialog(panel,e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} catch (IdUtilizadoException e1) {
				JOptionPane.showMessageDialog(panel,e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		
		
		app.gbc.gridx=0;
		app.gbc.gridy=0;
		app.gbc.gridwidth=1;
		app.gbc.gridheight=1;
		panel.add(etiquetaIdCamion,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		panel.add(ingresarIdCamion,app.gbc);
		
		
		app.gbc.gridx=0;
		app.gbc.gridy=1;
		app.gbc.gridwidth=1;
		app.gbc.gridheight=1;
		panel.add(etiquetaPatente,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		panel.add(ingresarPatente,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=2;
		app.gbc.gridwidth=1;
		panel.add(etiquetaMarca,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		panel.add(ingresarMarca,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=3;
		app.gbc.gridwidth=1;
		panel.add(etiquetaModelo,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		panel.add(ingresarModelo,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=4;
		app.gbc.gridwidth=1;
		panel.add(etiquetaKmRecorridos,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		panel.add(ingresarKmRecorridos,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=5;
		app.gbc.gridwidth=1;
		panel.add(etiquetaCostoxHora,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		panel.add(ingresarCostoxHora,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=6;
		app.gbc.gridwidth=1;
		panel.add(etiquetaCostoxKm,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		panel.add(ingresarCostoxKm,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=7;
		app.gbc.gridwidth=1;
		panel.add(etiquetaFechaDeCompra,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		panel.add(ingresarFechaDeCompra,app.gbc);
		
		
		app.gbc.fill=GridBagConstraints.NONE;
		app.gbc.gridx=2;
		app.gbc.gridy=8;
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
	
	private void pantallaModificarCamion(App app,Camion c) {
		app.desactivarMenu();
		
		JPanel panel=new JPanel(new GridBagLayout());
		
		JLabel etiquetaIdCamion = new JLabel("ID: ");
		JLabel etiquetaPatente=new JLabel("Patente: ");
		JLabel etiquetaMarca=new JLabel("Marca: ");
		JLabel etiquetaModelo=new JLabel("Modelo: ");
		JLabel etiquetaKmRecorridos=new JLabel("KM Recorridos: ");
		JLabel etiquetaCostoxHora=new JLabel("Costo por Hora: ");
		JLabel etiquetaCostoxKm=new JLabel("Costo por KM: ");
		JLabel etiquetaFechaDeCompra=new JLabel("Fecha de compra: ");
		
		JTextField ingresarIdCamion = new JTextField(c.getIdCamion().toString());
		ingresarIdCamion.setEditable(false);
		JTextField ingresarPatente=new JTextField(c.getPatente());
		JTextField ingresarMarca=new JTextField(c.getMarca());
		JTextField ingresarModelo = new JTextField(c.getModelo());
		JTextField ingresarKmRecorridos = new JTextField(c.getKmRecorridos().toString());
		JTextField ingresarCostoxHora=new JTextField(c.getCostoHora().toString());
		JTextField ingresarCostoxKm=new JTextField(c.getCostoKm().toString());
		JTextField ingresarFechaDeCompra = new JTextField(c.getFechaDeCompra().toString());
		
		JButton cancelar = new JButton("Cancelar");
		JButton modificar = new JButton("Modificar Camion");
		
		cancelar.addActionListener( e-> {
			this.pantallaPrincipalCamiones(app);
		});
		
		modificar.addActionListener( e -> {
			
			CamionController cc = new CamionController();
			
			String patente = ingresarPatente.getText();
			String marca = ingresarMarca.getText();
			String modelo = ingresarModelo.getText();
			String kmRecorridos = ingresarKmRecorridos.getText();
			String costoXhora = ingresarCostoxHora.getText();
			String costoXkm = ingresarCostoxKm.getText();
			String fecha = ingresarFechaDeCompra.getText();
 	
			try {
				
				cc.modificarCamion(c.getIdCamion(), patente, marca, modelo, kmRecorridos, costoXhora, costoXkm, fecha);
				JOptionPane.showMessageDialog(panel,"El camion fue modificado correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				pantallaPrincipalCamiones(app);
				
			} catch (CampoVacioException e1) {
				JOptionPane.showMessageDialog(panel,e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} catch (FormatoNumericoException e1) {
				JOptionPane.showMessageDialog(panel,e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} catch (LongitudException e1) {
				JOptionPane.showMessageDialog(panel,e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} catch (FormatoFechaException e1) {
				JOptionPane.showMessageDialog(panel,e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} catch (IdUtilizadoException e1) {
				JOptionPane.showMessageDialog(panel,e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		});
		
		
		
		
		app.gbc.gridx=0;
		app.gbc.gridy=0;
		app.gbc.gridwidth=1;
		app.gbc.gridheight=1;
		app.gbc.fill=GridBagConstraints.NONE;
		panel.add(etiquetaIdCamion,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		app.gbc.fill=GridBagConstraints.HORIZONTAL;
		panel.add(ingresarIdCamion,app.gbc);
		
		
		app.gbc.gridx=0;
		app.gbc.gridy=1;
		app.gbc.gridwidth=1;
		app.gbc.gridheight=1;
		app.gbc.fill=GridBagConstraints.NONE;
		panel.add(etiquetaPatente,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		app.gbc.fill=GridBagConstraints.HORIZONTAL;
		panel.add(ingresarPatente,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=2;
		app.gbc.gridwidth=1;
		app.gbc.fill=GridBagConstraints.NONE;
		panel.add(etiquetaMarca,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		app.gbc.fill=GridBagConstraints.HORIZONTAL;
		panel.add(ingresarMarca,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=3;
		app.gbc.gridwidth=1;
		app.gbc.fill=GridBagConstraints.NONE;
		panel.add(etiquetaModelo,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		app.gbc.fill=GridBagConstraints.HORIZONTAL;
		panel.add(ingresarModelo,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=4;
		app.gbc.gridwidth=1;
		app.gbc.fill=GridBagConstraints.NONE;
		panel.add(etiquetaKmRecorridos,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		app.gbc.fill=GridBagConstraints.HORIZONTAL;
		panel.add(ingresarKmRecorridos,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=5;
		app.gbc.gridwidth=1;
		app.gbc.fill=GridBagConstraints.NONE;
		panel.add(etiquetaCostoxHora,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		app.gbc.fill=GridBagConstraints.HORIZONTAL;
		panel.add(ingresarCostoxHora,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=6;
		app.gbc.gridwidth=1;
		app.gbc.fill=GridBagConstraints.NONE;
		panel.add(etiquetaCostoxKm,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		app.gbc.fill=GridBagConstraints.HORIZONTAL;
		panel.add(ingresarCostoxKm,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=7;
		app.gbc.gridwidth=1;
		app.gbc.fill=GridBagConstraints.NONE;
		panel.add(etiquetaFechaDeCompra,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		app.gbc.fill=GridBagConstraints.HORIZONTAL;
		panel.add(ingresarFechaDeCompra,app.gbc);
		
		
		app.gbc.fill=GridBagConstraints.NONE;
		app.gbc.gridx=2;
		app.gbc.gridy=8;
		app.gbc.gridwidth=1;
		app.gbc.gridheight=1;
		panel.add(cancelar,app.gbc);
		
		app.gbc.gridx=3;
		panel.add(modificar,app.gbc);
		
		app.resetGbc();
		app.setContentPane(panel);
		app.revalidate();
		app.repaint();
	}
	
	

	private JTable dibujarTablaCamiones() {


		MiModelo modelo = new MiModelo();	
		
		
		modelo.addColumn("Id Camion");
		modelo.addColumn("Patente");
		modelo.addColumn("Marca");
		modelo.addColumn("Modelo");
		modelo.addColumn("KM Recorridos");
		modelo.addColumn("Costo x KM");
		modelo.addColumn("Costo x Hora");
		modelo.addColumn("Fecha de Compra");
		
		JTable tablaCamiones=new JTable(modelo);
	
		
		TableRowSorter<TableModel> ordenador=new TableRowSorter<TableModel>(modelo);
		tablaCamiones.setRowSorter(ordenador);
		this.ordenador=ordenador;
		
		TableColumnModel modeloColumna = tablaCamiones.getColumnModel();
		
		
		modeloColumna.getColumn(0).setPreferredWidth(100);
		modeloColumna.getColumn(1).setPreferredWidth(100);
		modeloColumna.getColumn(2).setPreferredWidth(100);
		modeloColumna.getColumn(3).setPreferredWidth(100);
		modeloColumna.getColumn(4).setPreferredWidth(100);
		modeloColumna.getColumn(5).setPreferredWidth(100);
		modeloColumna.getColumn(6).setPreferredWidth(100);
		modeloColumna.getColumn(7).setPreferredWidth(100);
	
		CamionDao cd = new CamionDaoPostgreSql();
		
		List<Camion> listaCamiones = cd.buscarTodos();
		
		for(Camion unCamion : listaCamiones) {
			Object fila[]= new Object [8];
			fila[0]=unCamion.getIdCamion();
			fila[1]=unCamion.getPatente();
			fila[2]=unCamion.getMarca();
			fila[3]=unCamion.getModelo();
			fila[4]=unCamion.getKmRecorridos();
			fila[5]=unCamion.getCostoKm();
			fila[6]=unCamion.getCostoHora();
			fila[7]=unCamion.getFechaDeCompra();
			modelo.addRow(fila);
		}
		
		return tablaCamiones;
	}
	
}

