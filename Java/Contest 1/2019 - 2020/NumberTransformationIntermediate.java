/**
 *      ACSL 2019-2020 - Contest 1 - Number Transformation - Intermediate Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class NumberTransformationIntermediate {

    static long transformNumber (long num, int pos) {

        long result = 0;

        long[] digits = new long[20];
        int index = 0;
        while (num > 0) {
            digits[index] = num % 10;
            num /= 10;
            index++;
        }

        pos -= 1;
        for (int i = 0; i < index; i++)
        {
            if (i > pos) {
                digits[i] += digits[pos];
                digits[i] %= 10;
            }
            else if (i < pos) {
                digits[i] -= digits[pos];
                if (digits[i] < 0) {
                    digits[i] *= -1;
                }
            }
        }

        long base = 1;
        for (int i = 0; i < index; i++)
        {
            result += digits[i] * base;
            base *= 10;
        }

        return result;
    }

    public static void main (String [] args) {

        long[][] input = new long[10][];
        input[0] = new long[]{296351L, 5};
        input[1] = new long[]{762184L, 3};
        input[2] = new long[]{45873216L, 7};
        input[3] = new long[]{19750418L, 6};
        input[4] = new long[]{386257914L, 5};
        input[5] = new long[]{4318672L, 4};
        input[6] = new long[]{35197545L, 1};
        input[7] = new long[]{975318642L, 9};
        input[8] = new long[]{9876543210L, 5};
        input[9] = new long[]{314159265358L, 10};

        long[] output = {
                193648L, 873173L, 95322341L, 86727361L, 831752441L,
                2198216L, 80642095L, 924681357L, 3210941234L, 754315221114L
        };

        for (int i = 0; i < 10; i++)
        {
            long result = transformNumber(input[i][0], (int)input[i][1]);

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