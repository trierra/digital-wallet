package app.service.serviceUtils;

import app.storage.GraphDB;

import java.util.Stack;

/**
 * Graph processing algorithm for searching of shortest path between users
 */
public class BreadthFirstSearch {
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;  // marked[v] = is there an source-vertex path
    private int[] edgeTo;      // edgeTo[v] = previous edge on shortest source-vertex path
    private int[] distTo;      // distTo[v] = number of edges shortest source-vertex path

    public BreadthFirstSearch(GraphDB G, int source) {
        marked = new boolean[G.getVertexes()];
        distTo = new int[G.getVertexes()];
        edgeTo = new int[G.getVertexes()];
        bfs(G, source);
    }

    /**
     * breadth-first search from a single source
     */
    private void bfs(GraphDB G, int source) {
        Queue<Integer> q = new Queue<Integer>();
        for (int v = 0; v < G.getVertexes(); v++)
            distTo[v] = INFINITY;
        distTo[source] = 0;
        marked[source] = true;
        q.enqueue(source);

        while (!q.isEmpty()) {
            int vert1 = q.dequeue();
            for (int vert2 : G.getAdjacencyList(vert1)) {
                if (!marked[vert2]) {
                    edgeTo[vert2] = vert1;
                    distTo[vert2] = distTo[vert1] + 1;
                    marked[vert2] = true;
                    q.enqueue(vert2);
                }
            }
        }
    }

    boolean hasPathTo(int vertex) {
        return marked[vertex];
    }

    public int distTo(int vertex) {
        return distTo[vertex];
    }

    /**
     * Returns path to particular vertex
     *
     * @param v - vertex
     * @return
     */
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x])
            path.push(x);
        path.push(x);
        return path;
    }
}