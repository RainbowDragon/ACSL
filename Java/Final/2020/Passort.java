/**
 *      ACSL 2020 Final - Problem 2 - Passort - Junior / Intermediate Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class Passort {

    /*
     * Complete the 'passort' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING line as parameter.
     */
    static int passort (String line) {

        String str = removeNonAlphaNumeric(line);
        char[] cstr = str.toCharArray();

        char[] sstr = str.toCharArray();
        Arrays.sort(sstr);

        int sign = 1;
        int result = 0;
        boolean done = false;

        while (!isSorted(cstr, sstr))
        {
            int index1 = findFirst(cstr, sstr, sign);
            int index2 = findIndex(cstr, sstr[index1], sign, index1);

            char temp = cstr[index1];
            cstr[index1] = cstr[index2];
            cstr[index2] = temp;
            result++;
            sign *= -1;
        }

        return result;
    }

    static String removeNonAlphaNumeric (String s) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++)
        {
            if (isAlphaNumeric(s.charAt(i))) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    static boolean isAlphaNumeric (char c) {
        return ('0' <= c && c <= '9') || ('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z');
    }

    static boolean isSorted (char[] cstr, char[] sstr) {

        for (int i = 0; i < sstr.length; i++)
        {
            if (cstr[i] != sstr[i]) {
                return false;
            }
        }
        return true;
    }

    static int findFirst (char[] cstr, char[] sstr, int sign) {

        for (int i = 0; i < sstr.length; i++)
        {
            int index = i;
            if (sign < 0) {
                index = sstr.length - i - 1;
            }
            if (cstr[index] != sstr[index]) {
                return index;
            }
        }
        return -1;
    }

    static int findIndex (char[] cstr, char c, int sign, int index) {

        for (int i = index; i < cstr.length && 0 <= i; )
        {
            if (cstr[i] == c) {
                return i;
            }

            if (sign > 0) {
                i++;
            }
            else {
                i--;
            }
        }
        return -1;
    }

    public static void main (String [] args) {

        String[] input = {
                "ASORTING",
                "10 Java Programs",
                "CONNECTICUT - CT",
                "ASORTINGALGORITHM",
                "ACSL All-Star Contest",
                "0123456789BDFHJLNPRTVXZacegikmoqsuwx",
                "ZYXWVUTSRQPONMLKJIHGFEDCBA",
                "AP COMPUTER SCIENCE PRINCIPLES is abbreviated AP CSP",
                "American Computer Science League uses ACSL",
                "IT'S A BEAUTIFUL DAY IN THE NEIGHBORHOOD",
                "CONNECTICUT is the CONSTITUTION STATE!",
                "APPLE,BANANA,GRAPE,PEACH,PEAR,ORANGE,GUAVA,PAPAYA,MANGO,KIWI,PINEAPPLE",
                "There are 10 Kinds of People in the World, Those Who Know Binary and Those Who Don’t."
        };

        int[] output = {
                6, 11, 9, 11, 12, 0, 13, 36, 30, 27, 31, 52, 58
        };

        for (int i = 0; i < 13; i++)
        {
            int result = passort(input[i]);

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