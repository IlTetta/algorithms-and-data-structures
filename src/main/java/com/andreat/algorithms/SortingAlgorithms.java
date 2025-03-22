package com.andreat.algorithms;


public class SortingAlgorithms {

    // ---------- QUICK SORT ----------
    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    // ---------- MERGE SORT ----------
    public static void mergeSort(int[] arr) {
        if (arr.length <= 1) return;

        int[] temp = new int[arr.length];
        mergeSort(arr, temp, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int[] temp, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, temp, left, mid);
            mergeSort(arr, temp, mid + 1, right);
            merge(arr, temp, left, mid, right);
        }
    }

    private static void merge(int[] arr, int[] temp, int left, int mid, int right) {
        // Copia entrambi i sottovettori nell'array temporaneo
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
        }

        int i = left;     // Indice del primo sottovettore
        int j = mid + 1;  // Indice del secondo sottovettore
        int k = left;     // Indice dell'array originale

        // Unisci i due sottovettori in modo ordinato
        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                arr[k] = temp[i];
                i++;
            } else {
                arr[k] = temp[j];
                j++;
            }
            k++;
        }

        // Copia gli elementi rimanenti del primo sottovettore
        while (i <= mid) {
            arr[k] = temp[i];
            i++;
            k++;
        }

        // Gli elementi rimanenti del secondo sottovettore sono già in posizione
    }

    // ---------- HEAP SORT ----------
    public static void heapSort(int[] arr) {
        int n = arr.length;

        // Costruisci l'heap (riordina l'array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Estrai gli elementi uno ad uno dall'heap
        for (int i = n - 1; i > 0; i--) {
            // Sposta la radice attuale alla fine
            swap(arr, 0, i);

            // Richiama heapify sull'heap ridotto
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;  // Inizializza il più grande come radice
        int left = 2 * i + 1;  // left = 2*i + 1
        int right = 2 * i + 2;  // right = 2*i + 2

        // Se il figlio sinistro è più grande della radice
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        // Se il figlio destro è più grande del più grande finora
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // Se il più grande non è la radice
        if (largest != i) {
            swap(arr, i, largest);

            // Riapplica heapify sul sottoalbero coinvolto
            heapify(arr, n, largest);
        }
    }

    // ---------- INSERTION SORT ----------
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            // Sposta gli elementi di arr[0..i-1], che sono maggiori di key,
            // una posizione avanti rispetto alla loro posizione attuale
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    // ---------- BUBBLE SORT ----------
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    swapped = true;
                }
            }

            // Se non è stato eseguito alcuno scambio in questa passata,
            // l'array è già ordinato
            if (!swapped) break;
        }
    }

    // ---------- COUNTING SORT ----------
    public static void countingSort(int[] arr) {
        int max = findMax(arr);
        int min = findMin(arr);
        int range = max - min + 1;

        int[] count = new int[range];
        int[] output = new int[arr.length];

        // Memorizza il conteggio di ogni elemento
        for (int value : arr) {
            count[value - min]++;
        }

        // Calcola le posizioni cumulative
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // Costruisci l'array di output
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }

        // Copia l'array di output nell'array originale
        System.arraycopy(output, 0, arr, 0, arr.length);
    }

    private static int findMax(int[] arr) {
        int max = arr[0];
        for (int value : arr) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    private static int findMin(int[] arr) {
        int min = arr[0];
        for (int value : arr) {
            if (value < min) {
                min = value;
            }
        }
        return min;
    }

    // ---------- RADIX SORT ----------
    public static void radixSort(int[] arr) {
        // Trova il valore massimo per determinare il numero di cifre
        int max = findMax(arr);

        // Esegui il counting sort per ogni posizione delle cifre
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(arr, exp);
        }
    }

    private static void countingSortByDigit(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10]; // 0-9 cifre

        // Memorizza il conteggio delle occorrenze in count[]
        for (int value : arr) {
            count[(value / exp) % 10]++;
        }

        // Modifica count[] in modo che contenga le posizioni effettive
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Costruisci l'array di output
        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        // Copia l'array di output in arr[]
        System.arraycopy(output, 0, arr, 0, n);
    }

    // Metodo di utilità per scambiare due elementi in un array
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Metodo main per testare gli algoritmi
    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};

        // Crea copie dell'array per testare ogni algoritmo
        int[] arrQuick = arr.clone();
        int[] arrMerge = arr.clone();
        int[] arrHeap = arr.clone();
        int[] arrInsertion = arr.clone();
        int[] arrBubble = arr.clone();
        int[] arrCounting = arr.clone();
        int[] arrRadix = arr.clone();

        System.out.println("Array originale:");
        printArray(arr);

        quickSort(arrQuick);
        System.out.println("\nDopo QuickSort:");
        printArray(arrQuick);

        mergeSort(arrMerge);
        System.out.println("\nDopo MergeSort:");
        printArray(arrMerge);

        heapSort(arrHeap);
        System.out.println("\nDopo HeapSort:");
        printArray(arrHeap);

        insertionSort(arrInsertion);
        System.out.println("\nDopo InsertionSort:");
        printArray(arrInsertion);

        bubbleSort(arrBubble);
        System.out.println("\nDopo BubbleSort:");
        printArray(arrBubble);

        countingSort(arrCounting);
        System.out.println("\nDopo CountingSort:");
        printArray(arrCounting);

        radixSort(arrRadix);
        System.out.println("\nDopo RadixSort:");
        printArray(arrRadix);
    }

    // Metodo di utilità per stampare un array
    private static void printArray(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
