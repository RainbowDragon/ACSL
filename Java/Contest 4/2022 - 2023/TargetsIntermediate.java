/**
 *      ACSL 2022-2023 - Contest 4 - Targets - Intermediate Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class TargetsIntermediate {

    /*
     * Complete the 'targetsWithMostArrows' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. INTEGER size
     *  2. STRING targets
     */
    static String targetsWithMostArrows (int size, String targets) {

        boolean[][] grid = new boolean[size][size];
        StringTokenizer st = new StringTokenizer(targets);
        while (st.hasMoreTokens())
        {
            int location = Integer.parseInt(st.nextToken());
            int row = location / 10;
            int col = location % 10;
            grid[row][col] = true;
        }

        int[][] board = new int[size][size];

        for (int i = 0; i < size; i++)
        {
            updateBoard (grid, board, size, 0, i);
        }

        for (int i = 1; i < size-1; i++)
        {
            updateBoard (grid, board, size, i, 0);
            updateBoard (grid, board, size, i, size-1);
        }

        for (int i = 0; i < size; i++)
        {
            updateBoard (grid, board, size, size-1, i);
        }

        int mostCount = -1;
        String result = "";

        for (int i = 1; i < size-1; i++)
            for (int j = 1; j < size-1; j++)
            {
                if (board[i][j] > mostCount) {
                    mostCount = board[i][j];
                    result = "" + i + j;
                }
                else if (board[i][j] == mostCount) {
                    result += " " + i + j;
                }
            }

        return result;
    }

    static int[] dr = {0, -1, 0, 1, -1, -1, 1, 1};
    static int[] dc = {-1, 0, 1, 0, -1, 1, 1, -1};

    static void updateBoard (boolean[][] grid, int[][] board, int size, int row, int col) {

        for (int i = 0; i < 8; i++)
        {
            int sr = row;
            int sc = col;

            while (sr >= 0 && sr < size && sc >= 0 && sc < size)
            {
                if (grid[sr][sc]) {
                    board[sr][sc]++;
                    break;
                }

                sr += dr[i];
                sc += dc[i];
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
                {"6", "11 22 33 44 14 23 41"},
                {"10", "71 82 63 54 45 56 75 77 88 21 24 26 27 28 12 13 15 34 35 33 37"},
                {"5", "11 12 13 21 22 23 31 32 33"}
        };

        String[] output = {
                "13", "11 13", "16 32", "13 61", "12 21 22",
                "45", "31", "11 14 22 33 41 44", "63", "11 13 31 33"
        };

        for (int i = 0; i < 10; i++)
        {
            String result = targetsWithMostArrows(Integer.parseInt(input[i][0]), input[i][1]);

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