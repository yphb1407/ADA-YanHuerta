package c2;

import javax.swing.*;
import java.awt.*;

public class C2 extends JFrame {
    
    private JLabel etiquetaPromedio;
    private JTextArea areaCalificaciones;
    
    public C2() {
        // Configurar la ventana
        setTitle("Calculadora de Promedio de Calificaciones");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 250);
        setLocationRelativeTo(null); // Centrar la ventana
        
        // Crear panel principal con BorderLayout
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Crear y agregar etiqueta de título
        JLabel etiquetaTitulo = new JLabel("Promedio de Calificaciones", SwingConstants.CENTER);
        etiquetaTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        panelPrincipal.add(etiquetaTitulo, BorderLayout.NORTH);
        
        // Crear área de visualización de calificaciones
        areaCalificaciones = new JTextArea(5, 20);
        areaCalificaciones.setEditable(false);
        areaCalificaciones.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane panelDesplazamiento = new JScrollPane(areaCalificaciones);
        panelPrincipal.add(panelDesplazamiento, BorderLayout.CENTER);
        
        // Panel inferior con etiqueta de promedio y botón finalizar
        JPanel panelInferior = new JPanel(new BorderLayout(5, 5));
        
        etiquetaPromedio = new JLabel("Promedio: Calculando...");
        etiquetaPromedio.setFont(new Font("Arial", Font.BOLD, 14));
        panelInferior.add(etiquetaPromedio, BorderLayout.CENTER);
        
        JButton botonFinalizar = new JButton("Finalizar");
        botonFinalizar.addActionListener(e -> System.exit(0));
        panelInferior.add(botonFinalizar, BorderLayout.EAST);
        
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);
        
        // Agregar panel principal al marco
        add(panelPrincipal);
        
        // Calcular y mostrar calificaciones
        mostrarCalificaciones();
    }
    
    private void mostrarCalificaciones() {
        double[] calificaciones = {18, 20, 15, 14, 12, 16};
        double suma = 0;
        
        // Construir cadena de calificaciones
        StringBuilder textoCalificaciones = new StringBuilder("Calificaciones:\n");
        for (int i = 0; i < calificaciones.length; i++) {
            textoCalificaciones.append("Calificación ").append(i + 1).append(": ").append(calificaciones[i]).append("\n");
            suma += calificaciones[i];
        }
        
        double promedio = suma / calificaciones.length;
        
        // Actualizar componentes de la interfaz
        areaCalificaciones.setText(textoCalificaciones.toString());
        etiquetaPromedio.setText(String.format("Promedio: %.2f", promedio));
    }

    public static void main(String[] args) {
        // Ejecutar la interfaz gráfica en el hilo de despacho de eventos
        SwingUtilities.invokeLater(() -> {
            C2 marco = new C2();
            marco.setVisible(true);
        });
    }
}
