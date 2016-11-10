package app;

import java.util.LinkedList;
import java.util.Scanner;

import static app.Utils.parseInputData;

public class GraphDB {

    private int vertexes;
    private LinkedList<Integer>[] adj;

    public GraphDB(Scanner in, int vertexes) {

        this.vertexes = vertexes;
        adj = (LinkedList<Integer>[]) new LinkedList[vertexes];
        for (int v = 0; v < vertexes; v++) {
            adj[v] = new LinkedList<Integer>();
        }

        //skip first line
        in.nextLine();

        while (in.hasNext()) {
            String line = in.nextLine();
            int[] vertexArray = parseInputData(line);
            try {
                int vertex1 = vertexArray[0];
                int vertex2 = vertexArray[1];
                connect(vertex1, vertex2);
            } catch (NumberFormatException x) {
                System.err.println("Couldn't parse line: " + line + ", " + x.getMessage());
            }
        }
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= vertexes)
            throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (vertexes - 1));
    }

    private void connect(int vertex1, int vertex2) {
        validateVertex(vertex1);
        validateVertex(vertex2);
        adj[vertex1].add(vertex2);
        adj[vertex2].add(vertex1);
    }

    Iterable<Integer> getAdjacencyList(int v) {
        validateVertex(v);
        return adj[v];
    }

    public int getVertexes() {
        return vertexes;
    }

}