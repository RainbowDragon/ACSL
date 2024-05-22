/**
 *      ACSL 2023 Final - Problem 2 - Intervals - Junior / Intermediate Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class Intervals {

    /*
     * Complete the 'sumsNotCommon' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING intervals1
     *  2. STRING intervals2
     *  3. STRING intervals3
     */
    static String sumsNotCommon (String intervals1, String intervals2, String intervals3) {

        int[] count = new int[199];

        updateCount(intervals1, count);
        updateCount(intervals2, count);

        boolean hasThree = false;
        if (!intervals3.equals("null")) {
            updateCount(intervals3, count);
            hasThree = true;
        }

        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < count.length; i++)
        {
            if (count[i] == 1) {
                sum1 += i - offset;
            }
            if (count[i] == 2) {
                sum2 += i - offset;
            }
        }

        return hasThree ? sum1 + " " + sum2 : "" + sum1;
    }

    static void updateCount (String intervals, int[] count) {

        String[] intervalList = intervals.split(" ");

        for (int i = 0; i < intervalList.length; i++)
        {
            String interval = intervalList[i];
            int index = interval.indexOf(',');
            int start = Integer.parseInt(interval.substring(1, index));
            if (interval.charAt(0) == '(') {
                start++;
            }
            int end = Integer.parseInt(interval.substring(index+1, interval.length()-1));
            if (interval.charAt(interval.length()-1) == ')') {
                end--;
            }

            for (int k = start; k <= end; k++)
            {
                count[k+offset]++;
            }
        }
    }

    static int offset = 99;

    public static void main (String [] args) {

        String[][] input = {
                {
                        "(5,10) (-5,2] [24,28] [12,22)",
                        "[1,4] [15,25) [-4,-1) (7,12]",
                        "null"
                },
                {
                        "[0,5) (-15,-5] [8,12] (-4,0)",
                        "[3,7) (8,15) [-10,-4) [-4,2] [15,20)",
                        "null"
                },
                {
                        "(5,10] [24,25) [13,20] (0,4)",
                        "[1,5) [25,26] (8,12] (15,24)",
                        "[4,12] [-5,3) (12,25]"
                },
                {
                        "(-84,-80) (-65,-60] [-99,-90) [-80,-70]",
                        "(-71,-64) [-95,-91) [-74,-71] (-87,-81]",
                        "[-85,-79) (-71,-62] [-97,-87] (-61,-55)"
                },
                {
                        "(2,6) (13,17) (6,9) (9,13) (0,2)",
                        "[15,18] [0,5] [6,14]",
                        "(11,14] [3,4) (6,10] [5,5]"
                },
                {
                        "(6,8] [-4,3) [-10,-4) [12,14)",
                        "[7,10] (-10,-5) [11,16) (-2,3]",
                        "null"
                },
                {
                        "[6,8) (-4,3] (-10,-4] (12,14]",
                        "(7,11) [-10,-5] (11,16] [-2,3)",
                        "null"
                },
                {
                        "[16,18) (-4,13] (-12,-6] (22,24]",
                        "(17,21) [5,7] (11,16] [-3,3)",
                        "null"
                },
                {
                        "[61,81) (-42,-30] (-10,-4] (22,44]",
                        "(66,83) [-45,-25] [21,45) (-12,-3]",
                        "null"
                },
                {
                        "[1,5] [-20,-10] (-8,0) [6,13]",
                        "(-21,-8) [-2,2) [-6,-4) [3,10)",
                        "(4,12] (-20,0)"
                },
                {
                        "[21,52] [-10,0) (8,20) [6,8]",
                        "(25,48) [-7,2) [16,24) [3,10)",
                        "(14,22] (-20,0) [26,50) [2,12]"
                },
                {
                        "[19,89] (-9,9) [-99,-9]",
                        "(-59,0) [-96,-85] (2,45] [-80,-60] (45,98]",
                        "(33,67] (-76,-15) [-98,-80) (-10,32) (70,97]"
                },
                {
                        "[13,19] [1,2] [5,6] [9,10]",
                        "(15,19) [1,6] [9,15]",
                        "[19,20] [2,9) [10,18)"
                },
                {
                        "(0,7] [-15,-4) (17,25] (10,15] [-19,-19]",
                        "(9,9) (-20,-15) (10,20] [-12,-4) (0,7]",
                        "(15,25) [-12,-2] [-19,-15) [10,15] [0,8)"
                },
                {
                        "[1,9] [10,12] [13,13] [14,15) [15,16] (16,26]",
                        "[16,26] [1,15]",
                        "[1,26]"
                }
        };

        String[] output = {
                "218", "77", "16 208", "-1313 -1407", "43 69",
                "38", "69", "144", "254", "7 -2",
                "97 173", "-1 109", "35 77", "-16 72", "0 0"
        };

        for (int i = 0; i < 15; i++)
        {
            String result = sumsNotCommon(input[i][0], input[i][1], input[i][2]);

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
