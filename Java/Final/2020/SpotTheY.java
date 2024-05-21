/**
 *      ACSL 2020 Final - Problem 1 - Spot the Y - Junior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class SpotTheY {

    /*
     * Complete the 'spot_the_y' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. STRING line
     */
    static int spot_the_y (int n, String line) {

        int[][] grid = new int[n][n];

        StringTokenizer st = new StringTokenizer(line);
        int sign = 1;
        int result = 0;
        while (st.hasMoreTokens())
        {
            int location = Integer.parseInt(st.nextToken()) - 1;
            int row = location / n;
            int col = location % n;

            if (grid[row][col] == 0) {
                grid[row][col] = sign;

                for (int i = row-1; i <= row+1; i++)
                    for (int j = col-1; j <= col+1; j++)
                    {
                        result = checkGrid(grid, n, i, j, sign);
                        if (result > 0) {
                            return result;
                        }
                    }
            }
            else {
                grid[row][col] = 0;
            }

            sign *= -1;
        }

        return result;
    }

    static int checkGrid (int[][] grid, int n, int row, int col, int sign) {

        if (checkCell(grid, n, row, col, sign)) {
            if (checkCell(grid, n, row - 1, col - 1, sign)
                    && checkCell(grid, n, row - 1, col + 1, sign)
                    && checkCell(grid, n, row + 1, col, sign)) {
                return getCellValue(row, col, n) + getCellValue(row - 1, col - 1, n)
                        + getCellValue(row - 1, col + 1, n) + getCellValue(row + 1, col, n);
            }

            if (checkCell(grid, n, row + 1, col - 1, sign)
                    && checkCell(grid, n, row + 1, col + 1, sign)
                    && checkCell(grid, n, row - 1, col, sign)) {
                return getCellValue(row, col, n) + getCellValue(row + 1, col - 1, n)
                        + getCellValue(row + 1, col + 1, n) + getCellValue(row - 1, col, n);
            }

            if (checkCell(grid, n, row - 1, col - 1, sign)
                    && checkCell(grid, n, row + 1, col - 1, sign)
                    && checkCell(grid, n, row, col + 1, sign)) {
                return getCellValue(row, col, n) + getCellValue(row - 1, col - 1, n)
                        + getCellValue(row + 1, col - 1, n) + getCellValue(row, col + 1, n);
            }

            if (checkCell(grid, n, row - 1, col + 1, sign)
                    && checkCell(grid, n, row + 1, col + 1, sign)
                    && checkCell(grid, n, row, col - 1, sign)) {
                return getCellValue(row, col, n) + getCellValue(row - 1, col + 1, n)
                        + getCellValue(row + 1, col + 1, n) + getCellValue(row, col - 1, n);
            }
        }

        return 0;
    }

    static boolean checkCell (int[][] grid, int n, int row, int col, int sign) {
        return 0 <= row && row < n && 0 <= col && col < n && grid[row][col] == sign;
    }

    static int getCellValue (int row, int col, int n) {
        return row * n + col + 1;
    }

    public static void main (String [] args) {

        String[][] input = {
                {"5", "1 14 12 18 3 15 7 8"},
                {"5", "1 14 24 20 12 18 3 15 12 20 17 8"},
                {"6", "23 1 21 5 23 8 14 36 16 12 27 7"},
                {"5", "8 1 25 24 25 11 8 7 22 19 15 8 22 16 21 3"},
                {"6", "25 21 13 15 20 10 27 1 14 3 32 28 9 36 14 29 22 1"},
                {"6", "36 24 29 22 17 11 29 27 17 27 34 17 23 35 29"},
                {"8", "3 10 46 12 3 13 37 12 54 19 39 27 39 12"},
                {"8", "12 1 19 10 3 17 19 1 5 2 28 19 28 17 20"},
                {"4", "3 5 10 11 10 12 6 10 1 14 13 12 4 14 14 7 9 11 8 2 8 11 13 15"},
                {"7", "10 31 15 23 18 25 16 45 18 30 46 38 24 25 15 25 32 22 10"},
                {"9", "10 20 30 2 4 6 8 3 9 12 15 18 21 24 27 5 25 7 14 11 17 1"},
                {"10", "99 88 80 78 69 97 60 68 99 78 67 68 67 78 68 99"},
                {"3", "1 2 3 4 5 6 7 8 9 8 7 6 5 4 3 2 1 1 3 5 7 8 6 4 2 5 5"}
        };

        int[] output = {
            23, 55, 78, 27, 86, 74, 176, 48, 0, 117, 77, 277, 23
        };

        for (int i = 0; i < 13; i++)
        {
            int result = spot_the_y(Integer.parseInt(input[i][0]), input[i][1]);

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