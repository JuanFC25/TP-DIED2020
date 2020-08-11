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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import frsf.isi.died.app.App;
import frsf.isi.died.controller.InsumoGeneralController;
import frsf.isi.died.controller.InsumoLiquidoController;
import frsf.isi.died.controller.PlantaController;
import frsf.isi.died.dao.InsumoGeneralDao;
import frsf.isi.died.dao.InsumoGeneralDaoPostgreSql;
import frsf.isi.died.dao.PlantaDao;
import frsf.isi.died.dao.PlantaDaoPostgreSql;
import frsf.isi.died.dominio.Insumo;
import frsf.isi.died.dominio.InsumoGeneral;
import frsf.isi.died.dominio.Planta;
import frsf.isi.died.dominio.util.UnidadDeMedida;
import frsf.isi.died.exceptions.CampoVacioException;
import frsf.isi.died.exceptions.FormatoNumericoException;
import frsf.isi.died.exceptions.IdUtilizadoException;
import frsf.isi.died.exceptions.LongitudException;

public class InsumoGeneralGui {

	Integer IDInsumo;
	Double costoUnidadMedida;
	String unidadDeMedida;
	String descripcion;
	Double pesoPorUnidad;
	Integer valorId;
	
	
	public void pantallaPrincipalInsumoGeneral(App app) {
		
		app.activarMenu();
		
		JPanel panel=new JPanel(new GridBagLayout());
		
		JScrollPane scrollPlantas=new JScrollPane();
		JLabel tituloPlantas=new JLabel("LISTA DE INSUMOS GENERALES - Empresa x");
		JLabel filtrar = new JLabel("Filtar:");
		JTextField campoTexto = new JTextField(20);
		JButton boton3 = new JButton ("Buscar");
		JButton boton1 = new JButton("Agregar Insumo General");
		JButton botonEditar = new JButton("Editar");
		valorId=0;
		
		
		JTable tablaInsumosGenerales=this.dibujarTablaInsumosGenerales();
		
		tablaInsumosGenerales.addMouseListener(new MouseAdapter() 
		   {
		      public void mouseClicked(MouseEvent e) 
		      {
		         int fila = tablaInsumosGenerales.rowAtPoint(e.getPoint());
		         int columna = tablaInsumosGenerales.columnAtPoint(e.getPoint());
		         if ((fila > -1) && (columna > -1)) {
		             valorId = (Integer) tablaInsumosGenerales.getValueAt(fila, 0);
		         descripcion = (String) tablaInsumosGenerales.getValueAt(fila, 1);
		         unidadDeMedida = (String) tablaInsumosGenerales.getValueAt(fila, 2);
		         costoUnidadMedida = (Double) tablaInsumosGenerales.getValueAt(fila, 3);
		         pesoPorUnidad = (Double) tablaInsumosGenerales.getValueAt(fila, 4);
		        	
		      }
		   }});
		
	
		boton1.addActionListener( e-> {
			pantallaAgregarInsumoGeneral(app);
		});
		
		botonEditar.addActionListener( e-> {
			if(valorId == 0) {
				JOptionPane.showMessageDialog(panel,"Seleccione un insumo", "Error", JOptionPane.ERROR_MESSAGE);	
			}
			else {
				pantallaModificarInsumoGeneral(app, valorId, descripcion, unidadDeMedida,costoUnidadMedida, pesoPorUnidad);
			}
		});
		

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
		scrollPlantas.setViewportView(tablaInsumosGenerales);
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
		
		panel.add(botonEditar,app.gbc);
		
//		
//		app.gbc.gridx=2;
//		app.gbc.gridy = 4;
//		app.gbc.gridwidth=1;
//		app.gbc.gridheight=1;
//		app.gbc.fill=GridBagConstraints.NONE;
//		
//		panel.add(boton2,app.gbc);
//		
//		app.gbc.gridx=3;
//		app.gbc.gridy = 4;
//		app.gbc.gridwidth=1;
//		app.gbc.gridheight=1;
//		app.gbc.fill=GridBagConstraints.NONE;
//		panel.add(botonInsumos, app.gbc);
		
		
		app.resetGbc();
		app.setVerPlantasFalse();
		app.setVerCamionesTrue();
		app.setContentPane(panel);
		app.revalidate();
		app.repaint();


	}
	

	
	public void pantallaAgregarInsumoGeneral(App app) {
		app.desactivarMenu();
		
		JPanel panel=new JPanel(new GridBagLayout());
		
		
		JLabel etiquetaIdInsumo = new JLabel("ID: ");
		JLabel etiquetaDescripcion=new JLabel("Descripcion: ");
		JLabel etiquetaUnidad=new JLabel("Unidad: ");
		JLabel etiquetaCostoXinsumo=new JLabel("Costo por Insumo: ");
		JLabel etiquetaPeso=new JLabel("Peso: ");
		
		JTextField ingresarIdInsumo = new JTextField(30);
		JTextField ingresarDescripcion=new JTextField(30);
		JTextField ingresarUnidad=new JTextField(30);
		JTextField ingresarCostoXinsumo = new JTextField(30);
		JTextField ingresarPeso = new JTextField(30);
		
		JButton cancelar = new JButton("Cancelar");
		JButton agregar = new JButton("Agregar Planta");
		
		
		cancelar.addActionListener( e -> {
			this.pantallaPrincipalInsumoGeneral(app);
		});
		
		agregar.addActionListener( e -> {
	
			String id = ingresarIdInsumo.getText();
			String descripcion = ingresarDescripcion.getText();
			String unidad = ingresarUnidad.getText();
			String costoXinsumo = ingresarCostoXinsumo.getText();
			String peso = ingresarPeso.getText();
			
			InsumoGeneralController igc = new InsumoGeneralController();
//			
			try {
				igc.agregarInsumoGeneral(id,descripcion,unidad,costoXinsumo,peso);
			} catch (FormatoNumericoException | LongitudException | CampoVacioException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(panel,"El insumo fue correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			this.pantallaPrincipalInsumoGeneral(app);
			
//			try {
//				pc.agregarPlanta(id, planta, direc, tel);
//				JOptionPane.showMessageDialog(panel,"La planta fue agregada correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
//				this.pantallaPrincipalInsumoGeneral(app);
//			} catch (CampoVacioException e1) {
//				JOptionPane.showMessageDialog(panel,e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//			} catch (LongitudException e1) {
//				JOptionPane.showMessageDialog(panel,e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//			} catch (FormatoNumericoException e1) {
//				JOptionPane.showMessageDialog(panel,e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//			} catch (IdUtilizadoException e1) {
//				JOptionPane.showMessageDialog(panel,e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//			}
//	
		});
		
		app.gbc.gridx=0;
		app.gbc.gridy=0;
		app.gbc.gridwidth=1;
		app.gbc.gridheight=1;
		panel.add(etiquetaIdInsumo,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		panel.add(ingresarIdInsumo,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=1;
		app.gbc.gridwidth=1;
		app.gbc.gridheight=1;
		panel.add(etiquetaDescripcion,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		panel.add(ingresarDescripcion,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=2;
		app.gbc.gridwidth=1;
		panel.add(etiquetaUnidad,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		panel.add(ingresarUnidad,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=3;
		app.gbc.gridwidth=1;
		panel.add(etiquetaCostoXinsumo,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		panel.add(ingresarCostoXinsumo,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=4;
		app.gbc.gridwidth=1;
		panel.add(etiquetaPeso,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		panel.add(ingresarPeso,app.gbc);
		
		app.gbc.gridx=2;
		app.gbc.gridy=5;
		app.gbc.gridwidth=1;
		app.gbc.gridheight=1;
		panel.add(cancelar,app.gbc);
		
		app.gbc.gridx=3;
		panel.add(agregar,app.gbc);
		
		
		app.setContentPane(panel);
		app.revalidate();
		app.repaint();
		
	}
	
	
	public void pantallaModificarInsumoGeneral(App app,Integer valorId,String desc,String unidadMedida,Double costoU,Double peso) {
			app.desactivarMenu();
			
			JPanel panel=new JPanel(new GridBagLayout());
			JLabel etiquetaId = new JLabel("ID: ");
			JLabel etiquetaDescripcion=new JLabel("Descripcion: ");
			JLabel etiquetaUnidadMedida=new JLabel("Unidad de medida: ");
			JLabel etiquetaCostoUnidad=new JLabel("Costo Unidad: ");
			JLabel etiquetaPeso=new JLabel("Peso: ");
			JTextField ingresarId = new JTextField(valorId.toString());
			ingresarId.setEditable(false);
			JTextField ingresarDescripcion=new JTextField(desc);
			JTextField ingresarUnidad=new JTextField(unidadMedida);
			JTextField ingresarCostoUnidad = new JTextField(costoU.toString());
			JTextField ingresarPeso = new JTextField(peso.toString());
			JButton cancelar = new JButton("Cancelar");
			JButton agregar = new JButton("Modificar");
			
			
			
			cancelar.addActionListener( e -> {
				this.pantallaPrincipalInsumoGeneral(app);
			});
			
			agregar.addActionListener( e -> {
		
				
				String iDesc = ingresarDescripcion.getText();
				String iUnidad = ingresarUnidad.getText();
				String iCostoU= ingresarCostoUnidad.getText();
				String iPeso= ingresarPeso.getText();
				
				InsumoGeneralController igc = new InsumoGeneralController();
				try {
					igc.modificarInsumoGeneral(valorId.toString(), iDesc, iUnidad, iCostoU, iPeso);
					JOptionPane.showMessageDialog(panel,"El insumo general fue modificado correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
					this.pantallaPrincipalInsumoGeneral(app);
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
				
				this.pantallaPrincipalInsumoGeneral(app);
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
			panel.add(etiquetaDescripcion,app.gbc);
			
			app.gbc.gridx=1;
			app.gbc.gridwidth=3;
			app.gbc.fill=GridBagConstraints.HORIZONTAL;
			panel.add(ingresarDescripcion,app.gbc);
			
			app.gbc.gridx=0;
			app.gbc.gridy=2;
			app.gbc.gridwidth=1;
			app.gbc.fill=GridBagConstraints.NONE;
			panel.add(etiquetaUnidadMedida,app.gbc);
			
			app.gbc.gridx=1;
			app.gbc.gridwidth=3;
			app.gbc.fill=GridBagConstraints.HORIZONTAL;
			panel.add(ingresarUnidad,app.gbc);
			
			app.gbc.gridx=0;
			app.gbc.gridy=3;
			app.gbc.gridwidth=1;
			app.gbc.fill=GridBagConstraints.NONE;
			panel.add(etiquetaCostoUnidad,app.gbc);
			
			app.gbc.gridx=1;
			app.gbc.gridwidth=3;
			app.gbc.fill=GridBagConstraints.HORIZONTAL;
			panel.add(ingresarCostoUnidad,app.gbc);
			
			app.gbc.gridx=0;
			app.gbc.gridy=4;
			app.gbc.gridwidth=1;
			app.gbc.fill=GridBagConstraints.NONE;
			panel.add(etiquetaPeso,app.gbc);
			
			app.gbc.gridx=1;
			app.gbc.gridwidth=3;
			app.gbc.fill=GridBagConstraints.HORIZONTAL;
			panel.add(ingresarPeso,app.gbc);
			
			
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

	
	
	
	private JTable dibujarTablaInsumosGenerales() {
		
		class MiModelo extends DefaultTableModel	{
			private static final long serialVersionUID = 1L;

		public boolean isCellEditable (int row, int column) {
		       return false;
		   }
		}
		
		MiModelo modelo = new MiModelo();	
		
		modelo.addColumn("ID Insumo");
		modelo.addColumn("Descripcio");
		modelo.addColumn("Unidad Medida");
		modelo.addColumn("Costo por Unidad");
		modelo.addColumn("Peso por Unidad");
	
		JTable tablaInsumosG=new JTable(modelo);
		TableRowSorter<TableModel> ordenador=new TableRowSorter<TableModel>(modelo);
		tablaInsumosG.setRowSorter(ordenador);
		
		TableColumnModel modeloColumna = tablaInsumosG.getColumnModel();
		modeloColumna.getColumn(0).setPreferredWidth(100);
		modeloColumna.getColumn(1).setPreferredWidth(100);
		modeloColumna.getColumn(2).setPreferredWidth(100);
		modeloColumna.getColumn(3).setPreferredWidth(100);
		modeloColumna.getColumn(4).setPreferredWidth(100);
		
		
		InsumoGeneralDao igd= new InsumoGeneralDaoPostgreSql();
		List<Insumo> insumosG = igd.buscarTodos();
		
		
		for(Insumo unInsumo : insumosG) {
			Object fila[]= new Object [5];
			fila[0]=unInsumo.getIdInsumo();
			fila[1]=unInsumo.getDescripcion();
			fila[2]=unInsumo.getUnidadDeMedida();
			fila[3]=unInsumo.getCostoUnidadMedida();
			fila[4]=unInsumo.pesoPorUnidad();
			modelo.addRow(fila);
		}
		
	
		return tablaInsumosG;
	}
	
	
	
}


