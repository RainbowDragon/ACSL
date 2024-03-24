/**
 *      ACSL 2022-2023 - Contest 4 - Targets - Junior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class TargetsJunior {

    static String arrowForMostTargets (int size, String targets) {

        boolean[][] grid = new boolean[size][size];
        StringTokenizer st = new StringTokenizer(targets);
        while (st.hasMoreTokens())
        {
            int location = Integer.parseInt(st.nextToken());
            int row = location / 10;
            int col = location % 10;
            grid[row][col] = true;
        }

        mostCount = -1;

        for (int i = 0; i < size; i++)
        {
            getMostTargets (grid, size, 0, i);
        }

        for (int i = 1; i < size-1; i++)
        {
            getMostTargets (grid, size, i, 0);
            getMostTargets (grid, size, i, size-1);
        }

        for (int i = 0; i < size; i++)
        {
            getMostTargets (grid, size, size-1, i);
        }

        return result;
    }

    static int[] dr = {0, -1, 0, 1, -1, -1, 1, 1};
    static int[] dc = {-1, 0, 1, 0, -1, 1, 1, -1};

    static int mostCount = -1;
    static String result = "";

    static void getMostTargets (boolean[][] grid, int size, int row, int col) {

        for (int i = 0; i < 8; i++)
        {
            int count = 0;
            boolean inTarget = false;
            int sr = row;
            int sc = col;

            while (sr >= 0 && sr < size && sc >= 0 && sc < size)
            {
                if (!inTarget) {
                    if (grid[sr][sc]) {
                        inTarget = true;
                        count++;
                    }
                }
                else {
                    if (grid[sr][sc]) {
                        count++;
                    }
                    else {
                        break;
                    }
                }
                sr += dr[i];
                sc += dc[i];
            }

            if (count > mostCount) {
                mostCount = count;
                char dir = (char)('A' + i);
                result = "" + row + col + dir;
            }
        }
    }

    public static void main (String [] args) {

        String[][] input = new String[10][];
        input[0] = new String[]{"6", "13 21 41 42 44"};
        input[1] = new String[]{"5", "31 21 13 32 11 12"};
        input[2] = new String[]{"10", "81 84 86 87 88 71 73 75 77 32 33 45 47 48 11 13 15 16"};
        input[3] = new String[]{"8", "65 45 55 32 42 54 13 14 41 61 24"};
        input[4] = new String[]{"4", "12 22 21"};
        input[5] = new String[]{"9", "11 14 17 33 44 24 31 35 37 45 41 53 57 62 64 66 71 77"};
        input[6] = new String[]{"7", "15 23 24 32 35 31 45 55 44"};
        input[7] = new String[]{"6", "43 33 44 14 23 41"};
        input[8] = new String[]{"10", "25 71 82 63 54 45 56 75 77 88 21 24 26 27 28 12 13 15 34 35 33 37"};
        input[9] = new String[]{"8", "12 13 16 21 22 31 34 35 36 45 43 41 52 54 56 66 65 64 63 61"};

        String[] output = new String[10];
        output[0] = "40C";
        output[1] = "01D";
        output[2] = "89A";
        output[3] = "05D";
        output[4] = "02D";
        output[5] = "80F";
        output[6] = "65B";
        output[7] = "03D";
        output[8] = "29A";
        output[9] = "27H";

        for (int i = 0; i < 10; i++)
        {
            String result = arrowForMostTargets(Integer.parseInt(input[i][0]), input[i][1]);

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