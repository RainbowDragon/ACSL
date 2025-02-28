/**
 *      ACSL 2023-2024 - Contest 4 - ACSL 2248 - Junior Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

int rowCount = 8;
int colCount = 5;

int getLastValue(int sum) 
{
    int lastValue = 2;
    while (lastValue < sum)
    {
        lastValue *= 2;
    }

    return lastValue;
}

void shiftBoard(vector<vector<int>> & board) 
{
    for (int j = 0; j < colCount; j++)
    {
        vector<int> temp(rowCount);
        int index = rowCount - 1;
        for (int i = rowCount - 1; i >= 0; i--)
        {
            if (board[i][j] > 0) 
            {
                temp[index] = board[i][j];
                index--;
            }
        }

        for (int i = 0; i < rowCount; i++)
        {
            board[i][j] = temp[i];
        }
    }
}

void fillBoard(vector<vector<int>> & board, int maxValue, int minValue) 
{
    int value = maxValue;
    for (int i = 0; i < rowCount; i++)
        for (int j = 0; j < colCount; j++)
        {
            if (board[i][j] == 0) 
            {
                board[i][j] = value;
                if (value > minValue) 
                {
                    value /= 2;
                }
                else 
                {
                    value = maxValue;
                }
            }
        }
}

string getBoardAsString(vector<vector<int>> & board) 
{
    string result = "";
    for (int i = 0; i < rowCount; i++)
        for (int j = 0; j < colCount; j++)
        {
            if (i > 0 || j > 0) 
            {
                result += " ";
            }
            result += to_string(board[i][j]);
        }

    return result;
}

/*
 * Complete the 'play2248' function below.
 *
 * The function is expected to return a STRING.
 * The function accepts following parameters:
 *  1. STRING boardValues
 *  2. STRING pathValues
 */
string play2248(string boardValues, string pathValues)
{
    vector<vector<int>> board(rowCount, vector<int>(colCount, 0));
    istringstream iss(boardValues);
    for (int i = 0; i < rowCount; i++)
        for (int j = 0; j < colCount; j++)
        {
            iss >> board[i][j];
        }

    int sum = 0;
    int row = 0;
    int col = 0;
    stringstream ss(pathValues);
    string token;
    while (getline(ss, token, ' '))
    {
        int location = stoi(token);
        row = (location / 10) - 1;
        col = (location % 10) - 1;
        sum += board[row][col];
        board[row][col] = 0;
    }
    
    board[row][col] = getLastValue(sum);

    shiftBoard(board);

    fillBoard(board, 256, 2);
    
    return getBoardAsString(board);
}

int main()
{
    string input[10][2] = 
    {
        {"4 128 4 128 32 16 16 4 256 16 32 4 16 64 4 8 64 64 256 8 16 2 2 256 4 32 128 2 64 8 256 32 128 16 2 8 32 32 4 32", "13 23 32 41 51 61 72 82 83"},
        {"256 128 64 128 32 32 16 8 256 16 4 2 16 64 4 4 128 64 256 8 16 16 2 256 4 32 64 2 64 8 256 2 128 16 2 8 128 256 4 32", "51 52 61 62 73 82 83"},
        {"256 16 256 2 32 2 16 2 16 8 32 2 256 64 16 4 2 128 2 32 8 8 32 256 2 2 4 8 32 128 16 16 32 64 256 4 16 128 4 8", "14 23 32 42 41 51 52 63 72 73 74 83"},
        {"256 16 256 2 32 2 16 2 16 8 32 2 256 64 16 4 2 128 2 32 8 8 32 256 2 8 4 8 32 128 16 16 32 64 256 4 16 128 4 8", "14 23 32 41 51 52 61 71 72 82"},
        {"256 8 4 16 128 64 4 32 256 256 32 32 8 4 2 64 128 8 2 8 64 32 64 16 128 4 4 4 16 64 64 2 8 8 32 128 128 128 64 4", "13 22 33 43 54 64 75 84 83 82 81 13 22 33 43 54 64 75 84 83 82 81"},
        {"2 2 32 32 8 2 4 128 256 2 64 128 64 32 8 64 128 16 8 16 8 4 256 64 64 64 256 64 64 4 8 128 64 32 256 256 256 128 64 8", "54 55 64 63 73 72 83 82 81"},
        {"128 8 16 2 4 32 32 8 256 128 16 8 128 4 4 32 2 4 16 128 16 64 2 128 64 128 2 8 4 64 128 2 128 64 32 16 128 256 16 2", "12 23 32 31 41 52 61 71 82 83"},
        {"2 128 4 8 64 8 128 32 2 64 128 256 256 2 4 8 32 8 4 16 8 32 16 32 4 128 256 16 256 256 256 32 8 16 16 2 8 64 2 32", "24 34 35 44 43 53 63 74 75 85"},
        {"2 2 32 32 8 2 4 128 256 32 64 128 8 32 32 64 128 16 8 64 8 4 256 64 64 64 256 64 64 4 8 128 64 32 256 256 256 128 64 8", "13 14 25 34 45 54 55 64 63 73 72 83 82 81"},
        {"16 4 8 2 16 256 128 256 32 16 32 256 128 32 64 256 256 8 128 256 4 2 64 128 4 32 32 256 256 64 128 32 32 256 4 16 256 8 256 8", "15 25 24 34 35 44 54 63 64 74 84"}

    };

    string output[10] = 
    {
        "256 128 64 128 32 32 16 8 256 16 4 2 16 64 4 4 128 64 256 8 16 16 2 256 4 32 64 2 64 8 256 2 128 16 2 8 128 256 4 32",
        "256 128 64 128 32 32 16 64 256 16 256 8 8 64 4 32 128 16 256 8 4 16 64 256 4 4 2 2 64 8 256 128 2 16 2 8 2 1024 4 32",
        "256 128 64 32 32 16 8 4 2 8 256 256 128 16 16 2 64 256 64 32 32 16 256 2 2 2 16 128 256 128 16 4 32 32 256 4 16 512 4 8",
        "256 128 64 32 32 16 8 256 16 8 4 2 256 64 16 256 16 128 2 32 256 16 32 256 2 2 2 8 32 128 32 4 32 64 256 4 128 128 4 8",
        "256 256 128 64 32 64 16 8 4 128 32 8 2 256 256 64 32 128 16 2 64 128 32 256 8 4 32 64 4 128 64 4 4 2 64 1024 2 8 8 4",
        "2 256 128 64 32 2 16 8 4 8 64 2 2 32 2 64 4 32 256 8 8 128 128 32 16 64 128 64 8 4 8 4 16 32 256 2048 256 256 64 8",
        "256 128 64 2 4 32 16 16 256 128 8 4 128 4 4 2 256 4 16 128 128 32 2 128 64 32 2 8 4 64 16 2 128 64 32 16 2 1024 16 2",
        "2 128 256 128 64 8 128 32 16 8 128 256 4 2 64 8 32 4 256 64 8 32 32 8 16 128 256 256 32 4 256 32 8 256 256 2 8 64 2 128",
        "2 256 128 64 32 2 16 8 4 2 64 2 256 128 64 64 4 32 16 8 8 128 128 256 32 64 128 8 8 4 8 4 16 32 256 2048 256 256 64 8",
        "16 4 256 128 64 256 128 8 32 16 32 256 256 8 4 256 256 128 2 256 4 2 8 256 4 32 32 64 128 64 128 32 32 2 4 16 256 8 2048 8"
    };

    for (int i = 0; i < 10; i++)
    {
        string result = play2248(input[i][0], input[i][1]);

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