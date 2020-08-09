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
import frsf.isi.died.gui.InsumoGeneralGui;
import frsf.isi.died.gui.InsumoGui;
import frsf.isi.died.gui.InsumoLiquidoGui;
import frsf.isi.died.gui.PlantaGui;

public class App extends JFrame{

	
	public GridBagConstraints gbc;
	private JMenu menuPlantas;
	private JMenu menuCamiones;
	private JMenu menuInsumos;
	private JMenuBar barraMenu;
	private JMenuItem verPlantas;
	private JMenuItem verCamiones;
	private JMenuItem verInsumosGenerales;
	private JMenuItem verInsumosLiquidos;
	
	
	private PlantaGui pantallaPlantas;
	private CamionGui pantallaCamiones;
	private InsumoGeneralGui pantallaInsumosGenerales;
	private InsumoLiquidoGui pantallaInsumosLiquidos;
	
	private void armarApp() {
		
		
		this.menuPlantas = new JMenu("Planta");
		this.menuCamiones = new JMenu("Camion");
		this.menuInsumos = new JMenu("Insumo");
		this.barraMenu = new JMenuBar();
		this.gbc = new GridBagConstraints();
		this.verPlantas = new JMenuItem("Ver plantas");
		this.verCamiones = new JMenuItem("Ver camiones");
		this.verInsumosGenerales = new JMenuItem("Insumos Generales");
		this.verInsumosLiquidos = new JMenuItem("Insumos Liquidos");
		
		this.pantallaPlantas = new PlantaGui();
		this.pantallaCamiones= new CamionGui();
		this.pantallaInsumosGenerales = new InsumoGeneralGui();
		this.pantallaInsumosLiquidos= new InsumoLiquidoGui();
		
		
		pantallaPlantas.pantallaPrincipalPlantas(this);
		
		verPlantas.addActionListener(e->{
		
			pantallaPlantas.pantallaPrincipalPlantas(this);
			
			
			this.revalidate();
			this.repaint();
		});
		
		verInsumosGenerales.addActionListener(e->{
		
			pantallaInsumosGenerales.pantallaPrincipalInsumoGeneral(this);
			
			
			this.revalidate();
			this.repaint();
		}); // pantallaPrincipalInsumoGeneral
		
		verInsumosLiquidos.addActionListener(e->{
		
			pantallaInsumosLiquidos.pantallaPrincipalInsumoLiquido(this);
	
			this.revalidate();
			this.repaint();
		});
		
		verCamiones.addActionListener(e-> {
			
			//this.remove(panel);
			pantallaCamiones.pantallaPrincipalCamiones(this);
			
			this.revalidate();
			this.repaint();
		});
		
		
		menuPlantas.add(verPlantas);
		menuCamiones.add(verCamiones);
		menuInsumos.add(verInsumosGenerales);
		menuInsumos.add(verInsumosLiquidos);
		barraMenu.add(menuPlantas);
		barraMenu.add(menuInsumos);
		barraMenu.add(menuCamiones);
		this.setJMenuBar(barraMenu);
		
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
		this.gbc.fill=GridBagConstraints.NONE;
	}
	
	
	public void cambiarEstadoVerCamiones() {
		if(this.verCamiones.isEnabled()==true) {
			this.verCamiones.setEnabled(false);
		}
		else this.verCamiones.setEnabled(true);
	}
	
	public void activarMenu() {
		this.menuCamiones.setEnabled(true);
		this.menuInsumos.setEnabled(true);
		this.menuPlantas.setEnabled(true);
	}
	
	public void desactivarMenu() {
		this.menuCamiones.setEnabled(false);
		this.menuInsumos.setEnabled(false);
		this.menuPlantas.setEnabled(false);
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
	

