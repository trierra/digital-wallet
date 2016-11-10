package app.tests;

import app.BreadthFirstPaths;
import app.Graph;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by trierra on 11/9/16 for
 * digital-wallet.
 */
public class DWTest {

    public static void graphTest() throws FileNotFoundException {
        Graph graph = new Graph(new Scanner(new File("/Users/trierra/work/insight/digital-wallet/src/main/java/app/tests/graphTest")), 12);

        BreadthFirstPaths bfs = new BreadthFirstPaths(graph, 11);
        System.out.println(bfs.distTo(7));
        assert (bfs.distTo(7) == 1);
        assert (bfs.distTo(6) == 2);
        bfs = new BreadthFirstPaths(graph, 9);
        assert (bfs.distTo(4) == 2);
        assert (bfs.distTo(6) == 1);
        assert (bfs.distTo(2) == 4);
        assert bfs.distTo(3) == 2;

        bfs = new BreadthFirstPaths(graph, 2);
        assert (bfs.distTo(6) == 3);
    }

    public static void main(String[] args) {
        try {
            DWTest.graphTest();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
