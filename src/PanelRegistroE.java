import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

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

public class PanelRegistroE extends JPanel {
    private GestionEmpleados gestionEmpleados;
    private JTable tableRegistros;
    private DefaultTableModel model;
    private JTextField txtCIEmpleado;

      


     public PanelRegistroE(GestionEmpleados gestionEmpleados) {
                this.gestionEmpleados = gestionEmpleados;
                initialize(); // Llamamos a un método para inicializar componentes

        setForeground(new Color(0, 0, 0));
        setBackground(new Color(240, 240, 240));
        setBounds(0, 0, 632, 486);
        setLayout(null);

        JLabel lblTitulo = new JLabel("REGISTROS");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setForeground(new Color(52, 20, 41));
        lblTitulo.setFont(new Font("Dialog", Font.BOLD, 30));
        lblTitulo.setBounds(216, 11, 205, 40);
        add(lblTitulo);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 64, 612, 249);
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
        model.addColumn("Cargo");
        model.addColumn("Contacto");

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tableRegistros.setDefaultRenderer(Object.class, centerRenderer);
        scrollPane.setViewportView(tableRegistros);

        txtCIEmpleado = new JTextField();
        txtCIEmpleado.setBackground(new Color(248, 248, 248));
        txtCIEmpleado.setBounds(83, 359, 159, 31);
        add(txtCIEmpleado);
        txtCIEmpleado.setColumns(10);

        JLabel lblCIEmpleado = new JLabel("CI del Empleado");
        lblCIEmpleado.setFont(new Font("Dialog", Font.BOLD, 13));
        lblCIEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
        lblCIEmpleado.setForeground(new Color(52, 20, 41));
        lblCIEmpleado.setBounds(93, 401, 134, 31);
        add(lblCIEmpleado);

        JPanel paneBuscar = new JPanel();
        paneBuscar.addMouseListener(new PanelButtonMouseAdapter(paneBuscar) {
            public void mouseClicked(MouseEvent e) {
                if (!txtCIEmpleado.getText().isEmpty()) {
                    String ci = txtCIEmpleado.getText();
                    buscarEmpleado(ci);
                } else {
                    JOptionPane.showMessageDialog(null, "No se introdujo una CI para buscar en los registros.");
                }
            }
        });
        paneBuscar.setLayout(null);
        paneBuscar.setBackground(new Color(91, 34, 71));
        paneBuscar.setBounds(276, 359, 145, 40);
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
                llenarTabla();
                txtCIEmpleado.setText("");
            }
        });

        paneLimpiar.setLayout(null);
        paneLimpiar.setBackground(new Color(91, 34, 71));
        paneLimpiar.setBounds(455, 359, 145, 40);
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

        llenarTabla();  // Initial table filling
    }
     private void initialize() {
    	 model = new DefaultTableModel() {
             @Override
             public boolean isCellEditable(int row, int column) {
                 return false;
             }
         };}
    public void buscarEmpleado(String ciBus) {
        model.setRowCount(0);

        Empleado empleadoEncontrado = gestionEmpleados.obtenerEmpleado(ciBus);

        if (empleadoEncontrado != null) {
            Object[] fila = new Object[4];
            fila[0] = empleadoEncontrado.nombre;
            fila[1] = empleadoEncontrado.numeroIdentificacion;
            fila[2] = empleadoEncontrado.cargo;
            fila[3] = empleadoEncontrado.contacto;

            model.addRow(fila);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró ningún empleado con la CI especificada.");
        }
    }


    public void llenarTabla() {
        if (this.gestionEmpleados != null) {
            Iterable<Empleado> empleados = this.gestionEmpleados.obtenerTodos();

            // Limpiar el modelo sin eliminar las columnas
            model.setRowCount(0); // Limpiar el modelo antes de agregar nuevas filas

            if (empleados != null) {
                for (Empleado empleado : empleados) {
                    Object[] fila = new Object[4];
                    fila[0] = empleado.nombre;
                    fila[1] = empleado.numeroIdentificacion;
                    fila[2] = empleado.cargo;
                    fila[3] = empleado.contacto;
                    model.addRow(fila);
                }
            }
        } else {
            System.out.println("La variable gestionEmpleados es null");
        }
    }





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
    }
}
