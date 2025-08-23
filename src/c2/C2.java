package c2;

import javax.swing.*;
import java.awt.*;

public class C2 extends JFrame {
    
    private JLabel etiquetaPromedio;
    private JTextArea areaCalificaciones;
    private JComboBox<String> comboOrden; 
    private double[] calificaciones = {18, 20, 15, 14, 12, 16}; // calificaciones iniciales
    
    public C2() {
        // Configurar la ventana
        setTitle("Calculadora de Promedio con Ordenación Burbuja");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Centrar la ventana
        
        // Crear panel principal con BorderLayout
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Crear y agregar etiqueta de título
        JLabel etiquetaTitulo = new JLabel("Promedio y Ordenación de Calificaciones", SwingConstants.CENTER);
        etiquetaTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        panelPrincipal.add(etiquetaTitulo, BorderLayout.NORTH);
        
        // Crear área de visualización de calificaciones
        areaCalificaciones = new JTextArea(7, 25);
        areaCalificaciones.setEditable(false);
        areaCalificaciones.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane panelDesplazamiento = new JScrollPane(areaCalificaciones);
        panelPrincipal.add(panelDesplazamiento, BorderLayout.CENTER);
        
        // Panel inferior con promedio, selector de orden y botón finalizar
        JPanel panelInferior = new JPanel(new BorderLayout(5, 5));
        
        // Etiqueta de promedio
        etiquetaPromedio = new JLabel("Promedio: Calculando...");
        etiquetaPromedio.setFont(new Font("Arial", Font.BOLD, 14));
        panelInferior.add(etiquetaPromedio, BorderLayout.CENTER);
        
        // ComboBox para elegir el orden
        comboOrden = new JComboBox<>(new String[]{"Ascendente", "Descendente"});
        comboOrden.addActionListener(e -> mostrarCalificaciones()); // actualizar al cambiar
        panelInferior.add(comboOrden, BorderLayout.WEST);
        
        // Botón finalizar
        JButton botonFinalizar = new JButton("Finalizar");
        botonFinalizar.addActionListener(e -> System.exit(0));
        panelInferior.add(botonFinalizar, BorderLayout.EAST);
        
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);
        
        // Agregar panel principal al marco
        add(panelPrincipal);
        
        // Mostrar calificaciones iniciales
        mostrarCalificaciones();
    }
    
    private void mostrarCalificaciones() {
        // Copiamos el arreglo original para no modificarlo directamente
        double[] copia = calificaciones.clone();
        
        // Ordenamos según la opción seleccionada
        if (comboOrden.getSelectedItem().equals("Ascendente")) {
            ordenarBurbuja(copia, true);
        } else {
            ordenarBurbuja(copia, false);
        }
        
        double suma = 0;
        
        // Construir cadena de calificaciones
        StringBuilder textoCalificaciones = new StringBuilder("Calificaciones ordenadas:\n");
        for (int i = 0; i < copia.length; i++) {
            textoCalificaciones.append("Calificación ").append(i + 1).append(": ").append(copia[i]).append("\n");
            suma += copia[i];
        }
        
        double promedio = suma / copia.length;
        
        // Actualizar componentes de la interfaz
        areaCalificaciones.setText(textoCalificaciones.toString());
        etiquetaPromedio.setText(String.format("Promedio: %.2f", promedio));
    }
    
    // Método para ordenar con Burbuja (true = ascendente, false = descendente)
    private void ordenarBurbuja(double[] arreglo, boolean ascendente) {
        int n = arreglo.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if ((ascendente && arreglo[j] > arreglo[j + 1]) ||
                    (!ascendente && arreglo[j] < arreglo[j + 1])) {
                    
                    // Intercambio
                    double temp = arreglo[j];
                    arreglo[j] = arreglo[j + 1];
                    arreglo[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        // Ejecutar la interfaz gráfica en el hilo de despacho de eventos
        SwingUtilities.invokeLater(() -> {
            C2 marco = new C2();
            marco.setVisible(true);
        });
    }
}
