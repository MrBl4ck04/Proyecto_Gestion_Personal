import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

  // Declarar una instancia de GestionEmpleados
 public class PanelInicioE extends JPanel {
        private GestionEmpleados gestionEmpleados;

	private JTextField txtNombre;
	private JTextField txtCI;
	private JTextField txtContacto;
	private JTextField txtCargo;
	
	
	/**
	 * Create the panel.
	 * @param gestionEmpleados2 
	 */
	public PanelInicioE(GestionEmpleados gestionEmpleados) {
        this.gestionEmpleados = gestionEmpleados;

		setBackground(new Color(240, 240, 240));

		setBounds(0, 0, 632, 436);
		setLayout(null);
		
		JLabel lblTitulo = new JLabel("INICIO");
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 30));
		lblTitulo.setForeground(new Color(52, 20, 41));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(215, 39, 205, 40);
		add(lblTitulo);
		
		JLabel lblRegistro = new JLabel("Registro de Nuevo Empleado");
		lblRegistro.setFont(new Font("Dialog", Font.BOLD, 18));
		lblRegistro.setHorizontalAlignment(SwingConstants.LEFT);
		lblRegistro.setForeground(new Color(52, 20, 41));
		lblRegistro.setBounds(67, 105, 506, 26);
		add(lblRegistro);
		
		txtCI = new JTextField();
		txtCI.setBackground(new Color(248, 248, 248));
		txtCI.setHorizontalAlignment(SwingConstants.CENTER);
		txtCI.setFont(new Font("Dialog", Font.BOLD, 12));
		txtCI.setColumns(10);
		txtCI.setBounds(247, 155, 145, 26);
		add(txtCI);
		
		txtContacto = new JTextField();
		txtContacto.setBackground(new Color(248, 248, 248));
		txtContacto.setHorizontalAlignment(SwingConstants.CENTER);
		txtContacto.setFont(new Font("Dialog", Font.BOLD, 12));
		txtContacto.setColumns(10);
		txtContacto.setBounds(428, 155, 145, 26);
		add(txtContacto);
		
		JLabel lblCI = new JLabel("CI");
		lblCI.setHorizontalAlignment(SwingConstants.CENTER);
		lblCI.setForeground(new Color(52, 20, 41));
		lblCI.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCI.setBounds(247, 183, 145, 43);
		add(lblCI);
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCargo.setForeground(new Color(52, 20, 41));
		lblCargo.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCargo.setBounds(247, 281, 145, 26);
		add(lblCargo);
		
		txtCargo = new JTextField();
		txtCargo.setBackground(new Color(248, 248, 248));
		txtCargo.setHorizontalAlignment(SwingConstants.CENTER);
		txtCargo.setFont(new Font("Dialog", Font.BOLD, 12));
		txtCargo.setColumns(10);
		txtCargo.setBounds(247, 246, 145, 26);
		add(txtCargo);
		javax.swing.Timer timer = new javax.swing.Timer(1000, e -> {
		});
		timer.start();
		
		JLabel lblContacto = new JLabel("Contacto");
		lblContacto.setHorizontalAlignment(SwingConstants.CENTER);
		lblContacto.setForeground(new Color(52, 20, 41));
		lblContacto.setFont(new Font("Dialog", Font.BOLD, 14));
		lblContacto.setBounds(428, 191, 145, 26);
		add(lblContacto);
		
		ButtonGroup btnGroup = new ButtonGroup();
		JLabel lblS1 = new JLabel("");
		lblS1.setBounds(66, 151, 146, 14);
		add(lblS1);
		
		JLabel lblS2 = new JLabel("");
		lblS2.setBounds(247, 151, 146, 14);
		add(lblS2);
		
		JLabel lblS3 = new JLabel("");
		lblS3.setBounds(427, 151, 146, 14);
		add(lblS3);
		
		JLabel lblS4 = new JLabel("");
		lblS4.setBounds(155, 241, 146, 14);
		add(lblS4);
		
		JLabel lblS5 = new JLabel("");
		lblS5.setBounds(334, 241, 146, 14);
		add(lblS5);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setForeground(new Color(52, 20, 41));
		lblNombre.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNombre.setBounds(77, 200, 145, 26);
		add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombre.setFont(new Font("Dialog", Font.BOLD, 12));
		txtNombre.setColumns(10);
		txtNombre.setBackground(new Color(248, 248, 248));
		txtNombre.setBounds(76, 155, 145, 26);
		add(txtNombre);
		
			        // JButton para el botón de Guardar
			        JButton btnGuardar = new JButton("Guardar");
			        btnGuardar.setForeground(new Color(255, 255, 255));
			        btnGuardar.setBounds(156, 343, 145, 40);
			        add(btnGuardar);
			        btnGuardar.setBackground(new Color(128, 0, 128));
			        
			        JButton btnLimpiar = new JButton("Limpiar");
			        btnLimpiar.setBounds(0, 36, 145, 4);
			        btnLimpiar.addActionListener(new ActionListener() {
			            public void actionPerformed(ActionEvent e) {
			                // Código para limpiar los campos
			                txtNombre.setText("");
			                txtCI.setText("");
			                txtCargo.setText("");
			                txtContacto.setText("");
			            }
			        });
			        
			        btnLimpiar.setForeground(Color.WHITE);
			        btnLimpiar.setBackground(new Color(128, 0, 128));
			        btnLimpiar.setBounds(334, 343, 145, 40);
			        add(btnLimpiar);
			        btnGuardar.addActionListener(new ActionListener() {
			            public void actionPerformed(ActionEvent e) {
			                boolean b = false;
			                if (txtCI.getText().isEmpty() || txtCargo.getText().isEmpty() || txtContacto.getText().isEmpty()) {
			                    JOptionPane.showMessageDialog(null, "Alguno de los campos requeridos está vacío, por favor complete todos los datos.");
			                } else {
			                    // Crear un nuevo empleado con los datos ingresados
			                    Empleado nuevoEmpleado = new Empleado(
			                            txtNombre.getText(),
			                            txtCI.getText(),
			                            txtCargo.getText(),
			                            txtContacto.getText()
			                    );

			                    // Agregar el nuevo empleado a la instancia de GestionEmpleados
			                    gestionEmpleados.agregarEmpleado(nuevoEmpleado.numeroIdentificacion, nuevoEmpleado);

			                    // Mostrar mensaje de éxito
			                    JOptionPane.showMessageDialog(null, "Empleado agregado con éxito!");

			                    // Limpiar los campos
			                    txtNombre.setText("");
			                    txtCI.setText("");
			                    txtCargo.setText("");
			                    txtContacto.setText("");
			                    b = true;
			                }

			                if (b) {
			                    // Guardar datos al hacer clic en el botón "Guardar"
			                    ManejoArchivos.guardarDatos("empleados.txt", gestionEmpleados);
			                } else {
			                    JOptionPane.showMessageDialog(null, "Error al agregar el empleado.");
			                }
			            }
			        });
		
		
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
}

			
