/**
 *      ACSL 2022-2023 - Contest 4 - Targets - Intermediate Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class TargetsIntermediate {

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

        String[][] input = new String[10][];
        input[0] = new String[]{"6", "13 21 41 42 44"};
        input[1] = new String[]{"5", "31 21 13 32 11 12"};
        input[2] = new String[]{"10", "81 84 86 87 88 71 73 75 77 32 33 45 47 48 11 13 15 16"};
        input[3] = new String[]{"8", "65 45 55 32 42 54 13 14 41 61 24"};
        input[4] = new String[]{"4", "12 22 21"};
        input[5] = new String[]{"9", "11 14 17 33 44 24 31 35 37 45 41 53 57 62 64 66 71 77"};
        input[6] = new String[]{"7", "15 23 24 32 35 31 45 55 44"};
        input[7] = new String[]{"6", "11 22 33 44 14 23 41"};
        input[8] = new String[]{"10", "71 82 63 54 45 56 75 77 88 21 24 26 27 28 12 13 15 34 35 33 37"};
        input[9] = new String[]{"5", "11 12 13 21 22 23 31 32 33"};

        String[] output = new String[10];
        output[0] = "13";
        output[1] = "11 13";
        output[2] = "16 32";
        output[3] = "13 61";
        output[4] = "12 21 22";
        output[5] = "45";
        output[6] = "31";
        output[7] = "11 14 22 33 41 44";
        output[8] = "63";
        output[9] = "11 13 31 33";

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