/**
 *      ACSL 2019-2020 - Contest 3 - Veitch - Junior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class VeitchJunior {

    static String getBooleanExpression (String veitchDiagram) {

        int[] masksInOrder = {
                240, 15, 204, 102, 51, 153,
                192, 96, 48, 12, 6, 3,
                136, 68, 34, 17, 144, 9,
                128, 64, 32, 16,
                8, 4, 2, 1
        };
        String[] booleanExpressionsInOrder = {
                "B", "~B", "A", "C", "~A", "~C",
                "AB", "BC", "~AB", "A~B", "~BC", "~A~B",
                "A~C", "AC", "~AC", "~A~C", "B~C", "~B~C",
                "AB~C", "ABC", "~ABC", "~AB~C",
                "A~B~C", "A~BC", "~A~BC", "~A~B~C"
        };

        int number = Integer.parseInt(veitchDiagram, 16);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < booleanExpressionsInOrder.length; i++)
        {
            number = checkMatch(number, masksInOrder[i], booleanExpressionsInOrder[i], sb);
        }

        return sb.toString();
    }

    static int checkMatch (int number, int mask, String expression, StringBuilder sb) {

        if ((number & mask) == mask) {
            if (!sb.isEmpty()) {
                sb.append("+");
            }
            sb.append(expression);

            number -= mask;
        }

        return number;
    }

    public static void main (String [] args) {

        String[] input = {"33", "3C", "94", "77", "95", "F0", "1D", "9D", "E9", "E7"};

        String[] output = {
                "~A", "~AB+A~B", "B~C+A~BC", "C+~A~C", "~A~C+AB~C+A~BC",
                "B", "A~B+~A~C", "~C+A~BC", "AB+~B~C+~ABC", "C+AB~C+~A~B~C"
        };

        for (int i = 0; i < 10; i++)
        {
            String result = getBooleanExpression(input[i]);

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