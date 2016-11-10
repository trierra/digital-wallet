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
        return bfs.distTo(user2);
    }
}
