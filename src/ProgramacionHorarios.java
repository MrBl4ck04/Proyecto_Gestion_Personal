import java.util.HashMap;

public class ProgramacionHorarios {
    private HashMap<String, GestionHorarios> programacion;

    public ProgramacionHorarios() {
        this.programacion = new HashMap<>();
    }

    public void agregarGestionHorarios(String claveEmpleado, GestionHorarios gestionHorarios) {
        programacion.put(claveEmpleado, gestionHorarios);
    }

    public void mostrarHorarios(String claveEmpleado) {
        GestionHorarios gestionHorarios = programacion.get(claveEmpleado);
        if (gestionHorarios != null) {
            System.out.println("\n==== Horarios Programados ====");
            System.out.println("Gestión de Horarios encontrada para el empleado con clave: " + claveEmpleado);
            gestionHorarios.mostrarTodosHorarios();
        } else {
            System.out.println("No hay horarios programados para el empleado con clave: " + claveEmpleado);
        }
    }
}
