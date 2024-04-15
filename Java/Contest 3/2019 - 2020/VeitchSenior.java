/**
 *      ACSL 2019-2020 - Contest 3 - Veitch - Senior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class VeitchSenior {

    static String getBooleanExpression (String veitchDiagram) {

        String[] binaryExpressionsInOrder = {
                "*1**", "***1", "*0**", "1***", "**1*", "0***", "**0*", "***0",
                "*1*0", "*1*1", "*0*1", "*0*0", "1*0*", "1*1*", "0*1*", "0*0*",
                "11**", "*11*", "01**", "1**1", "**11", "0**1", "10**", "*01*",
                "00**", "*10*", "**01", "*00*", "1**0", "**10", "0**0", "**00",
                "11*0", "*110", "01*0", "11*1", "*111", "01*1", "10*1", "*011",
                "00*1", "10*0", "*010", "00*0", "110*", "1*01", "100*", "111*",
                "1*11", "101*", "011*", "0*11", "001*", "010*", "0*01", "000*",
                "*100", "*101", "*001", "*000", "1*00", "1*10", "0*10", "0*00",
                "1100", "1110", "0110", "0100", "1101", "1111", "0111", "0101",
                "1001", "1011", "0011", "0001", "1000", "1010", "0010", "0000"
        };
        String[] grid = {
                "1100", "1110", "0110", "0100", "1101", "1111", "0111", "0101",
                "1001", "1011", "0011", "0001", "1000", "1010", "0010", "0000"
        };

        int number = Integer.parseInt(veitchDiagram, 16);

        StringBuilder sb = new StringBuilder();
        for (String binaryExpression : binaryExpressionsInOrder)
        {
            int mask = getIntegerFromBinaryExpression(binaryExpression, grid);
            if ((number & mask) == mask) {
                if (!sb.isEmpty()) {
                    sb.append("+");
                }
                sb.append(getBooleanExpressionFromBinaryExpression(binaryExpression));
                number -= mask;
            }
        }

        return sb.toString();
    }

    static String getBooleanExpressionFromBinaryExpression (String binaryExpression) {

        StringBuilder sb = new StringBuilder();
        String inputs = "ABCD";

        for (int i = 0; i < 4; i++)
        {
            if (binaryExpression.charAt(i) == '1') {
                sb.append(inputs.charAt(i));
            }
            else if (binaryExpression.charAt(i) == '0') {
                sb.append('~');
                sb.append(inputs.charAt(i));
            }
        }

        return sb.toString();
    }

    static int getIntegerFromBinaryExpression (String binaryExpression, String[]grid) {

        int result = 0;
        for (String cell : grid)
        {
            if (checkMatch(binaryExpression, cell)) {
                result += 1;
            }

            result <<= 1;
        }

        return result >> 1;
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

        String[] input = {
                "FF33", "00CC", "6090", "8810", "9008",
                "F0B8", "9699", "8DD8", "C3C3", "F111"
        };

        String[] output = {
                "B+~A~B",
                "A~B",
                "BC~D+~B~CD",
                "AB~C+~A~B~CD",
                "B~C~D+A~B~C~D",
                "B~D+~A~BD+A~B~C",
                "~B~C+BCD+B~C~D",
                "A~C+ACD+~A~CD",
                "AB~D+~ABD+A~BD+~A~B~D",
                "B~D+~A~CD+~A~B~C~D"
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