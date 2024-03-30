/**
 *      ACSL 2022-2023 - Contest 4 - Targets - Senior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class TargetsSenior {

    /*
     * Complete the 'missingArrow' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. INTEGER size
     *  2. STRING targets
     *  3. STRING numbers
     *  4. STRING arrows
     */
    static String missingArrow (int size, String targets, String numbers, String arrows) {

        boolean[][] grid = new boolean[size][size];
        StringTokenizer st = new StringTokenizer(targets);
        while (st.hasMoreTokens())
        {
            int location = Integer.parseInt(st.nextToken());
            int r = location / 10;
            int c = location % 10;
            grid[r][c] = true;
        }

        int[] rowCount = new int[size];
        int[] colCount = new int[size];
        for (int i = 0; i < size; i++)
        {
            rowCount[i] = numbers.charAt(i) - '0';
            colCount[i] = numbers.charAt(i+size+1) - '0';
        }

        int[][] board = new int[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
            {
                board[i][j] = -1;
            }

        st = new StringTokenizer(arrows);
        while (st.hasMoreTokens())
        {
            String token = st.nextToken();
            int r = token.charAt(0) - '0';
            int c = token.charAt(1) - '0';
            int dir = token.charAt(2) - 'A';
            board[r][c] = dir;
        }

        int[] rowSum = new int[size];
        int[] colSum = new int[size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
            {
                if (board[i][j] >= 0) {
                    rowSum[i]++;
                    colSum[j]++;
                }
            }

        int row = -1;
        int col = -1;
        for (int i = 0; i < size; i++)
        {
            if (rowSum[i] < rowCount[i]) {
                row = i;
            }

            if (colSum[i] < colCount[i]) {
                col = i;
            }
        }

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
            {
                if (board[i][j] >= 0) {
                    updateBoard (grid, size, i, j, board[i][j]);
                }
            }

        int dir = -1;
        for (int i = 0; i < 8; i++)
        {
            int sr = row;
            int sc = col;

            while (sr >= 0 && sr < size && sc >= 0 && sc < size)
            {
                if (grid[sr][sc]) {
                    dir = i;
                    break;
                }

                sr += dr[i];
                sc += dc[i];
            }
        }

        return "" + row + col + (char)('A'+dir);
    }

    static int[] dr = {0, -1, 0, 1, -1, -1, 1, 1};
    static int[] dc = {-1, 0, 1, 0, -1, 1, 1, -1};

    static void updateBoard (boolean[][] grid, int size, int row, int col, int dir) {

        int sr = row;
        int sc = col;

        while (sr >= 0 && sr < size && sc >= 0 && sc < size)
        {
            if (grid[sr][sc]) {
                grid[sr][sc] = false;
                break;
            }

            sr += dr[dir];
            sc += dc[dir];
        }
    }

    public static void main (String [] args) {

        String[][] input = new String[10][];
        input[0] = new String[]{"4", "02 11 20 21", "0103 1012", "13E 30B 33E"};
        input[1] = new String[]{"6", "15 23 24 32 33 34 42 43 51", "401211 401211", "00G 20G 40G 53B 02G 03D 04G 35A"};
        input[2] = new String[]{"6", "00 10 13 20 21 24 30 31 43 50 53", "420113 022124", "01H 02H 03H 04H 15H 14H 45A 51B 55E 52E"};
        input[3] = new String[]{"6", "02 05 13 23 12 35 24 00", "011114 410111", "14F 20F 50F 53F 51F 40F 30F"};
        input[4] = new String[]{"6", "01 10 23 42 53 12 04 52 00", "200232 111024", "05H 34H 35H 54E 55E 40F 45A 41B"};
        input[5] = new String[]{"6", "02 04 10 13 20 33 35 40 44 55", "202204 212122", "00C 05A 22F 25D 32E 50B 51F 53E 54C"};
        input[6] = new String[]{"6", "01 10 20 21 22 23 24 31 45 51", "310123 100441", "03A 04D 05H 13A 34A 43E 44E 53A 54F"};
        input[7] = new String[]{"6", "11 12 13 30 35 41 42 43 45 51 52", "323102 422102", "00G 01G 02G 10G 15D 20D 22D 23G 31D 50C"};
        input[8] = new String[]{"6", "11 20 22 31 32 33 40 42 44 50 51 53", "411132 231114", "00D 01D 04H 05H 15H 21D 30D 43A 45A 52A 55A"};
        input[9] = new String[]{"6", "05 11 20 21 22 23 33 42 43 52 53 54", "321312 401043", "00D 02H 04H 14H 15B 24H 34H 35H 40C 50C 55A"};

        String[] output = new String[10];
        output[0] = "32E";
        output[1] = "30C";
        output[2] = "35H";
        output[3] = "55E";
        output[4] = "02D";
        output[5] = "34D";
        output[6] = "50B";
        output[7] = "55A";
        output[8] = "41H";
        output[9] = "30F";

        for (int i = 0; i < 10; i++)
        {
            String result = missingArrow(Integer.parseInt(input[i][0]), input[i][1], input[i][2], input[i][3]);

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