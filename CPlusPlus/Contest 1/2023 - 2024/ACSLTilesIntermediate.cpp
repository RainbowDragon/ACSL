/**
 *      ACSL 2023-2024 - Contest 1 - ACSL Tiles - Intermediate Division
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
    int startIndex = 0;
    bool isLastDouble = false;

    stringstream ss(tiles);
    string token;
    while (getline(ss, token, ' '))
    {
        int cur = stoi(token);
        int front = cur / 10;
        int back = cur % 10;

        bool isMatched = false;
        int index = startIndex;

        if (isLastDouble) 
        {
            if (rows[index] == front) 
            {
                isMatched = true;
                rows[index] = back;
            }
            else if (rows[index] == back) 
            {
                isMatched = true;
                rows[index] = front;
            }
        }
        else 
        {
            for (int i = 0; i < 4; i++) 
            {
                index = (startIndex + i) % 4;
                if (rows[index] == front) 
                {
                    isMatched = true;
                    rows[index] = back;
                    break;
                } 
                else if (rows[index] == back) 
                {
                    isMatched = true;
                    rows[index] = front;
                    break;
                }
            }
        }

        if (!isMatched) 
        {
            result += (front + back);
        }
        else 
        {
            isLastDouble = (front == back);
            if (isLastDouble) 
            {
                startIndex = index;
            }
            else 
            {
                startIndex = index + 1;
            }
        }
    }

    return result;
}

int main()
{
    string input[10][2] = 
    {
        {"5923", "56 27 73 34 99 45 32 19 64 57 18"},
        {"4687", "81 72 15 89 36 21 13 67 42 93 48 83 45 47 52 94 62"},
        {"1932", "94 81 13 43 21 31 89 69 18 28 86 88 29 89 92"},
        {"1957", "32 69 87 73 31 88 62"},
        {"1542", "24 44 39 32 92 63 47 76 37 78 38"},
        {"5179", "14 92 71 51 42 19 18 28 89 29 96 46 13 57 78 27"},
        {"4287", "69 36 21 93 94 35 83 62 88 97 18 72 42 73 75 31 28 52 66 87"},
        {"1745", "21 37 92 42 93 96 19 72 78 18 71 36 56 85 99 97 31 98 23"},
        {"6655", "23 55 55 45 94 99 12 99 89 32 77 65 58 57 66 27 16 76"},
        {"3333", "43 35 55 34 37 53 23 49 13 22 98 12 33 11 53 79 57 39 77 43 72 22 12 48 17 15 75"}
    };

    int output[10] = 
    {
        21, 86, 11, 23, 46, 16, 114, 61, 36, 67
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