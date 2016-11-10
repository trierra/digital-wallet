package app;

import app.service.PaymentService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static app.Utils.parseInputData;

/**
 * Created by trierra on 11/9/16 for
 * digital-wallet.
 * <p>
 */

public class antifraud {

    //Use Graph data structure to imitate db
    private GraphDB graphDB;
    private PaymentService paymentService;

    public antifraud(File file, int graphSize) throws FileNotFoundException {
        this.graphDB = new GraphDB(new Scanner(file), graphSize);
        this.paymentService = new PaymentService();
    }

    //TODO: write output to file and read from test
    public void feature1(File inputData) throws FileNotFoundException {
        Scanner sc = new Scanner(inputData);
        sc.nextLine();
        while (sc.hasNext()) {

            int distance = paymentService.makePayment(parseInputData(sc.nextLine()), this.graphDB);

            if (distance == 0) {
                System.out.println("Would you like to pay yourself?");
            } else if (distance != 1) {
                System.out.println("unverified");
            } else {
                System.out.println("trusted");
            }
        }
    }

    //TODO: write output to file and read from test

    public void feature2(File inputData) throws FileNotFoundException {
        Scanner sc = new Scanner(inputData);
        sc.nextLine();
        while (sc.hasNext()) {

            int distance = paymentService.makePayment(parseInputData(sc.nextLine()), this.graphDB);
            if (distance == 0) {
                System.out.println("Would you like to pay yourself?");
            } else if (distance != 2) {
                System.out.println("unverified");
            } else {
                System.out.println("trusted");
            }
        }
    }

    //TODO: write output to file and read from test
    public void feature3(File inputData) throws FileNotFoundException {
        Scanner sc = new Scanner(inputData);
        sc.nextLine();
        while (sc.hasNext()) {

            int distance = paymentService.makePayment(parseInputData(sc.nextLine()), this.graphDB);

            if (distance == 0) {
                System.out.println("Would you like to pay yourself?");
            } else if (distance > 4) {
                System.out.println("unverified");
            } else {
                System.out.println("trusted");
            }
        }
    }

    public static void main(String[] args) {
        try {
            //TODO: count file size
            antifraud app = new antifraud(new File("/Users/trierra/work/insight/digital-wallet/paymo_input/batch_payment.csv"), 3938361);
            File inputData = new File("/Users/trierra/work/insight/digital-wallet/paymo_input/stream_payment.txt");

            app.feature1(inputData);
            app.feature2(inputData);
            app.feature3(inputData);
        } catch (FileNotFoundException e) {
            System.err.print("Check the file: " + e.getMessage());
        }
    }
}
