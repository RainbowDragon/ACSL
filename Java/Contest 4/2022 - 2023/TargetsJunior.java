/**
 *      ACSL 2022-2023 - Contest 4 - Targets - Junior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class TargetsJunior {

    /*
     * Complete the 'arrowForMostTargets' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     * 1. INTEGER size
     * 2. STRING targets
     */
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

        String[][] input = {
                {"6", "13 21 41 42 44"},
                {"5", "31 21 13 32 11 12"},
                {"10", "81 84 86 87 88 71 73 75 77 32 33 45 47 48 11 13 15 16"},
                {"8", "65 45 55 32 42 54 13 14 41 61 24"},
                {"4", "12 22 21"},
                {"9", "11 14 17 33 44 24 31 35 37 45 41 53 57 62 64 66 71 77"},
                {"7", "15 23 24 32 35 31 45 55 44"},
                {"6", "43 33 44 14 23 41"},
                {"10", "25 71 82 63 54 45 56 75 77 88 21 24 26 27 28 12 13 15 34 35 33 37"},
                {"8", "12 13 16 21 22 31 34 35 36 45 43 41 52 54 56 66 65 64 63 61"}
        };

        String[] output = {
                "40C", "01D", "89A", "05D", "02D", "80F", "65B", "03D", "29A", "27H"
        };

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