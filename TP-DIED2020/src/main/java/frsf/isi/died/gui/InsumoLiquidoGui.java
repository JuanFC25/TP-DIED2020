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
import frsf.isi.died.controller.InsumoGeneralController;
import frsf.isi.died.controller.InsumoLiquidoController;
import frsf.isi.died.controller.PlantaController;
import frsf.isi.died.dao.InsumoGeneralDao;
import frsf.isi.died.dao.InsumoGeneralDaoPostgreSql;
import frsf.isi.died.dao.InsumoLiquidoDao;
import frsf.isi.died.dao.InsumoLiquidoDaoPostgreSql;
import frsf.isi.died.dominio.Insumo;
import frsf.isi.died.dominio.InsumoLiquido;
import frsf.isi.died.dominio.util.UnidadDeMedida;
import frsf.isi.died.exceptions.CampoVacioException;
import frsf.isi.died.exceptions.FormatoNumericoException;
import frsf.isi.died.exceptions.IdUtilizadoException;
import frsf.isi.died.exceptions.LongitudException;

public class InsumoLiquidoGui {

	Integer IDInsumo;
	Double costoUnidadMedida;
	UnidadDeMedida unidadDeMedida;
	String descripcion;
	Double pesoPorUnidadDouble;
	
	
	
	public void pantallaPrincipalInsumoLiquido(App app) {
		
		app.activarMenu();
		
		JPanel panel=new JPanel(new GridBagLayout());
		
		JScrollPane scrollPlantas=new JScrollPane();
		JLabel tituloPlantas=new JLabel("LISTA DE INSUMOS LIQUIDOS - Empresa ABC");
		JLabel filtrar = new JLabel("Filtar:");
		JTextField campoTexto = new JTextField(20);
		JButton boton3 = new JButton ("Buscar");
		JButton boton1 = new JButton("Agregar Insumo Liquido");
		JButton botonEditar = new JButton("Editar");
		//valorId=0;
		
		
		JTable tablaInsumosLiquidos=this.dibujarTablaInsumosLiquidos();
		
		tablaInsumosLiquidos.addMouseListener(new MouseAdapter() 
		   {
		      public void mouseClicked(MouseEvent e) 
		      {
		         int fila = tablaInsumosLiquidos.rowAtPoint(e.getPoint());
		         int columna = tablaInsumosLiquidos.columnAtPoint(e.getPoint());
		         if ((fila > -1) && (columna > -1))
//		            valorId = (Integer) tablaInsumosGenerales.getValueAt(fila,0);
//		         	nombre = (String) tablaInsumosGenerales.getValueAt(fila, 1);
//		         	direccion = (String) tablaInsumosGenerales.getValueAt(fila, 2);
//		         	telefono = (Integer) tablaInsumosGenerales.getValueAt(fila,3);
		        	IDInsumo = (Integer) tablaInsumosLiquidos.getValueAt(fila, 0);
		        	
		      }
		   });
		
	
		boton1.addActionListener( e-> {
			pantallaAgregarInsumoGeneral(app);
		});
		
//		botonEditar.addActionListener( e-> {
//			if(valorId == 0) {
//				JOptionPane.showMessageDialog(panel,"Seleccione una planta", "Error", JOptionPane.ERROR_MESSAGE);	
//			}
//			else {
//				pantallaModificarPlanta(app,valorId,nombre,direccion,telefono);
//			}
//		});
		

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
		scrollPlantas.setViewportView(tablaInsumosLiquidos);
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
		JLabel etiquetaCostoXinsumo=new JLabel("Costo por Unidad: ");
		JLabel etiquetaPeso=new JLabel("Densidad: ");
		
		JTextField ingresarIdInsumo = new JTextField(30);
		JTextField ingresarDescripcion=new JTextField(30);
		JTextField ingresarUnidad=new JTextField(30);
		JTextField ingresarCostoXinsumo = new JTextField(30);
		JTextField ingresarDensidad = new JTextField(30);
		
		JButton cancelar = new JButton("Cancelar");
		JButton agregar = new JButton("Agregar");
		
		
		cancelar.addActionListener( e -> {
			this.pantallaPrincipalInsumoLiquido(app);
		});
		
		agregar.addActionListener( e -> {
	
			String id = ingresarIdInsumo.getText();
			String descripcion = ingresarDescripcion.getText();
			String unidad = ingresarUnidad.getText();
			String costoXinsumo = ingresarCostoXinsumo.getText();
			String densidad = ingresarDensidad.getText();
			
			InsumoLiquidoController igc = new InsumoLiquidoController();
//			
			try {
				igc.agregarInsumoLiquido(id,descripcion,unidad,costoXinsumo,densidad);
				JOptionPane.showMessageDialog(panel,"El insumo fue agregado correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				this.pantallaPrincipalInsumoLiquido(app);
			} catch (CampoVacioException | FormatoNumericoException | LongitudException | IdUtilizadoException e1) {
				JOptionPane.showMessageDialog(panel,e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			
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
		panel.add(ingresarDensidad,app.gbc);
		
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
			this.pantallaPrincipalInsumoLiquido(app);
		});
		
		agregar.addActionListener( e -> {
	
			
			String planta = ingresarNombrePlanta.getText();
			String direc = ingresarDireccion.getText();
			String tel= ingresarTelefono.getText();
	
			PlantaController pc = new PlantaController();
		
			try {
				pc.modificarPlanta(valorId, planta, direc, tel);
				JOptionPane.showMessageDialog(panel,"La planta fue modificada correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				this.pantallaPrincipalInsumoLiquido(app);
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
			
			this.pantallaPrincipalInsumoLiquido(app);
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
	
	
	
	private JTable dibujarTablaInsumosLiquidos() {
		
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
		modelo.addColumn("Densidad");
	
		JTable tablaInsumosL=new JTable(modelo);
		TableRowSorter<TableModel> ordenador=new TableRowSorter<TableModel>(modelo);
		tablaInsumosL.setRowSorter(ordenador);
		
		TableColumnModel modeloColumna = tablaInsumosL.getColumnModel();
		modeloColumna.getColumn(0).setPreferredWidth(100);
		modeloColumna.getColumn(1).setPreferredWidth(100);
		modeloColumna.getColumn(2).setPreferredWidth(100);
		modeloColumna.getColumn(3).setPreferredWidth(100);
		modeloColumna.getColumn(4).setPreferredWidth(100);
		
		
		InsumoLiquidoDao ild= new InsumoLiquidoDaoPostgreSql();
		List<Insumo> insumosL = ild.buscarTodos();
		
		
		for(Insumo unInsumo : insumosL) {
			Object fila[]= new Object [5];
			fila[0]=unInsumo.getIdInsumo();
			fila[1]=unInsumo.getDescripcion();
			fila[2]=unInsumo.getUnidadDeMedida();
			fila[3]=unInsumo.getCostoUnidadMedida();
			fila[4]=unInsumo.pesoPorUnidad();
			modelo.addRow(fila);
		}
		
	
		return tablaInsumosL;
	}
	
}
