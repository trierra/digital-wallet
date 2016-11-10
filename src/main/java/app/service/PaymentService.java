package app.service;

import app.BreadthFirstSearch;
import app.GraphDB;

import java.io.FileNotFoundException;

/**
 * Created by trierra on 11/9/16 for
 * wallet.
 */
public class PaymentService {

    public int makePayment(int[] users, GraphDB graphDB) throws FileNotFoundException {
        int user1 = users[0];
        int user2 = users[1];
        BreadthFirstSearch bfs = new BreadthFirstSearch(graphDB, user1);
        int dist = bfs.distTo(user2);
        System.out.println("Payment between " + user1 + " and " + user2 + ". Distance = " + dist + " with path " + bfs.pathTo((user2)));
        return dist;
    }
}
