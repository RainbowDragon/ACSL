/**
 *      ACSL 2020-2021 - Contest 4 - Graphs - Intermediate Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

/*
 * Complete the 'sumPathsOfLength2' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts following parameters:
 *  1. STRING edges
 */
int sumPathsOfLength2(string edges)
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
    for (int i = 1; i < 10; i++)
        for (int j = 1; j < 10; j++)
            for (int k = 1; k < 10; k++)
            {
                if (i != j && j != k && k != i && graph[i][j] && graph[j][k])
                {
                    result += i*100 + j*10 + k;
                }
            }

    return result;
}

int main()
{
    string input[10] = 
    {
        "12 23 34 41 31",
        "12 23 34 41 13 32",
        "76 75 12 13 23 31 34 41 56",
        "34 45 56 63 64 61 13",
        "12 21 13 15 53 33",
        "12 31 41 42 43 45 51 63 64 56 16",
        "12 13 22 23 24 34 42 98 71 87 17 96 67",
        "12 14 21 24 25 32 41 43 59 65 91 87 76 95",
        "11 12 14 15 23 25 31 43 45 51 52 68 79 87 89",
        "55 77 45 54"
    };

    int output[10] = 
    {
        1653, 1789, 2956, 4515, 581, 8478, 6301, 7880, 7249, 0
    };

    for (int i = 0; i < 10; i++)
    {
        int result = sumPathsOfLength2(input[i]);

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