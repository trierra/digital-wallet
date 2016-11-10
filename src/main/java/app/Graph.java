package app;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Graph {
    private int V;
    private LinkedList<Integer>[] adj;

    public Graph(Scanner in, int V) {

        this.V = V;
//        this.V = 3938361;
        adj = (LinkedList<Integer>[]) new LinkedList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new LinkedList<Integer>();
        }

        in.nextLine();
        int index = 0;

        int wrongLines = 0;
        while (in.hasNext()) {
            index++;
            String line = in.nextLine();
            if (!line.contains(",")) {
                wrongLines++;
                continue;
            }
            String[] arr = line.split(",");
            if (arr.length < 5) {
                wrongLines++;
                System.out.println("Illegal line: " + line + ", index: " + index + ", arr: " + Arrays.toString(arr));
                continue;
            }
            try {
                int v1 = Integer.parseInt(arr[1].trim());
                int v2 = Integer.parseInt(arr[2].trim());
                addEdge(v1, v2);
            } catch (Exception x) {
                System.out.println(line);
                wrongLines++;
            }
        }

        System.out.println("wrong lines: " + wrongLines);
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        adj[v].add(w);
        adj[w].add(v);
    }

    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public int V(){
        return V;
    }

}