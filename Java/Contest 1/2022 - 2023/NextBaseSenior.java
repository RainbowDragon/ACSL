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
    static int findModeCount (int num, int base, String start) {

        int result = 0;
        int[] counts = new int[base];
        int startValue = getIntegerWithBase(start, base);

        for (int i = 0; i < num; i++)
        {
            int number = startValue + i;

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

    static int getIntegerWithBase (String str, int base) {

        int result = 0;

        for (int i = 0; i < str.length(); i++)
        {
            int digit = getDigitValue(str.charAt(i));
            result = result * base + digit;
        }

        return result;
    }

    static int getDigitValue (char c) {

        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        else {
            return c - 'A' + 10;
        }
    }

    public static void main (String [] args) {

        String[][] input = new String[10][];
        input[0] = new String[]{"15", "8", "2"};
        input[1] = new String[]{"25", "2", "1111011"};
        input[2] = new String[]{"20", "12", "9AB"};
        input[3] = new String[]{"10", "16", "ABCDEF"};
        input[4] = new String[]{"1000", "2", "1"};
        input[5] = new String[]{"50", "4", "12"};
        input[6] = new String[]{"75", "9", "384"};
        input[7] = new String[]{"500", "14", "9CBA"};
        input[8] = new String[]{"700", "11", "AAA0"};
        input[9] = new String[]{"25", "2", "110000111010"};

        int[] output = {9, 105, 14, 10, 4938, 42, 88, 336, 940, 165};

        for (int i = 0; i < 10; i++)
        {
            int result = findModeCount(Integer.parseInt(input[i][0]), Integer.parseInt(input[i][1]), input[i][2]);

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