//import java.util.Scanner;
//
//public class InterfazUsuario {
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        GestionEmpleados gestionEmpleados = new GestionEmpleados();
//        GestionHorarios gestionHorarios = new GestionHorarios();
//        ProgramacionHorarios programacionHorarios = new ProgramacionHorarios();
//        RegistroAsistencia registroAsistencia = new RegistroAsistencia();  // Corregido
//        AsignacionTareas asignacionTareas = new AsignacionTareas();
//
//        // Cargar datos desde archivos .txt
//        ManejoArchivos.cargarDatos("empleados.txt", gestionEmpleados);
//        ManejoArchivos.cargarDatos("horarios.txt", gestionHorarios);
//        ManejoArchivos.cargarDatos("asistencia.txt", registroAsistencia);
//
//        boolean salir = false;
//
//        while (!salir) {
//            System.out.println("\n==== Menú Principal ====");
//            System.out.println("1. Gestionar Empleados");
//            System.out.println("2. Gestionar Horarios");
//            System.out.println("3. Registrar Asistencia");
//            System.out.println("4. Asignar Tareas");
//            System.out.println("5. Programar Horarios");
//            System.out.println("6. Salir");
//            System.out.print("Ingrese su opción: ");
//
//            int opcion = scanner.nextInt();
//            scanner.nextLine(); // Consumir el salto de línea
//
//            switch (opcion) {
//                case 1:
//                    gestionarEmpleados(scanner, gestionEmpleados);
//                    break;
//                case 2:
//                    gestionarHorarios(scanner, gestionHorarios);
//                    break;
//                case 3:
//                    registrarAsistencia(scanner, registroAsistencia, gestionEmpleados);
//                    break;
//                case 4:
//                    asignarTareas(scanner, asignacionTareas);
//                    break;
//                case 5:
//                    mostrarHorariosProgramados(scanner, programacionHorarios);
//                    break;
//                case 6:
//                    salir = true;
//                    System.out.println("¡Hasta luego!");
//                    break;
//                default:
//                    System.out.println("Opción no válida. Inténtelo de nuevo.");
//            }
//        }
//
//        // Guardar datos en archivos .txt antes de salir
//        ManejoArchivos.guardarDatos("empleados.txt", gestionEmpleados);
//        ManejoArchivos.guardarDatos("horarios.txt", gestionHorarios);
//        ManejoArchivos.guardarDatos("asistencia.txt", registroAsistencia);
//    }
//
//    private static void gestionarEmpleados(Scanner scanner, GestionEmpleados gestionEmpleados) {
//        System.out.println("\n==== Gestionar Empleados ====");
//        System.out.println("1. Agregar Empleado");
//        System.out.println("2. Buscar Empleado");
//        System.out.println("3. Mostrar Todos los Empleados");
//        System.out.print("Ingrese su opción: ");
//
//        int opcion = scanner.nextInt();
//        scanner.nextLine(); // Consumir el salto de línea
//
//        switch (opcion) {
//            case 1:
//                agregarEmpleado(scanner, gestionEmpleados);
//                break;
//            case 2:
//                buscarEmpleado(scanner, gestionEmpleados);
//                break;
//            case 3:
//                gestionEmpleados.mostrarTodosEmpleados();
//                break;
//            default:
//                System.out.println("Opción no válida. Inténtelo de nuevo.");
//        }
//    }
//
//    private static void agregarEmpleado(Scanner scanner, GestionEmpleados gestionEmpleados) {
//        System.out.println("\n==== Agregar Empleado ====");
//        System.out.print("Nombre: ");
//        String nombre = scanner.nextLine();
//        System.out.print("Número de Identificación: ");
//        String numeroIdentificacion = scanner.nextLine();
//        System.out.print("Cargo: ");
//        String cargo = scanner.nextLine();
//        System.out.print("Contacto: ");
//        String contacto = scanner.nextLine();
//
//        Empleado nuevoEmpleado = new Empleado(nombre, numeroIdentificacion, cargo, contacto);
//        gestionEmpleados.agregarEmpleado(numeroIdentificacion, nuevoEmpleado);
//
//        System.out.println("Empleado agregado con éxito.");
//    }
//
//    private static void buscarEmpleado(Scanner scanner, GestionEmpleados gestionEmpleados) {
//        System.out.println("\n==== Buscar Empleado ====");
//        System.out.print("Ingrese el número de Identificación del Empleado: ");
//        String numeroIdentificacion = scanner.nextLine();
//
//        Empleado empleadoEncontrado = gestionEmpleados.obtenerEmpleado(numeroIdentificacion);
//
//        if (empleadoEncontrado != null) {
//            System.out.println("Empleado Encontrado:");
//            System.out.println(empleadoEncontrado);
//        } else {
//            System.out.println("Empleado no encontrado.");
//        }
//    }
//
//    private static void gestionarHorarios(Scanner scanner, GestionHorarios gestionHorarios) {
//        System.out.println("\n==== Gestionar Horarios ====");
//        System.out.println("1. Agregar Horario");
//        System.out.println("2. Mostrar Todos los Horarios");
//        System.out.print("Ingrese su opción: ");
//
//        int opcion = scanner.nextInt();
//        scanner.nextLine(); // Consumir el salto de línea
//
//        switch (opcion) {
//            case 1:
//                agregarHorario(scanner, gestionHorarios);
//                break;
//            case 2:
//                gestionHorarios.mostrarTodosHorarios();
//                break;
//            default:
//                System.out.println("Opción no válida. Inténtelo de nuevo.");
//        }
//    }
//
//    private static void agregarHorario(Scanner scanner, GestionHorarios gestionHorarios) {
//        System.out.println("\n==== Agregar Horario ====");
//        System.out.print("Número de Identificación del Empleado: ");
//        String numeroIdentificacion = scanner.nextLine(); // Nueva línea para obtener la clave del empleado
//        System.out.print("Día: ");
//        String dia = scanner.nextLine();
//        System.out.print("Hora de Inicio: ");
//        String horaInicio = scanner.nextLine();
//        System.out.print("Hora de Fin: ");
//        String horaFin = scanner.nextLine();
//
//        Horario nuevoHorario = new Horario(numeroIdentificacion, dia, horaInicio, horaFin);
//        gestionHorarios.agregarHorario(numeroIdentificacion, dia, nuevoHorario);
//
//
//        System.out.println("Horario agregado con éxito.");
//    }
//
//    private static void mostrarHorariosProgramados(Scanner scanner, ProgramacionHorarios programacionHorarios) {
//        System.out.println("\n==== Mostrar Horarios Programados ====");
//        System.out.print("Ingrese el número de Identificación del Empleado: ");
//        String numeroIdentificacion = scanner.nextLine();
//
//        programacionHorarios.mostrarHorarios(numeroIdentificacion);
//    }
//
//
//    private static void registrarAsistencia(Scanner scanner, RegistroAsistencia registroAsistencia, GestionEmpleados gestionEmpleados) {
//        System.out.println("\n==== Registrar Asistencia ====");
//        System.out.print("Ingrese el número de Identificación del Empleado: ");
//        String numeroIdentificacion = scanner.nextLine();   
//
//        Empleado empleado = gestionEmpleados.obtenerEmpleado(numeroIdentificacion);
//
//        if (empleado != null) {
//            System.out.println("Empleado Encontrado:");
//            System.out.println(empleado);
//
//            System.out.print("Registro de Entrada/Salida: ");
//            String registro = scanner.nextLine();
//            registroAsistencia.registrarEntradaSalida(numeroIdentificacion, registro);
//            registroAsistencia.agregarEmpleado(numeroIdentificacion, empleado); // Agrega el empleado
//            System.out.println("Asistencia registrada con éxito.");
//
//            // Guarda los datos en el archivo
//            ManejoArchivos.guardarDatos("asistencia.txt", registroAsistencia);
//        } else {
//            System.out.println("Empleado no encontrado.");
//        }
//    }
//
//    private static void asignarTareas(Scanner scanner, AsignacionTareas asignacionTareas) {
//        // Implementa la lógica para asignar tareas
//    }
//}
