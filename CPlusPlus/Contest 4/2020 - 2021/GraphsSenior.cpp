/**
 *      ACSL 2020-2021 - Contest 4 - Graphs - Senior Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

int dfs(vector<vector<bool>> & graph, vector<bool> & visited, int node, int length, int maxLength, int path)
{
    if (length == maxLength)
    {
        return path;
    }

    int result = 0;
    for (int i = 1; i < 10; i++)
    {
        if (graph[node][i] && !visited[i])
        {
            visited[i] = true;
            result += dfs(graph, visited, i, length+1, maxLength, path*10+i);
            visited[i] = false;
        }
    }

    return result;
}

/*
 * Complete the 'sumPathsOfLengthN' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts following parameters:
 *  1. INTEGER num
 *  2. STRING edges
 */
int sumPathsOfLengthN(int num, string edges)
{
    vector<vector<bool>> graph(10, vector<bool>(10, false));

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
    vector<bool> visited(10, false);
    for (int i = 1; i < 10; i++)
    {
        visited[i] = true;
        result += dfs(graph, visited, i, 0, num, i);
        visited[i] = false;
    }

    return result;
}

int main()
{
    string input[10][2] = 
    {
        {"2", "12 23 34 41 31"},
        {"3", "12 23 34 41 13 32"},
        {"4", "67 75 54 12 13 23 31 34 41 56 45"},
        {"3", "34 45 56 63 64 61 13"},
        {"2", "12 21 13 15 53 33"},
        {"2", "12 31 41 42 43 45 51 63 64 56 16"},
        {"3", "12 13 22 23 24 34 42 98 71 87 17 96 67"},
        {"4", "12 14 21 24 25 32 41 43 59 65 91 87 76 95"},
        {"3", "11 12 14 15 23 25 31 43 45 51 52 68 79 87 89"},
        {"2", "55 77 45 54"}
    };

    int output[10] = 
    {
        1653, 15242, 356313, 37651, 581, 8478, 74349, 754366, 59578, 0
    };

    for (int i = 0; i < 10; i++)
    {
        int result = sumPathsOfLengthN(stoi(input[i][0]), input[i][1]);

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