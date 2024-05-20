/**
 *      ACSL 2019-2020 - Contest 1 - Number Transformation - Senior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class NumberTransformationSenior {

    static long transformNumber (long num, int pos) {

        long result = 0;

        long[] digits = new long[20];
        int count = countPrimeFactors(num);
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
            }
            else if (i < pos) {
                digits[i] -= digits[pos];
                if (digits[i] < 0) {
                    digits[i] *= -1;
                }
            }
        }
        digits[pos] = count;

        long base = 1;
        for (int i = 0; i < index; i++)
        {
            result += digits[i] * base;
            base *= 10;
            if (digits[i] > 9) {
                base *= 10;
            }
        }

        return result;
    }

    static int countPrimeFactors (long num) {

        int count = 0;

        if (num % 2 == 0) {
            count++;

            while (num % 2 == 0) {
                num /= 2;
            }
        }

        for (long i = 3; i <= Math.sqrt(num); i += 2)
        {
            if (num % i == 0) {
                count++;

                while (num % i == 0) {
                    num /= i;
                }
            }
        }

        if (num > 2) {
            count++;
        }

        return count;
    }

    public static void main (String [] args) {

        long[][] input = {
                {102438L, 3}, {4329L, 1}, {6710L, 2}, {16807L, 1}, {60098065452L, 7},
                {43287L, 3}, {72431685L, 1}, {246897531573L, 12}, {96783L, 5}, {16058314729L, 3}
        };

        long[] output = {
                546414L, 1312113L, 7841L, 8131571L, 1488173823436L,
                65365L, 12798611133L, 424675311351L, 23216L, 8137121510811152L
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