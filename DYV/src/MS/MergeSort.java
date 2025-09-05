package MS;
import java.util.Arrays;
import java.io.*;
public class MergeSort {
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        mergeSort(arr, 0, arr.length - 1);
    }
    private static void mergeSort(int[] arr, int lower, int upper) {
        if (lower < upper) {
            int mid = lower + (upper - lower) / 2; // Evita overflow
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
    public static void guardarEnArchivo(int[] arr, String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write("Array ordenado: " + Arrays.toString(arr));
            writer.newLine();
            writer.write("Tamaño: " + arr.length + " elementos");
            System.out.println("Resultado guardado en: " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }
    public static int[] leerDesdeArchivo(String nombreArchivo) {
        try (DataInputStream dis = new DataInputStream(
              new FileInputStream(nombreArchivo))) {
            
            int size = dis.readInt();
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = dis.readInt();
            }
            System.out.println("Archivo leído exitosamente: " + nombreArchivo);
            return arr;
            
        } catch (IOException e) {
            System.out.println("Error al leer archivo: " + e.getMessage());
            return null;
        }
    }
    public static void main(String[] args) {
        int[] myArr = {1, 4, 2, 3, 6, 9, 7, 10, 8, 5};
        System.out.println("Array original: " + Arrays.toString(myArr));
        mergeSort(myArr);
        System.out.println("Array ordenado: " + Arrays.toString(myArr));
        guardarEnArchivo(myArr, "resultado_mergesort.txt");
    }
}
/*
Descripción:
Este programa implementa el algoritmo de ordenamiento Merge Sort en Java 
con funcionalidades adicionales de entrada/salida en archivos.

Características principales:
1. Ordenamiento:
   - El método `mergeSort(int[] arr)` organiza un arreglo de enteros en 
     orden ascendente utilizando el algoritmo Merge Sort (divide y vencerás).
   - Se emplea un método auxiliar `merge()` que combina dos subarreglos
     previamente ordenados en uno solo ordenado.

2. Entrada/Salida de Archivos:
   - `guardarEnArchivo(int[] arr, String nombreArchivo)`:
        Guarda el arreglo ya ordenado en un archivo de texto, incluyendo 
        su contenido y el tamaño total.
   - `leerDesdeArchivo(String nombreArchivo)`:
        Lee desde un archivo binario que contiene primero el tamaño del 
        arreglo (int) y luego los elementos del arreglo (int).
        Devuelve el arreglo leído.

3. Seguridad:
   - Se usa `try-with-resources` para manejar correctamente los streams 
     de archivos y evitar fugas de recursos.
   - En el cálculo del punto medio, se utiliza `lower + (upper - lower) / 2` 
     para evitar posibles desbordamientos (overflow).

Ejemplo de uso:
- Arreglo inicial: [1, 4, 2, 3, 6, 9, 7, 10, 8, 5]
- Tras aplicar Merge Sort, se obtiene: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
- El resultado se guarda en el archivo "resultado_mergesort.txt".
*/


