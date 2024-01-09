/**
 *      ACSL 2021-2022 - Contest 3 - Fibonacci & Pascal - Junior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class FibonacciPascalJunior {

    /*
     * Complete the 'printNumbers' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts INTEGER fibNumber as parameter.
     */
    static String printNumbers (int fibNumber) {

        int n = getFibonacciIndex(fibNumber);
        int i = 0;

        StringBuilder sb = new StringBuilder();
        int current = 1;
        sb.append(current);

        while (i + 1 < n) {
            current = (current * (n - i) * (n - i - 1)) / (n * (i + 1));
            n--;
            i++;
            sb.append(" ");
            sb.append(current);
        }

        return sb.toString();
    }

    static int getFibonacciIndex (int fibNumber) {

        int index = 1;
        int first = 1;
        int second = 1;

        while (second < fibNumber) {
            int temp = first;
            first = second;
            second += temp;
            index++;
        }

        return index;
    }

    public static void main (String [] args) {

        int[] input = {8, 89, 610, 10946, 317811, 2, 55, 1597, 17711, 832040};

        String[] output = {
                "1 4 3",
                "1 9 28 35 15 1",
                "1 13 66 165 210 126 28 1",
                "1 19 153 680 1820 3003 3003 1716 495 55 1",
                "1 26 300 2024 8855 26334 54264 77520 75582 48620 19448 4368 455 14",
                "1 1",
                "1 8 21 20 5",
                "1 15 91 286 495 462 210 36 1",
                "1 20 171 816 2380 4368 5005 3432 1287 220 11",
                "1 28 351 2600 12650 42504 100947 170544 203490 167960 92378 31824 6188 560 15"};

        for (int i = 0; i < 10; i++)
        {
            String result = printNumbers(input[i]);

            if (output[i].equals(result)) {
                System.out.println("Test Case " + i + ": Passed!");
            }
            else {
                System.out.println("Test Case " + i + ": Failed!");
                System.out.println("Expected output: " + output[i]);
                System.out.println("Your output: " + result);
            }
        }
    }
}