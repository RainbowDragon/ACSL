/**
 *      ACSL 2019-2020 - Contest 2 - String Differences - Intermediate Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class StringDifferencesIntermediate {

    static int samenessFactor (String s1, String s2) {

        boolean done = false;

        while (!done)
        {
            done = true;

            StringBuilder sb1 = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();

            int len = Math.min(s1.length(), s2.length());
            for (int i = 0; i < len; i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    sb1.append(s1.charAt(i));
                    sb2.append(s2.charAt(i));
                }
                else {
                    done = false;
                }
            }
            s1 = sb1 + s1.substring(len);
            s2 = sb2 + s2.substring(len);

            len = Math.min(s1.length(), s2.length());
            for (int i = 0; i < len; i++)
            {
                if (i + 1 < s2.length() && s1.charAt(i) == s2.charAt(i+1)) {
                    s2 = s2.substring(0,i) + s2.substring(i+1);
                    done = false;
                    break;
                }
                else if (i + 1 < s1.length() && s1.charAt(i+1) == s2.charAt(i)) {
                    s1 = s1.substring(0, i) + s1.substring(i+1);
                    done = false;
                    break;
                }
            }
        }

        int result = 0;
        int len = Math.min(s1.length(), s2.length());
        for (int i = 0; i < len; i++)
        {
            result += s1.charAt(i) - s2.charAt(i);
        }
        result += s1.length() + s2.length() - 2 * len;

        return result;
    }

    public static void main (String [] args) {

        String[][] input = {
                {"BLAMEABLENESSES", "BLAMELESSNESSES"},
                {"MEZZAMINES", "RAZZMATAZZ"},
                {"ABBREVIATIONS", "ABBREVIATORS"},
                {"ABCDEFGHIJKLMNO", "ABKCLDZZHQJWWLX"},
                {"ABCDEFGHIJKL", "ABXEWFRRH"},
                {"MYARTLOLLIPOPS", "MYLARBALLOONS"},
                {"MASSACHUSETTSBAYCOLONY", "MINUTEMANNATIONALHISTORICALPARK"},
                {"LOWERMACTOWNSHIPPA", "CRANBERRYTOWNSHIPPA"},
                {"AMERICANCOMPUTERSCIENCELEAGUE", "NATIONALACADEMICGAMESLEAGUE"},
                {"ABCDEFGHIJK", "ABDCEFGKILKJMN"}
        };

        int[] output = {-35, -5, -4, -86, -52, 23, 27, 11, 68, -9};

        for (int i = 0; i < 10; i++)
        {
            int result = samenessFactor(input[i][0], input[i][1]);

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