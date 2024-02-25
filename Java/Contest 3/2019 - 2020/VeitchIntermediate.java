/**
 *      ACSL 2019-2020 - Contest 3 - Veitch - Intermediate Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class VeitchIntermediate {

    static String getVeitchDiagram (String booleanExpression) {

        int[] veitchDiagram = new int[16];
        String[] grid = {
                "1100", "1110", "0110", "0100", "1101", "1111", "0111", "0101",
                "1001", "1011", "0011", "0001", "1000", "1010", "0010", "0000"};
        String[] expressions = booleanExpression.replace("+", " ").split(" ");

        for (String expression : expressions)
        {
            String binaryExpression = convertExpression(expression);
            for (int i = 0; i < 16; i++)
            {
                if (checkMatch(binaryExpression, grid[i])) {
                    veitchDiagram[i] = 1;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i=i+4)
        {
            int value = 8 * veitchDiagram[i] + 4 * veitchDiagram[i+1] + 2 * veitchDiagram[i+2] + veitchDiagram[i+3];
            if (value < 10) {
                sb.append(value);
            }
            else {
                sb.append((char)('A' + value - 10));
            }
        }

        return sb.toString();
    }

    static String convertExpression (String expression) {

        StringBuilder sb = new StringBuilder();
        String inputs = "ABCD";

        for (int i = 0; i < 4; i++)
        {
            String input = "" + inputs.charAt(i);
            if (expression.contains("~" + input)) {
                sb.append("0");
            }
            else if (expression.contains(input)) {
                sb.append("1");
            }
            else {
                sb.append("*");
            }
        }

        return sb.toString();
    }

    static boolean checkMatch (String binaryExpression, String cell) {

        boolean match = true;
        for (int i = 0; i < 4; i++)
        {
            if (binaryExpression.charAt(i) != '*' && binaryExpression.charAt(i) != cell.charAt(i)) {
                match = false;
                break;
            }
        }

        return match;
    }

    public static void main (String [] args) {

        String[] input = new String[10];
        input[0] = "AB+~AB+~A~B";
        input[1] = "AB~C~D+AB~CD+~A~B~CD";
        input[2] = "AB~C~D+~AB~C~D+A~B~C~D";
        input[3] = "B~D+~B~D";
        input[4] = "~A~BD+~A~B~D";
        input[5] = "B~D+~A~BD+A~B~C";
        input[6] = "~B~C+BCD+B~C~D";
        input[7] = "A~C+ACD+~A~CD";
        input[8] = "AB~D+~ABD+A~BD+~A~B~D";
        input[9] = "B~D+~A~CD+~A~B~C~D";

        String[] output = {"FF33", "8810", "9008", "F00F", "0033", "F0B8", "9699", "8DD8", "C3C3", "F111"};

        for (int i = 0; i < 10; i++)
        {
            String result = getVeitchDiagram(input[i]);

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