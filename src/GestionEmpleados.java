import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

class Empleado {
    String nombre;
    String numeroIdentificacion;
    String cargo;
    String contacto;
    // Otros detalles personales según sea necesario

    public Empleado(String nombre, String numeroIdentificacion, String cargo, String contacto) {
        this.nombre = nombre;
        this.numeroIdentificacion = numeroIdentificacion;
        this.cargo = cargo;
        this.contacto = contacto;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Número de Identificación: " + numeroIdentificacion
                + ", Cargo: " + cargo + ", Contacto: " + contacto;
    }
    
    public String getNombre(){
    	return this.nombre;
    }
}


class GestionEmpleados implements Gestion<Empleado> {
	static HashMap<String, Empleado> empleados;
	
	public void cargarDatosDesdeArchivo(String rutaArchivo) {
        try {
            List<String> lineas = Files.readAllLines(Paths.get(rutaArchivo), StandardCharsets.UTF_8);

            for (String linea : lineas) {
                agregarDesdeTexto(linea);
            }
        } catch (IOException e) {
            e.printStackTrace(); // Manejo adecuado de la excepción según tus necesidades
        }
    }
	
	public HashMap<String, Empleado> cargarHashMap(String nombreArchivo) {
		HashMap<String, Empleado> empleados = null;
		
		return empleados;
	}

    public GestionEmpleados() {
        this.empleados = new HashMap<>();
    }

    public void agregarEmpleado(String clave, Empleado empleado) {
        empleados.put(clave, empleado);
    }

    static Empleado obtenerEmpleado(String clave) {
        return empleados.get(clave);
    }

    public void mostrarTodosEmpleados() {
        System.out.println("\n==== Todos los Empleados ====");
        for (Empleado empleado : empleados.values()) {
            System.out.println(empleado);
        }
    }

    @Override
    public void agregarDesdeTexto(String texto) {
        String[] partes = texto.split(", ");
        if (partes.length == 4) {
            Empleado empleado = new Empleado(
                    partes[0].substring(partes[0].indexOf(":") + 2),
                    partes[1].substring(partes[1].indexOf(":") + 2),
                    partes[2].substring(partes[2].indexOf(":") + 2),
                    partes[3].substring(partes[3].indexOf(":") + 2)
            );
            agregarEmpleado(empleado.numeroIdentificacion, empleado);
        }
    }


    @Override
    public String convertirATexto(Empleado empleado) {
        return empleado.toString();
    }

    public  Iterable<Empleado> obtenerTodos() {
        return empleados.values();
    }
}