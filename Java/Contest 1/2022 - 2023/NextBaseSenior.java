/**
 *      ACSL 2022-2023 - Contest 1 - Next Base - Senior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class NextBaseSenior {

    /*
     * Complete the 'findModeCount' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     * 1. INTEGER num
     * 2. INTEGER base
     * 3. STRING start
     */
    static int findModeCount (int num, int base, int start) {

        int result = 0;
        int[] counts = new int[base];

        for (int i = 0; i < num; i++)
        {
            int number = start + i;

            while (number > 0) {
                counts[number % base]++;
                number /= base;
            }
        }

        for (int i = 0; i < base; i++)
        {
            result = Math.max(result, counts[i]);
        }

        return result;
    }

    public static void main (String [] args) {

        int[][] input = new int[10][];
        input[0] = new int[]{15, 8, 2};
        input[1] = new int[]{25, 2, 123};
        input[2] = new int[]{20, 12, 1427};
        input[3] = new int[]{10, 16, 11259375};
        input[4] = new int[]{1000, 2, 1};
        input[5] = new int[]{50, 4, 6};
        input[6] = new int[]{75, 9, 319};
        input[7] = new int[]{500, 14, 27212};
        input[8] = new int[]{700, 11, 14630};
        input[9] = new int[]{25, 2, 3130};

        int[] output = {9, 105, 14, 10, 4938, 42, 88, 336, 940, 165};

        for (int i = 0; i < 10; i++)
        {
            int result = findModeCount(input[i][0], input[i][1], input[i][2]);

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