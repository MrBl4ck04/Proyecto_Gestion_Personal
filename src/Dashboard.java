import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class Dashboard extends JFrame {
	
	private Image img_logo = new ImageIcon(Dashboard.class.getResource("res/LogoPersonal.png")).getImage().getScaledInstance(350, 175, Image.SCALE_SMOOTH);
	private Image img_inicio = new ImageIcon(Dashboard.class.getResource("res/InicioLogo.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
	private Image img_registros = new ImageIcon(Dashboard.class.getResource("res/RegIcon.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
	private Image img_regHorarios = new ImageIcon(Dashboard.class.getResource("res/regHorarios.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
	private Image img_regTareas = new ImageIcon(Dashboard.class.getResource("res/RegTareas.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
	private Image img_regAsistencia = new ImageIcon(Dashboard.class.getResource("res/RegAsistencia.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
	private Image img_signOut = new ImageIcon(Dashboard.class.getResource("res/SignOutIcon.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
	
	/**
	 * 
	 */
	private JPanel contentPane;
    private PanelInicioE panelInicioE;
    private PanelRegistroE panelRegistroE;
    private GestionEmpleados gestionEmpleados;
    private PanelAsistenciaE panelAsistenciaE;
    private RegistroAsistencia registroAsistencia;
    private PanelHorariosE panelHorariosE;
    private GestionHorarios gestionHorarios;
    private PanelTareas panelTareas;

	
	/**
	 * Create the frame.
	 */
	public Dashboard() {
		
        gestionEmpleados = new GestionEmpleados();
        registroAsistencia = new RegistroAsistencia();
        gestionHorarios = new GestionHorarios();
		setBackground(new Color(56, 56, 55));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 987, 524);
		setUndecorated(true);

		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(240, 240, 240));
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new LineBorder(new Color(46, 40, 40)));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		panelInicioE = new PanelInicioE(gestionEmpleados);
        panelRegistroE = new PanelRegistroE(gestionEmpleados);
        panelAsistenciaE = new PanelAsistenciaE(registroAsistencia, gestionEmpleados);
        panelHorariosE = new PanelHorariosE(gestionHorarios, gestionEmpleados);
        panelTareas = new PanelTareas();
        
		panelInicioE.setBackground(new Color(240, 240, 240));
		JPanel paneMenu = new JPanel();
		paneMenu.setBackground(new Color(91, 34, 71));
		paneMenu.setBounds(0, 0, 335, 524);
		contentPane.add(paneMenu);
		paneMenu.setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setBounds(10, 11, 315, 131);
		lblLogo.setIcon(new ImageIcon(img_logo));
		paneMenu.add(lblLogo);
		
		JPanel paneInicio = new JPanel();
		paneInicio.addMouseListener(new PanelButtonMouseAdapter(paneInicio) {
			@Override
			public void mouseClicked(MouseEvent e) {
				GestionEmpleados gestionEmpleados = new GestionEmpleados();
				menuClicked(panelInicioE);
		        gestionEmpleados.cargarDatosDesdeArchivo("empleados.txt");

			}
		});
		paneInicio.setBackground(new Color(91, 34, 71));
		paneInicio.setBounds(0, 150, 335, 50);
		paneMenu.add(paneInicio);
		paneInicio.setLayout(null);
		
		JLabel lblInicio = new JLabel("INICIO");
		lblInicio.setForeground(new Color(240, 240, 240));
		lblInicio.setFont(new Font("Dialog", Font.BOLD, 15));
		lblInicio.setBounds(102, 11, 223, 28);
		paneInicio.add(lblInicio);
		
		
		JLabel lblInicioIcon = new JLabel("");
		lblInicioIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblInicioIcon.setBounds(23, 0, 54, 50);
		lblInicioIcon.setIcon(new ImageIcon(img_inicio));
		paneInicio.add(lblInicioIcon);
		
		JPanel paneRegistro = new JPanel();
		paneRegistro.setForeground(new Color(0, 0, 0));
		paneRegistro.addMouseListener(new PanelButtonMouseAdapter(paneRegistro) {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(panelRegistroE);
				panelRegistroE.llenarTabla();
			}
		});
		paneRegistro.setBackground(new Color(91, 34, 71));
		paneRegistro.setBounds(0, 200, 335, 50);
		paneMenu.add(paneRegistro);
		paneRegistro.setLayout(null);
		
		JLabel lblRegistros = new JLabel("EMPLEADOS");
		lblRegistros.setForeground(new Color(240, 240, 240));
		lblRegistros.setFont(new Font("Dialog", Font.BOLD, 15));
		lblRegistros.setBounds(102, 11, 223, 28);
		paneRegistro.add(lblRegistros);
		
		JLabel lblRegIcon = new JLabel("");
		lblRegIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegIcon.setBounds(23, 0, 54, 50);
		lblRegIcon.setIcon(new ImageIcon(img_registros));
		paneRegistro.add(lblRegIcon);
		
		
		JPanel paneSignOut = new JPanel();
		paneSignOut.setForeground(new Color(0, 0, 0));
		paneSignOut.addMouseListener(new PanelButtonMouseAdapter(paneSignOut) {
			@Override
			public void mouseClicked(MouseEvent e) { 
				if(JOptionPane.showConfirmDialog(null, "Esta seguro de querer cerrar sesion?") == 0) {
					//Login login = new Login();
					//login.setVisible(true);
					Dashboard.this.dispose();
				}
			}
		});
		paneSignOut.setBackground(new Color(91, 34, 71));
		paneSignOut.setBounds(0, 450, 335, 50);
		paneMenu.add(paneSignOut);
		paneSignOut.setLayout(null);
		JPanel paneRegSalida = new JPanel();
		paneRegSalida.setForeground(new Color(0, 0, 0));
		paneRegSalida.addMouseListener(new PanelButtonMouseAdapter(paneRegSalida) {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});

		
		JLabel lblCerrarSesion = new JLabel("CERRAR SESION");
		lblCerrarSesion.setForeground(new Color(240, 240, 240));
		lblCerrarSesion.setFont(new Font("Dialog", Font.BOLD, 15));
		lblCerrarSesion.setBounds(102, 11, 223, 28);
		
		paneSignOut.add(lblCerrarSesion);
		
		JLabel lblSignOutIcon = new JLabel("");
		lblSignOutIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignOutIcon.setBounds(23, 0, 54, 50);
		lblSignOutIcon.setIcon(new ImageIcon(img_signOut));
		paneSignOut.add(lblSignOutIcon);
		
		JPanel paneHorarios = new JPanel();
		paneHorarios.setLayout(null);
		paneHorarios.setForeground(Color.BLACK);
		paneHorarios.setBackground(new Color(91, 34, 71));
		paneHorarios.setBounds(0, 249, 335, 50);
		paneMenu.add(paneHorarios);

		JLabel lblHorarios = new JLabel("HORARIOS");
		lblHorarios.setForeground(SystemColor.menu);
		lblHorarios.setFont(new Font("Dialog", Font.BOLD, 15));
		lblHorarios.setBounds(102, 11, 223, 28);
		paneHorarios.add(lblHorarios);

		JLabel lblHorariosIcon = new JLabel(""); // Añade un icono si lo deseas
		lblHorariosIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblHorariosIcon.setBounds(23, 0, 54, 50);
		lblHorariosIcon.setIcon(new ImageIcon(img_regHorarios));
		paneHorarios.add(lblHorariosIcon);

		paneHorarios.addMouseListener(new PanelButtonMouseAdapter(paneHorarios) {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        menuClicked(panelHorariosE);
		    }
		});

		JPanel paneTareas = new JPanel();
		paneTareas.setLayout(null);
		paneTareas.setForeground(Color.BLACK);
		paneTareas.setBackground(new Color(91, 34, 71));
		paneTareas.setBounds(0, 300, 335, 50);
		paneMenu.add(paneTareas);
		paneTareas.addMouseListener(new PanelButtonMouseAdapter(paneTareas) {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(panelTareas);
			}
		});
		
		JLabel lblTareas = new JLabel("TAREAS");
		lblTareas.setForeground(SystemColor.menu);
		lblTareas.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTareas.setBounds(102, 11, 223, 28);
		paneTareas.add(lblTareas);
		
		JLabel lblTarIcon = new JLabel("");
		lblTarIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblTarIcon.setBounds(23, 0, 54, 50);
		lblTarIcon.setIcon(new ImageIcon(img_regTareas));
		paneTareas.add(lblTarIcon);
		
		JPanel PaneAsistencia = new JPanel();
		PaneAsistencia.setLayout(null);
		PaneAsistencia.setForeground(Color.BLACK);
		PaneAsistencia.setBackground(new Color(91, 34, 71));
		PaneAsistencia.setBounds(0, 351, 335, 50);
		paneMenu.add(PaneAsistencia);
		PaneAsistencia.addMouseListener(new PanelButtonMouseAdapter(PaneAsistencia) {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(panelAsistenciaE);
				//panelAsistenciaE.llenarTabla();
			}
		});
		
		JLabel lblAsistencia = new JLabel("ASISTENCIA");
		lblAsistencia.setForeground(SystemColor.menu);
		lblAsistencia.setFont(new Font("Dialog", Font.BOLD, 15));
		lblAsistencia.setBounds(102, 11, 223, 28);
		PaneAsistencia.add(lblAsistencia);
		
		
		JLabel lblAsisIcon = new JLabel("");
		lblAsisIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblAsisIcon.setBounds(23, 0, 54, 50);
		lblAsisIcon.setIcon(new ImageIcon(img_regAsistencia));
		PaneAsistencia.add(lblAsisIcon);
		
		JLabel lblExit = new JLabel("X");
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(JOptionPane.showConfirmDialog(null, "Estas seguro de que quieres salir?", "Confirmar", JOptionPane.YES_NO_OPTION) == 0) {
					Dashboard.this.dispose();
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblExit.setForeground(Color.RED);
			}
			
			public void mouseExited(MouseEvent arg0) {
				lblExit.setForeground(Color.BLACK);
			}
		});
		lblExit.setForeground(Color.BLACK);
		lblExit.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setBounds(957, 0, 30, 27);
		contentPane.add(lblExit);
		
		JLabel lblMin = new JLabel("-");
		lblMin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Dashboard.this.setState(Dashboard.ICONIFIED);
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblMin.setForeground(new Color(188, 221, 0));
			}
			
			public void mouseExited(MouseEvent arg0) {
				lblMin.setForeground(Color.BLACK);
			}
		});
		lblMin.setHorizontalAlignment(SwingConstants.CENTER);
		lblMin.setForeground(Color.BLACK);
		lblMin.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblMin.setBounds(923, 0, 30, 27);
		contentPane.add(lblMin);
		
		JPanel paneMainContent = new JPanel();
		paneMainContent.setBounds(345, 27, 632, 486);
		contentPane.add(paneMainContent);
		paneMainContent.setLayout(null);
		
		paneMainContent.add(panelInicioE);
		paneMainContent.add(panelRegistroE);
		paneMainContent.add(panelAsistenciaE);
		paneMainContent.add(panelHorariosE);
		paneMainContent.add(panelTareas);
		
		menuClicked(panelInicioE);
		
		
	}
	
	public void menuClicked(JPanel panel) {
		panelInicioE.setVisible(false);
		panelRegistroE.setVisible(false);
		panelAsistenciaE.setVisible(false);
		panelHorariosE.setVisible(false);
		panelTareas.setVisible(false);
		
		panel.setVisible(true);
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


	public static void main(String[] args) {
	    SwingUtilities.invokeLater(() -> {
	        Dashboard dashboard = new Dashboard();
	        dashboard.setVisible(true);

	        // Agregar esta línea para cargar los datos desde el archivo
	        dashboard.gestionEmpleados.cargarDatosDesdeArchivo("empleados.txt");
	        dashboard.registroAsistencia.cargarDatosDesdeArchivo("asistencia.txt");
	        dashboard.gestionHorarios.cargarDatosDesdeArchivo("horarios.txt");

	    });
	}
}
