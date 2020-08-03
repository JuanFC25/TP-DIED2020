package frsf.isi.died.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import frsf.isi.died.app.App;

public class PlantaGui {

	
	
	public void pantallaPrincipalPlantas(App app) {
		
		JPanel panel=new JPanel(new GridBagLayout());
		
		JScrollPane scrollPlantas=new JScrollPane();
		JLabel tituloPlantas=new JLabel("LISTA DE PLANTAS - Empresa x");
		JLabel filtrar = new JLabel("Filtar:");
		JTextField campoTexto = new JTextField(20);
		JButton boton3 = new JButton ("Buscar");
		JButton boton1 = new JButton("Agregar Planta");
		JButton boton2 = new JButton("Ver Pedidos");
		//this.scrollOrdenes=new JScrollPane();
		//this.boton1 = new JButton("PLANTAS");
		//this.boton2 = new JButton("ORDENES");
		
		//this.primeraVez=true;
		
		
		boton1.addActionListener( e-> {
			pantallaAgregarPlanta(app);
		});

		//JLabel tituloOrdenes=new JLabel("LISTA DE ORDENES - Empresa x");
		
		app.gbc.gridx = 0;
		app.gbc.gridy = 0;
		app.gbc.gridwidth=3;
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
		panel.add(boton3,app.gbc);
			
		app.gbc.gridx = 0;
		app.gbc.gridy = 2;
		app.gbc.gridwidth=6;
		app.gbc.gridheight=1;
		app.gbc.weightx=0.1;
		app.gbc.fill=GridBagConstraints.HORIZONTAL;
		JTable tablaPlantas=this.dibujarTablaPlantas();
		scrollPlantas.setViewportView(tablaPlantas);
		panel.add(scrollPlantas,app.gbc);
		app.gbc.weightx=0;

	
		app.gbc.gridx=0;
		app.gbc.gridy = 4;
		app.gbc.gridwidth=1;
		app.gbc.gridheight=1;
		app.gbc.fill=GridBagConstraints.NONE;
		app.gbc.anchor=GridBagConstraints.FIRST_LINE_END;
		
		panel.add(boton1,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridy = 4;
		app.gbc.gridwidth=1;
		app.gbc.gridheight=1;
		app.gbc.fill=GridBagConstraints.NONE;
		
		panel.add(boton2,app.gbc);
		
		
		app.resetGbc();
		app.setVerPlantasFalse();
		app.setVerCamionesTrue();
		app.setContentPane(panel);
		app.revalidate();
		app.repaint();


	}
	
	
	private JTable dibujarTablaPlantas() {
		DefaultTableModel modelo = new DefaultTableModel();	
		
		modelo.addColumn("Nombre");
		modelo.addColumn("Direccion");
		modelo.addColumn("Telefono");
		modelo.addColumn("Editar");
		
		JTable tablaPlantas=new JTable(modelo);
		
		TableRowSorter<TableModel> ordenador=new TableRowSorter<TableModel>(modelo);
		tablaPlantas.setRowSorter(ordenador);
		
		TableColumnModel modeloColumna = tablaPlantas.getColumnModel();
		modeloColumna.getColumn(0).setPreferredWidth(245);
		modeloColumna.getColumn(1).setPreferredWidth(245);
		modeloColumna.getColumn(2).setPreferredWidth(245);
		modeloColumna.getColumn(3).setPreferredWidth(20);
		
		for (int i=0 ; i<50;i++) {
			Object fila[] = new Object[4];
			fila[0]="Los pepitos";
			fila[1]="Marcelino Escalada";
			fila[2]=342123564;
			fila[3]= new JButton("");
			modelo.addRow(fila);
			fila[0]="Los aaaa";
			fila[1]="Marcelino";
			fila[2]=342123564;
			JButton A=new JButton();
			A.setPreferredSize(new Dimension(2, 2));
			fila[3]= A;
			modelo.addRow(fila);
			}
	
		return tablaPlantas;
	}
	
	
	public void pantallaAgregarPlanta(App app) {
		JPanel panel=new JPanel(new GridBagLayout());
		String nombrePlanta;
		JLabel etiquetaNombrePlanta=new JLabel("Nombre planta: ");
		JLabel direccion=new JLabel("Direccion: ");
		JLabel telefono=new JLabel("Telefono: ");
		JTextField ingresarNombrePlanta=new JTextField(30);
		JTextField ingresarDireccion=new JTextField(30);
		JTextField ingresarTelefono = new JTextField(30);
		JButton cancelar = new JButton("Cancelar");
		JButton agregar = new JButton("Agregar Planta");
		
		
		cancelar.addActionListener( e -> {
			this.pantallaPrincipalPlantas(app);
		});
		
		agregar.addActionListener( e -> {
			ingresarNombrePlanta.getText();
		});
		
		
		
		//app.gbc.weightx=0.1;
		//app.gbc.weighty=0.1;
		app.gbc.gridx=0;
		app.gbc.gridy=0;
		app.gbc.gridwidth=1;
		app.gbc.gridheight=1;
		panel.add(etiquetaNombrePlanta,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		panel.add(ingresarNombrePlanta,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=1;
		app.gbc.gridwidth=1;
		panel.add(direccion,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		panel.add(ingresarDireccion,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=2;
		app.gbc.gridwidth=1;
		panel.add(telefono,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		panel.add(ingresarTelefono,app.gbc);
		
		
		app.gbc.gridx=2;
		app.gbc.gridy=3;
		app.gbc.gridwidth=1;
		app.gbc.gridheight=1;
		panel.add(cancelar,app.gbc);
		
		app.gbc.gridx=3;
		panel.add(agregar,app.gbc);
		
		
		app.setContentPane(panel);
		app.revalidate();
		app.repaint();
		
	}
	
	
}
















//boton1.addActionListener(e->{
//
//	
//	panel.remove(tituloOrdenes);
//	gbc.gridx = 1;
//	gbc.gridy = 1;
//	gbc.gridwidth=3;
//	gbc.gridheight=1;
//	panel.add(tituloPlantas,gbc);
//	
//	
//	this.dibujarTablaPlantas();
//	panel.remove(scrollOrdenes);
//	scrollPlantas.setViewportView(tablaPlantas);
//	
//	gbc.gridx = 1;
//	gbc.gridy = 4;
//	gbc.gridwidth=6;
//	gbc.gridheight=1;
//	panel.add(scrollPlantas,gbc);
//	
//	boton1.setEnabled(false);
//	boton2.setEnabled(true);
//	boton3.setText("Agregar Planta");
//	this.revalidate();
//	this.repaint();
//	
//});
//
//
//boton2.addActionListener(e-> {
//	
//	panel.remove(tituloPlantas);
//	gbc.gridx = 1;
//	gbc.gridy = 1;
//	gbc.gridwidth=3;
//	gbc.gridheight=1;
//	panel.add(tituloOrdenes,gbc);
//	
//	
//	this.dibujarTablaOrdenes();
//	panel.remove(scrollPlantas);
//	scrollOrdenes.setViewportView(tablaOrdenes);
//	
//	gbc.gridx = 1;
//	gbc.gridy = 4;
//	gbc.gridwidth=6;
//	gbc.gridheight=1;
//	panel.add(scrollOrdenes,gbc);
//	
//	boton2.setEnabled(false);
//	boton1.setEnabled(true);
//	boton3.setText("Agregar Orden");
//	this.revalidate();
//	this.repaint();
//	
//	
//});
//
