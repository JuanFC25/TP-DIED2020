package frsf.isi.died.gui.util;

import javax.swing.table.DefaultTableModel;

public class MiModelo extends DefaultTableModel	{
	private static final long serialVersionUID = 1L;

public boolean isCellEditable (int row, int column) {
       return false;
   }
}