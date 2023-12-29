/**
 *      ACSL 2020-2021 - Contest 2 - Lex Strings - Senior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class LexStringsSenior {

    /*
     * Complete the 'rearrangedString' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */
    static String rearrangeString (String s) {

        int[] charCount = new int[128];
        int maxCount = 0;
        for (int i = 0; i < s.length(); i++)
        {
            int index = getIndexOfChar(s.charAt(i));
            if (index != -1) {
                charCount[index]++;
                maxCount = Math.max(maxCount, charCount[index]);
            }
        }

        String[] blocks = new String[maxCount+1];
        Arrays.fill(blocks, "");
        for (int i = 0; i < 128; i++)
        {
            if (charCount[i] > 0) {
                blocks[charCount[i]] += (char)i;
            }
        }

        String result = "";
        int sign = 1;
        boolean isFirst = true;
        for (int i = maxCount; i > 0; i--)
        {
            if (blocks[i].length() > 0) {
                if (!isFirst) {
                    result += ',';
                }
                result += i;
                if (sign > 0) {
                    result += blocks[i];
                }
                else {
                    result += reverseString(blocks[i]);
                }
                isFirst = false;
                sign *= -1;
            }
        }

        return result;
    }

    static int getIndexOfChar (char c) {

        if (c >= 'A' && c <= 'Z') {
            return c;
        }

        if (c >= 'a' && c <= 'z') {
            return c;
        }

        if (c >= '0' && c <= '9') {
            return c;
        }

        return -1;
    }

    static String reverseString (String str) {

        String result = "";
        for (int i = str.length()-1; i >= 0; i--)
        {
            result += str.charAt(i);
        }
        return result;
    }

    public static void main (String [] args) {

        String[] input = new String[10];
        input[0] = "This is an Example of Sorting an interesting string";
        input[1] = "HackerRank.com was used for the ACSL Finals this year.";
        input[2] = "The digits of PI are 3.141592653.";
        input[3] = "She sells seashells by the seashore.";
        input[4] = "Programming languages include Java, Python, C++, Visual BASIC, Ruby, and Scratch.";
        input[5] = "COVID-19 is a global pandemic and a virus that changed everything in the entire world.";
        input[6] = "The Computer Science Teacher Association had a virtual Conference in 2020.";
        input[7] = "The digits of PI are 3.14159265358979323846264778327, not rounded.";
        input[8] = "Peter Piper picked a peck of pickled peppers. How many pickled peppers did Peter Piper pick?";
        input[9] = "There are 10 kinds of people: those who know binary and those who don't.";

        String[] output = {
                "6in,4ts,3aegr,2o,1ESTfhlmpx",
                "5a,4se,3r,2tonkihc,1ACFHLRSdflmuwy",
                "2135ei,1tsrohgfdaTPI9642",
                "7es,4lh,2a,1ytrobS",
                "8a,5n,4gu,3rlic,2CPSdehmosty,1vbVRJIBA",
                "7ae,6ni,5t,4rhd,3gl,2vsoc,119CDIOVbmpuwy",
                "9e,5nica,4or,3th,202CTsu,1vpmlfdSA",
                "53,472,345689deo,2trni1,1IPTafghsu",
                "14e,13p,7i,6r,5cdk,4P,2alost,1ywnmfH",
                "8o,7e,5hn,3wtsrda,2ikp,1ylfbT10"};

        for (int i = 0; i < 10; i++)
        {
            String result = rearrangeString(input[i]);

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