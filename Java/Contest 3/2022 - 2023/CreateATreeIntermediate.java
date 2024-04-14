/**
 *      ACSL 2022-2023 - Contest 3 - Create A Tree - Intermediate Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class CreateATreeIntermediate {

    /*
     * Complete the 'onlyLeftOrRight' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING input as parameter.
     */
    static String onlyLeftOrRight (String input) {

        int n = input.length();
        char[] letters = new char[n];
        int[] values = new int[n];

        letters[0] = input.charAt(0);
        values[0] = 0;
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
        }

        StringBuilder left = new StringBuilder();
        StringBuilder right = new StringBuilder();
        for (int i = 0; i < n; i++)
        {
            char c = letters[i];
            int v = values[i];

            boolean searchLeft = false;
            int index = i - 1;
            while (index >= 0 && values[index] >= v)
            {
                if (values[index] == v + 1) {
                    searchLeft = true;
                    break;
                }
                index--;
            }

            boolean searchRight = false;
            index = i + 1;
            while (index < n && values[index] >= v)
            {
                if (values[index] == v + 1) {
                    searchRight = true;
                    break;
                }
                index++;
            }

            if (searchLeft && !searchRight) {
                left.append(c);
            }
            else if (!searchLeft && searchRight) {
                right.append(c);
            }
        }

        if (left.isEmpty()) {
            left.append("NONE");
        }
        if (right.isEmpty()) {
            right.append("NONE");
        }

        return left + " " + right;
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
                "NOY H",
                "AERY CNS",
                "NOUV NONE",
                "FLS IOR",
                "AR CEL",
                "HT O",
                "T A",
                "CIILU FG",
                "AORV NONE",
                "NONE ABDFHKMOQTVWY"
        };

        for (int i = 0; i < 10; i++)
        {
            String result = onlyLeftOrRight(input[i]);

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