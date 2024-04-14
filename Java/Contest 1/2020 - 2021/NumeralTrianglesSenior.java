/**
 *      ACSL 2020-2021 - Contest 1 - Numeral Triangles - Senior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class NumeralTrianglesSenior {

    /*
     * Complete the 'sumOfLastRow' function below.
     *
     * The function is expected to return a CHARACTER.
     * The function accepts following parameters:
     *  1. STRING s
     *  2. STRING d
     *  3. INTEGER r
     */
    static String sumOfLastRow (String s, String d, int r) {

        int sum = 0;
        int numbersToSkip = r * (r - 1) / 2;

        int startingNumber = hex2decimal(s);
        int delta = hex2decimal(d);
        int firstNumberRthRow = startingNumber + delta * numbersToSkip;

        for (int i = 0; i < r; i++)
        {
            sum += transformToSingleHexDigit(firstNumberRthRow);
            firstNumberRthRow += delta;
        }
        sum = transformToSingleHexDigit(sum);

        String[] hexDigit = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
        return hexDigit[sum];
    }

    static int hex2decimal (String hexStr) {

        int number = 0;

        for (int i = 0; i < hexStr.length(); i++)
        {
            char c = hexStr.charAt(i);
            int digit;
            if (c >= '0' && c <= '9') {
                digit = c - '0';
            }
            else {
                digit = c - 'A' + 10;
            }
            number *= 16;
            number += digit;
        }

        return number;
    }

    static int transformToSingleHexDigit (int number) {

        while (number > 15)
        {
            int sum = 0;
            while (number > 0)
            {
                sum += number % 16;
                number /= 16;
            }
            number = sum;
        }

        return number;
    }

    public static void main (String [] args) {

        String[][] input = {
                {"A", "9", "5"},
                {"ABC", "F", "4"},
                {"BAD", "50", "10"},
                {"FED", "ABC", "25"},
                {"184", "231", "35"},
                {"ABE", "CAB", "40"},
                {"31415", "92653", "60"},
                {"DEAF", "BED", "72"},
                {"BAD", "DAD", "100"},
                {"704", "1776", "244"}
        };

        String[] output = {"5", "C", "A", "F", "5", "5", "F", "3", "A", "E"};

        for (int i = 0; i < 10; i++)
        {
            String result = sumOfLastRow(input[i][0], input[i][1], Integer.parseInt(input[i][2]));

            if (output[i].equals(result)) {
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