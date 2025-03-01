/**
 *      ACSL 2022-2023 - Contest 4 - Targets - Intermediate Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

int dr[8] = {0, -1, 0, 1, -1, -1, 1, 1};
int dc[8] = {-1, 0, 1, 0, -1, 1, 1, -1};

void updateBoard(vector<vector<bool>> & grid, vector<vector<int>> & board, int size, int row, int col)
{
    for (int i = 0; i < 8; i++)
    {
        int sr = row;
        int sc = col;

        while (sr >= 0 && sr < size && sc >= 0 && sc < size)
        {
            if (grid[sr][sc])
            {
                board[sr][sc]++;
                break;
            }
            sr += dr[i];
            sc += dc[i];
        }
    }
}

/*
 * Complete the 'targetsWithMostArrows' function below.
 *
 * The function is expected to return a STRING.
 * The function accepts following parameters:
 *  1. INTEGER size
 *  2. STRING targets
 */
string targetsWithMostArrows(int size, string targets)
{
    vector<vector<bool>> grid(size, vector<bool>(size, false));

    stringstream ss(targets);
    string token;
    while (getline(ss, token, ' '))
    {
        int location = stoi(token);
        int row = location / 10;
        int col = location % 10;
        grid[row][col] = true;
    }

    vector<vector<int>> board(size, vector<int>(size, 0));

    for (int i = 0; i < size; i++)
    {
        updateBoard(grid, board, size, 0, i);
    }

    for (int i = 1; i < size-1; i++)
    {
        updateBoard(grid, board, size, i, 0);
        updateBoard(grid, board, size, i, size-1);
    }

    for (int i = 0; i < size; i++)
    {
        updateBoard(grid, board, size, size-1, i);
    }

    int mostCount = -1;
    string result = "";

    for (int i = 1; i < size-1; i++)
        for (int j = 1; j < size-1; j++)
        {
            if (board[i][j] > mostCount)
            {
                mostCount = board[i][j];
                result = to_string(i) + to_string(j);
            }
            else if (board[i][j] == mostCount)
            {
                result += " " + to_string(i) + to_string(j);
            }
        }

    return result;
}

int main()
{
    string input[10][2] = 
    {
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

    string output[10] = 
    {
        "13", "11 13", "16 32", "13 61", "12 21 22",
        "45", "31", "11 14 22 33 41 44", "63", "11 13 31 33"
    };

    for (int i = 0; i < 10; i++)
    {
        string result = targetsWithMostArrows(stoi(input[i][0]), input[i][1]);

        if (output[i].compare(result) == 0) 
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