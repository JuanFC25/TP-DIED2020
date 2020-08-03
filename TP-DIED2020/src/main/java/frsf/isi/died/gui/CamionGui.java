package frsf.isi.died.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import frsf.isi.died.app.App;

public class CamionGui {

	public void pantallaPrincipalCamiones(App app) {
		
		JPanel panel = new JPanel(new GridBagLayout());
		JScrollPane scrollCamiones=new JScrollPane();
	    JTable tablaCamiones = this.dibujarTablaCamiones();
		JLabel etiqueta1 = new JLabel("Lista de Camiones - Empresa X");
		scrollCamiones.setViewportView(tablaCamiones);
		
		app.gbc.gridx=0;
		app.gbc.gridy=0;
		panel.add(etiqueta1,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=1;
		app.gbc.gridwidth=5;
		app.gbc.weightx=0.1;
		app.gbc.fill=GridBagConstraints.BOTH;
	
		panel.add(scrollCamiones,app.gbc);
		
		
		app.resetGbc();
		app.setVerCamionesFalse();
		app.setVerPlantasTrue();
		app.setContentPane(panel);
		app.revalidate();
		app.repaint();
	}
	
	
	
	private JTable dibujarTablaCamiones() {
		
		DefaultTableModel modelo = new DefaultTableModel();	
		
		modelo.addColumn("Patente");
		modelo.addColumn("Modelo");
		modelo.addColumn("KM Recorridos");
		modelo.addColumn("Costo x KM");
		modelo.addColumn("Costo x Hora");
		modelo.addColumn("Fecha de Compra");
		
		JTable tablaCamiones=new JTable(modelo);
		
		TableRowSorter<TableModel> ordenador=new TableRowSorter<TableModel>(modelo);
		tablaCamiones.setRowSorter(ordenador);
		
		TableColumnModel modeloColumna = tablaCamiones.getColumnModel();
		
		modeloColumna.getColumn(0).setPreferredWidth(100);
		modeloColumna.getColumn(1).setPreferredWidth(100);
		modeloColumna.getColumn(2).setPreferredWidth(100);
		modeloColumna.getColumn(3).setPreferredWidth(100);
		modeloColumna.getColumn(4).setPreferredWidth(100);
		modeloColumna.getColumn(5).setPreferredWidth(100);
	
		
		return tablaCamiones;
	}
}
