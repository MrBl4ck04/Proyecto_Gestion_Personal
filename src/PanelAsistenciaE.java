import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
  // Declarar una instancia de GestionEmpleados
 public class PanelAsistenciaE extends JPanel {
        private RegistroAsistencia registroAsistencia;
        private GestionEmpleados gestionEmpleados;
        
	private JTextField txtCI;
	private JTextField txtIngreso;
	private JTextField txtSalida;
    private JTable tableRegistros;
    private DefaultTableModel model;

	
	/**
	 * Create the panel.
	 * @param gestionEmpleados2 
	 */
	public PanelAsistenciaE(RegistroAsistencia registroAsistencia, GestionEmpleados gestionEmpleados) {
	    ManejoArchivos.cargarDatos("asistencia.txt", registroAsistencia);

        this.registroAsistencia = registroAsistencia;
        this.gestionEmpleados = gestionEmpleados;
        ManejoArchivos.guardarDatos("asistencia.txt", registroAsistencia);
		setBackground(new Color(240, 240, 240));

		setBounds(0, 0, 632, 436);
		setLayout(null);
		
		JLabel lblTitulo = new JLabel("ASISTENCIA");
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 30));
		lblTitulo.setForeground(new Color(52, 20, 41));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(0, 0, 205, 40);
		add(lblTitulo);
		
		JLabel lblRegistro = new JLabel("Registrar Asistencia");
		lblRegistro.setFont(new Font("Dialog", Font.BOLD, 18));
		lblRegistro.setHorizontalAlignment(SwingConstants.LEFT);
		lblRegistro.setForeground(new Color(52, 20, 41));
		lblRegistro.setBounds(10, 35, 506, 26);
		add(lblRegistro);
		
		txtIngreso = new JTextField();
		txtIngreso.setBackground(new Color(248, 248, 248));
		txtIngreso.setHorizontalAlignment(SwingConstants.CENTER);
		txtIngreso.setFont(new Font("Dialog", Font.BOLD, 12));
		txtIngreso.setColumns(10);
		txtIngreso.setBounds(188, 67, 145, 26);
		add(txtIngreso);
		
		txtSalida = new JTextField();
		txtSalida.setBackground(new Color(248, 248, 248));
		txtSalida.setHorizontalAlignment(SwingConstants.CENTER);
		txtSalida.setFont(new Font("Dialog", Font.BOLD, 12));
		txtSalida.setColumns(10);
		txtSalida.setBounds(354, 67, 145, 26);
		add(txtSalida);
		
		JLabel lblInicio = new JLabel("Hora Ingreso");
		lblInicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblInicio.setForeground(new Color(52, 20, 41));
		lblInicio.setFont(new Font("Dialog", Font.BOLD, 14));
		lblInicio.setBounds(188, 92, 137, 26);
		add(lblInicio);
		javax.swing.Timer timer = new javax.swing.Timer(1000, e -> {
		});
		timer.start();
		
		JLabel lblSalida = new JLabel("Hora Salida");
		lblSalida.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalida.setForeground(new Color(52, 20, 41));
		lblSalida.setFont(new Font("Dialog", Font.BOLD, 14));
		lblSalida.setBounds(354, 92, 145, 26);
		add(lblSalida);
		
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
			                txtIngreso.setText("");
			                txtSalida.setText("");
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
		                    String ingreso = txtIngreso.getText();
		                    String salida = txtSalida.getText();
		                    Empleado empleado = gestionEmpleados.obtenerEmpleado(ci);
		                    
		                    if (txtCI.getText().isEmpty() || txtIngreso.getText().isEmpty() || txtSalida.getText().isEmpty()) {
		                        JOptionPane.showMessageDialog(null, "Complete todos los campos requeridos.");
		                    } else {
		                        Empleado empleado1 = gestionEmpleados.obtenerEmpleado(ci);
		                        if (empleado != null) {
		                            registroAsistencia.registrarEntradaSalida(ci, ingreso + " - " + salida);
		                            registroAsistencia.agregarEmpleado(ci, empleado);
		                            ManejoArchivos.guardarDatos("asistencia.txt", registroAsistencia);
		                            JOptionPane.showMessageDialog(null, "Asistencia registrada con éxito.");
		                        } else {
		                            JOptionPane.showMessageDialog(null, "Empleado no encontrado.");
		                        }
		                    }
		                    actualizarTablaRegistros();
		                }
			        });
		

			        JScrollPane scrollPane = new JScrollPane();
			        scrollPane.setBounds(0, 187, 612, 249);
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

			        model.addColumn("Nombre");
			        model.addColumn("CI");
			        model.addColumn("Hora Ingreso - Salida");

			        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			        tableRegistros.setDefaultRenderer(Object.class, centerRenderer);
			        scrollPane.setViewportView(tableRegistros);
			        
			        ManejoArchivos.cargarDatos("asistencia.txt", registroAsistencia);
			        ManejoArchivos.guardarDatos("asistencia.txt", registroAsistencia); // Mover al final del constructor
			    
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
	    model.setRowCount(0); // Limpiar la tabla
	    for (Map.Entry<String, String> entry : registroAsistencia.registroEntradaSalida.entrySet()) {
	        String ci = entry.getKey();
	        String registro = entry.getValue();
	        Empleado empleado = registroAsistencia.obtenerEmpleado(ci);
	        if (empleado != null) {
	            model.addRow(new Object[]{empleado.nombre, ci, registro});
	        }
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
	    // Crear instancias de GestionEmpleados y RegistroAsistencia
	    GestionEmpleados gestionEmpleados = new GestionEmpleados(/* parámetros apropiados */);
	    RegistroAsistencia registroAsistencia = new RegistroAsistencia(/* parámetros apropiados */);

	    // Crear la instancia de PanelAsistenciaE y pasar las instancias creadas
	    PanelAsistenciaE panelAsistenciaE = new PanelAsistenciaE(registroAsistencia, gestionEmpleados);

	    // Configurar y mostrar el JFrame u otra ventana principal
	    JFrame frame = new JFrame("Tu Aplicación");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.getContentPane().add(panelAsistenciaE);
	    frame.setSize(640, 480);
	    frame.setVisible(true);
	}

}

			
