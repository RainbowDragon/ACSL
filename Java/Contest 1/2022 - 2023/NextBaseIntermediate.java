/**
 *      ACSL 2022-2023 - Contest 1 - Next Base - Intermediate Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class NextBaseIntermediate {

    /*
     * Complete the 'countLargestDigit' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     * 1. INTEGER num
     * 2. INTEGER base
     * 3. INTEGER start
     */
    static int countLargestDigit (int num, int base, long start) {

        int result = 0;
        int largestDigit = base - 1;
        long startValue = Long.parseLong(Long.toString(start), base);

        for (int i = 0; i < num; i++)
        {
            long number = startValue + i;

            while (number > 0) {
                if (number % base == largestDigit) {
                    result++;
                }
                number /= base;
            }
        }

        return result;
    }

    public static void main (String [] args) {

        String[][] input = {
                {"15", "8", "2"},
                {"20", "3", "12"},
                {"25", "5", "324"},
                {"13", "9", "1652"},
                {"45", "2", "1111011"},
                {"1000", "8", "10"},
                {"50", "4", "13"},
                {"75", "9", "384"},
                {"100", "6", "555"},
                {"25", "2", "110000111010"}
        };

        int[] output = {2, 21, 24, 1, 170, 357, 34, 13, 31, 135};

        for (int i = 0; i < 10; i++)
        {
            int result = countLargestDigit(Integer.parseInt(input[i][0]), Integer.parseInt(input[i][1]), Long.parseLong(input[i][2]));

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