package com.gimnasio.app;
import com.gimnasio.model.TipoMembresia;
import com.gimnasio.model.Clase;
import com.gimnasio.model.Miembro;
import java.io.*;
import java.util.Scanner;
public class GimnasioApp {
    // Arrays puros con capacidad inicial
    private static TipoMembresia[] tiposMembresia = new TipoMembresia[5];
    private static Clase[] clases = new Clase[5];
    private static Miembro[] miembros = new Miembro[5];
    // Contadores de elementos
    private static int cantMembresias = 0;
    private static int cantClases = 0;
    private static int cantMiembros = 0;
    private static Scanner scanner = new Scanner(System.in);
    // Métodos
    private static void redimensionarMembresias() {
        TipoMembresia[] nuevo = new TipoMembresia[tiposMembresia.length * 2];
        System.arraycopy(tiposMembresia, 0, nuevo, 0, cantMembresias);
        tiposMembresia = nuevo;
        System.out.println("Array de membresías redimensionado a " + tiposMembresia.length);
    }
    private static void redimensionarClases() {
        Clase[] nuevo = new Clase[clases.length * 2];
        System.arraycopy(clases, 0, nuevo, 0, cantClases);
        clases = nuevo;
        System.out.println("Array de clases redimensionado a " + clases.length);
    }
    private static void redimensionarMiembros() {
        Miembro[] nuevo = new Miembro[miembros.length * 2];
        System.arraycopy(miembros, 0, nuevo, 0, cantMiembros);
        miembros = nuevo;
        System.out.println("Array de miembros redimensionado a " + miembros.length);
    }
    // Cargar datos desde archivos
    public static void cargarDatos() {
        cargarMembresias();
        cargarClases();
        cargarMiembros();
    }
private static void cargarMiembros() {
    try (BufferedReader reader = new BufferedReader(new FileReader("miembros.txt"))) {
        String linea;
        while ((linea = reader.readLine()) != null) {
            Miembro miembro = Miembro.fromString(linea);
            if (miembro != null) {
                if (cantMiembros == miembros.length) {
                    redimensionarMiembros();
                }
                miembros[cantMiembros++] = miembro;
            }
        }
    } catch (IOException e) {
        System.err.println("Error al cargar miembros: " + e.getMessage());
    }
}
    private static void cargarMembresias() {
        try (BufferedReader reader = new BufferedReader(new FileReader("membresias.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                TipoMembresia tm = TipoMembresia.fromString(linea);
                if (tm != null) {
                    if (cantMembresias == tiposMembresia.length) {
                        redimensionarMembresias();
                    }
                    tiposMembresia[cantMembresias++] = tm;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar membresías: " + e.getMessage());
        }
    }
    private static void cargarClases() {
    try (BufferedReader reader = new BufferedReader(new FileReader("clases.txt"))) {
        String linea;
        while ((linea = reader.readLine()) != null) {
            Clase clase = Clase.fromString(linea);
            if (clase != null) {
                if (cantClases == clases.length) {
                    redimensionarClases();
                }
                clases[cantClases++] = clase;
            }
        }
    } catch (IOException e) {
        System.err.println("Error al cargar clases: " + e.getMessage());
    }
}
public static boolean iniciarSesion(String usuario, String contrasena) {
    try (BufferedReader reader = new BufferedReader(new FileReader("usuarios.txt"))) {
        String linea;
        while ((linea = reader.readLine()) != null) {
            String[] credenciales = linea.split(",");
            if (credenciales.length == 2 && 
                credenciales[0].equals(usuario) && 
                credenciales[1].equals(contrasena)) {
                return true;
            }
        }
    } catch (IOException e) {
        System.err.println("Error al leer usuarios.txt: " + e.getMessage());
    }
    return false;
}
public static void mostrarMenuPrincipal() {
    int opcion;
    do {
        System.out.println("\n=== MENÚ PRINCIPAL ===");
        System.out.println("1. Gestión de Membresías");
        System.out.println("2. Gestión de Clases");
        System.out.println("3. Gestión de Miembros");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
        
        Scanner scanner = new Scanner(System.in);
        opcion = Integer.parseInt(scanner.nextLine());
        switch (opcion) {
            case 1 -> menuMembresias();
            case 2 -> menuClases();
            case 3 -> menuMiembros();
            case 0 -> System.out.println("Saliendo...");
            default -> System.out.println("Opción no válida.");
        }
    } while (opcion != 0);
}
private static void menuMembresias() {
    int opcion;
    do {
        System.out.println("\n=== MENÚ DE MEMBRESÍAS ===");
        System.out.println("1. Ver Membresías");
        System.out.println("2. Agregar Membresía");
        System.out.println("3. Eliminar Membresía");
        System.out.println("0. Volver al Menú Principal");
        System.out.print("Seleccione una opción: ");
        
        Scanner scanner = new Scanner(System.in);
        opcion = Integer.parseInt(scanner.nextLine());
        switch (opcion) {
            case 1 -> verMembresias();
            case 2 -> agregarMembresia();
            case 3 -> eliminarMembresia();
            case 0 -> System.out.println("Volviendo al menú principal...");
            default -> System.out.println("Opción no válida.");
        }
    } while (opcion != 0);
}
private static void verMembresias() {
    System.out.println("\n=== LISTA DE MEMBRESÍAS ===");
    for (int i = 0; i < cantMembresias; i++) {
        System.out.println(tiposMembresia[i].toString());
    }
}
private static void menuClases() {
    int opcion;
    do {
        System.out.println("\n=== MENÚ DE CLASES ===");
        System.out.println("1. Ver Clases");
        System.out.println("2. Agregar Clase");
        System.out.println("3. Eliminar Clase");
        System.out.println("0. Volver al Menú Principal");
        System.out.print("Seleccione una opción: ");
        
        Scanner scanner = new Scanner(System.in);
        opcion = Integer.parseInt(scanner.nextLine());
        switch (opcion) {
            case 1 -> verClases();
            case 2 -> agregarClase();
            case 3 -> eliminarClase();
            case 0 -> System.out.println("Volviendo al menú principal...");
            default -> System.out.println("Opción no válida.");
        }
    } while (opcion != 0);
}
private static void agregarClase() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Ingrese el código de la clase: ");
    String codigo = scanner.nextLine();
    System.out.print("Ingrese el nombre de la clase: ");
    String nombre = scanner.nextLine();
    System.out.print("Ingrese la descripción de la clase: ");
    String descripcion = scanner.nextLine();
    System.out.print("Ingrese el instructor: ");
    String instructor = scanner.nextLine();
    System.out.print("Ingrese el horario: ");
    String horario = scanner.nextLine();
    System.out.print("Ingrese la duración en minutos: ");
    int duracionMinutos = Integer.parseInt(scanner.nextLine());
    System.out.print("Ingrese la capacidad máxima: ");
    int capacidadMaxima = Integer.parseInt(scanner.nextLine());
    System.out.print("Ingrese el nivel: ");
    String nivel = scanner.nextLine();
    System.out.print("Ingrese observaciones: ");
    String observaciones = scanner.nextLine();
    Clase nuevaClase = new Clase(codigo, nombre, descripcion, instructor, horario, duracionMinutos, capacidadMaxima, nivel, observaciones);
    if (cantClases == clases.length) {
        redimensionarClases();
    }
    clases[cantClases++] = nuevaClase;
    System.out.println("Clase agregada exitosamente.");
}
private static void eliminarClase() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Ingrese el código de la clase a eliminar: ");
    String codigo = scanner.nextLine();
    
    for (int i = 0; i < cantClases; i++) {
        if (clases[i].getCodigo().equals(codigo)) {
            // Desplazar elementos hacia la izquierda
            for (int j = i; j < cantClases - 1; j++) {
                clases[j] = clases[j + 1];
            }
            clases[--cantClases] = null; // Limpiar la última posición
            System.out.println("Clase eliminada exitosamente.");
            return;
        }
    }
    System.out.println("Clase no encontrada.");
}
private static void verClases() {
    System.out.println("\n=== LISTA DE CLASES ===");
    for (int i = 0; i < cantClases; i++) {
        System.out.println(clases[i].toString());
    }
}
private static void menuMiembros() {
    int opcion;
    do {
        System.out.println("\n=== MENÚ DE MIEMBROS ===");
        System.out.println("1. Ver Miembros");
        System.out.println("2. Agregar Miembro");
        System.out.println("3. Eliminar Miembro");
        System.out.println("0. Volver al Menú Principal");
        System.out.print("Seleccione una opción: ");
        
        Scanner scanner = new Scanner(System.in);
        opcion = Integer.parseInt(scanner.nextLine());
        switch (opcion) {
            case 1: verMiembros(); break;
            case 2: agregarMiembro(); break;
            case 3: eliminarMiembro(); break;
            case 0: System.out.println("Volviendo al menú principal..."); break;
            default: System.out.println("Opción no válida.");
        }
    } while (opcion != 0);
}
private static void verMiembros() {
    System.out.println("\n=== LISTA DE MIEMBROS ===");
    for (int i = 0; i < cantMiembros; i++) {
        System.out.println(miembros[i].toString());
    }
}
private static void agregarMiembro() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Ingrese el DNI del miembro: ");
    String dni = scanner.nextLine();
    System.out.print("Ingrese los nombres del miembro: ");
    String nombres = scanner.nextLine();
    System.out.print("Ingrese los apellidos del miembro: ");
    String apellidos = scanner.nextLine();
    System.out.print("Ingrese la fecha de nacimiento (YYYY-MM-DD): ");
    String fechaNacimiento = scanner.nextLine();
    System.out.print("Ingrese la dirección: ");
    String direccion = scanner.nextLine();
    System.out.print("Ingrese el teléfono: ");
    String telefono = scanner.nextLine();
    System.out.print("Ingrese el correo: ");
    String correo = scanner.nextLine();
    System.out.print("Ingrese el código de la membresía: ");
    String codigoMembresia = scanner.nextLine();
    System.out.print("Ingrese la fecha de inicio (YYYY-MM-DD): ");
    String fechaInicio = scanner.nextLine();
    System.out.print("Ingrese la fecha de vencimiento (YYYY-MM-DD): ");
    String fechaVencimiento = scanner.nextLine();
    System.out.print("Ingrese el estado (Activo/Inactivo): ");
    String estado = scanner.nextLine();

    TipoMembresia tipoMembresia = buscarTipoMembresia(codigoMembresia);
    if (tipoMembresia == null) {
        System.out.println("Membresía no encontrada. No se puede agregar el miembro.");
        return;
    }

    Miembro nuevoMiembro = new Miembro(dni, nombres, apellidos, fechaNacimiento, direccion, telefono, correo, tipoMembresia, fechaInicio, fechaVencimiento, estado);
    if (cantMiembros == miembros.length) {
        redimensionarMiembros();
    }
    miembros[cantMiembros++] = nuevoMiembro;
    System.out.println("Miembro agregado exitosamente.");
}

private static TipoMembresia buscarTipoMembresia(String codigo) {
    for (int i = 0; i < cantMembresias; i++) {
        if (tiposMembresia[i].getCodigo().equals(codigo)) {
            return tiposMembresia[i];
        }
    }
    return null;
}
private static void eliminarMiembro() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Ingrese el DNI del miembro a eliminar: ");
    String dni = scanner.nextLine();
    
