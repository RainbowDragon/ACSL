/**
 *      ACSL 2022-2023 - Contest 2 - Binary Counting - Senior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class BinaryCountingSenior {

    /*
     * Complete the 'findLastOctal' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */
    static int findLastOctal (String s) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++)
        {
            String charBinaryString = getBinaryStringForChar(s.charAt(i));
            sb.append(charBinaryString);
        }
        String binaryString = sb.toString();

        int number = 0;
        boolean found = false;
        while (!found)
        {
            String numberString = getBaseStringForInt(number, 2);
            int length = numberString.length();

            int first = binaryString.indexOf(numberString);
            if (first != -1) {
                binaryString = binaryString.substring(0, first) + binaryString.substring(first+length);
            }

            int last = binaryString.lastIndexOf(numberString);
            if (last != -1) {
                binaryString = binaryString.substring(0, last) + binaryString.substring(last+length);
            }

            if (first == -1 && last == -1) {
                found = true;
            }
            else {
                number++;
            }
        }
        String octalString = getOctalStringFromBinaryString(binaryString);

        number = 0;
        found = false;
        while (!found)
        {
            String numberString = getBaseStringForInt(number, 8);
            int len = numberString.length();

            int first = octalString.indexOf(numberString);
            if (first != -1) {
                octalString = octalString.substring(0, first) + octalString.substring(first+len);
            }

            int last = octalString.lastIndexOf(numberString);
            if (last != -1) {
                octalString = octalString.substring(0, last) + octalString.substring(last+len);
            }

            if (first == -1 && last == -1) {
                found = true;
                number--;
            }
            else {
                number++;
            }
        }

        return number;
    }

    static String getBinaryStringForChar (char c) {

        char[] result = new char[8];
        Arrays.fill(result, '0');

        int number = c;
        int index = 7;
        while (number > 0)
        {
            result[index] = (number % 2 == 0) ? '0' : '1';
            number /= 2;
            index--;
        }

        return new String(result);
    }

    static String getBaseStringForInt (int number, int base) {

        if (number == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        while (number > 0)
        {
            int remainder = number % base;
            number /= base;
            sb.append(remainder);
        }

        return sb.reverse().toString();
    }

    static String getOctalStringFromBinaryString (String binaryString) {

        int length = binaryString.length();
        if (length % 3 == 1) {
            binaryString = "00" + binaryString;
        }
        else if (length % 3 == 2) {
            binaryString = "0" + binaryString;
        }

        char[] result = new char[binaryString.length()/3];
        for (int i = 0; i < result.length; i++)
        {
            String numberString = binaryString.substring(i*3, (i+1)*3);
            int number = 0;
            for (int j = 0; j < 3; j++)
            {
                number = number*2 + (numberString.charAt(j) - '0');
            }
            result[i] = (char)(number + '0');
        }

        return new String(result);
    }

    public static void main (String [] args) {

        String[] input = new String[10];
        input[0] = "Roses are red.";
        input[1] = "A is for Alpha; B is for Bravo; C is for Charlie.";
        input[2] = "A stitch in time saves nine.";
        input[3] = "1, 2: Buckle my shoe! 3, 4: Shut the door!";
        input[4] = "The quick brown fox jumped over the lazy dogs.";
        input[5] = "ACSL is 45 years old and going strong.";
        input[6] = "What was the first computer programming language you learned?";
        input[7] = "Lions and Tigers and Bears, Oh My! This is from The Wizard of Oz.";
        input[8] = "zyxwvutsrqponmlkjihgfedcba";
        input[9] = "~{w|x|y|z}";

        int[] output = {4, 9, 8, 6, 5, 6, 9, 9, 2, -1};

        for (int i = 0; i < 10; i++)
        {
            int result = findLastOctal(input[i]);

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