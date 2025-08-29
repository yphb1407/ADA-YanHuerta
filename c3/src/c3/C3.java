package c3;
import javax.swing.*;
import java.awt.*;
public class C3 extends JFrame {
    private JLabel etiquetaPromedio;
    private JTextArea areaCalificaciones;
    private JComboBox<String> comboOrden; 
    private double[] calificaciones; // ahora se llenará con un for
    public C3() {
        setTitle("Calculadora de Promedio con Ordenación por Inserción");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        calificaciones = new double[6]; // ejemplo: 6 notas
        for (int i = 0; i < calificaciones.length; i++) {
            calificaciones[i] = Double.parseDouble(
                JOptionPane.showInputDialog("Ingrese la nota " + (i + 1) + ":")
            );
        }
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel etiquetaTitulo = new JLabel("Promedio y Ordenación de Calificaciones", SwingConstants.CENTER);
        etiquetaTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        panelPrincipal.add(etiquetaTitulo, BorderLayout.NORTH);
        areaCalificaciones = new JTextArea(7, 25);
        areaCalificaciones.setEditable(false);
        areaCalificaciones.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane panelDesplazamiento = new JScrollPane(areaCalificaciones);
        panelPrincipal.add(panelDesplazamiento, BorderLayout.CENTER);
        JPanel panelInferior = new JPanel(new BorderLayout(5, 5));
        etiquetaPromedio = new JLabel("Promedio: Calculando...");
        etiquetaPromedio.setFont(new Font("Arial", Font.BOLD, 14));
        panelInferior.add(etiquetaPromedio, BorderLayout.CENTER);
        comboOrden = new JComboBox<>(new String[]{"Ascendente", "Descendente"});
        comboOrden.addActionListener(e -> mostrarCalificaciones());
        panelInferior.add(comboOrden, BorderLayout.WEST);
        JButton botonFinalizar = new JButton("Finalizar");
        botonFinalizar.addActionListener(e -> System.exit(0));
        panelInferior.add(botonFinalizar, BorderLayout.EAST);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);
        add(panelPrincipal);
        mostrarCalificaciones();
    }
    private void mostrarCalificaciones() {
        double[] copia = calificaciones.clone();
        if (comboOrden.getSelectedItem().equals("Ascendente")) {
            Insercion(copia, true);
        } else {
            Insercion(copia, false);
        }
        double suma = 0;
        StringBuilder textoCalificaciones = new StringBuilder("Calificaciones ordenadas:\n");
        for (int i = 0; i < copia.length; i++) {
            textoCalificaciones.append("Calificación ").append(i + 1).append(": ").append(copia[i]).append("\n");
            suma += copia[i];
        }
        double promedio = suma / copia.length;
        areaCalificaciones.setText(textoCalificaciones.toString());
        etiquetaPromedio.setText(String.format("Promedio: %.2f", promedio));
    }
    private void Insercion(double[] arreglo, boolean ascendente) {
        for (int i = 1; i < arreglo.length; i++) {
            double key = arreglo[i];
            int j = i - 1;
            while (j >= 0 && ((ascendente && arreglo[j] > key) ||
                              (!ascendente && arreglo[j] < key))) {
                arreglo[j + 1] = arreglo[j];
                j--;
            }
            arreglo[j + 1] = key;
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            C3 marco = new C3();
            marco.setVisible(true);
        });
    }
}