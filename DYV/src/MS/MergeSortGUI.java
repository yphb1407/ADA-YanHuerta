package MS;
import java.util.Arrays;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MergeSortGUI extends JFrame {
    
    private JTextArea areaTexto;
    private int[] myArr;
    private final Random random = new Random();
    
    public MergeSortGUI() {
        setTitle("Merge Sort - Ordenador de Arrays");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new BorderLayout(10, 10));
        
        // --- Título ---
        JLabel titulo = new JLabel("Ordenador con Merge Sort", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setForeground(new Color(30, 144, 255));
        add(titulo, BorderLayout.NORTH);
        
        // --- Área de texto ---
        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setFont(new Font("Consolas", Font.PLAIN, 14));
        areaTexto.setBackground(new Color(245, 245, 245));
        JScrollPane scroll = new JScrollPane(areaTexto);
        add(scroll, BorderLayout.CENTER);
        
        // --- Panel de botones ---
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnGenerar = new JButton("🔄 Generar nuevo array");
        JButton btnGuardar = new JButton("💾 Guardar en TXT");
        JButton btnFinalizar = new JButton("❌ Finalizar");
        
        btnGenerar.setFont(new Font("Arial", Font.BOLD, 14));
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 14));
        btnFinalizar.setFont(new Font("Arial", Font.BOLD, 14));
        
        panelBotones.add(btnGenerar);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnFinalizar);
        add(panelBotones, BorderLayout.SOUTH);
        
        // --- Generar primer array automáticamente ---
        generarNuevoArray();
        
        // --- Acción botón Generar ---
        btnGenerar.addActionListener(e -> {
            generarNuevoArray();
        });
        
        // --- Acción botón Guardar ---
        btnGuardar.addActionListener(e -> {
            guardarEnArchivoTexto(myArr, "resultado_mergesort.txt");
        });
        
        // --- Acción botón Finalizar ---
        btnFinalizar.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(this,
                "¿Seguro que deseas salir?",
                "Confirmar salida",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
            if (opcion == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }
    
    // --- Generar un nuevo array aleatorio ---
    private void generarNuevoArray() {
        myArr = new int[50]; // tamaño fijo de 50 números
        for (int i = 0; i < myArr.length; i++) {
            myArr[i] = random.nextInt(100) + 1; // números entre 1 y 100
        }
        
        // Ordenar con MergeSort
        int[] copia = Arrays.copyOf(myArr, myArr.length);
        mergeSort(copia);
        
        // Mostrar en la interfaz
        areaTexto.setText(""); // limpiar texto
        areaTexto.append("Array generado (50 números):\n" + Arrays.toString(myArr) + "\n\n");
        areaTexto.append("Array ordenado:\n" + Arrays.toString(copia) + "\n");
    }
    
    // --- Algoritmo MergeSort ---
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        mergeSort(arr, 0, arr.length - 1);
    }
    
    private static void mergeSort(int[] arr, int lower, int upper) {
        if (lower < upper) {
            int mid = lower + (upper - lower) / 2;
            mergeSort(arr, lower, mid);
            mergeSort(arr, mid + 1, upper);
            merge(arr, lower, mid, upper);
        }
    }
    
    private static void merge(int[] arr, int lower, int mid, int upper) {
        int leftSize = mid - lower + 1;
        int rightSize = upper - mid;
        
        int[] leftArray = new int[leftSize];
        int[] rightArray = new int[rightSize];
        
        for (int i = 0; i < leftSize; i++) {
            leftArray[i] = arr[lower + i];
        }
        for (int j = 0; j < rightSize; j++) {
            rightArray[j] = arr[mid + 1 + j];
        }
        
        int i = 0, j = 0, k = lower;
        while (i < leftSize && j < rightSize) {
            if (leftArray[i] <= rightArray[j]) {
                arr[k] = leftArray[i];
                i++;
            } else {
                arr[k] = rightArray[j];
                j++;
            }
            k++;
        }
        
        while (i < leftSize) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }
        
        while (j < rightSize) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }
    }
    
    // --- Guardar en TXT ---
    public static void guardarEnArchivoTexto(int[] arr, String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write("Array original: " + Arrays.toString(arr));
            writer.newLine();
            
            int[] copia = Arrays.copyOf(arr, arr.length);
            mergeSort(copia);
            writer.write("Array ordenado: " + Arrays.toString(copia));
            
            writer.newLine();
            writer.write("Tamaño: " + arr.length + " elementos");
            JOptionPane.showMessageDialog(null, 
                "✅ Archivo guardado exitosamente en:\n" + nombreArchivo,
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al guardar: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // --- Main ---
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MergeSortGUI().setVisible(true);
        });
    }
}
