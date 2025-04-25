/**
 *      ACSL 2022-2023 - Contest 4 - Targets - Senior Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

int dr[8] = {0, -1, 0, 1, -1, -1, 1, 1};
int dc[8] = {-1, 0, 1, 0, -1, 1, 1, -1};
char dir[8] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};

void updateBoard(vector<vector<bool>>& grid, int size, int row, int col, int d)
{
    int sr = row;
    int sc = col;

    while (sr >= 0 && sr < size && sc >= 0 && sc < size)
    {
        if (grid[sr][sc])
        {
            grid[sr][sc] = false;
            break;
        }

        sr += dr[d];
        sc += dc[d];
    }
}

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
string missingArrow(int size, string targets, string numbers, string arrows)
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

    vector<int> rowCount(size, 0);
    vector<int> colCount(size, 0);
    for (int i = 0; i < size; i++)
    {
        rowCount[i] = numbers[i] - '0';
        colCount[i] = numbers[i+size+1] -'0';
    }

    vector<vector<int>> board(size, vector<int>(size, -1));
    
    stringstream aa(arrows);
    while (getline(aa, token, ' '))
    {
        int r = token[0] - '0';
        int c = token[1] - '0';
        int dir = token[2] - 'A';
        board[r][c] = dir;
    }

    vector<int> rowSum(size, 0);
    vector<int> colSum(size, 0);
    for (int i = 0; i < size; i++)
        for (int j = 0; j < size; j++)
        {
            if (board[i][j] >= 0)
            {
                rowSum[i]++;
                colSum[j]++;
            }
        }

    int row = -1;
    int col = -1;
    for (int i = 0; i < size; i++)
    {
        if (rowSum[i] < rowCount[i])
        {
            row = i;
        }
        if (colSum[i] < colCount[i])
        {
            col = i;
        }
    }

    for (int i = 0; i < size; i++)
        for (int j = 0; j < size; j++)
        {
            if (board[i][j] >= 0)
            {
                updateBoard(grid, size, i, j, board[i][j]);
            }
        }

    int d = -1;
    for (int i = 0; i < 8; i++)
    {
        int sr = row;
        int sc = col;

        while (sr >= 0 && sr < size && sc >= 0 && sc < size)
        {
            if (grid[sr][sc])
            {
                d = i;
                break;
            }

            sr += dr[i];
            sc += dc[i];
        }
    }

    return to_string(row) + to_string(col) + dir[d];
}

int main()
{
    string input[10][4] = 
    {
        {"4", "02 11 20 21", "0103 1012", "13E 30B 33E"},
        {"6", "15 23 24 32 33 34 42 43 51", "401211 401211", "00G 20G 40G 53B 02G 03D 04G 35A"},
        {"6", "00 10 13 20 21 24 30 31 43 50 53", "420113 022124", "01H 02H 03H 04H 15H 14H 45A 51B 55E 52E"},
        {"6", "02 05 13 23 12 35 24 00", "011114 410111", "14F 20F 50F 53F 51F 40F 30F"},
        {"6", "01 10 23 42 53 12 04 52 00", "200232 111024", "05H 34H 35H 54E 55E 40F 45A 41B"},
        {"6", "02 04 10 13 20 33 35 40 44 55", "202204 212122", "00C 05A 22F 25D 32E 50B 51F 53E 54C"},
        {"6", "01 10 20 21 22 23 24 31 45 51", "310123 100441", "03A 04D 05H 13A 34A 43E 44E 53A 54F"},
        {"6", "11 12 13 30 35 41 42 43 45 51 52", "323102 422102", "00G 01G 02G 10G 15D 20D 22D 23G 31D 50C"},
        {"6", "11 20 22 31 32 33 40 42 44 50 51 53", "411132 231114", "00D 01D 04H 05H 15H 21D 30D 43A 45A 52A 55A"},
        {"6", "05 11 20 21 22 23 33 42 43 52 53 54", "321312 401043", "00D 02H 04H 14H 15B 24H 34H 35H 40C 50C 55A"}
    };

    string output[10] = 
    {
        "32E", "30C", "35H", "55E", "02D", "34D", "50B", "55A", "41H", "30F"
    };

    for (int i = 0; i < 10; i++)
    {
        string result = missingArrow(stoi(input[i][0]), input[i][1], input[i][2], input[i][3]);

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