package app.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import static app.service.serviceUtils.Utils.parseInputData;

/**
 * "In-memory database" graph representation.
 * Solution for storage and searching of connections in social network using Breath First Search algorithm.
 */
public class GraphDB {

    private int vertexes;
    private LinkedList<Integer>[] adj;

    /**
     * Processing data to create a graph of initial state
     *
     * @param batchPayment - initial state daya
     * @param vertexes     -size of graph
     * @throws FileNotFoundException
     */
    public GraphDB(File batchPayment, int vertexes) throws FileNotFoundException {

        this.vertexes = vertexes;
        adj = (LinkedList<Integer>[]) new LinkedList[vertexes];
        for (int v = 0; v < vertexes; v++) {
            adj[v] = new LinkedList<Integer>();
        }
        connectData(batchPayment);
    }

    /**
     * Fill graph with data
     *
     * @param batchPayment - data file
     */
    private void connectData(File batchPayment) throws FileNotFoundException {

        Scanner in = new Scanner(batchPayment);
        //skip first line
        in.nextLine();

        while (in.hasNext()) {
            String line = in.nextLine();

            int[] vertexArray = parseInputData(line);

            if (vertexArray == null) {
                continue;
            }
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

    /**
     * Add edges between vertexes
     * @param vertex1
     * @param vertex2
     */
    private void connect(int vertex1, int vertex2) {
        validateVertex(vertex1);
        validateVertex(vertex2);
        adj[vertex1].add(vertex2);
        adj[vertex2].add(vertex1);
    }

    /**
     * Returns list of connections for each vertex
     * @param v - vertex
     * @return
     */
    public Iterable<Integer> getAdjacencyList(int v) {
        validateVertex(v);
        return adj[v];
    }


    public int getVertexes() {
        return vertexes;
    }

}