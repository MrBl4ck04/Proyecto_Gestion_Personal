import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Queue;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import res.ImageRenderer;

public class PanelTareas extends JPanel implements MouseListener{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	protected JTable tableTareas;
	protected NonEditableTableModel model;
	private JTextField txtIdUser;
	DefaultTableModel modeloOriginal;
	
	private Image img_completar = new ImageIcon(Dashboard.class.getResource("res/completado.png")).getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
	
	private JTextField txtNombre;
	private JTextField txtTarea;

	/**
	 * Create the panel.
	 */
	public PanelTareas() {
		setForeground(new Color(254, 244, 244));
		setBackground(new Color(240, 240, 240));
		
		setBounds(0, 0, 632, 436);
		setLayout(null);
		
		JLabel lblTitulo = new JLabel("TAREAS");
		lblTitulo.setBackground(new Color(52, 20, 41));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(new Color(52, 20, 41));
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 30));
		lblTitulo.setBounds(219, 13, 213, 40);
		add(lblTitulo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(386, 64, 196, 337);
		add(scrollPane);
		
		tableTareas = new JTable();
		tableTareas.setForeground(new Color(46, 40, 40));
		tableTareas.setBackground(new Color(240, 240, 240));
		
		model = new NonEditableTableModel();
		tableTareas.setModel(model);
		tableTareas.addMouseListener(this);
		tableTareas.getTableHeader().setReorderingAllowed(false);
		tableTareas.getTableHeader().setResizingAllowed(false);
		tableTareas.setDefaultRenderer(Object.class, new ImageRenderer());
		
		model.addColumn("Tareas");
		model.addColumn(" ");
		
		tableTareas.getColumnModel().getColumn(0).setMaxWidth(175);
		tableTareas.getColumnModel().getColumn(1).setMaxWidth(20);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		tableTareas.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tableTareas.getColumnModel().getColumn(1).setCellRenderer(new ImageRenderer());
		
		scrollPane.setViewportView(tableTareas);
		
		txtIdUser = new JTextField();
		txtIdUser.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdUser.setBackground(new Color(248, 248, 248));
		txtIdUser.setBounds(10, 64, 134, 31);
		add(txtIdUser);
		txtIdUser.setColumns(10);
		
		JLabel lblIdUser = new JLabel("Id de Usuario");
		lblIdUser.setBackground(new Color(52, 20, 41));
		lblIdUser.setFont(new Font("Dialog", Font.BOLD, 13));
		lblIdUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdUser.setForeground(new Color(52, 20, 41));
		lblIdUser.setBounds(30, 106, 99, 31);
		add(lblIdUser);
		
		JPanel paneBuscar = new JPanel();
		paneBuscar.addMouseListener(new PanelButtonMouseAdapter(paneBuscar) {
			public void mouseClicked(MouseEvent e) {
				try {
					Empleado empleado = GestionEmpleados.obtenerEmpleado(txtIdUser.getText());
					txtNombre.setText(empleado.getNombre());
					llenarBusquedaId(txtIdUser.getText());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "El id de usuario no pudo ser encontrado.");
				}
				
			}
		});
		paneBuscar.setLayout(null);
		paneBuscar.setBackground(new Color(91, 34, 71));
		paneBuscar.setBounds(169, 64, 145, 40);
		add(paneBuscar);
		
		JLabel lblBuscar = new JLabel("Buscar");
		lblBuscar.setForeground(new Color(240, 240, 240));
		lblBuscar.setHorizontalAlignment(SwingConstants.LEFT);
		lblBuscar.setFont(new Font("Dialog", Font.BOLD, 13));
		lblBuscar.setBounds(65, 11, 80, 14);
		paneBuscar.add(lblBuscar);
		
		JLabel lblBuscarIcon = new JLabel("");
		lblBuscarIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarIcon.setBounds(9, 0, 46, 40);
		paneBuscar.add(lblBuscarIcon);
		
		JPanel paneLimpiar = new JPanel();
		paneLimpiar.addMouseListener(new PanelButtonMouseAdapter(paneLimpiar) {
		    public void mouseClicked(MouseEvent e) {
		        txtIdUser.setText("");
		        txtNombre.setText("");
		        txtTarea.setText("");
		        model.setRowCount(0);
		    }
		});

		paneLimpiar.setLayout(null);
		paneLimpiar.setBackground(new Color(91, 34, 71));
		paneLimpiar.setBounds(169, 148, 145, 40);
		add(paneLimpiar);
		
		JLabel lblLimpiar = new JLabel("Limpiar");
		lblLimpiar.setForeground(new Color(240, 240, 240));
		lblLimpiar.setHorizontalAlignment(SwingConstants.LEFT);
		lblLimpiar.setFont(new Font("Dialog", Font.BOLD, 13));
		lblLimpiar.setBounds(65, 11, 80, 14);
		paneLimpiar.add(lblLimpiar);
		
		JLabel lblLimpiarIcon = new JLabel("");
		lblLimpiarIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblLimpiarIcon.setBounds(9, 0, 46, 40);
		paneLimpiar.add(lblLimpiarIcon);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombre.setBackground(new Color(248, 248, 248));
		txtNombre.setColumns(10);
		txtNombre.setBounds(10, 148, 134, 31);
		add(txtNombre);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBackground(new Color(52, 20, 41));
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setForeground(new Color(52, 20, 41));
		lblNombre.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNombre.setBounds(30, 190, 99, 31);
		add(lblNombre);
		
		JPanel paneAddTarea = new JPanel();
		paneAddTarea.addMouseListener(new PanelButtonMouseAdapter(paneAddTarea) {
		    public void mouseClicked(MouseEvent e) {
		    	Tarea tarea = new Tarea(txtTarea.getText());
		    	Empleado empleado = GestionEmpleados.obtenerEmpleado(txtIdUser.getText());
		    	AsignacionTareas.asignarTareaEnTxt("tareas.txt", empleado.getNombre(), tarea);
		    	txtTarea.setText("");
		    	llenarBusquedaId(txtIdUser.getText());
		    }
		});
		paneAddTarea.setLayout(null);
		paneAddTarea.setBackground(new Color(91, 34, 71));
		paneAddTarea.setBounds(169, 232, 145, 40);
		add(paneAddTarea);
		
		JLabel lblRegistrar = new JLabel("Añadir Tarea");
		lblRegistrar.setHorizontalAlignment(SwingConstants.LEFT);
		lblRegistrar.setForeground(SystemColor.menu);
		lblRegistrar.setFont(new Font("Dialog", Font.BOLD, 13));
		lblRegistrar.setBounds(52, 11, 83, 14);
		paneAddTarea.add(lblRegistrar);
		
		JLabel lblAddIcon = new JLabel("");
		lblAddIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddIcon.setBounds(9, 0, 46, 40);
		paneAddTarea.add(lblAddIcon);
		txtIdUser.setBorder(null);
		txtNombre.setBorder(null);
		
		txtTarea = new JTextField();
		txtTarea.setHorizontalAlignment(SwingConstants.CENTER);
		txtTarea.setColumns(10);
		txtTarea.setBorder(null);
		txtTarea.setBackground(new Color(248, 248, 248));
		txtTarea.setBounds(10, 232, 134, 31);
		add(txtTarea);
		
		JLabel lblTarea = new JLabel("Tarea");
		lblTarea.setHorizontalAlignment(SwingConstants.CENTER);
		lblTarea.setForeground(new Color(52, 20, 41));
		lblTarea.setFont(new Font("Dialog", Font.BOLD, 13));
		lblTarea.setBackground(new Color(52, 20, 41));
		lblTarea.setBounds(30, 274, 99, 31);
		add(lblTarea);
		
		modeloOriginal = (DefaultTableModel) tableTareas.getModel();
		
	}
	
	class NonEditableTableModel extends DefaultTableModel {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}
	
	public void setTablaRegistros(JTable tableRegistros) {
	    this.tableTareas = tableRegistros;
	}
	
	public void llenarBusquedaId(String idBus) {
		model.setRowCount(0);
	    try {
	    	Empleado empleado = GestionEmpleados.obtenerEmpleado(idBus);
	    	Queue<Tarea> tareas = AsignacionTareas.cargarCola("tareas.txt", empleado.getNombre());
	    	if (!tareas.isEmpty()) {
	    	    for(Tarea t: tareas) {
	    	    	Object[] fila = new Object[2];
	    	    	fila[0] = t.getNombre();
	    	    	fila[1] = new JLabel(new ImageIcon(img_completar));
	    	    	modeloOriginal.addRow(fila);	
	    	    }
	    	}
	    	else {
	    		JOptionPane.showMessageDialog(null, "El usuario no tiene tareas pendientes.");
	    	}
		    
	    }catch(Exception ex) {
	    	JOptionPane.showMessageDialog(null, ex+"El id debe contener solamente caracteres numericos.");
	    }
	}
	
	
	private class PanelButtonMouseAdapter extends MouseAdapter {
		JPanel panel;
		public PanelButtonMouseAdapter(JPanel panel) {
			this.panel = panel;
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			panel.setBackground(new Color(109, 41, 86));
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			panel.setBackground(new Color(91, 34, 71));
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			panel.setBackground(new Color(129, 48, 100));
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			panel.setBackground(new Color(91, 34, 71));
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int fila = tableTareas.rowAtPoint(e.getPoint());
		int columna = tableTareas.columnAtPoint(e.getPoint());
		if(columna == 1) {
			String tarea = tableTareas.getValueAt(fila, 0).toString();
			if(JOptionPane.showConfirmDialog(null, "Estas seguro de querer completar la tarea " + tarea +"?", "Confirmar", JOptionPane.YES_NO_OPTION) == 0) {
				int index = fila;
				AsignacionTareas.completarTarea("tareas.txt", txtNombre.getText(), index);
				llenarBusquedaId(txtIdUser.getText());
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
