/**
 *      ACSL 2022-2023 - Contest 3 - Create A Tree - Senior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class CreateATreeSenior {

    /*
     * Complete the 'getTraversals' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING input as parameter.
     */
    static String getTraversals (String input) {

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

        String first = preorder(letters, values, 0, n-1, 0);
        String second = postorder(letters, values, 0, n-1, 0);

        return first + " " + second;
    }

    static String preorder (char[] letters, int[] values, int left, int right, int level) {

        boolean found = false;
        int index;
        for (index = left; index <= right; index++)
        {
            if (values[index] == level) {
                found = true;
                break;
            }
        }

        if (!found) {
            return "";
        }

        String leftStr = preorder(letters, values, left, index-1, level+1);
        String rightStr = preorder(letters, values, index+1, right, level+1);

        return letters[index] + leftStr + rightStr;
    }

    static String postorder (char[] letters, int[] values, int left, int right, int level) {

        boolean found = false;
        int index;
        for (index = left; index <= right; index++)
        {
            if (values[index] == level) {
                found = true;
                break;
            }
        }

        if (!found) {
            return "";
        }

        String leftStr = postorder(letters, values, left, index-1, level+1);
        String rightStr = postorder(letters, values, index+1, right, level+1);

        return leftStr + rightStr + letters[index];
    }

    public static void main (String [] args) {

        String[] input = new String[10];
        input[0] = "PYTHONN";
        input[1] = "BINARYSEARCHTREE";
        input[2] = "CORONAVIRUS";
        input[3] = "FINALSFORACSL";
        input[4] = "HACKERRANKPLATFORM";
        input[5] = "MOTHER";
        input[6] = "ACSLCONTEST";
        input[7] = "SUPERCALIFRAGILISTIC";
        input[8] = "JAVAPROGRAMMING";
        input[9] = "ABDFHKMOQTVWYZ";

        String[] output = new String[10];
        output[0] = "PHONNYT NNOHTYP";
        output[1] = "BAAIECEEHNRRRYST AAEECHERRTSYRNIB";
        output[2] = "CAOONIRRVUS AINORSUVROC";
        output[3] = "FAAFCINLLSORS ACFALLSROSNIF";
        output[4] = "HAAACEFKKRRNLMPORT AAFECAKMLORPNRTRKH";
        output[5] = "MHEOTR EHRTOM";
        output[6] = "ACCSLEONSTT CENSOLTTSCA";
        output[7] = "SPECAACLIFGIIILRRSUT ACACIIIGFLILERSRPTUS";
        output[8] = "JAAAGGIVPOMMNRR AAGIGAMNMORRPVJ";
        output[9] = "ABDFHKMOQTVWYZ ZYWVTQOMKHFDBA";

        for (int i = 0; i < 10; i++)
        {
            String result = getTraversals(input[i]);

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