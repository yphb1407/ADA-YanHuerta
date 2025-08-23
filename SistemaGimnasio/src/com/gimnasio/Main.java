package com.gimnasio;
import com.gimnasio.app.GimnasioApp;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        // 1. Cargar datos desde archivos
        GimnasioApp.cargarDatos();
        
        // 2. Autenticación
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== SISTEMA DE GESTIÓN DE GIMNASIO ===");
        
        int intentos = 0;
        boolean autenticado = false;
        
        while (!autenticado && intentos < 3) {
            System.out.print("Usuario: ");
            String usuario = scanner.nextLine();
            System.out.print("Contraseña: ");
            String contrasena = scanner.nextLine();
            
            if (GimnasioApp.iniciarSesion(usuario, contrasena)) {
                autenticado = true;
                System.out.println("¡Acceso concedido!");
                GimnasioApp.mostrarMenuPrincipal();
            } else {
                intentos++;
                System.out.println("Credenciales incorrectas. Intentos restantes: " + (3 - intentos));
            }
        }
        
        if (!autenticado) {
            System.out.println("Acceso denegado. Saliendo...");
        }
        scanner.close();
    }
}