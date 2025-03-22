package com.andreat.data_structures;

public class Utilizzo {
    public static void main(String[] args) {
        // Esempio di utilizzo di Lista Concatenata
        System.out.println("===== LISTA CONCATENATA =====");
        LinkedList<Integer> list = new LinkedList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        list.addFirst(5);
        System.out.println("Lista: " + list);
        list.remove(20);
        System.out.println("Dopo la rimozione: " + list);
        System.out.println("Contiene 30? " + list.contains(30));
        System.out.println();

        // Esempio di utilizzo di Albero Binario di Ricerca
        System.out.println("===== ALBERO BINARIO DI RICERCA =====");
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);
        System.out.print("Visita inorder: ");
        bst.inorderTraversal();
        System.out.println("Contiene 40? " + bst.search(40));
        bst.delete(30);
        System.out.print("Dopo la rimozione di 30: ");
        bst.inorderTraversal();
        System.out.println();

        // Esempio di utilizzo di Max Heap
        System.out.println("===== MAX HEAP =====");
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        maxHeap.insert(10);
        maxHeap.insert(30);
        maxHeap.insert(20);
        maxHeap.insert(5);
        maxHeap.insert(1);
        System.out.println("Max Heap: " + maxHeap);
        System.out.println("Max value: " + maxHeap.peek());
        System.out.println("Extracted max: " + maxHeap.extractMax());
        System.out.println("Heap after extraction: " + maxHeap);
        System.out.println();

        // Esempio di utilizzo di Tabella Hash
        System.out.println("===== TABELLA HASH =====");
        HashMap<String, Integer> map = new HashMap<>();
        map.put("uno", 1);
        map.put("due", 2);
        map.put("tre", 3);
        System.out.println("Valore di 'due': " + map.get("due"));
        map.put("due", 22); // Aggiorna il valore
        System.out.println("Valore aggiornato di 'due': " + map.get("due"));
        map.remove("uno");
        System.out.println("Valore di 'uno' dopo la rimozione: " + map.get("uno"));
        System.out.println();

        // Esempio di utilizzo di Grafo con liste di adiacenza
        System.out.println("===== GRAFO (LISTE DI ADIACENZA) =====");
        Graph<String> graph = new Graph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "D");
        System.out.println("Grafo:");
        graph.printGraph();
        System.out.print("DFS partendo da A: ");
        graph.dfs("A");
        System.out.println();
        System.out.print("BFS partendo da A: ");
        graph.bfs("A");
        System.out.println("\n");

        // Esempio di utilizzo di Grafo con matrice di adiacenza
        System.out.println("===== GRAFO (MATRICE DI ADIACENZA) =====");
        GraphMatrix graphMatrix = new GraphMatrix(4);
        graphMatrix.addEdge(0, 1); // A -> B
        graphMatrix.addEdge(0, 2); // A -> C
        graphMatrix.addEdge(1, 3); // B -> D
        graphMatrix.addEdge(2, 3); // C -> D
        System.out.println("Matrice di adiacenza:");
        graphMatrix.printMatrix();
        System.out.print("DFS partendo da 0: ");
        graphMatrix.dfs(0);
        System.out.println();
        System.out.print("BFS partendo da 0: ");
        graphMatrix.bfs(0);
        System.out.println("\n");

        // Esempio di utilizzo di Trie
        System.out.println("===== TRIE =====");
        Trie trie = new Trie();
        trie.insert("cane");
        trie.insert("casa");
        trie.insert("cavallo");
        trie.insert("gatto");
        System.out.println("Contiene 'cane'? " + trie.search("cane"));
        System.out.println("Contiene 'cat'? " + trie.search("cat"));
        System.out.println("Inizia con 'ca'? " + trie.startsWith("ca"));
        trie.delete("cane");
        System.out.println("Contiene 'cane' dopo la rimozione? " + trie.search("cane"));
        System.out.println("Contiene ancora 'casa'? " + trie.search("casa"));
        System.out.println();

        // Esempio di utilizzo di Union-Find
        System.out.println("===== UNION-FIND =====");
        UnionFind uf = new UnionFind(10);
        uf.union(0, 1);
        uf.union(2, 3);
        uf.union(4, 5);
        uf.union(6, 7);
        uf.union(8, 9);
        uf.union(0, 2);
        uf.union(4, 6);
        uf.union(0, 4);
        System.out.println("0 e 9 sono connessi? " + uf.connected(0, 9));
        uf.union(8, 0);
        System.out.println("0 e 9 sono connessi dopo l'unione? " + uf.connected(0, 9));
        System.out.println("Numero di insiemi distinti: " + uf.countSets());
    }
}
