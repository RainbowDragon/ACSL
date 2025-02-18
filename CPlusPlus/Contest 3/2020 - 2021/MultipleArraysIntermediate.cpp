/**
 *      ACSL 2020-2021 - Contest 3 - MultipleArrays - Intermediate Division
 * 
 */

#include <bits/stdc++.h>

using namespace std;

/*
 * Complete the 'sumSmallestInVisitedCells' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts following parameters:
 *  1. STRING rows_cols
 *  2. STRING array1
 *  3. STRING array2
 *  4. STRING array3
 */
int sumSmallestInVisitedCells(string rows_cols, string array1, string array2, string array3)
{
    istringstream iss(rows_cols);
    int row, col;
    iss >> row >> col;
    
    int arrays[3][row][col];
    string lists[3] = {array1, array2, array3};
    for (int i = 0; i < 3; i++)
    {
        stringstream ss(lists[i]);
        string token;
        for (int x = 0; x < row; x++)
            for (int y = 0; y < col; y++)
            {
                getline(ss, token, ' ');
                arrays[i][x][y] = stoi(token);
            }
    }

    int i = 0;
    int j = 0;
    int result = min(arrays[0][i][j], min(arrays[1][i][j], arrays[2][i][j]));
    while (i + 1 < row && j + 1 < col)
    {
        int curMax = arrays[0][i][j+1] - 1;
        int maxCount = 1;
        int nextI = i;
        int nextJ = j;

        for (int k = 0; k < 3; k++)
        {
            if (arrays[k][i][j+1] > curMax) 
            {
                curMax = arrays[k][i][j+1];
                maxCount = 1;
                nextI = i;
                nextJ = j + 1;
            }
            else if (arrays[k][i][j+1] == curMax) 
            {
                maxCount++;
            }

            if (arrays[k][i+1][j] > curMax) 
            {
                curMax = arrays[k][i+1][j];
                maxCount = 1;
                nextI = i + 1;
                nextJ = j;
            }
            else if (arrays[k][i+1][j] == curMax) 
            {
                maxCount++;
            }            
        }

        if (maxCount == 1) 
        {
            i = nextI;
            j = nextJ;
        }
        else 
        {
            i++;
            j++;
        }

        result += min(arrays[0][i][j], min(arrays[1][i][j], arrays[2][i][j]));
    }

    return result;
}

int main()
{
    string input[8][4] =
    {
        {
            "3 4",
            "1 2 3 4 7 7 8 9 5 6 7 8",
            "6 8 6 4 4 5 3 2 8 3 1 9",
            "3 6 7 3 4 6 2 1 3 2 5 5"
        },
        {
            "4 2",
            "31 17 24 19 15 29 22 26",
            "25 13 25 18 19 27 19 13",
            "12 15 17 18 29 16 25 20"
        },
        {
            "4 5",
            "3 1 4 1 5 9 2 6 5 3 5 8 9 7 9 3 2 3 8 4",
            "6 2 6 4 3 3 8 3 2 7 7 2 3 8 3 3 4 6 2 6",
            "5 8 3 2 3 9 7 9 8 5 3 5 6 2 9 5 1 4 1 3"
        },
        {
            "4 3",
            "3 1 4 1 5 2 2 6 5 3 5 8",
            "9 7 1 3 2 6 8 4 6 2 6 4",
            "3 2 1 3 2 1 1 2 3 1 2 3"
        },
        {
            "4 6",
            "4 8 3 7 1 6 7 6 2 4 3 3 7 5 1 0 5 8 2 0 9 5 3 2",
            "4 4 5 9 2 3 0 2 1 9 6 4 0 6 2 8 4 2 0 8 9 3 5 2",
            "6 9 5 0 3 4 8 7 5 3 4 2 1 1 7 10 6 7 9 3 1 2 3 2"
        },
        {
            "5 3",
            "31 41 59 26 53 58 97 93 23 84 62 64 33 83 27",
            "95 28 84 19 71 69 39 93 75 10 85 20 97 49 44",
            "59 23 78 61 40 62 97 20 49 98 62 80 34 83 53"
        },
        {
            "4 5",
            "-3 -1 -4 -1 -5 -9 -2 -6 -5 -3 -5 -8 -9 -7 -4 -3 -5 -3 -8 -4",
            "-6 -2 -6 -4 -3 -3 -8 -3 -2 -7 -9 -5 -2 -8 -8 -4 -4 -9 -7 -1",
            "-6 -9 -3 -9 -9 -3 -7 -5 -1 -5 -8 -2 -9 -7 -4 -9 -4 -4 -5 -9"
        },
        {
            "5 5",
            "1 2 3 4 5 6 7 8 9 10 11 12 13 12 11 10 9 8 7 8 5 4 3 2 1",
            "2 4 6 8 10 12 14 16 18 20 22 20 18 16 14 12 10 8 26 4 2 4 6 8 10",
            "1 3 5 7 9 11 13 15 17 19 21 23 25 23 21 19 17 15 13 1 9 7 5 3 1"
        }
    };

    int output[8] = 
    {
        6, 60, 16, 9, 11, 159, -49, 63
    };

    for (int i = 0; i < 8; i++)
    {
        int result = sumSmallestInVisitedCells(input[i][0], input[i][1], input[i][2], input[i][3]);

        if (output[i] == result) 
        {
            cout << "Test Case " << i << ": Passed!" << endl;
        }
        else 
        {
            cout << "Test Case " << i << ": Failed!" << endl;
            cout << "Expected output: " << output[i] << endl;
            cout << "Your output: " << result << endl;
        }
    }

    return 0;
}