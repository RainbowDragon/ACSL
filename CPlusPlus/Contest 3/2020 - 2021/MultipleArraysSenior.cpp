/**
 *      ACSL 2020-2021 - Contest 3 - MultipleArrays - Senior Division
 * 
 */

#include <bits/stdc++.h>

using namespace std;

/*
 * Complete the 'sumOfMinAlongPath' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts following parameters:
 *  1. STRING dim
 *  2. STRING_ARRAY arrays
 */
int sumOfMinAlongPath(string dim, vector<string> arrays)
{
    istringstream iss(dim);
    int row, col;
    iss >> row >> col;

    int n = arrays.size();
    int boards[n][row][col];
    for (int i = 0; i < n; i++)
    {
        stringstream ss(arrays[i]);
        string token;
        for (int x = 0; x < row; x++)
            for (int y = 0; y < col; y++)
            {
                getline(ss, token, ' ');
                boards[i][x][y] = stoi(token);
            }
    }

    int dx[8] = {-1, -1, -1, 0, 0, 1, 1, 1};
    int dy[8] = {-1, 0, 1, -1, 1, -1, 0, 1};
    bool visited[row][col];

    int i = 0; 
    int j = 0;
    int result = 0;
    while (!visited[i][j])
    {
        visited[i][j] = true;
        int smallest = boards[0][i][j];
        for (int l = 0; l < n; l++)
        {
            smallest = min(smallest, boards[l][i][j]);
        }
        result += smallest;

        unordered_map<int, int> countMap;
        for (int k = 0; k < 8; k++)
        {
            int nextI = i + dx[k];
            int nextJ = j + dy[k];

            if (nextI >= 0 && nextI < row && nextJ >= 0 && nextJ < col)
            {
                for (int l = 0; l < n; l++)
                {
                    if (countMap.find(boards[l][nextI][nextJ]) != countMap.end())
                    {
                        countMap[boards[l][nextI][nextJ]]++;
                    }
                    else
                    {
                        countMap[boards[l][nextI][nextJ]] = 1;
                    }
                }
            }
        }

        vector<int> numberList;
        for (auto pair : countMap)
        {
            if (pair.second == 1)
            {
                numberList.push_back(pair.first);
            }
        }
        sort(numberList.begin(), numberList.end());
        int maxNumber = numberList[numberList.size()-1];

        bool found = false;
        for (int k = 0; k < 8; k++)
        {
            int nextI = i + dx[k];
            int nextJ = j + dy[k];

            if (nextI >= 0 && nextI < row && nextJ >= 0 && nextJ < col)
            {
                for (int l = 0; l < n; l++)
                {
                    if (boards[l][nextI][nextJ] == maxNumber)
                    {
                        i = nextI;
                        j = nextJ;
                        found = true;
                        break;
                    }
                }
            }

            if (found)
            {
                break;
            }
        }
    }

    return result;
}

int main()
{
    vector<string> input[8] =
    {
        {
            "3 4",
            "6 2 7 4 6 1 5 7 5 6 7 8",
            "4 8 6 4 4 5 7 2 7 6 5 4",
            "3 6 9 2 4 8 2 6 3 2 1 4",
            "4 3 5 7 6 8 9 1 2 9 3 5"
        },
        {
            "4 4",
            "5 2 8 3 1 8 5 3 0 7 1 7 9 5 8 6",
            "5 4 0 9 5 4 6 2 8 1 8 2 8 1 7 2",
            "2 7 1 8 2 8 5 8 2 8 4 5 9 0 4 5"
        },
        {
            "5 3",
            "9 9 9 8 8 8 7 7 7 6 6 6 5 5 5",
            "5 6 7 8 5 5 6 7 8 9 5 6 7 8 9",
            "5 6 3 2 1 9 4 3 2 1 5 4 3 2 1",
            "5 5 5 6 6 6 7 7 7 8 8 8 9 9 9",
            "1 2 3 4 5 6 7 8 9 8 7 6 5 4 3"
        },
        {
            "3 5",
            "5 3 4 5 6 7 8 9 8 7 6 5 4 3 2",
            "1 3 5 7 9 7 5 9 1 2 4 3 8 6 4",
            "3 2 4 5 1 6 5 8 9 2 3 8 1 4 6"
        },
        {
            "5 3",
            "8 6 4 2 5 2 4 6 8 7 8 6 4 2 0",
            "7 9 7 5 3 8 3 6 7 9 1 9 7 5 3",
            "5 2 3 4 8 6 7 8 9 5 2 3 4 5 6",
            "9 4 9 2 3 9 1 8 7 6 5 4 3 2 8",
            "1 5 4 7 8 9 3 2 1 4 5 6 9 8 7"
        },
        {
            "5 6",
            "3 1 4 1 5 9 2 6 5 3 5 8 9 7 9 3 2 3 8 4 6 2 6 4 3 3 8 3 2 7",
            "7 2 3 8 3 3 4 6 2 6 4 8 3 2 3 9 7 9 8 5 3 5 6 2 9 5 1 4 1 3",
            "6 2 8 3 1 8 5 3 6 7 1 8 6 2 5 3 1 8 5 3 4 7 6 8 6 2 8 3 1 8",
            "2 7 1 8 2 8 1 8 2 8 4 6 2 7 1 8 2 8 1 8 2 8 4 6 2 7 1 8 2 8",
            "1 4 1 5 9 2 6 5 3 5 8 9 7 9 3 2 3 8 4 6 2 6 4 3 3 8 3 2 7 3",
            "4 1 5 9 2 6 5 3 5 8 9 7 9 3 2 3 8 4 6 2 6 4 3 3 8 3 2 7 3 1"
        },
        {
            "5 4",
            "11 12 13 14 15 16 17 18 19 20 11 12 13 14 15 16 17 18 19 20",
            "21 22 23 24 24 14 16 18 20 18 28 38 10 12 14 12 12 12 14 14",
            "12 11 23 13 15 25 17 27 19 29 11 11 13 13 15 15 17 17 19 19",
            "21 31 15 27 11 23 27 19 23 29 31 19 18 17 16 15 14 13 12 11"
        },
        {
            "4 5",
            "-2 -1 -4 -1 -5 -9 -2 -6 -5 -3 -5 -4 -9 -7 -9 -3 -2 -3 -8 -4",
            "-6 -2 -6 -4 -3 -3 -8 -3 -2 -7 -1 -2 -4 -8 -4 -2 -1 -1 -3 -9",
            "-2 -4 -6 -8 -6 -5 -2 -3 -3 -5 -7 -9 -7 -5 -3 -5 -2 -3 -5 -7",
            "-4 -5 -2 -6 -9 -1 -3 -6 -8 -9 -1 -2 -5 -6 -2 -9 -6 -5 -3 -2",
            "-3 -1 -4 -1 -5 -9 -2 -6 -5 -3 -5 -8 -9 -7 -9 -3 -2 -3 -8 -4",
            "-6 -2 -6 -4 -3 -3 -8 -3 -2 -7 -3 -1 -8 -1 -5 -9 -2 -6 -5 -3",
            "-5 -8 -9 -7 -9 -3 -2 -3 -8 -4 -6 -2 -6 -4 -3 -3 -8 -3 -2 -7"
        }
    };

    int output[8] = 
    {
        6, 12, 6, 17, 9, 14, 60, -48
    };

    for (int i = 0; i < 8; i++)
    {
        vector<string> arrays(input[i].begin()+1, input[i].end());
        int result = sumOfMinAlongPath(input[i][0], arrays);

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