/**
 *      ACSL 2023-2024 - Contest 1 - ACSL Tiles - Junior Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

/*
 * Complete the 'findDiscardSum' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts following parameters:
 * 1. INTEGER originalRows
 * 2. STRING tiles
 */
int findDiscardSum(int originalRows, string tiles)
{
    int rows[4];
    for (int i = 3; i >= 0; i--)
    {
        rows[i] = originalRows % 10;
        originalRows /= 10;
    }

    int result = 0;

    stringstream ss(tiles);
    string token;
    while (getline(ss, token, ' '))
    {
        int cur = stoi(token);
        int front = cur / 10;
        int back = cur % 10;

        bool isMatched = false;

        for (int i = 0; i < 4; i++)
        {
            if (rows[i] == front) 
            {
                isMatched = true;
                rows[i] = back;
                break;
            }
            else if (rows[i] == back) 
            {
                isMatched = true;
                rows[i] = front;
                break;
            }
        }

        if (!isMatched) 
        {
            result += (front + back);
        }
    }

    return result;
}

int main()
{
    string input[10][2] = 
    {
        {"5923", "56 85 27 73 14 34 62"},
        {"8423", "74 92 57 93 26 87 14 63 82 54 12"},
        {"1253", "51 81 35 84 95 26 59 13 71 35 46 28"},
        {"2694", "69 76 41 89 16 78 64 36 12 95 52"},
        {"6479", "58 73 92 54 75 35 78 25 81 24 16 95 36 82 14 27 43 13 51"},
        {"3972", "18 17 65 61 37 51 57 38 72 92 54 59 43 41 31 28"},
        {"9146", "95 74 51 19 75 26 32 39 35 31 25 73"},
        {"7918", "63 18 56 98 48 52 26 92 83 13 17 58 91 67 58"},
        {"9758", "52 14 51 27 77 62 76 82 96 56 46 49 87"},
        {"7169", "71 56 15 65 98 71 89 71 11 55 77 17 66 51"}
    };

    int output[10] = 
    {
        18, 26, 31, 22, 45, 56, 0, 59, 48, 14
    };

    for (int i = 0; i < 10; i++)
    {
        int result = findDiscardSum(stoi(input[i][0]), input[i][1]);

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