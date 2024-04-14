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

        long[][] input = {
                {296351L, 5},
                {762184L, 3},
                {45873216L, 7},
                {19750418L, 6},
                {386257914L, 5},
                {4318672L, 4},
                {35197545L, 1},
                {975318642L, 9},
                {9876543210L, 5},
                {314159265358L, 10}
        };

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