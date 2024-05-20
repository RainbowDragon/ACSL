/**
 *      ACSL 2020-2021 - Contest 1 - Numeral Triangles - Intermediate Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class NumeralTrianglesIntermediate {

    /*
     * Complete the 'sumOfLastRow' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER s
     *  2. INTEGER d
     *  3. INTEGER r
     */
    static int sumOfLastRow (int s, int d, int r) {

        int sum = 0;
        int numbersToSkip = r * (r - 1) / 2;

        int startingNumber = oct2decimal(s);
        int delta = oct2decimal(d);
        int firstNumberRthRow = startingNumber + delta * numbersToSkip;

        for (int i = 0; i < r; i++)
        {
            sum += sumOfOctDigit(firstNumberRthRow);
            firstNumberRthRow += delta;
        }

        return sum;
    }

    static int oct2decimal (int octNumber) {

        int number = 0;
        int base = 1;

        while (octNumber > 0)
        {
            int digit = octNumber % 10;
            number += digit * base;
            base *= 8;
            octNumber /= 10;
        }

        return number;
    }

    static int sumOfOctDigit (int number) {

        int sum = 0;

        while (number > 0) {
            sum += number % 8;
            number /= 8;
        }

        return sum;
    }

    public static void main (String [] args) {

        int[][] input = {
                {2, 3, 5}, {221, 2, 4}, {1, 4, 20}, {10, 10, 10}, {3245, 5, 11},
                {4567, 7, 65}, {3141, 5, 26}, {765, 43, 21}, {704, 1776, 20}, {77, 7, 100}
        };

        int[] output = {
                36, 38, 230, 99, 178, 1038, 429, 329, 374, 1547
        };

        for (int i = 0; i < 10; i++)
        {
            int result = sumOfLastRow(input[i][0], input[i][1], input[i][2]);

            if (output[i] == result) {
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