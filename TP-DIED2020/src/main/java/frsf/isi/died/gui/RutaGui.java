package frsf.isi.died.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.function.UnaryOperator;

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
import frsf.isi.died.controller.RutaController;
import frsf.isi.died.dao.PlantaDao;
import frsf.isi.died.dao.PlantaDaoPostgreSql;
import frsf.isi.died.dao.RutaDao;
import frsf.isi.died.dao.RutaDaoPostgreSql;
import frsf.isi.died.dominio.Planta;
import frsf.isi.died.dominio.Ruta;
import frsf.isi.died.exceptions.CampoVacioException;
import frsf.isi.died.exceptions.FormatoNumericoException;
import frsf.isi.died.exceptions.IdUtilizadoException;
import frsf.isi.died.exceptions.LongitudException;
import frsf.isi.died.exceptions.MismaPlantaException;

public class RutaGui {

	
	
	public void pantallaPrincipalRutas(App app) {
		
		Object [] atributos = new Object[6];
		atributos[0]=-1;
		
		JPanel panel=new JPanel(new GridBagLayout());
		
		JScrollPane scrollRutas=new JScrollPane();
		JLabel tituloRutas=new JLabel("LISTA DE RUTAS - Empresa x");
		
		JTable tablaRutas=this.dibujarTablaRutas();
		scrollRutas.setViewportView(tablaRutas);
		
		JButton botonAgregarRuta = new JButton("Agregar Ruta");
		JButton botonEditarRuta = new JButton("Editar Ruta");
		JButton BotonBorrarRuta = new JButton("Borrar Ruta");
		
		
		tablaRutas.addMouseListener(new MouseAdapter() 
		   {
		      public void mouseClicked(MouseEvent e) 
		      {
		         int fila = tablaRutas.rowAtPoint(e.getPoint());
		         int columna = tablaRutas.columnAtPoint(e.getPoint());
		         if ((fila > -1) && (columna > -1))
		            atributos[0] = tablaRutas.getValueAt(fila,0);
		         	atributos[1] = tablaRutas.getValueAt(fila, 1);
		         	atributos[2] = tablaRutas.getValueAt(fila, 2);
		         	atributos[3] = tablaRutas.getValueAt(fila,3);
		         	atributos[4] = tablaRutas.getValueAt(fila,4);
		         	atributos[5] = tablaRutas.getValueAt(fila,5);
		      }
		   });
		
		
		
		
		
		botonAgregarRuta.addActionListener( e-> {
			pantallaAgregarRuta(app);
		});
		
	
		
		
		BotonBorrarRuta.addActionListener( e-> {
			
			if((Integer) atributos[0] == -1) {
				JOptionPane.showMessageDialog(panel,"Seleccione una ruta", "Error", JOptionPane.ERROR_MESSAGE);	
			}
			else {
				int confirmado = JOptionPane.showConfirmDialog(panel,"¿Desea borrar la ruta?");

						if (JOptionPane.OK_OPTION == confirmado) {
							RutaController rc = new RutaController();
							rc.borrarRuta((Integer) atributos[0]);
							JOptionPane.showMessageDialog(panel,"La ruta fue borrada correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
							pantallaPrincipalRutas(app);
						}	
			}
		});
		
		app.gbc.gridx=0;
		app.gbc.gridwidth=3;
		app.gbc.gridy=0;
		panel.add(tituloRutas,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=1;
		app.gbc.gridwidth=5;
		app.gbc.weightx=0.1;
		app.gbc.fill=GridBagConstraints.BOTH;
		panel.add(scrollRutas,app.gbc);
		app.gbc.weightx=0;
		
		app.gbc.gridx=0;
		app.gbc.gridwidth=1;
		app.gbc.gridy=2;
		panel.add(botonAgregarRuta,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=1;
		app.gbc.gridy=2;
		panel.add(botonEditarRuta,app.gbc);
		
		app.gbc.gridx=2;
		app.gbc.gridwidth=1;
		app.gbc.gridy=2;
		panel.add(BotonBorrarRuta,app.gbc);
		
		
		
		app.resetGbc();
		app.setVerCamionesFalse();
		app.setVerPlantasTrue();
		app.setContentPane(panel);
		app.revalidate();
		app.repaint();
		
		
		
	}
	
	public void pantallaAgregarRuta(App app) {
		
		JPanel panel=new JPanel(new GridBagLayout());
		
		JLabel etiquetaIdRuta = new JLabel("ID Ruta:");
		JLabel etiquetaPlantaOrigen = new JLabel("Planta Origen:");
		JLabel etiquetaPlantaDestino = new JLabel("Planta Destino:");
		JLabel etiquetaDistanciaEnKm = new JLabel("Distancia(KM):");
		JLabel etiquetaDuracion = new JLabel("Duracion(hs);");
		JLabel etiquetaPeso = new JLabel("Peso maximo(Kg):");
		
		JComboBox<Planta> listaPlantasOrigen = new JComboBox<Planta>();
		JComboBox<Planta> listaPlantasDestino = new JComboBox<Planta>();
		
		JTextField ingresarIdRuta = new JTextField(30);
		JTextField ingresarDistanciaEnKm = new JTextField(30);
		JTextField ingresarDuracion = new JTextField(30);
		JTextField ingresarPeso = new JTextField(30);
		
		JButton cancelar = new JButton("Cancelar");
		JButton agregar = new JButton("Agregar Ruta");

		
		PlantaDao pd = new PlantaDaoPostgreSql();
		List<Planta> listaAux = pd.buscarTodos();
		
		for(Planta unaPlanta : listaAux) {
			listaPlantasOrigen.addItem(unaPlanta);
			listaPlantasDestino.addItem(unaPlanta);
		}
		
		
		
		cancelar.addActionListener( e-> {
			pantallaPrincipalRutas(app);
		});
		
		agregar.addActionListener( e-> {
			
			Planta pOrigen = (Planta) listaPlantasOrigen.getSelectedItem();
			Planta pDestino = (Planta) listaPlantasDestino.getSelectedItem();
			String idRuta = ingresarIdRuta.getText();
			String distancia = ingresarDistanciaEnKm.getText();
			String duracion = ingresarDuracion.getText();
			String peso = ingresarPeso.getText();
			
			RutaController rc = new RutaController();
			
			try {
				rc.agregarRuta(idRuta,pOrigen,pDestino,distancia,duracion,peso);
				JOptionPane.showMessageDialog(panel,"La ruta fue agregada correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				this.pantallaPrincipalRutas(app);
			} catch (FormatoNumericoException | LongitudException | CampoVacioException | MismaPlantaException | IdUtilizadoException e1) {
				JOptionPane.showMessageDialog(panel,e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		});
		

		app.gbc.gridx=0;
		app.gbc.gridy=0;
		app.gbc.gridwidth=1;
		app.gbc.gridheight=1;
		panel.add(etiquetaIdRuta,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		panel.add(ingresarIdRuta,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=1;
		app.gbc.gridwidth=1;
		app.gbc.gridheight=1;
		panel.add(etiquetaPlantaOrigen,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		app.gbc.fill=GridBagConstraints.BOTH;
		panel.add(listaPlantasOrigen,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=2;
		app.gbc.gridwidth=1;
		app.gbc.fill=GridBagConstraints.NONE;
		panel.add(etiquetaPlantaDestino,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		app.gbc.fill=GridBagConstraints.BOTH;
		panel.add(listaPlantasDestino,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=3;
		app.gbc.gridwidth=1;
		app.gbc.fill=GridBagConstraints.NONE;
		panel.add(etiquetaDistanciaEnKm,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		panel.add(ingresarDistanciaEnKm,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=4;
		app.gbc.gridwidth=1;
		panel.add(etiquetaDuracion,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		panel.add(ingresarDuracion,app.gbc);
		
		app.gbc.gridx=0;
		app.gbc.gridy=5;
		app.gbc.gridwidth=1;
		panel.add(etiquetaPeso,app.gbc);
		
		app.gbc.gridx=1;
		app.gbc.gridwidth=3;
		panel.add(ingresarPeso,app.gbc);
		
		
		
		app.gbc.gridx=2;
		app.gbc.gridy=6;
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
	
	
	
	private JTable dibujarTablaRutas() {
		
		class MiModelo extends DefaultTableModel	{
			private static final long serialVersionUID = 1L;

		public boolean isCellEditable (int row, int column) {
		       return false;
		   }
		}
		
		MiModelo modelo = new MiModelo();	
		
		modelo.addColumn("ID Ruta");
		modelo.addColumn("Planta Origen");
		modelo.addColumn("Planta Destino");
		modelo.addColumn("Distancia en KM");
		modelo.addColumn("Duracion Estimada(hs)");
		modelo.addColumn("Peso Maximo(kg)");
	
		JTable tablaPlantas=new JTable(modelo);
		TableRowSorter<TableModel> ordenador=new TableRowSorter<TableModel>(modelo);
		tablaPlantas.setRowSorter(ordenador);
		
		TableColumnModel modeloColumna = tablaPlantas.getColumnModel();
		modeloColumna.getColumn(0).setPreferredWidth(166);
		modeloColumna.getColumn(1).setPreferredWidth(166);
		modeloColumna.getColumn(2).setPreferredWidth(166);
		modeloColumna.getColumn(3).setPreferredWidth(166);
		modeloColumna.getColumn(4).setPreferredWidth(166);
		modeloColumna.getColumn(5).setPreferredWidth(166);
		
		RutaDao rd = new RutaDaoPostgreSql();
		PlantaDao pd =new PlantaDaoPostgreSql();
		
		
		List<Ruta> rutas = rd.buscarTodos();
		
		for(Ruta unaRuta : rutas) {
			Object fila[]= new Object [6];
			fila[0]=unaRuta.getIdRuta();
			fila[1]=pd.obtenerPlanta(unaRuta.getIdPlantaOrigen()).getNombrePlanta();
			fila[2]=pd.obtenerPlanta(unaRuta.getIdPlantaDestino()).getNombrePlanta();
			fila[3]=unaRuta.getDistancia();
			fila[4]=unaRuta.getduracionEstimadaHoras();
			fila[5]=unaRuta.getPesoMaximo();
			
			modelo.addRow(fila);
		}
		
		return tablaPlantas;
	}
	
	
}
