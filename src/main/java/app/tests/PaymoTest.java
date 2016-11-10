package app.tests;

import app.service.serviceUtils.BreadthFirstSearch;
import app.storage.GraphDB;
import app.antifraud;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 * Created by trierra on 11/9/16 for
 * digital-wallet.
 */
public class PaymoTest {

    static String pathToBatchTest = "/Users/trierra/work/insight/digital-wallet/insight_testsuite/tests/test-1-paymo-trans/paymo_input/batch_test.txt";
    static String pathToFeatureTest = "/Users/trierra/work/insight/digital-wallet/insight_testsuite/tests/test-1-paymo-trans/paymo_input/features.test";


    public static void graphTest() throws FileNotFoundException {
        GraphDB graphDB = new GraphDB(new File(pathToBatchTest), 12);
        BreadthFirstSearch bfs = new BreadthFirstSearch(graphDB, 11);

        assert (bfs.distTo(7) == 1);
        assert bfs.pathTo(7).toString().equals("[7, 11]");

        assert (bfs.distTo(6) == 2);
        assert (bfs.pathTo(6).toString().equals("[6, 9, 11]") || bfs.pathTo(6).toString().equals("[6, 7, 11]"));

        assert (bfs.distTo(5) == 2);
        assert bfs.pathTo(5).toString().equals("[5, 9, 11]");

        bfs = new BreadthFirstSearch(graphDB, 9);

        assert (bfs.distTo(4) == 2);
        assert bfs.pathTo(4).toString().equals("[4, 6, 9]");

        assert (bfs.distTo(6) == 1);
        assert bfs.pathTo(6).toString().equals("[6, 9]");

        assert (bfs.distTo(2) == 3);
        assert bfs.pathTo(2).toString().equals("[2, 1, 7, 9]");

        assert bfs.distTo(3) == 2;
        assert bfs.pathTo(3).toString().equals("[3, 6, 9]");

        bfs = new BreadthFirstSearch(graphDB, 10);
        assert bfs.distTo(7) == 3;
        assert bfs.pathTo(7).toString().equals("[7, 9, 5, 10]");

        assert bfs.distTo(2) == 5;
        assert bfs.pathTo(2).toString().equals("[2, 1, 7, 9, 5, 10]");
        assert !bfs.pathTo(2).toString().equals("[2, 1, 7, 6, 9, 5, 10]");

        bfs = new BreadthFirstSearch(graphDB, 2);
        assert (bfs.distTo(6) == 3);
        //should  be fail
//        assert (bfs.distTo(6) == 1);
    }

    public static void feature1Test() throws FileNotFoundException, UnsupportedEncodingException {
        int lines = 1;
        antifraud app = new antifraud(new File(pathToBatchTest), 12);
        app.feature1(new File(pathToFeatureTest));

    }

    public static void feature2Test() throws FileNotFoundException, UnsupportedEncodingException {
        int lines = 1;

        antifraud app = new antifraud(new File(pathToBatchTest), 12);
        app.feature2(new File(pathToFeatureTest));
    }

    public static void feature3Test() throws FileNotFoundException, UnsupportedEncodingException {
        int lines = 1;

        antifraud app = new antifraud(new File(pathToBatchTest), 12);
        app.feature3(new File(pathToFeatureTest));
    }

    public static void main(String[] args) {
        try {
            PaymoTest.graphTest();
            feature1Test();
            System.out.println("------------------------------------------------");

            feature2Test();
            System.out.println("------------------------------------------------");

            feature3Test();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
