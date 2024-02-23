/**
 *      ACSL 2019-2020 - Contest 3 - Veitch - Junior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class VeitchJunior {

    static String getBooleanExpression (String veitchDiagram) {

        int input = getIntegerFromVeitchDiagram(veitchDiagram);

        StringBuilder sb = new StringBuilder();

        input = checkMatch(input, 240, "B", sb);
        input = checkMatch(input, 15, "~B", sb);
        input = checkMatch(input, 204, "A", sb);
        input = checkMatch(input, 102, "C", sb);
        input = checkMatch(input, 51, "~A", sb);
        input = checkMatch(input, 153, "~C", sb);

        input = checkMatch(input, 192, "AB", sb);
        input = checkMatch(input, 96, "BC", sb);
        input = checkMatch(input, 48, "~AB", sb);
        input = checkMatch(input, 12, "A~B", sb);
        input = checkMatch(input, 6, "~BC", sb);
        input = checkMatch(input, 3, "~A~B", sb);
        input = checkMatch(input, 136, "A~C", sb);
        input = checkMatch(input, 68, "AC", sb);
        input = checkMatch(input, 34, "~AC", sb);
        input = checkMatch(input, 17, "~A~C", sb);
        input = checkMatch(input, 144, "B~C", sb);
        input = checkMatch(input, 9, "~B~C", sb);

        input = checkMatch(input, 128, "AB~C", sb);
        input = checkMatch(input, 64, "ABC", sb);
        input = checkMatch(input, 32, "~ABC", sb);
        input = checkMatch(input, 16, "~AB~C", sb);
        input = checkMatch(input, 8, "A~B~C", sb);
        input = checkMatch(input, 4, "A~BC", sb);
        input = checkMatch(input, 2, "~A~BC", sb);
        checkMatch(input, 1, "~A~B~C", sb);

        return sb.toString();
    }

    static int checkMatch (int input, int mask, String expression, StringBuilder sb) {

        if ((input & mask) == mask) {
            if (!sb.isEmpty()) {
                sb.append(" + ");
            }
            sb.append(expression);

            input = input & (~mask);
        }

        return input;
    }

    static int getIntegerFromVeitchDiagram (String veitchDiagram) {

        int front = Integer.parseInt(veitchDiagram.substring(0,1), 16);
        int back = Integer.parseInt(veitchDiagram.substring(1,2), 16);

        return (front << 4) + back;
    }

    public static void main (String [] args) {

        String[] input = {"33", "3C", "94", "77", "95", "F0", "1D", "9D", "E9", "E7"};

        String[] output = {"~A", "~AB + A~B", "B~C + A~BC", "C + ~A~C", "~A~C + AB~C + A~BC", "B", "A~B + ~A~C", "~C + A~BC", "AB + ~B~C + ~ABC", "C + AB~C + ~A~B~C"};

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