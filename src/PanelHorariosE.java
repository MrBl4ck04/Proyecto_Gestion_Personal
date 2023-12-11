import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

  // Declarar una instancia de GestionEmpleados
public class PanelHorariosE extends JPanel {
    private GestionHorarios gestionHorarios;
    private GestionEmpleados gestionEmpleados;
    private JTextField txtCI;
    private JTextField txtDia;
    private JTextField txtInicio;
    private JTextField txtFin;  // Agregado para manejar la hora de fin
    private JTable tableRegistros;
    private DefaultTableModel model;
    private JTextField textFin;
    
	
	/**
	 * Create the panel.
	 * @param gestionEmpleados2 
	 */
	public PanelHorariosE(GestionHorarios gestionHorarios, GestionEmpleados gestionEmpleados) {
        this.gestionHorarios = gestionHorarios;
        this.gestionEmpleados = gestionEmpleados;
        ManejoArchivos.guardarDatos("horarios.txt", gestionHorarios);
		setBackground(new Color(240, 240, 240));

		setBounds(0, 0, 632, 436);
		setLayout(null);
		
		JLabel lblTitulo = new JLabel("HORARIOS");
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 30));
		lblTitulo.setForeground(new Color(52, 20, 41));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(0, 0, 205, 40);
		add(lblTitulo);
		
		JLabel lblRegistro = new JLabel("Registrar Horarios");
		lblRegistro.setFont(new Font("Dialog", Font.BOLD, 18));
		lblRegistro.setHorizontalAlignment(SwingConstants.LEFT);
		lblRegistro.setForeground(new Color(52, 20, 41));
		lblRegistro.setBounds(20, 36, 506, 26);
		add(lblRegistro);
		
		txtDia = new JTextField();
		txtDia.setBackground(new Color(248, 248, 248));
		txtDia.setHorizontalAlignment(SwingConstants.CENTER);
		txtDia.setFont(new Font("Dialog", Font.BOLD, 12));
		txtDia.setColumns(10);
		txtDia.setBounds(188, 67, 106, 26);
		add(txtDia);
		
		txtInicio = new JTextField();
		txtInicio.setBackground(new Color(248, 248, 248));
		txtInicio.setHorizontalAlignment(SwingConstants.CENTER);
		txtInicio.setFont(new Font("Dialog", Font.BOLD, 12));
		txtInicio.setColumns(10);
		txtInicio.setBounds(314, 67, 121, 26);
		add(txtInicio);
		
		JLabel lblDia = new JLabel("Dia");
		lblDia.setHorizontalAlignment(SwingConstants.CENTER);
		lblDia.setForeground(new Color(52, 20, 41));
		lblDia.setFont(new Font("Dialog", Font.BOLD, 14));
		lblDia.setBounds(175, 92, 137, 26);
		add(lblDia);
		javax.swing.Timer timer = new javax.swing.Timer(1000, e -> {
		});
		timer.start();
		
		JLabel lblInicio = new JLabel("Hora Inicio");
		lblInicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblInicio.setForeground(new Color(52, 20, 41));
		lblInicio.setFont(new Font("Dialog", Font.BOLD, 14));
		lblInicio.setBounds(304, 92, 145, 26);
		add(lblInicio);
		
		ButtonGroup btnGroup = new ButtonGroup();
		
		
		JLabel lblCI = new JLabel("CI");
		lblCI.setHorizontalAlignment(SwingConstants.CENTER);
		lblCI.setForeground(new Color(52, 20, 41));
		lblCI.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCI.setBounds(20, 92, 145, 26);
		add(lblCI);
		
		txtCI = new JTextField();
		txtCI.setHorizontalAlignment(SwingConstants.CENTER);
		txtCI.setFont(new Font("Dialog", Font.BOLD, 12));
		txtCI.setColumns(10);
		txtCI.setBackground(new Color(248, 248, 248));
		txtCI.setBounds(20, 67, 145, 26);
		add(txtCI);
		
			        // JButton para el botón de Guardar
			        JButton btnGuardar = new JButton("Guardar");
			        btnGuardar.setForeground(new Color(255, 255, 255));
			        btnGuardar.setBounds(20, 125, 145, 40);
			        add(btnGuardar);
			        btnGuardar.setBackground(new Color(128, 0, 128));
			        
			        JButton btnLimpiar = new JButton("Limpiar");
			        btnLimpiar.setBounds(0, 36, 145, 4);
			        btnLimpiar.addActionListener(new ActionListener() {
			            public void actionPerformed(ActionEvent e) {
			                // Código para limpiar los campos
			                txtCI.setText("");
			                txtDia.setText("");
			                txtInicio.setText("");
			                txtFin.setText("");
			            }
			        });
			        
			        btnLimpiar.setForeground(Color.WHITE);
			        btnLimpiar.setBackground(new Color(128, 0, 128));
			        btnLimpiar.setBounds(188, 125, 145, 40);
			        add(btnLimpiar);
			        btnGuardar.addActionListener(new ActionListener() {
			            public void actionPerformed(ActionEvent e) {
			                boolean b = false;
			                String ci = txtCI.getText();
			                String dia = txtDia.getText();
			                String inicio = txtInicio.getText();
			                String fin = textFin.getText();  // Obtener la hora de fin
			                Empleado empleado = gestionEmpleados.obtenerEmpleado(ci);

			                if (txtCI.getText().isEmpty() || txtDia.getText().isEmpty() || txtInicio.getText().isEmpty() || textFin.getText().isEmpty()) {
			                    JOptionPane.showMessageDialog(null, "Complete todos los campos requeridos.");
			                } else {
			                    Empleado empleado1 = gestionEmpleados.obtenerEmpleado(ci);
			                    if (empleado1 != null) {
			                        Horario horario = new Horario(ci, dia, inicio, fin);
			                        gestionHorarios.agregarHorario(ci, dia, horario);
			                        ManejoArchivos.guardarDatos("horarios.txt", gestionHorarios);
			                        JOptionPane.showMessageDialog(null, "Horario registrado con éxito.");
			                    } else {
			                        JOptionPane.showMessageDialog(null, "Empleado no encontrado.");
			                    }
			                }
		                    actualizarTablaRegistros();

			            }
			        });
			        
			        ManejoArchivos.cargarDatos("horarios.txt", gestionHorarios);

			        JScrollPane scrollPane = new JScrollPane();
			        scrollPane.setBounds(10, 187, 612, 249);
			        add(scrollPane);

			        tableRegistros = new JTable();
			        tableRegistros.setForeground(new Color(46, 40, 40));
			        tableRegistros.setBackground(new Color(240, 240, 240));

			        model = new DefaultTableModel() {
			            @Override
			            public boolean isCellEditable(int row, int column) {
			                return false;
			            }
			        };

			        tableRegistros.setModel(model);
			        tableRegistros.getTableHeader().setReorderingAllowed(false);
			        tableRegistros.getTableHeader().setResizingAllowed(false);

			        model.addColumn("CI");
			        model.addColumn("Día");
			        model.addColumn("Hora Inicio");
			        model.addColumn("Hora Fin");

			        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			        tableRegistros.setDefaultRenderer(Object.class, centerRenderer);
			        scrollPane.setViewportView(tableRegistros);
			        
			        JLabel lblFin = new JLabel("Hora Fin");
			        lblFin.setHorizontalAlignment(SwingConstants.CENTER);
			        lblFin.setForeground(new Color(52, 20, 41));
			        lblFin.setFont(new Font("Dialog", Font.BOLD, 14));
			        lblFin.setBounds(445, 92, 145, 26);
			        add(lblFin);
			        
			        textFin = new JTextField();
			        textFin.setHorizontalAlignment(SwingConstants.CENTER);
			        textFin.setFont(new Font("Dialog", Font.BOLD, 12));
			        textFin.setColumns(10);
			        textFin.setBackground(new Color(248, 248, 248));
			        textFin.setBounds(454, 67, 121, 26);
			        add(textFin);
			        
			        ManejoArchivos.cargarDatos("horarios.txt", gestionHorarios);
                    actualizarTablaRegistros();

	}
	
	
	
	public static int contarLineas(String nombreArchivo) {
	    int contador = 0;
	    try (BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo))) {
	        while (lector.readLine() != null) {
	            contador++;
	        }
	    } catch (IOException e) {
	        e.printStackTrace(); // Cambiar a un manejo adecuado de la excepción
	    }
	    return contador;
	}
	
	// Ejemplo de corrección en PanelAsistenciaE
	private void actualizarTablaRegistros() {
        model.setRowCount(0);
        for (Horario horario : gestionHorarios.obtenerTodos()) {
            model.addRow(new Object[]{horario.claveEmpleado, horario.dia, horario.horaInicio, horario.horaFin});
        }
    }
	
	

	public void menuClicked(JPanel panel) {
		
	
	class PanelButtonMouseAdapter extends MouseAdapter {
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
	}}	
	public static void main(String[] args) {
        GestionEmpleados gestionEmpleados = new GestionEmpleados();
        GestionHorarios gestionHorarios = new GestionHorarios();
        PanelHorariosE panelHorariosE = new PanelHorariosE(gestionHorarios, gestionEmpleados);

        JFrame frame = new JFrame("Tu Aplicación");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panelHorariosE);
        frame.setSize(640, 480);
        frame.setVisible(true);
    }
}

			
