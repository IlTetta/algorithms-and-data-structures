package com.andreat.algorithms;

public class SearchingAlgorithms {

    // ---------- RICERCA BINARIA ----------
    /**
     * Implementazione iterativa della ricerca binaria.
     * Array DEVE essere ordinato.
     * Complessità: O(log n)
     * @param arr Array ordinato
     * @param target Elemento da cercare
     * @return Indice dell'elemento cercato, -1 se non trovato
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if(arr[mid] == target) {
                return mid;
            }

            if(arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    /**
     * Implementazione ricorsiva della ricerca binaria.
     * Array DEVE essere ordinato.
     *
     * @param arr Array ordinato
     * @param target Elemento da cercare
     * @return Indice dell'elemento cercato, -1 se non trovato
     */
    public static int binarySearchRecursive(int[] arr, int target) {
        return binarySearchRecursive(arr, target, 0, arr.length - 1);
    }

    private static int binarySearchRecursive(int[] arr, int target, int left, int right) {
        if(left > right)
            return -1;

        int mid = left + (right - left) / 2;

        if(arr[mid] == target)
            return mid;

        if(arr[mid] > target)
            return binarySearchRecursive(arr, target, left, mid - 1);

        return binarySearchRecursive(arr, target, mid + 1, right);
    }

