package frsf.isi.died.app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import frsf.isi.died.gui.CamionGui;
import frsf.isi.died.gui.InsumoGui;
import frsf.isi.died.gui.PlantaGui;

public class App extends JFrame{

	//private JPanel panel;
	public GridBagConstraints gbc;
	private JMenu menuPlantas;
	private JMenu menuCamiones;
	private JMenu menuInsumos;
	private JMenuBar barraMenu;
	private JMenuItem verPlantas;
	private JMenuItem verCamiones;
	
	private PlantaGui pantallaPlantas;
	private CamionGui pantallaCamiones;
	private InsumoGui pantallaInsumos;
	 
	private void armarApp() {
		
		//this.panel=new JPanel(new GridBagLayout());
		this.menuPlantas = new JMenu("Planta");
		this.menuCamiones = new JMenu("Camion");
		this.menuInsumos = new JMenu("Insumo");
		this.barraMenu = new JMenuBar();
		this.gbc = new GridBagConstraints();
		this.verPlantas = new JMenuItem("Ver plantas");
		this.verCamiones = new JMenuItem("Ver camiones");
		
		this.pantallaPlantas = new PlantaGui();
		this.pantallaCamiones= new CamionGui();
		this.pantallaInsumos = new InsumoGui();
	
		pantallaPlantas.pantallaPrincipalPlantas(this);
		
		verPlantas.addActionListener(e->{
		
			pantallaPlantas.pantallaPrincipalPlantas(this);
			
			
			this.revalidate();
			this.repaint();
		});
		
		menuInsumos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		verCamiones.addActionListener(e-> {
			
			//this.remove(panel);
			pantallaCamiones.pantallaPrincipalCamiones(this);
			
			this.revalidate();
			this.repaint();
		});
		
		
		menuPlantas.add(verPlantas);
		menuCamiones.add(verCamiones);
		barraMenu.add(menuPlantas);
		barraMenu.add(menuInsumos);
		barraMenu.add(menuCamiones);
		this.setJMenuBar(barraMenu);
		
	

//		gbc.gridx=0;
//		gbc.weightx=0.1;
//		gbc.weighty=0.1;
//		gbc.gridy=1;
//		gbc.gridwidth=3;
//		gbc.gridheight=1;
//		gbc.fill=GridBagConstraints.NORTHWEST;
//		panel.add(botonPlantas,gbc);
//		
//		gbc.gridy=3;
//		panel.add(botonInsumos,gbc);
//		
//		gbc.gridy=5;
//		panel.add(botonCamiones,gbc);
//		
//		this.setContentPane(panel);
//		
		
	}
	
	
	public void setVerPlantasTrue() {
		this.verPlantas.setEnabled(true);
	}
	public void setVerPlantasFalse() {
		this.verPlantas.setEnabled(false);
	}
	public void setVerCamionesTrue() {
		this.verCamiones.setEnabled(true);
	}
	public void setVerCamionesFalse() {
		this.verCamiones.setEnabled(false);
	}
	
	public void resetGbc() {
		this.gbc.gridx=0;
		this.gbc.gridy=0;
		this.gbc.gridheight=1;
		this.gbc.gridwidth=1;
		this.gbc.weightx=0;
		this.gbc.weighty=0;
	}
	
	
	public void cambiarEstadoVerCamiones() {
		if(this.verCamiones.isEnabled()==true) {
			this.verCamiones.setEnabled(false);
		}
		else this.verCamiones.setEnabled(true);
	}
	
	
	public static void main(String[] args) {
	
		App aplicacion = new App();
		aplicacion.setSize(1000, 600);
		aplicacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aplicacion.armarApp();
		aplicacion.setTitle("Gestor Camiones");
		aplicacion.setVisible(true);
	

	}
}
	

















