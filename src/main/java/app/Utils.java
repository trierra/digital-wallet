package app;

import java.util.Arrays;

/**
 * Created by trierra on 11/9/16 for
 * wallet.
 */
public class Utils {

    public static int[] parseInputData(String line) {
        int[] result = new int[2];

        if (!line.contains(",")) {
            System.err.println("Illegal line: " + line + ", index:" + ", arr: " + line);
            return null;
        }

        String[] arr = line.split(",");
        if (arr.length < 5) {
            System.err.println("Illegal line: " + line + ", index:" + ", arr: " + Arrays.toString(arr));
        }

        try {
            result[0] = Integer.parseInt(arr[1].trim());
            result[1] = Integer.parseInt(arr[2].trim());

        } catch (NumberFormatException e) {
            System.err.println("Can't parse line: " + line + ", index:" + ", arr: " + Arrays.toString(arr) + ";  " + e.getMessage());
        }

        return result;
    }
}
