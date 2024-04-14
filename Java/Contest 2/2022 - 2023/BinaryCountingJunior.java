/**
 *      ACSL 2022-2023 - Contest 2 - Binary Counting - Junior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class BinaryCountingJunior {

    /*
     * Complete the 'findLastBinary' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */
    static int findLastBinary (String s) {

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
            String numberString = getBinaryStringForInt(number);
            if (!binaryString.contains(numberString)) {
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

    static String getBinaryStringForInt (int number) {

        if (number == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        while (number > 0)
        {
            int remainder = number % 2;
            number /= 2;
            sb.append(remainder);
        }

        return sb.reverse().toString();
    }

    public static void main (String [] args) {

        String[] input = {
                "Roses are red.",
                "A is Alpha; B is Bravo; C is Charlie.",
                "A stitch in time saves nine.",
                "1, 2: Buckle my shoe! 3, 4: Shut the door!",
                "Is HackerRank the platform used by ACSL?",
                "What was the first computer programming language you learned?",
                "Lions and Tigers and Bears, Oh My! This is from The Wizard of Oz.",
                "Knock, knock. Who is there? Hawaii. Hawaii who? I am good, Hawaii you?",
                "How do you use HackerRank to do each ACSL competition?",
                "~{w|x|y|z}"
        };

        int[] output = {16, 20, 14, 30, 61, 61, 30, 64, 33, 20};

        for (int i = 0; i < 10; i++)
        {
            int result = findLastBinary(input[i]);

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