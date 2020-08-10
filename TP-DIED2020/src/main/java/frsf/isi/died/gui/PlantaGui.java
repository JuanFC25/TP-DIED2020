package frsf.isi.died.gui;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
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
import frsf.isi.died.controller.PlantaController;
import frsf.isi.died.dao.PlantaDao;
import frsf.isi.died.dao.PlantaDaoPostgreSql;
import frsf.isi.died.dominio.Planta;
import frsf.isi.died.exceptions.CampoVacioException;
import frsf.isi.died.exceptions.FormatoNumericoException;
import frsf.isi.died.exceptions.IdUtilizadoException;
import frsf.isi.died.exceptions.LongitudException;
import frsf.isi.died.gui.util.MiModelo;

public class PlantaGui {

	Integer valorId,telefono;
	String nombre,direccion;
	
	
	public void pantallaPrincipalPlantas(App app) {
		
		app.activarMenu();
		
		JPanel panel=new JPanel(new GridBagLayout());
		
		JScrollPane scrollPlantas=new JScrollPane();
		
		JLabel tituloPlantas=new JLabel("LISTA DE PLANTAS - Empresa x");
		JLabel filtrar = new JLabel("Filtar:");
		
		JTextField campoTexto = new JTextField(15);
		
		JButton botonBuscar = new JButton ("Buscar");
		JButton botonAgregarPlanta = new JButton("Agregar Planta");
		JButton botonEliminarPlanta = new JButton("Eliminar Planta");
		JButton botonVerStock = new JButton("Ver Stock");
		JButton botonVerPedidos = new JButton("Ver Pedidos");
		JButton botonEditar = new JButton("Editar");
		JButton botonRutas = new JButton("Ver Rutas");
		
		JTable tablaPlantas=this.dibujarTablaPlantas();
		
		valorId=0;
		
		
		
		
		tablaPlantas.addMouseListener(new MouseAdapter() 
		   {
		      public void mouseClicked(MouseEvent e) 
		      {
		         int fila = tablaPlantas.rowAtPoint(e.getPoint());
		         int columna = tablaPlantas.columnAtPoint(e.getPoint());
		         if ((fila > -1) && (columna > -1))
		            valorId = (Integer) tablaPlantas.getValueAt(fila,0);
		         	nombre = (String) tablaPlantas.getValueAt(fila, 1);
		         	direccion = (String) tablaPlantas.getValueAt(fila, 2);
		         	telefono = (Integer) tablaPlantas.getValueAt(fila,3);
		      }
		   });
		
	
		botonAgregarPlanta.addActionListener( e-> {
			pantallaAgregarPlanta(app);
		});
		
		botonEditar.addActionListener( e-> {
			if(valorId == 0) {
				JOptionPane.showMessageDialog(panel,"Seleccione una planta", "Error", JOptionPane.ERROR_MESSAGE);	
			}
			else {
				pantallaModificarPlanta(app,valorId,nombre,direccion,telefono);
			}
		});
		
		botonRutas.addActionListener( e-> {
			RutaGui pantallaRutas=new RutaGui();
			pantallaRutas.pantallaPrincipalRutas(app);
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
		scrollPlantas.setViewportView(tablaPlantas);
		panel.add(scrollPlantas,app.gbc);
		app.gbc.weightx=0;

	
		app.gbc.gridx=0;
		app.gbc.gridy = 4;
		app.gbc.gridwidth=1;
		app.gbc.gridheight=1;
		app.gbc.fill=GridBagConstraints.NONE;
		app.gbc.anchor=GridBagConstraints.FIRST_LINE_END;
		
		panel.add(botonAgregarPlanta,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridy = 4;
		app.gbc.gridwidth=1;
		app.gbc.gridheight=1;
		app.gbc.fill=GridBagConstraints.NONE;
		panel.add(botonEditar,app.gbc);
		
		app.gbc.gridx=2;
		app.gbc.gridy = 4;
		app.gbc.gridwidth=1;
		app.gbc.gridheight=1;
		app.gbc.fill=GridBagConstraints.NONE;
		panel.add(botonEliminarPlanta,app.gbc);
		
		app.gbc.gridx=3;
		app.gbc.gridy = 4;
		app.gbc.gridwidth=1;
		app.gbc.gridheight=1;
		panel.add(botonVerStock, app.gbc);
		
		
		app.gbc.gridx=4;
		app.gbc.gridy = 4;
		app.gbc.gridwidth=1;
		app.gbc.gridheight=1;
		panel.add(botonVerPedidos, app.gbc);
		
		
		app.gbc.gridx=5;
		app.gbc.gridy = 4;
		app.gbc.gridwidth=1;
		app.gbc.gridheight=1;
		app.gbc.fill=GridBagConstraints.NONE;
		panel.add(botonRutas,app.gbc);

		app.resetGbc();
		app.setVerPlantasFalse();
		app.setVerCamionesTrue();
		app.setContentPane(panel);
		app.revalidate();
		app.repaint();


	}
	

	
	public void pantallaAgregarPlanta(App app) {
		app.desactivarMenu();
		
		JPanel panel=new JPanel(new GridBagLayout());
		
		JLabel etiquetaId = new JLabel("ID: ");
		JLabel etiquetaNombrePlanta=new JLabel("Nombre planta: ");
		JLabel direccion=new JLabel("Direccion: ");
		JLabel telefono=new JLabel("Telefono: ");
		
		JTextField ingresarId = new JTextField(30);
		JTextField ingresarNombrePlanta=new JTextField(30);
		JTextField ingresarDireccion=new JTextField(30);
		JTextField ingresarTelefono = new JTextField(30);
		
		JButton cancelar = new JButton("Cancelar");
		JButton agregar = new JButton("Agregar Planta");
		
		
		cancelar.addActionListener( e -> {
			this.pantallaPrincipalPlantas(app);
		});
		
		agregar.addActionListener( e -> {
	
			String id = ingresarId.getText();
			String planta = ingresarNombrePlanta.getText();
			String direc = ingresarDireccion.getText();
			String tel= ingresarTelefono.getText();
			
			PlantaController pc = new PlantaController();
			
			try {
				pc.agregarPlanta(id, planta, direc, tel);
				JOptionPane.showMessageDialog(panel,"La planta fue agregada correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				this.pantallaPrincipalPlantas(app);
			} catch (CampoVacioException e1) {
				JOptionPane.showMessageDialog(panel,e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} catch (LongitudException e1) {
				JOptionPane.showMessageDialog(panel,e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} catch (FormatoNumericoException e1) {
				JOptionPane.showMessageDialog(panel,e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} catch (IdUtilizadoException e1) {
				JOptionPane.showMessageDialog(panel,e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
	
		});
		
		app.gbc.gridx=0;
		app.gbc.gridy=0;
		app.gbc.gridwidth=1;
		app.gbc.gridheight=1;
		panel.add(etiquetaId,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		panel.add(ingresarId,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=1;
		app.gbc.gridwidth=1;
		app.gbc.gridheight=1;
		panel.add(etiquetaNombrePlanta,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		panel.add(ingresarNombrePlanta,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=2;
		app.gbc.gridwidth=1;
		panel.add(direccion,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		panel.add(ingresarDireccion,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=3;
		app.gbc.gridwidth=1;
		panel.add(telefono,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		panel.add(ingresarTelefono,app.gbc);
		
		
		app.gbc.gridx=2;
		app.gbc.gridy=4;
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
	
	
	public void pantallaModificarPlanta(App app,Integer valorId,String nombre,String direccion,Integer telefono) {
		app.desactivarMenu();
		
		JPanel panel=new JPanel(new GridBagLayout());
		JLabel etiquetaId = new JLabel("ID: ");
		JLabel etiquetaNombrePlanta=new JLabel("Nombre planta: ");
		JLabel etiquetaDireccion=new JLabel("Direccion: ");
		JLabel etiquetaTelefono=new JLabel("Telefono: ");
		JTextField ingresarId = new JTextField(valorId.toString());
		ingresarId.setEditable(false);
		JTextField ingresarNombrePlanta=new JTextField(nombre);
		JTextField ingresarDireccion=new JTextField(direccion);
		JTextField ingresarTelefono = new JTextField(telefono.toString());
		JButton cancelar = new JButton("Cancelar");
		JButton agregar = new JButton("Modificar");
		
		
		
		cancelar.addActionListener( e -> {
			this.pantallaPrincipalPlantas(app);
		});
		
		agregar.addActionListener( e -> {
	
			
			String planta = ingresarNombrePlanta.getText();
			String direc = ingresarDireccion.getText();
			String tel= ingresarTelefono.getText();
	
			PlantaController pc = new PlantaController();
		
			try {
				pc.modificarPlanta(valorId, planta, direc, tel);
				JOptionPane.showMessageDialog(panel,"La planta fue modificada correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				this.pantallaPrincipalPlantas(app);
			} catch (CampoVacioException e1) {
				JOptionPane.showMessageDialog(panel,e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			} catch (LongitudException e1) {
				JOptionPane.showMessageDialog(panel,e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			} catch (FormatoNumericoException e1) {
				JOptionPane.showMessageDialog(panel,e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
			
			this.pantallaPrincipalPlantas(app);
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
		panel.add(etiquetaNombrePlanta,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		app.gbc.fill=GridBagConstraints.HORIZONTAL;
		panel.add(ingresarNombrePlanta,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=2;
		app.gbc.gridwidth=1;
		app.gbc.fill=GridBagConstraints.NONE;
		panel.add(etiquetaDireccion,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		app.gbc.fill=GridBagConstraints.HORIZONTAL;
		panel.add(ingresarDireccion,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=3;
		app.gbc.gridwidth=1;
		app.gbc.fill=GridBagConstraints.NONE;
		panel.add(etiquetaTelefono,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		app.gbc.fill=GridBagConstraints.HORIZONTAL;
		panel.add(ingresarTelefono,app.gbc);
		
		
		app.gbc.gridx=2;
		app.gbc.gridy=4;
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
	
	
	
	private JTable dibujarTablaPlantas() {
		

		MiModelo modelo = new MiModelo();	
		
		modelo.addColumn("ID Planta");
		modelo.addColumn("Nombre");
		modelo.addColumn("Direccion");
		modelo.addColumn("Telefono");
	
		JTable tablaPlantas=new JTable(modelo);
		TableRowSorter<TableModel> ordenador=new TableRowSorter<TableModel>(modelo);
		tablaPlantas.setRowSorter(ordenador);
		
		TableColumnModel modeloColumna = tablaPlantas.getColumnModel();
		modeloColumna.getColumn(0).setPreferredWidth(200);
		modeloColumna.getColumn(1).setPreferredWidth(200);
		modeloColumna.getColumn(2).setPreferredWidth(200);
		modeloColumna.getColumn(3).setPreferredWidth(200);
		
		
		
		PlantaDao p = new PlantaDaoPostgreSql();
		
		List<Planta> plantas = p.buscarTodos();
		
		for(Planta unaPlanta : plantas) {
			Object fila[]= new Object [4];
			fila[0]=unaPlanta.getIdPlanta();
			fila[1]=unaPlanta.getNombrePlanta();
			fila[2]=unaPlanta.getDireccion();
			fila[3]=unaPlanta.getTelefono();
			
			modelo.addRow(fila);
		}
		
	
		return tablaPlantas;
	}
	
	
	
}
