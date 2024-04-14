/**
 *      ACSL 2019-2020 - Contest 1 - Number Transformation - Junior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class NumberTransformationJunior {

    static long transformNumber (long num, int pos, int del) {

        long result = 0;

        long[] digits = new long[20];
        int index = 0;
        while (num > 0) {
            digits[index] = num % 10;
            num /= 10;
            index++;
        }

        pos -= 1;
        long digit = digits[pos];
        if (digit < 5) {
            digit += del;
            digit %= 10;
        }
        else {
            digit -= del;
            if (digit < 0) {
                digit *= -1;
            }
            while (digit > 9) {
                digit /= 10;
            }
        }
        digits[pos] = digit;

        for (int i = 0; i < pos; i++)
        {
            digits[i] = 0;
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
                {124987L, 2, 3},
                {540670L, 3, 9},
                {7145042L, 2, 8},
                {124987L, 2, 523},
                {4386709L, 1, 2},
                {4318762L, 4, 3},
                {72431685L, 1, 7},
                {123456789L, 7, 8},
                {9876543210L, 10, 25},
                {314159265358L, 8, 428}
        };

        long[] output = {
                124950L, 540300L, 7145020L, 124950L, 4386707L,
                4315000L, 72431682L, 121000000L, 1000000000L, 314140000000L
        };

        for (int i = 0; i < 10; i++)
        {
            long result = transformNumber(input[i][0], (int)input[i][1], (int)input[i][2]);

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