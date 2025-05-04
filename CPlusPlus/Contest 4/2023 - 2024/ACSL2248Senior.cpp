/**
 *      ACSL 2023-2024 - Contest 4 - ACSL 2248 - Senior Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

int rowCount = 8;
int colCount = 5;
int maxLen = 0;
int dr[8] = {0, -1, 0, 1, -1, -1, 1, 1};
int dc[8] = {-1, 0, 1, 0, -1, 1, 1, -1};

int getLastValue(int sum) 
{
    int lastValue = 2;
    while (lastValue < sum)
    {
        lastValue *= 2;
    }

    return lastValue;
}

int getMaxValue(vector<vector<int>> & board)
{
    int maxValue = 0;
    for (int i = 0; i < rowCount; i++)
        for (int j = 0; j < colCount; j++)
        {
            maxValue = max(maxValue, board[i][j]);
        }    

    return maxValue;
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

void findAllPath(vector<vector<int>> & board, vector<string> & allPaths, int row, int col, string path, bool isFirst, int lastValue)
{
    bool found = false;
    for (int k = 0; k < 8; k++)
    {
        int nextRow = row + dr[k];
        int nextCol = col + dc[k];
        if (0 <= nextRow && nextRow < rowCount && 0 <= nextCol && nextCol < colCount) 
        {
            if (isFirst) 
            {
                if (board[nextRow][nextCol] == board[row][col]) 
                {
                    int value = board[row][col];
                    board[row][col] = board[nextRow][nextCol] = 0;
                    findAllPath(board, allPaths, nextRow, nextCol, path+to_string(row)+to_string(col)+" "+to_string(nextRow)+to_string(nextCol), false, value);
                    board[row][col] = board[nextRow][nextCol] = value;
                }
            }
            else 
            {
                if (board[nextRow][nextCol] == lastValue || board[nextRow][nextCol] == (lastValue * 2)) 
                {
                    found = true;
                    int value = board[nextRow][nextCol];
                    board[nextRow][nextCol] = 0;
                    findAllPath(board, allPaths, nextRow, nextCol, path+" "+to_string(nextRow)+to_string(nextCol), false, value);
                    board[nextRow][nextCol] = value;
                }
            }
        }
    }

    if (!isFirst && !found) 
    {
        if (path.length() > maxLen) 
        {
            allPaths.clear();
            allPaths.push_back(path);
            maxLen = path.length();
        }
        else if (path.length() == maxLen) 
        {
            allPaths.push_back(path);
        }
    }
}

void playOneRound(vector<vector<int>> & board)
{
    vector<string> allPaths;
    maxLen = 0;

    for (int i = 0; i < rowCount; i++)
        for (int j = 0; j < colCount; j++)
        {
            findAllPath(board, allPaths, i, j, "", true, -1);
        }    

    if (allPaths.size() > 1)
    {
        sort(allPaths.begin(), allPaths.end());
    }
    string pathValues = allPaths[0];

    stringstream ss(pathValues);
    string token;
    int sum = 0;
    int row = 0;
    int col = 0;
    while (getline(ss, token, ' '))
    {
        int location = stoi(token);
        row = location / 10;
        col = location % 10;
        sum += board[row][col];
        board[row][col] = 0;
    }

    board[row][col] = getLastValue(sum);

    int maxValue = getMaxValue(board);
    int minValue = maxValue / 128;
    replenishBoard(board, minValue);

    shiftBoard(board);

    fillBoard(board, maxValue, minValue);    
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
    for (int i = 0; i < rowCount; i++)
        for (int j = 0; j < colCount; j++)
        {
            iss >> board[i][j];
        }

    for (int k = 0; k < 3; k++)
    {
        playOneRound(board);
    }    
    
    return getBoardAsString(board);
}

int main()
{
    string input[10] = 
    {
        "4 128 4 128 32 16 16 4 256 16 32 4 16 64 4 8 64 64 256 8 16 2 2 256 4 32 128 2 64 8 256 32 128 16 2 8 32 32 4 32",
        "256 128 64 128 32 32 16 8 256 16 4 2 16 64 4 4 128 64 256 8 16 16 2 256 4 32 64 2 64 8 256 2 128 16 2 8 128 256 4 32",
        "256 16 256 2 32 2 16 2 16 8 32 2 256 64 16 4 2 128 2 32 8 8 32 256 2 2 4 8 32 128 16 16 32 64 256 4 16 128 4 8",
        "256 8 4 16 128 64 4 32 256 256 8 32 8 4 2 64 128 8 2 8 64 16 64 16 128 4 4 4 4 64 64 2 8 8 32 128 128 128 64 4",
        "4 16 8 2 32 2 2 8 32 4 2 16 16 4 128 128 8 4 2 128 128 64 8 128 128 4 2 16 32 16 8 8 128 16 32 32 8 128 2 128",
        "2 2 32 16 2 2 8 256 2 128 32 16 64 16 32 8 8 4 16 4 8 8 32 64 2 64 8 64 8 256 128 256 16 8 32 16 2 2 128 2",
        "2 4 256 128 4 256 32 8 64 64 64 128 16 256 64 64 4 4 64 64 64 4 8 64 2 32 4 2 128 256 16 64 64 2 8 256 32 32 32 2",
        "32 4 128 128 4 2 128 16 2 128 8 256 256 16 256 256 128 8 256 2 64 64 256 64 128 64 16 8 8 64 256 256 16 2 8 2 64 256 32 256",
        "2 2 32 32 8 2 32 128 256 32 64 128 64 32 32 64 128 16 8 64 8 4 256 64 64 64 256 64 64 4 8 128 64 32 256 256 256 128 64 8",
        "256 128 64 32 16 64 4 64 64 64 16 64 32 4 64 256 128 8 16 16 16 64 32 8 64 8 2 2 128 128 128 4 64 256 8 64 256 8 128 256"
    };

    string output[10] = 
    {
        "4096 2048 1024 512 256 4096 128 64 32 4096 32 2048 1024 512 256 512 128 64 32 4096 32 2048 1024 256 512 256 256 256 256 64 32 128 64 256 1024 1024 128 256 64 32",
        "4096 4096 2048 1024 256 4096 512 256 128 4096 32 64 32 4096 256 512 2048 1024 2048 4096 32 128 64 512 512 4096 2048 1024 32 64 32 128 64 512 1024 1024 2048 1024 32 32",
        "8192 4096 2048 1024 512 256 128 64 8192 4096 2048 1024 512 256 128 1024 2048 1024 512 64 256 128 64 128 256 2048 8192 256 1024 64 64 512 64 1024 512 256 128 128 1024 256",
        "2048 1024 512 256 128 64 32 16 2048 1024 512 512 256 128 64 32 256 32 16 2048 256 16 1024 512 256 64 2048 128 64 128 64 512 128 16 64 128 128 128 64 32",
        "1024 512 256 128 64 64 32 16 128 512 1024 8 1024 1024 32 32 512 256 64 1024 256 16 8 8 32 128 256 128 64 32 128 128 128 1024 256 32 64 16 32 128",
        "1024 1024 512 256 64 32 512 128 64 1024 512 16 256 32 128 1024 256 8 16 32 128 16 256 128 512 64 8 32 8 32 128 8 64 8 256 16 256 16 128 32",
        "4096 2048 1024 512 256 256 128 64 32 2048 4096 2048 1024 512 256 4096 2048 1024 512 64 128 64 32 4096 1024 1024 512 1024 512 128 256 32 128 64 4096 256 2048 32 128 1024",
        "32768 16384 8192 4096 2048 1024 512 256 32768 16384 8192 4096 2048 1024 512 256 32768 16384 8192 512 32768 4096 2048 1024 8192 512 256 4096 2048 1024 2048 256 256 2048 1024 256 256 256 8192 256",
        "16384 8192 4096 2048 1024 512 256 128 16384 8192 4096 2048 1024 512 256 128 16384 8192 4096 1024 2048 1024 512 256 256 1024 256 128 2048 2048 16384 2048 1024 512 1024 512 2048 2048 256 256",
        "8192 4096 2048 1024 512 256 128 64 8192 4096 2048 1024 512 256 128 128 64 8192 256 2048 2048 4096 2048 128 256 64 1024 1024 128 64 8192 512 512 256 64 256 256 512 128 2048"
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