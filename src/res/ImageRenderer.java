package res;

import java.awt.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class ImageRenderer extends DefaultTableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
		
		if(o instanceof JLabel) { 
			JLabel lbl = (JLabel) o;
			return lbl;
		}
		return super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1); 
	}
}
