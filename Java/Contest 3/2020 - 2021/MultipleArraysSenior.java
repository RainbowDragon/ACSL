/**
 *      ACSL 2020-2021 - Contest 3 - MultipleArrays - Senior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class MultipleArraysSenior {

    /*
     * Complete the 'sumOfMinAlongPath' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING dim
     *  2. STRING_ARRAY arrays
     */
    static int sumOfMinAlongPath (String dim, List<String> arrays) {

        StringTokenizer st = new StringTokenizer(dim);
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());

        int n = arrays.size();
        int[][][] boards = new int[n][row][col];
        for (int i = 0; i < n; i++)
        {
            fillArray(arrays.get(i), boards, row, col, i);
        }

        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
        boolean[][] visited = new boolean[row][col];

        int i = 0;
        int j = 0;
        int result = 0;
        while (!visited[i][j])
        {
            visited[i][j] = true;
            result += findSmallest(boards, i, j, n);

            HashMap<Integer, Integer> countMap = new HashMap<>();
            for (int k = 0; k < 8; k++)
            {
                int nextI = i + dx[k];
                int nextJ = j + dy[k];

                if (nextI >= 0 && nextI < row && nextJ >= 0 && nextJ < col) {
                    for (int l = 0; l < n; l++)
                    {
                        if (countMap.containsKey(boards[l][nextI][nextJ])) {
                            countMap.put(boards[l][nextI][nextJ], countMap.get(boards[l][nextI][nextJ])+1);
                        }
                        else {
                            countMap.put(boards[l][nextI][nextJ], 1);
                        }
                    }
                }
            }

            ArrayList<Integer> numberList = new ArrayList<>();
            for (int key : countMap.keySet())
            {
                if (countMap.get(key) == 1) {
                    numberList.add(key);
                }
            }
            Collections.sort(numberList);
            int maxNumber = numberList.getLast();

            boolean found = false;
            for (int k = 0; k < 8; k++)
            {
                int nextI = i + dx[k];
                int nextJ = j + dy[k];

                if (nextI >= 0 && nextI < row && nextJ >= 0 && nextJ < col) {
                    for (int l = 0; l < n; l++)
                    {
                        if (boards[l][nextI][nextJ] == maxNumber) {
                            i = nextI;
                            j = nextJ;
                            found = true;
                            break;
                        }
                    }
                }

                if (found) {
                    break;
                }
            }
        }

        return result;
    }

    static void fillArray (String array, int[][][] arrays, int row, int col, int index) {

        StringTokenizer st = new StringTokenizer(array);
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
            {
                arrays[index][i][j] = Integer.parseInt(st.nextToken());
            }
    }

    static int findSmallest (int[][][] arrays, int row, int col, int n) {

        int result = arrays[0][row][col]+1;
        for (int i = 0; i < n; i++)
        {
            result = Math.min(result, arrays[i][row][col]);
        }

        return result;
    }

    public static void main (String [] args) {

        String[][] input = {
                {
                        "3 4",
                        "6 2 7 4 6 1 5 7 5 6 7 8",
                        "4 8 6 4 4 5 7 2 7 6 5 4",
                        "3 6 9 2 4 8 2 6 3 2 1 4",
                        "4 3 5 7 6 8 9 1 2 9 3 5"
                },
                {
                        "4 4",
                        "5 2 8 3 1 8 5 3 0 7 1 7 9 5 8 6",
                        "5 4 0 9 5 4 6 2 8 1 8 2 8 1 7 2",
                        "2 7 1 8 2 8 5 8 2 8 4 5 9 0 4 5"
                },
                {
                        "5 3",
                        "9 9 9 8 8 8 7 7 7 6 6 6 5 5 5",
                        "5 6 7 8 5 5 6 7 8 9 5 6 7 8 9",
                        "5 6 3 2 1 9 4 3 2 1 5 4 3 2 1",
                        "5 5 5 6 6 6 7 7 7 8 8 8 9 9 9",
                        "1 2 3 4 5 6 7 8 9 8 7 6 5 4 3"
                },
                {
                        "3 5",
                        "5 3 4 5 6 7 8 9 8 7 6 5 4 3 2",
                        "1 3 5 7 9 7 5 9 1 2 4 3 8 6 4",
                        "3 2 4 5 1 6 5 8 9 2 3 8 1 4 6"
                },
                {
                        "5 3",
                        "8 6 4 2 5 2 4 6 8 7 8 6 4 2 0",
                        "7 9 7 5 3 8 3 6 7 9 1 9 7 5 3",
                        "5 2 3 4 8 6 7 8 9 5 2 3 4 5 6",
                        "9 4 9 2 3 9 1 8 7 6 5 4 3 2 8",
                        "1 5 4 7 8 9 3 2 1 4 5 6 9 8 7"
                },
                {
                        "5 6",
                        "3 1 4 1 5 9 2 6 5 3 5 8 9 7 9 3 2 3 8 4 6 2 6 4 3 3 8 3 2 7",
                        "7 2 3 8 3 3 4 6 2 6 4 8 3 2 3 9 7 9 8 5 3 5 6 2 9 5 1 4 1 3",
                        "6 2 8 3 1 8 5 3 6 7 1 8 6 2 5 3 1 8 5 3 4 7 6 8 6 2 8 3 1 8",
                        "2 7 1 8 2 8 1 8 2 8 4 6 2 7 1 8 2 8 1 8 2 8 4 6 2 7 1 8 2 8",
                        "1 4 1 5 9 2 6 5 3 5 8 9 7 9 3 2 3 8 4 6 2 6 4 3 3 8 3 2 7 3",
                        "4 1 5 9 2 6 5 3 5 8 9 7 9 3 2 3 8 4 6 2 6 4 3 3 8 3 2 7 3 1"
                },
                {
                        "5 4",
                        "11 12 13 14 15 16 17 18 19 20 11 12 13 14 15 16 17 18 19 20",
                        "21 22 23 24 24 14 16 18 20 18 28 38 10 12 14 12 12 12 14 14",
                        "12 11 23 13 15 25 17 27 19 29 11 11 13 13 15 15 17 17 19 19",
                        "21 31 15 27 11 23 27 19 23 29 31 19 18 17 16 15 14 13 12 11"
                },
                {
                        "4 5",
                        "-2 -1 -4 -1 -5 -9 -2 -6 -5 -3 -5 -4 -9 -7 -9 -3 -2 -3 -8 -4",
                        "-6 -2 -6 -4 -3 -3 -8 -3 -2 -7 -1 -2 -4 -8 -4 -2 -1 -1 -3 -9",
                        "-2 -4 -6 -8 -6 -5 -2 -3 -3 -5 -7 -9 -7 -5 -3 -5 -2 -3 -5 -7",
                        "-4 -5 -2 -6 -9 -1 -3 -6 -8 -9 -1 -2 -5 -6 -2 -9 -6 -5 -3 -2",
                        "-3 -1 -4 -1 -5 -9 -2 -6 -5 -3 -5 -8 -9 -7 -9 -3 -2 -3 -8 -4",
                        "-6 -2 -6 -4 -3 -3 -8 -3 -2 -7 -3 -1 -8 -1 -5 -9 -2 -6 -5 -3",
                        "-5 -8 -9 -7 -9 -3 -2 -3 -8 -4 -6 -2 -6 -4 -3 -3 -8 -3 -2 -7"
                }
        };

        int[] output = {
                6, 12, 6, 17, 9, 14, 60, -48
        };

        for (int i = 0; i < 8; i++)
        {
            List<String> arrays = new ArrayList<>(Arrays.asList(input[i]).subList(1, input[i].length));

            int result = sumOfMinAlongPath(input[i][0], arrays);

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