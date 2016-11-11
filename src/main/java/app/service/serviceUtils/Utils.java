package app.service.serviceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by trierra on 11/9/16 for
 * wallet.
 */
public class Utils {

    public static int[] parseInputData(String line) {
        int[] result = new int[2];

        if (!line.contains(",")) {
            System.err.println("Illegal line: " + line);
            return null;
        }

        String[] arr = line.split(",");
        if (arr.length < 5) {
            System.err.println("Illegal line: " + line);
            return null;
        }

        try {
            result[0] = Integer.parseInt(arr[1].trim());
            result[1] = Integer.parseInt(arr[2].trim());

        } catch (NumberFormatException e) {
            System.err.println("Can't parse line: " + line  + e.getMessage());
        }

        return result;
    }

    /**
     * Detecting graph size according to max user id
     * @param batchPayment - input data
     * @return
     * @throws FileNotFoundException
     */
    public static int detectGraphSize(File batchPayment) throws FileNotFoundException {
        int max = 0;
        Scanner in = new Scanner(batchPayment);
        //skip first line
        in.nextLine();

        while (in.hasNext()) {
            String line = in.nextLine();

            int[] vertexArray = parseInputData(line);

            if (vertexArray != null) {
                try {
                    if (max < Math.max(vertexArray[0], vertexArray[1])) {
                        max = Math.max(vertexArray[0], vertexArray[1]);
                    }
                } catch (NumberFormatException x) {
                    System.err.println("Couldn't parse line: " + line + ", " + x.getMessage());
                }
            }
        }
        return max;
    }
}