    for (int i = 0; i < cantMiembros; i++) {
        if (miembros[i].getDni().equals(dni)) {
            // Desplazar elementos hacia la izquierda
            for (int j = i; j < cantMiembros - 1; j++) {
                miembros[j] = miembros[j + 1];
            }
            miembros[--cantMiembros] = null; // Limpiar la última posición
            System.out.println("Miembro eliminado exitosamente.");
            return;
        }
    }
    System.out.println("Miembro no encontrado.");
}
private static void agregarMembresia() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Ingrese el código de la membresía: ");
    String codigo = scanner.nextLine();
    System.out.print("Ingrese el nombre de la membresía: ");
    String nombre = scanner.nextLine();
    System.out.print("Ingrese el precio de la membresía: ");
    double precio = Double.parseDouble(scanner.nextLine());
    System.out.print("Ingrese la duración en días: ");
    int duracionDias = Integer.parseInt(scanner.nextLine());
    TipoMembresia nuevaMembresia = new TipoMembresia(codigo, nombre, precio, duracionDias);
    if (cantMembresias == tiposMembresia.length) {
        redimensionarMembresias();
    }
    tiposMembresia[cantMembresias++] = nuevaMembresia;
    System.out.println("Membresía agregada exitosamente.");
}
private static void eliminarMembresia() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Ingrese el código de la membresía a eliminar: ");
    String codigo = scanner.nextLine();
    
    for (int i = 0; i < cantMembresias; i++) {
        if (tiposMembresia[i].getCodigo().equals(codigo)) {
            // Desplazar elementos hacia la izquierda
            for (int j = i; j < cantMembresias - 1; j++) {
                tiposMembresia[j] = tiposMembresia[j + 1];
            }
            tiposMembresia[--cantMembresias] = null; // Limpiar la última posición
            System.out.println("Membresía eliminada exitosamente.");
            return;
        }
    }
    System.out.println("Membresía no encontrada.");
}
}