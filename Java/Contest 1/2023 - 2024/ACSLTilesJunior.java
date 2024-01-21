/**
 *      ACSL 2023-2024 - Contest 1 - ACSL Tiles - Junior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class ACSLTilesJunior {

    /*
     * Complete the 'findDiscardSum' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     * 1. INTEGER originalRows
     * 2. STRING tiles
     */
    static int findDiscardSum (int originalRows, String tiles) {

        int[] rows = new int[4];
        for (int i = 3; i >= 0; i--)
        {
            rows[i] = originalRows % 10;
            originalRows /= 10;
        }

        int result = 0;

        StringTokenizer st = new StringTokenizer(tiles);
        while (st.hasMoreTokens())
        {
            int cur = Integer.parseInt(st.nextToken());
            int front = cur / 10;
            int back = cur % 10;

            boolean isMatched = false;

            for (int i = 0; i < 4; i++)
            {
                if (rows[i] == front) {
                    isMatched = true;
                    rows[i] = back;
                    break;
                }
                else if (rows[i] == back) {
                    isMatched = true;
                    rows[i] = front;
                    break;
                }
            }

            if (!isMatched) {
                result += (front + back);
            }
        }

        return result;
    }

    public static void main (String [] args) {

        String[][] input = new String[10][];
        input[0] = new String[]{"5923", "56 85 27 73 14 34 62"};
        input[1] = new String[]{"8423", "74 92 57 93 26 87 14 63 82 54 12"};
        input[2] = new String[]{"1253", "51 81 35 84 95 26 59 13 71 35 46 28"};
        input[3] = new String[]{"2694", "69 76 41 89 16 78 64 36 12 95 52"};
        input[4] = new String[]{"6479", "58 73 92 54 75 35 78 25 81 24 16 95 36 82 14 27 43 13 51"};
        input[5] = new String[]{"3972", "18 17 65 61 37 51 57 38 72 92 54 59 43 41 31 28"};
        input[6] = new String[]{"9146", "95 74 51 19 75 26 32 39 35 31 25 73"};
        input[7] = new String[]{"7918", "63 18 56 98 48 52 26 92 83 13 17 58 91 67 58"};
        input[8] = new String[]{"9758", "52 14 51 27 77 62 76 82 96 56 46 49 87"};
        input[9] = new String[]{"7169", "71 56 15 65 98 71 89 71 11 55 77 17 66 51"};

        int[] output = {18, 26, 31, 22, 45, 56, 0, 59, 48, 14};

        for (int i = 0; i < 10; i++)
        {
            int result = findDiscardSum(Integer.parseInt(input[i][0]), input[i][1]);

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