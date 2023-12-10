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
    static int findDigitSum (int num, int base, int start) {

        int result = 0;
    
        for (int i = 0; i < num; i++)
        {
            int number = start + i;
            int sum = 0;

            while (number > 0) {
                sum += number % base;
                number /= base;
            }

            result += sum;
        }

        return result;
    }

    public static void main (String [] args) {

        int[][] input = new int[10][];
        input[0] = new int[]{15, 8, 2};
        input[1] = new int[]{20, 3, 5};
        input[2] = new int[]{25, 5, 89};
        input[3] = new int[]{13, 9, 1262};
        input[4] = new int[]{45, 2, 123};
        input[5] = new int[]{1000, 8, 8};
        input[6] = new int[]{50, 4, 7};
        input[7] = new int[]{75, 9, 319};
        input[8] = new int[]{100, 6, 215};
        input[9] = new int[]{25, 2, 3130};

        int[] output = {65, 64, 189, 212, 170, 10948, 225, 876, 675, 135};

        for (int i = 0; i < 10; i++)
        {
            int result = findDigitSum(input[i][0], input[i][1], input[i][2]);

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