//
//JMenuBar menu;
//JMenu menuArchivo;
//JMenu menuAyuda;
//JPanel panel;
//JTable tablaPlantas;
//JTable tablaOrdenes;
//JScrollPane scrollPlantas;
//JScrollPane scrollOrdenes;
//JButton boton1;
//JButton boton2;	
//JButton boton3;
//Boolean primeraVez;
//
//private void armarApp() {
//	
//	
//	
//	this.panel= new JPanel(new GridBagLayout());
//	this.menuArchivo=new JMenu("Archivo");
//	this.menuAyuda=new JMenu("Ayuda");
//	this.scrollPlantas=new JScrollPane();
//	this.scrollOrdenes=new JScrollPane();
//	this.boton1 = new JButton("PLANTAS");
//	this.boton2 = new JButton("ORDENES");
//	this.boton3 = new JButton("Agregar Planta");
//	this.primeraVez=true;
//	
//	
//	GridBagConstraints gbc = new GridBagConstraints();
//	
//	JLabel tituloPlantas=new JLabel("LISTA DE PLANTAS - Empresa x");
//	JLabel tituloOrdenes=new JLabel("LISTA DE ORDENES - Empresa x");
//	
//	gbc.gridx = 1;
//	gbc.gridy = 1;
//	gbc.gridwidth=3;
//	gbc.gridheight=1;
//	panel.add(tituloPlantas,gbc);
//	
//	
//	
//	gbc.gridx = 1;
//	gbc.gridy = 3;
//	gbc.gridwidth=1;
//	gbc.gridheight=1;
//	panel.add(boton1,gbc);
//	
//	gbc.gridx = 2;
//	gbc.gridy = 3;
//	gbc.gridwidth=1;
//	gbc.gridheight=1;
//	panel.add(boton2,gbc);
//	
//	
//	
//	
//	gbc.gridx = 1;
//	gbc.gridy = 4;
//	gbc.gridwidth=6;
//	gbc.gridheight=1;
//	this.dibujarTablaPlantas();
//	scrollPlantas.setViewportView(tablaPlantas);
//	panel.add(scrollPlantas,gbc);
//	boton1.setEnabled(false);
//
//	
//	gbc.gridx=1;
//	gbc.gridy = 6;
//	gbc.gridwidth=1;
//	gbc.gridheight=1;
//	panel.add(boton3,gbc);
//	
//	
//	
//	boton1.addActionListener(e->{
//	
//		
//		panel.remove(tituloOrdenes);
//		gbc.gridx = 1;
//		gbc.gridy = 1;
//		gbc.gridwidth=3;
//		gbc.gridheight=1;
//		panel.add(tituloPlantas,gbc);
//		
//		
//		this.dibujarTablaPlantas();
//		panel.remove(scrollOrdenes);
//		scrollPlantas.setViewportView(tablaPlantas);
//		
//		gbc.gridx = 1;
//		gbc.gridy = 4;
//		gbc.gridwidth=6;
//		gbc.gridheight=1;
//		panel.add(scrollPlantas,gbc);
//		
//		boton1.setEnabled(false);
//		boton2.setEnabled(true);
//		boton3.setText("Agregar Planta");
//		this.revalidate();
//		this.repaint();
//		
//	});
//	
//	
//	boton2.addActionListener(e-> {
//		
//		panel.remove(tituloPlantas);
//		gbc.gridx = 1;
//		gbc.gridy = 1;
//		gbc.gridwidth=3;
//		gbc.gridheight=1;
//		panel.add(tituloOrdenes,gbc);
//		
//		
//		this.dibujarTablaOrdenes();
//		panel.remove(scrollPlantas);
//		scrollOrdenes.setViewportView(tablaOrdenes);
//		
//		gbc.gridx = 1;
//		gbc.gridy = 4;
//		gbc.gridwidth=6;
//		gbc.gridheight=1;
//		panel.add(scrollOrdenes,gbc);
//		
//		boton2.setEnabled(false);
//		boton1.setEnabled(true);
//		boton3.setText("Agregar Orden");
//		this.revalidate();
//		this.repaint();
//		
//		
//	});
//	
//	
//	this.setContentPane(panel);
//
//}
//
//private void dibujarTablaPlantas() {
//	
//	DefaultTableModel modelo = new DefaultTableModel();	
//	
//	modelo.addColumn("Nombre");
//	modelo.addColumn("Direccion");
//	modelo.addColumn("Telefono");
//	modelo.addColumn("Editar");
//	
//	this.tablaPlantas=new JTable(modelo);
//	
//	TableRowSorter<TableModel> ordenador=new TableRowSorter<TableModel>(modelo);
//	this.tablaPlantas.setRowSorter(ordenador);
//	
////	TableColumnModel modeloColumna = this.tablaPlantas.getColumnModel();
////	modeloColumna.getColumn(0).setPreferredWidth(200);
////	modeloColumna.getColumn(1).setPreferredWidth(250);
////	modeloColumna.getColumn(2).setPreferredWidth(180);
////	modeloColumna.getColumn(3).setPreferredWidth(320);
//	
//	for (int i=0 ; i<50;i++) {
//		Object fila[] = new Object[4];
//		fila[0]="Los pepitos";
//		fila[1]="Marcelino Escalada";
//		fila[2]=342123564;
//		fila[3]= new JButton("");
//		modelo.addRow(fila);
//		fila[0]="Los aaaa";
//		fila[1]="Marcelino";
//		fila[2]=342123564;
//		JButton A=new JButton();
//		A.setPreferredSize(new Dimension(2, 2));
//		fila[3]= A;
//		modelo.addRow(fila);
//		}
//
//}
//
//
//private void dibujarTablaOrdenes() {
//	
//    DefaultTableModel modelo = new DefaultTableModel();	
//	modelo.addColumn("tablaCambiada1");
//	modelo.addColumn("tablaCambiada2");
//	modelo.addColumn("tablaCambiada3");
//	modelo.addColumn("tablaCambiada4");
//	
//	TableRowSorter<TableModel> ordenador=new TableRowSorter<TableModel>(modelo);
//	
//	this.tablaOrdenes=new JTable(modelo);
//	this.tablaOrdenes.setRowSorter(ordenador);
//	
////	TableColumnModel modeloColumna = this.tablaPlantas.getColumnModel();
////	modeloColumna.getColumn(0).setPreferredWidth(200);
////	modeloColumna.getColumn(1).setPreferredWidth(250);
////	modeloColumna.getColumn(2).setPreferredWidth(180);
////	modeloColumna.getColumn(3).setPreferredWidth(320);
//	
//	
//	for (int i=0 ; i<50;i++) {
//	Object fila[] = new Object[4];
//	fila[0]="Sin datos";
//	fila[1]="Sin datos";
//	fila[2]="f";
//	fila[3]= new JButton("");
//	modelo.addRow(fila);
//	fila[0]="Sin datos";
//	fila[1]="Sin datos";
//	fila[2]="f";
//	JButton A=new JButton();
//	A.setPreferredSize(new Dimension(2, 2));
//	fila[3]= A;
//	modelo.addRow(fila);
//	}
//	
//
//	
//}