    // ---------- RICERCA LINEARE ----------
    /**
     * Implementazione della ricerca lineare.
     * Funziona su array ordinati e non ordinati.
     * Complessità: O(n)
     * @param arr
     * @param target
     * @return
     */
    public static int linearSearch(int[] arr, int target) {
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == target)
                return i;
        }

        return -1;
    }

    // ---------- JUMP SEARCH ----------
    /**
     * Implementazione della ricerca a salti.
     * Array DEVE essere ordinato.
     * Complessità: O(√n)
     * @param arr
     * @param target
     * @return
     */
    public static int jumpSearch(int[] arr, int target) {
        int n = arr.length;
        int step = (int) Math.floor(Math.sqrt(n));
        int prev = 0;

        while(arr[Math.min(step, n) - 1] < target) {
            prev = step;
            step += (int) Math.floor(Math.sqrt(n));
            if(prev >= n)
                return -1;
        }

        while(arr[prev] < target) {
            prev++;
            if(prev == Math.min(step, n))
                return -1;
        }

        if(arr[prev] == target)
            return prev;

        return -1;
    }

    // ---------- INTERPOLATION SEARCH ----------

    /**
     * Implementazione della ricerca per interpolazione.
     * Array DEVE essere ordinato.
     * Migliore di binary search per array distribuiti uniformemente.
     * Complessità: O(log log n) in media, O(n) nel caso peggiore
     * @param arr Array ordinato
     * @param target Elemento da cercare
     * @return Indice dell'elemento cercato, -1 se non trovato
     */
    public static int interpolationSearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while(low <= high && target >= arr[low] && target <= arr[high]){
            if(low == high){
                if(arr[low] == target) return low;
                return -1;
            }

            int pos = low + ((target - arr[low]) * (high - low) /
                    (arr[high] - arr[low]));

            if(arr[pos] == target)
                return pos;

            if(arr[pos] < target)
                low = pos + 1;
            else
                high = pos - 1;
        }

        return -1;
    }

    // ---------- EXPONENTIAL SEARCH ----------

    /**
     * Implementazione della ricerca esponenziale.
     * Array DEVE essere ordinato.
     * Utile quando l'elemento è vicino all'inizio dell'array.
     * Complessità: O(log n)
     * @param arr Array ordinato
     * @param target Elemento da cercare
     * @return Indice dell'elemento cercato, -1 se non trovato
     */
    public static int exponentialSearch(int[] arr, int target){
        int n = arr.length;

        if(arr[0] == target)
            return 0;

        int i = 1;
        while(i < n && arr[i] <= target)
            i *= 2;

        return binarySearchRecursive(arr, target, i / 2, Math.min(i, n-1));
    }

    // ---------- BREATH-FIRST SEARCH (BFS) ----------

    /**
     * Implementazione di BFS su un grafo rappresentato con matrice di adiacenza.
     * Complessità: O(V + E) dove V è il numero di vertici e E il numero di archi.
     * @param graph Grafo rappresentato con matrice di adiacenza
     * @param start Nodo di partenza
     */
    public static void bfs(int[][] graph, int start){
        int n = graph.length;
        boolean[] visited = new boolean[n];

        java.util.Queue<Integer> queue = new java.util.LinkedList<>();

        visited[start] = true;
        queue.add(start);

        System.out.println("BFS a partire dal nodo " + start + ":");

        while(!queue.isEmpty()){
            int current = queue.poll();
            System.out.print(current + " ");

            for(int i = 0; i < n; i++){
                if(graph[current][i] == 1 && !visited[i]){
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }

        System.out.println();
    }

    // ---------- DEPTH-FIRST SEARCH (DFS) ----------

    /**
     * Implementazione di DFS su un grafo rappresentato con matrice di adiacenza.
     * Complessità: O(V + E) dove V è il numero di vertici e E il numero di archi.
     * @param graph Grafo rappresentato con matrice di adiacenza
     * @param start Nodo di partenza
     */
    public static void dfs(int[][] graph, int start){
        int n = graph.length;
        boolean[] visited = new boolean[n];

        System.out.println("DFS a partire dal nodo " + start + ":");
        dfsRecursive(graph, start, visited);
        System.out.println();
    }

    private static void dfsRecursive(int[][] graph, int vertex, boolean[] visited){
        visited[vertex] = true;
        System.out.print(vertex + " ");

        int n = graph.length;
        for(int i = 0; i < n; i++) {
            if (graph[vertex][i] == 1 && !visited[i])
                dfsRecursive(graph, i, visited);
        }
    }

    // ---------- ALGORITMO DI DIJKSTRA ----------

    /**
     * Implementazione dell'algoritmo di Dijkstra per trovare i cammini minimi da un nodo sorgente.
     * Grafo rappresentato con matrice di adiacenza.
     * Complessità: O(V^2) dove V è il numero di vertici.
     * @param graph Grafo rappresentato con matrice di adiacenza
     * @param start Nodo sorgente
     */
    public static void dijkstra(int[][] graph, int start){
        int n = graph.length;
        int[] dist = new int[n];
        boolean[] sptSet = new boolean[n];

        for(int i = 0; i < n; i++){
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        dist[start] = 0;

        for(int count = 0; count < n-1; count++){
            int u = minDistance(dist, sptSet, n);

            sptSet[u] = true;

            for(int v = 0; v < n; v++){
                if(!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE &&
                        dist[u] + graph[u][v] < dist[v]){
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        System.out.println("Distanze minime dal nodo sorgente " + start + ":");
        for(int i = 0; i < n; i++){
            System.out.println("Al nodo " + i + ": " + (dist[i] == Integer.MAX_VALUE ? "INFINITO" : dist[i]));
        }
    }

    private static int minDistance(int[] dist, boolean[] sptSet, int n){
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for(int v = 0; v < n; v++){
            if(!sptSet[v] && dist[v] <= min){
                min = dist[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    // ---------- A* SEARCH ALGORITHM ----------




    // Metodo main per testare gli algoritmi
    public static void main(String[] args) {
        // Test della ricerca binaria e altre ricerche su array
        int[] sortedArray = {2, 3, 4, 10, 40, 50, 60, 70, 80, 90};
        int target = 10;

        System.out.println("Array ordinato:");
        printArray(sortedArray);

        int binaryResult = binarySearch(sortedArray, target);
        System.out.println("\nRicerca Binaria per " + target +
                ": " + (binaryResult == -1 ? "Elemento non trovato" :
                "Elemento trovato all'indice " + binaryResult));

        int recursiveResult = binarySearchRecursive(sortedArray, target);
        System.out.println("Ricerca Binaria Ricorsiva per " + target +
                ": " + (recursiveResult == -1 ? "Elemento non trovato" :
                "Elemento trovato all'indice " + recursiveResult));

        int linearResult = linearSearch(sortedArray, target);
        System.out.println("Ricerca Lineare per " + target +
                ": " + (linearResult == -1 ? "Elemento non trovato" :
                "Elemento trovato all'indice " + linearResult));

        int jumpResult = jumpSearch(sortedArray, target);
        System.out.println("Jump Search per " + target +
                ": " + (jumpResult == -1 ? "Elemento non trovato" :
                "Elemento trovato all'indice " + jumpResult));

        int interpolationResult = interpolationSearch(sortedArray, target);
        System.out.println("Interpolation Search per " + target +
                ": " + (interpolationResult == -1 ? "Elemento non trovato" :
                "Elemento trovato all'indice " + interpolationResult));

        int exponentialResult = exponentialSearch(sortedArray, target);
        System.out.println("Exponential Search per " + target +
                ": " + (exponentialResult == -1 ? "Elemento non trovato" :
                "Elemento trovato all'indice " + exponentialResult));

        // Test di algoritmi per grafi
        System.out.println("\n--- Test algoritmi di ricerca su grafi ---");

        // Esempio di grafo rappresentato come matrice di adiacenza
        // 0 -- 1 -- 2
        // |    |    |
        // 3 -- 4    5
        //
        // I numeri indicano i nodi, le linee indicano le connessioni
        int[][] graph = new int[][] {
                {0, 1, 0, 1, 0, 0},  // Nodo 0 è connesso a 1 e 3
                {1, 0, 1, 0, 1, 0},  // Nodo 1 è connesso a 0, 2 e 4
                {0, 1, 0, 0, 0, 1},  // Nodo 2 è connesso a 1 e 5
                {1, 0, 0, 0, 1, 0},  // Nodo 3 è connesso a 0 e 4
                {0, 1, 0, 1, 0, 0},  // Nodo 4 è connesso a 1 e 3
                {0, 0, 1, 0, 0, 0}   // Nodo 5 è connesso a 2
        };

        // Esegui BFS dal nodo 0
        bfs(graph, 0);

        // Esegui DFS dal nodo 0
        dfs(graph, 0);

        // Esempio di grafo con pesi per Dijkstra
        // Usiamo 0 per indicare nessuna connessione
        int[][] weightedGraph = new int[][] {
                {0, 4, 0, 0, 0, 0},  // Da nodo 0 a nodo 1 costa 4
                {4, 0, 8, 0, 0, 0},  // ecc.
                {0, 8, 0, 0, 0, 1},
                {0, 0, 0, 0, 9, 0},
                {0, 0, 0, 9, 0, 10},
                {0, 0, 1, 0, 10, 0}
        };

        // Esegui Dijkstra dal nodo 0
        dijkstra(weightedGraph, 0);

        // Commento sull'A*
        System.out.println("\n--- Algoritmo A* ---");
        //aStarSearch();
    }

    // Metodo di utilità per stampare un array
    private static void printArray(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
