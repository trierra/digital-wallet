package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by trierra on 11/9/16 for
 * digital-wallet.
 * <p>
 * Description: Data structure, used to store and connect the app.tests of users
 * Dependencies: java.util.List
 */
public class antifraud {

    private boolean checkConnection(int vertex1, int vertex2, Graph graph) throws FileNotFoundException {

        long start = System.currentTimeMillis();
        BreadthFirstPaths bfs = new BreadthFirstPaths(graph, vertex1);

        int lvl = bfs.distTo(vertex2);
        System.out.println("Time: " + (System.currentTimeMillis() - start));

        if (lvl <= 4) {
            System.out.println("trusted: " + lvl);
        } else {
            System.out.println("untrusted: " + lvl);
        }
        return false;
    }


    public static void main(String[] args) throws FileNotFoundException {
        antifraud app = new antifraud();
        int x = 3000001;

        Graph graph = new Graph(new Scanner(new File("/Users/trierra/work/insight/digital-wallet/paymo_input/batch_payment.csv")), 3938361);
        Scanner sc = new Scanner(new File("/Users/trierra/work/insight/digital-wallet/paymo_input/stream_payment.txt"));
        sc.nextLine();

        while (sc.hasNext()) {

            String line = sc.nextLine();
            if (!line.contains(",")) {
                continue;
            }
            String[] arr = line.split(",");
            if (arr.length < 5) {
                System.out.println("Illegal line: " + line + ", index:" + ", arr: " + Arrays.toString(arr));
                continue;
            }
            try {
                int v1 = Integer.parseInt(arr[1].trim());
                int v2 = Integer.parseInt(arr[2].trim());
                app.checkConnection(v1, v2, graph);
            } catch (Exception xcxx) {
                System.out.println(line);
            }
        }
    }


}
