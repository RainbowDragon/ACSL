/**
 *      ACSL 2022-2023 - Contest 3 - Create A Tree - Junior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class CreateATreeJunior {

    /*
     * Complete the 'listByValue' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING input as parameter.
     */
    static String listByValue (String input) {

        int n = input.length();
        char[] letters = new char[n];
        int[] values = new int[n];

        letters[0] = input.charAt(0);
        values[0] = 0;
        int maxValue = 0;
        for (int i = 1; i < n; i++)
        {
            int j = 0;
            char c = input.charAt(i);
            while (j < i && c > letters[j]) {
                j++;
            }
            for (int k = i; k > j; k--)
            {
                letters[k] = letters[k-1];
                values[k] = values[k-1];
            }

            letters[j] = c;
            if (j == 0) {
                values[j] = values[j+1] + 1;
            }
            else if (j == i) {
                values[j] = values[j-1] + 1;
            }
            else {
                values[j] = Math.max(values[j-1], values[j+1]) + 1;
            }

            maxValue = Math.max(maxValue, values[j]);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= maxValue; i++)
            for (int j = 0; j < n; j++)
            {
                if (values[j] == i) {
                    sb.append(letters[j]);
                }
            }

        return sb.toString();
    }

    public static void main (String [] args) {

        String[] input = {
                "PYTHONN",
                "BINARYSEARCHTREE",
                "CORONAVIRUS",
                "FINALSFORACSL",
                "HACKERRANKPLATFORM",
                "MOTHER",
                "ACSLCONTEST",
                "SUPERCALIFRAGILISTIC",
                "JAVAPROGRAMMING",
                "ABDFHKMOQTVWYZ"
        };

        String[] output = {
                "PHYOTNN",
                "BAIAENCHRERYERST",
                "CAOORNRVIUS",
                "FAIAFNCLSLORS",
                "HAKACKRAERTFNLPMOR",
                "MHOETR",
                "ACCSLTEOTNS",
                "SPUERTCLRSAIACFLGIII",
                "JAVAGPAGIORMRMN",
                "ABDFHKMOQTVWYZ"
        };

        for (int i = 0; i < 10; i++)
        {
            String result = listByValue(input[i]);

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