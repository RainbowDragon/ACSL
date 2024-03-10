/**
 *      ACSL 2020-2021 - Contest 2 - Lex Strings - Junior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class LexStringsJunior {

    /*
     * Complete the 'rearrangedString' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */
    static String rearrangeString (String s) {

        int[] charCount = new int[26];
        int maxCount = 0;
        for (int i = 0; i < s.length(); i++)
        {
            int index = getIndexOfChar(s.charAt(i));
            if (index != -1) {
                charCount[index]++;
                maxCount = Math.max(maxCount, charCount[index]);
            }
        }

        StringBuilder sb = new StringBuilder();
        char lastChar = ' ';
        for (int j = 0; j < maxCount; j++)
        {
            for (int i = 0; i < 26; i++)
            {
                if (charCount[i] > j) {
                    char curChar = (char)('a' + i);
                    if (lastChar != curChar) {
                        sb.append(curChar);
                        lastChar = curChar;
                    }
                }
            }
        }

        return sb.toString();
    }

    static int getIndexOfChar (char c) {

        if (c >= 'A' && c <= 'Z') {
            return c - 'A';
        }

        if (c >= 'a' && c <= 'z') {
            return c - 'a';
        }

        return -1;
    }

    public static void main (String [] args) {

        String[] input = new String[10];
        input[0] = "A good sorting algorithm.";
        input[1] = "Tennessee is the volunteer state.";
        input[2] = "Einstein was a genius.";
        input[3] = "Tom Sawyer & the Mississippi River";
        input[4] = "She sells seashells by the seashore.";
        input[5] = "Peter Piper picked a peck of pickled peppers.";
        input[6] = "Computer Science Teachers Association had a virtual Conference in 2020.";
        input[7] = "HackerRank.com was used for the ACSL Finals this past year.";
        input[8] = "Programming languages include Java, Python, C++, BASIC, and Scratch.";
        input[9] = "COVID-19 is a global pandemic and a virus that changed everything.";

        String[] output = {
                "adghilmnorstagiortgo",
                "aehilnorstuvenstenstestete",
                "aeginstuwaeinseins",
                "aehimoprstvwyeimprsteirsisis",
                "abehlorstyaehlsehlsehlseseses",
                "acdefikloprstcdeikprceikprepepepepep",
                "acdefhilmnoprstuvacehinorstuaceinorstaceinorstaceinacece",
                "acdefhiklmnoprstuwyacefhiklnorstacehrstaersasasa",
                "abcdeghijlmnoprstuvyacdeghilmnoprstuacginrsacgnacna",
                "abcdeghilmnoprstuvyacdeghilnorstvacdeghintvadeinaia"
        };

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