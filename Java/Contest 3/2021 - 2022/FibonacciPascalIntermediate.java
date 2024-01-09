/**
 *      ACSL 2021-2022 - Contest 3 - Fibonacci & Pascal - Intermediate Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class FibonacciPascalIntermediate {

    /*
     * Complete the 'findOddEvenMax' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts INTEGER fibNumber as parameter.
     */
    static String findOddEvenMax (int fibNumber) {

        int n = getFibonacciIndex(fibNumber);
        int i = 0;

        int curOdd = 1;
        int curEven = 0;
        long curMax = 1;
        long current = 1;

        while (i + 1 < n) {
            current = (current * (n - i) * (n - i - 1)) / n / (i + 1);
            n--;
            i++;
            if (current % 2 == 0) {
                curEven++;
            }
            else {
                curOdd++;
            }
            curMax = Math.max(curMax, current);
        }

        return curOdd + " " + curEven + " " + curMax;
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

        int[] input = {8, 89, 610, 10946, 317811, 55, 1597, 832040, 9227465, 1836311903};

        String[] output = {
                "2 1 4",
                "5 1 35",
                "4 4 210",
                "8 3 3003",
                "3 11 77520",
                "3 2 21",
                "5 4 495",
                "4 11 203490",
                "9 9 2042975",
                "7 16 354817320"};

        for (int i = 0; i < 10; i++)
        {
            String result = findOddEvenMax(input[i]);

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