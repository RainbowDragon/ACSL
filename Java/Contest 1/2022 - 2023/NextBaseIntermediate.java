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
        long startValue = getIntegerWithBase(start, base);

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

    static long getIntegerWithBase (long number, int base) {

        long result = 0;
        long power = 1;

        while (number > 0)
        {
            result += (number % 10) * power;
            power *= base;
            number /= 10;
        }

        return result;
    }

    public static void main (String [] args) {

        long[][] input = new long[10][];
        input[0] = new long[]{15, 8, 2L};
        input[1] = new long[]{20, 3, 12L};
        input[2] = new long[]{25, 5, 324L};
        input[3] = new long[]{13, 9, 1652L};
        input[4] = new long[]{45, 2, 1111011L};
        input[5] = new long[]{1000, 8, 10L};
        input[6] = new long[]{50, 4, 13L};
        input[7] = new long[]{75, 9, 384L};
        input[8] = new long[]{100, 6, 555L};
        input[9] = new long[]{25, 2, 110000111010L};

        int[] output = {2, 21, 24, 1, 170, 357, 34, 13, 31, 135};

        for (int i = 0; i < 10; i++)
        {
            int result = countLargestDigit((int)input[i][0], (int)input[i][1], input[i][2]);

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