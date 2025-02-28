/**
 *      ACSL 2020-2021 - Contest 4 - Graphs - Junior Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

/*
 * Complete the 'findCharacteristic' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts following parameters:
 *  1. INTEGER choice
 *  2. STRING edges
 */
int findCharacteristic(int choice, string edges)
{
    bool graph[10][10];
    memset(graph, false, sizeof graph);

    stringstream ss(edges);
    string token;
    while (getline(ss, token, ' '))
    {
        int number = stoi(token);
        int from = number / 10;
        int to = number % 10;
        graph[from][to] = true;
    }

    int result = 0;
    if (choice == 1)
    {
        int count1 = 0;
        for (int i = 1; i < 10; i++)
        {
            if (graph[i][i]) 
            {
                count1++;
            }
        }

        int count2 = 0;
        for (int i = 1; i < 10; i++)
            for (int j = 1; j < 10; j++)
            {
                if (i != j && graph[i][j] && graph[j][i]) 
                {
                    count2++;
                }
            }
        count2 /= 2;

        result = count1 + count2;
    }
    else if (choice == 2)
    {
        int count = 0;
        int maxIndex = 0;
        for (int i = 1; i < 10; i++)
        {
            int current = 0;
            for (int j = 1; j < 10; j++)
            {
                if (graph[i][j]) 
                {
                    current++;
                }
            }

            if (current > count) 
            {
                count = current;
                maxIndex = i;
            }
        }

        for (int i = 1; i < 10; i++)
        {
            if (graph[maxIndex][i]) 
            {
                result += maxIndex*10 + i;
            }
        }
    }
    else if (choice == 3)
    {
        for (int i = 1; i < 10; i++)
            for (int j = 1; j < 10; j++)
                for (int k = 1; k < 10; k++)
                {
                    if (graph[i][j] && graph[j][k])
                    {
                        result++;
                    }
                }
    }

    return result;
}

int main()
{
    string input[10][2] = 
    {
        {"2", "12 13 23 31 34 41"},
        {"1", "12 23 34 11 21 32 45 53 95 43 99 29 91"},
        {"3", "12 23 34 41 31 52 45 61 14 21 33 55 13 54 32 56 36"},
        {"1", "12 11 33 34 43 55 52 41 31 25 88 79 98 45 13 42 87 35 51 21 14 78"},
        {"2", "12 11 33 34 43 55 52 41 31 25 88 79 98 45 13 42 87 35 51 21 14 78"},
        {"1", "12 31 41 42 43 45 51 63 64 56 16"},
        {"2", "12 13 22 23 24 34 42 98 71 87 17 96 67"},
        {"3", "12 14 21 24 25 32 41 43 59 65 91 87 76 95"},
        {"2", "11 12 14 15 23 25 31 43 45 51 52 68 79 87 89"},
        {"3", "55 77 45 54"}
    };

    int output[10] = 
    {
        25, 5, 49, 10, 50, 0, 42, 24, 52, 6
    };

    for (int i = 0; i < 10; i++)
    {
        int result = findCharacteristic(stoi(input[i][0]), input[i][1]);

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