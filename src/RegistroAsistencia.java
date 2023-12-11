import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

class RegistroAsistencia implements Gestion<Empleado> {
    HashMap<String, Empleado> empleados;
    HashMap<String, String> registroEntradaSalida;
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
    public RegistroAsistencia() {
        empleados = new HashMap<>();
        registroEntradaSalida = new HashMap<>();
    }

    public void cargarDesdeArchivo(String nombreArchivo) {
        ManejoArchivos.cargarDatos(nombreArchivo, this);
    }

    public void guardarEnArchivo(String nombreArchivo) {
        ManejoArchivos.guardarDatos(nombreArchivo, this);
    }
    
    public void registrarEntradaSalida(String claveEmpleado, String registro) {
        registroEntradaSalida.put(claveEmpleado, registro);
    }

    public String obtenerRegistro(String claveEmpleado) {
        return registroEntradaSalida.get(claveEmpleado);
    }

    @Override
    public void agregarDesdeTexto(String texto) {
        String[] partes = texto.split(", ");
        if (partes.length == 2) {
            String claveEmpleado = partes[0].substring(partes[0].indexOf(":") + 2);
            String registro = partes[1].substring(partes[1].indexOf(":") + 2);
            registroEntradaSalida.put(claveEmpleado, registro);
        }
    }

    @Override
    public String convertirATexto(Empleado empleado) {
        if (empleado != null) {
            String claveEmpleado = empleado.numeroIdentificacion;
            String registro = registroEntradaSalida.get(claveEmpleado);
            if (registro != null) {
                return "Número de Identificación: " + claveEmpleado + ", Registro: " + registro;
            }
        }
        return "";
    }

    @Override
    public Iterable<Empleado> obtenerTodos() {
        return empleados.values();
    }

    public void agregarEmpleado(String clave, Empleado empleado) {
        empleados.put(clave, empleado);
    }

    public Empleado obtenerEmpleado(String clave) {
        return empleados.get(clave);
    }
}
