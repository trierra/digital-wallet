package app;

import app.service.PaymentService;
import app.storage.GraphDB;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import static app.service.serviceUtils.Utils.parseInputData;

/**
 * Created by trierra on 11/9/16 for
 * digital-wallet.
 * <p>
 */

public class antifraud {

    //Use Graph data structure to imitate db
    private GraphDB graphDB;
    private PaymentService paymentService;

    public antifraud(File file) throws FileNotFoundException {
        this.graphDB = new GraphDB(file);
        this.paymentService = new PaymentService();
    }

    public void feature1(File inputData) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner sc = new Scanner(inputData);
        sc.nextLine();
        PrintWriter writer = new PrintWriter("output1.txt", "UTF-8");
        while (sc.hasNext()) {
            int distance = paymentService.makePayment(parseInputData(sc.nextLine()), this.graphDB);
            if (distance == 0) {
                System.out.println("Would you like to pay yourself?");
            } else if (distance != 1) {
                System.out.println("unverified: You've never had a transaction with this user before. Are you sure you would like to proceed with this payment?");
                writer.println("unverified");
            } else {
                System.out.println("trusted");
                writer.println("trusted");
            }
        }
        writer.close();
    }


    public void feature2(File inputData) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner sc = new Scanner(inputData);
        PrintWriter writer = new PrintWriter("output2.txt", "UTF-8");
        sc.nextLine();
        while (sc.hasNext()) {
            int distance = paymentService.makePayment(parseInputData(sc.nextLine()), this.graphDB);
            if (distance == 0) {
                System.out.println("Would you like to pay yourself?");
            } else if (distance > 2) {
                System.out.println("unverified: This user is not a friend or a \"friend of a friend\". Are you sure you would like to proceed with this payment?");
                writer.println("unverified");

            } else {
                System.out.println("trusted");
                writer.println("trusted");
            }
        }
        writer.close();
    }

    public void feature3(File inputData) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner sc = new Scanner(inputData);
        PrintWriter writer = new PrintWriter("output3.txt", "UTF-8");
        sc.nextLine();
        while (sc.hasNext()) {
            int distance = paymentService.makePayment(parseInputData(sc.nextLine()), this.graphDB);
            if (distance == 0) {
                System.out.println("Would you like to pay yourself?");
            } else if (distance > 4) {
                System.out.println("unverified: This user is outside from your \"4th degree friends network. Are you sure you would like to proceed with this payment?");
                writer.println("unverified");
            } else {
                System.out.println("trusted");
                writer.println("trusted");
            }
        }
        writer.close();
    }

    public static void main(String[] args) {
        try {
            //TODO: count file size
            antifraud app = new antifraud(new File("/Users/trierra/work/insight/digital-wallet/paymo_input/batch_payment.csv"));
            File inputData = new File("/Users/trierra/work/insight/digital-wallet/paymo_input/stream_payment.txt");

            app.feature1(inputData);
            System.out.println("------------------------------------------------");
            app.feature2(inputData);
            System.out.println("------------------------------------------------");
            app.feature3(inputData);

        } catch (FileNotFoundException e) {
            System.err.print("Check the file: " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.err.print("Unsupported Encoding: " + e.getMessage());
        }
    }
}
