/**
 *      ACSL 2022-2023 - Contest 1 - Next Base - Junior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class NextBaseJunior {

    /*
     * Complete the 'findDigitSum' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     * 1. INTEGER num
     * 2. INTEGER base
     * 3. INTEGER start
     */
    static int findDigitSum (int num, int base, long start) {

        int result = 0;
        long startValue = Long.parseLong(Long.toString(start), base);

        for (int i = 0; i < num; i++)
        {
            long number = startValue + i;
            int sum = 0;

            while (number > 0) {
                sum += (int)(number % base);
                number /= base;
            }

            result += sum;
        }

        return result;
    }

    public static void main (String [] args) {

        String[][] input = {
                {"15", "8", "2"}, {"20", "3", "12"}, {"25", "5", "324"}, {"13", "9", "1652"}, {"45", "2", "1111011"},
                {"1000", "8", "10"}, {"50", "4", "13"}, {"75", "9", "384"}, {"100", "6", "555"}, {"25", "2", "110000111010"}
        };

        int[] output = {
                65, 64, 189, 212, 170, 10948, 225, 876, 675, 135
        };

        for (int i = 0; i < 10; i++)
        {
            int result = findDigitSum(Integer.parseInt(input[i][0]), Integer.parseInt(input[i][1]), Long.parseLong(input[i][2]));

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