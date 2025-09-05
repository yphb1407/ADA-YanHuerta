package MS;
import java.util.Arrays;
import java.io.*;

public class MergeSort {
    
    // Interfaz principal más amigable
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        mergeSort(arr, 0, arr.length - 1);
    }
    
    // Método recursivo interno
    private static void mergeSort(int[] arr, int lower, int upper) {
        if (lower < upper) {
            int mid = lower + (upper - lower) / 2; // Evita overflow
            mergeSort(arr, lower, mid);
            mergeSort(arr, mid + 1, upper);
            merge(arr, lower, mid, upper);
        }
    }
    
    // Método para fusionar los subarrays
    private static void merge(int[] arr, int lower, int mid, int upper) {
        int leftSize = mid - lower + 1;
        int rightSize = upper - mid;
        
        int[] leftArray = new int[leftSize];
        int[] rightArray = new int[rightSize];
        
        // Copiar datos a arrays temporales
        for (int i = 0; i < leftSize; i++) {
            leftArray[i] = arr[lower + i];
        }
        for (int j = 0; j < rightSize; j++) {
            rightArray[j] = arr[mid + 1 + j];
        }
        
        // Fusionar los arrays temporales
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
        
        // Copiar elementos restantes
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
    
    // Método para guardar en archivo de texto
    public static void guardarEnArchivo(int[] arr, String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write("Array ordenado: " + Arrays.toString(arr));
            writer.newLine();
            writer.write("Tamaño: " + arr.length + " elementos");
            System.out.println("✅ Resultado guardado en: " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("❌ Error al guardar: " + e.getMessage());
        }
    }
    
    // Método para leer desde archivo binario
    public static int[] leerDesdeArchivo(String nombreArchivo) {
        try (DataInputStream dis = new DataInputStream(
              new FileInputStream(nombreArchivo))) {
            
            int size = dis.readInt();
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = dis.readInt();
            }
            System.out.println("✅ Archivo leído exitosamente: " + nombreArchivo);
            return arr;
            
        } catch (IOException e) {
            System.out.println("❌ Error al leer archivo: " + e.getMessage());
            return null;
        }
    }
    
    // Método principal de prueba
    public static void main(String[] args) {
        int[] myArr = {1, 4, 2, 3, 6, 9, 7, 10, 8, 5};
        
        System.out.println("Array original: " + Arrays.toString(myArr));
        
        // Ordenar el array
        mergeSort(myArr);
        System.out.println("Array ordenado: " + Arrays.toString(myArr));
        
        // Guardar resultados
        guardarEnArchivo(myArr, "resultado_mergesort.txt");
    }
}

