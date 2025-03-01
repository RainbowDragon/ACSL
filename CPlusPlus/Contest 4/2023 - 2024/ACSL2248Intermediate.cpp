/**
 *      ACSL 2023-2024 - Contest 4 - ACSL 2248 - Intermediate Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

int rowCount = 8;
int colCount = 5;
int dr[5] = {0, 0, 1, 1, 1};
int dc[5] = {-1, 1, -1, 0, 1};

int getLastValue(int sum) 
{
    int lastValue = 2;
    while (lastValue < sum)
    {
        lastValue *= 2;
    }

    return lastValue;
}

void replenishBoard(vector<vector<int>> & board, int minValue)
{
    for (int i = 0; i < rowCount; i++)
        for (int j = 0; j < colCount; j++)
        {
            if (board[i][j] < minValue) 
            {
                board[i][j] = 0;
            }
        }
}

void shiftBoard(vector<vector<int>> & board) 
{
    for (int j = 0; j < colCount; j++)
    {
        vector<int> temp(rowCount, 0);
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
 * The function accepts STRING boardValues as parameter.
 */
string play2248(string boardValues)
{
    vector<vector<int>> board(rowCount, vector<int>(colCount, 0));
    istringstream iss(boardValues);
    int maxValue = 0;
    for (int i = 0; i < rowCount; i++)
        for (int j = 0; j < colCount; j++)
        {
            iss >> board[i][j];
            maxValue = max(maxValue, board[i][j]);
        }
    
    int sum = 0;
    int row = 0;
    int col = 0;
    bool found = false;
    int lastValue = 0;
    for (int i = 0; i < rowCount; i++)
    {
        for (int j = 0; j < colCount; j++)
        {
            for (int k = 1; k < 5; k++)
            {
                row = i + dr[k];
                col = j + dc[k];
                if (0 <= row && row < rowCount && 0 <= col && col < colCount && board[i][j] == board[row][col]) 
                {
                    found = true;
                    lastValue = board[i][j];
                    sum += lastValue * 2;
                    board[i][j] = board[row][col] = 0;
                    break;
                }
            }
            if (found)
            {
                break;
            }
        }
        if (found)
        {
            break;
        }
    }

    bool isDone = false;
    while (!isDone) 
    {
        isDone = true;
        for (int k = 0; k < 5; k++)
        {
            int nextRow = row + dr[k];
            int nextCol = col + dc[k];
            if (0 <= nextRow && nextRow < rowCount && 0 <= nextCol && nextCol < colCount) 
            {
                if (board[nextRow][nextCol] == lastValue || board[nextRow][nextCol] == (lastValue * 2)) 
                {
                    isDone = false;
                    lastValue = board[nextRow][nextCol];
                    sum += lastValue;
                    row = nextRow;
                    col = nextCol;
                    board[nextRow][nextCol] = 0;
                    break;
                }
            }
        }
    }    
    
    board[row][col] = getLastValue(sum);

    maxValue = max(maxValue, board[row][col]);
    int minValue = maxValue / 128;
    replenishBoard(board, minValue);

    shiftBoard(board);

    fillBoard(board, maxValue, minValue);
    
    return getBoardAsString(board);
}

int main()
{
    string input[10] = 
    {
        "8 128 4 128 32 16 16 4 256 16 32 4 16 64 4 8 64 64 256 8 16 2 2 256 4 32 128 2 64 8 256 32 128 16 2 8 64 64 128 32",
        "256 128 64 128 32 32 16 8 256 16 4 2 16 64 4 4 128 32 256 8 16 16 64 256 4 32 64 2 64 8 256 2 128 16 2 8 128 256 4 32",
        "256 16 256 2 32 2 32 2 16 8 32 2 256 64 16 4 2 128 2 32 8 8 32 256 2 2 4 8 32 128 2 16 32 64 256 4 2 128 4 8",
        "8 8 16 64 64 256 2 128 16 4 4 64 4 64 16 256 16 64 64 32 32 64 64 256 128 8 128 64 2 16 4 16 256 4 8 64 256 32 16 64",
        "4 16 8 2 32 2 2 8 32 4 2 16 16 4 128 128 32 4 2 128 128 64 8 128 128 4 2 16 32 16 8 8 128 64 32 32 8 128 2 128",
        "2 2 32 16 2 2 8 256 2 128 4 16 64 16 32 8 8 4 16 4 8 8 32 64 2 64 8 64 8 256 128 256 16 8 32 16 32 64 128 2",
        "2 4 256 128 4 256 32 8 64 64 64 128 16 256 64 64 4 4 64 64 64 4 8 64 2 32 4 2 128 256 16 64 64 2 8 256 32 32 32 2",
        "256 8 4 16 128 64 4 32 256 256 8 32 8 4 2 64 8 8 2 8 64 16 64 16 128 4 4 4 32 64 64 2 8 8 32 128 128 128 64 4",
        "256 16 256 2 32 2 32 2 16 8 32 2 256 64 16 4 2 128 2 32 8 8 32 256 2 2 4 8 32 128 16 16 32 64 256 4 16 128 4 8",
        "2 64 32 32 8 32 32 128 256 32 64 128 64 32 32 64 128 16 8 64 8 4 256 64 64 64 256 64 64 4 8 128 64 32 256 256 256 128 64 8"
    };

    string output[10] = 
    {
        "512 256 128 128 64 32 16 8 256 32 4 512 256 64 16 8 128 64 256 4 16 128 32 256 8 32 16 16 64 4 256 64 64 16 8 8 128 128 512 32",
        "1024 512 256 128 64 32 16 8 128 1024 256 512 256 256 128 32 64 32 64 32 16 16 8 256 16 32 128 64 256 8 256 128 8 64 8 8 16 1024 16 32",
        "512 256 128 64 32 16 8 4 512 32 256 128 64 32 8 16 8 256 16 16 4 512 256 64 32 256 16 128 256 128 32 32 32 32 256 4 4 512 4 8",
        "1024 512 256 128 64 32 16 8 1024 512 256 128 64 32 16 256 8 1024 512 64 256 64 256 64 128 32 16 128 64 16 8 16 64 256 8 64 1024 32 16 64",
        "512 256 128 64 32 32 16 8 4 4 4 512 256 128 128 128 64 4 32 128 512 32 8 4 128 4 16 16 128 16 8 8 128 32 32 32 8 128 64 128",
        "512 256 128 64 32 16 8 4 16 512 256 128 32 16 64 32 16 256 16 128 8 4 64 64 32 64 8 4 8 4 128 16 32 8 256 16 256 64 512 32",
        "1024 512 256 128 64 256 32 16 8 1024 64 512 256 256 128 64 64 8 32 16 64 32 16 8 1024 32 128 8 128 512 16 64 64 256 1024 256 32 32 32 8",
        "256 128 64 16 128 256 32 16 256 256 64 8 32 4 2 64 32 8 2 8 64 64 64 16 128 4 4 4 32 64 64 2 8 8 32 128 128 128 64 4",
        "256 128 64 32 32 16 8 4 16 8 2 256 256 64 16 256 16 256 2 32 2 32 128 256 2 32 2 32 32 128 2 4 32 64 256 4 128 128 4 8",
        "1024 512 256 128 64 32 32 16 8 1024 64 512 256 256 8 64 64 128 8 32 8 32 16 64 64 64 1024 64 64 64 8 128 64 32 256 256 256 128 64 8"
    };

    for (int i = 0; i < 10; i++)
    {
        string result = play2248(input[i]);

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