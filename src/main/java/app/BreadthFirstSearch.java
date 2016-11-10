package app;

import java.util.Stack;

public class BreadthFirstSearch {
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;  // marked[v] = is there an s-v path
    private int[] edgeTo;      // edgeTo[v] = previous edge on shortest s-v path
    private int[] distTo;      // distTo[v] = number of edges shortest s-v path

    public BreadthFirstSearch(GraphDB G, int s) {
        marked = new boolean[G.getVertexes()];
        distTo = new int[G.getVertexes()];
        edgeTo = new int[G.getVertexes()];
        bfs(G, s);
//        assert check(G, s);
    }


    // breadth-first search from a single source
    private void bfs(GraphDB G, int s) {
        Queue<Integer> q = new Queue<Integer>();
        for (int v = 0; v < G.getVertexes(); v++)
            distTo[v] = INFINITY;
        distTo[s] = 0;
        marked[s] = true;
        q.enqueue(s);

        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.getAdjacencyList(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public int distTo(int v) {
        return distTo[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x])
            path.push(x);
        path.push(x);
        return path;
    }


//    // check optimality conditions for single source
//    private boolean check(GraphDB G, int s) {
//
//        // check that the distance of s = 0
//        if (distTo[s] != 0) {
//            System.out.println("distance of source " + s + " to itself = " + distTo[s]);
//            return false;
//        }
//
//        // check that for each edge v-w dist[w] <= dist[v] + 1
//        // provided v is reachable from s
//        for (int v = 0; v < G.getVertexes(); v++) {
//            for (int w : G.getAdjacencyList(v)) {
//                if (hasPathTo(v) != hasPathTo(w)) {
//                    System.out.println("edge " + v + "-" + w);
//                    System.out.println("hasPathTo(" + v + ") = " + hasPathTo(v));
//                    System.out.println("hasPathTo(" + w + ") = " + hasPathTo(w));
//                    return false;
//                }
//                if (hasPathTo(v) && (distTo[w] > distTo[v] + 1)) {
//                    System.out.println("edge " + v + "-" + w);
//                    System.out.println("distTo[" + v + "] = " + distTo[v]);
//                    System.out.println("distTo[" + w + "] = " + distTo[w]);
//                    return false;
//                }
//            }
//        }
//
//        // check that v = edgeTo[w] satisfies distTo[w] = distTo[v] + 1
//        // provided v is reachable from s
//        for (int w = 0; w < G.getVertexes(); w++) {
//            if (!hasPathTo(w) || w == s) continue;
//            int v = edgeTo[w];
//            if (distTo[w] != distTo[v] + 1) {
//                System.out.println("shortest path edge " + v + "-" + w);
//                System.out.println("distTo[" + v + "] = " + distTo[v]);
//                System.out.println("distTo[" + w + "] = " + distTo[w]);
//                return false;
//            }
//        }
//
//        return true;
//    }
}