/**
 *      ACSL 2020-2021 - Contest 3 - MultipleArrays - Junior Division
 * 
 */

#include <bits/stdc++.h>

using namespace std;

/*
 * Complete the 'sumOfLargest' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts following parameters:
 *  1. STRING list1
 *  2. STRING list2
 *  3. STRING list3
 */
int sumOfLargest(string list1, string list2, string list3)
{
    string lists[3] = {list1, list2, list3};

    vector<int> als[3];
    int maxIndex = 0;
    for (int i = 0; i < 3; i++)
    {
        stringstream ss(lists[i]);
        string token;
        while (getline(ss, token, ' '))
        {
            als[i].push_back(stoi(token));
        }

        if (als[i].size() > als[maxIndex].size())
        {
            maxIndex = i;
        }
    }

    int maxLength = als[maxIndex].size();
    int result = 0;
    for (int i = 0; i < maxLength; i++)
    {
        int curMax = als[maxIndex][i];
        for (int j = 0; j < 3; j++)
        {
            if (i < als[j].size())
            {
                curMax = max(curMax, als[j][i]);
            }
        }

        result += curMax;
    }

    return result;
}

int main()
{
    string input[10][3] =
    {
        {
            "6 8 1 5 2 3 5 3 7 9",
            "7 6 2 9",
            "9 3 4 6 1 8 6 4 2 8 4"
        },
        {
            "1 3 5 7 9 2 4 6 8 10",
            "5 2 6 4 8 7 9 11 14 12",
            "4 2 6 4 7 1 9 22 21 9"
        },
        {
            "5 6 7 8 9 1 2",
            "9 8 7 6 5 0 1 2 3 4",
            "8 6 4 2 1 3 5 7"
        },
        {   
            "1",
            "1 2",
            "1 2 3"
        },
        {   
            "31 41 59 26 53 58 97 93 23 84 62 64 33 83 27",
            "56 89 23 14 26 37 48 59 61 72 83 94",
            "42 35 68 79 82 91 20 51 64 97 86"
        },
        {   
            "3 1 4 1 5 9 2 6 5 3 5 8 9 7",
            "9 3 2 3 8 4 6 2 7 9 8 5 3 5 6 2 9 5 1 4 1 3",
            "6 2 8 3 1 8 5 3 0 6"
        },
        {
            "31 41 59 26 53 58 97 93 23 84 62 64 33 83 27",
            "21 32 43 54 65 76 87 98 90 70 50 30 10",
            "20 40 60 80 12 23 34 45 56 67 78 89"
        },
        {
            "8765 4321 9012 3456 7890 321 654 987",
            "9123 5326 8975 345 789",
            "7654 6235 5798 6543 4567 32 54 1024 2048 4096"
        },
        {
            "-5 -6 -7 -8 -9 -10 -11",
            "-1 -2 -3 -4 -12 -8 -10 -16 -14 -12 -10 -5",
            "-6 -9 -1 -2 -10 -7 -9 -21 -15 -10"
        },
        {   
            "1",
            "1",
            "1"
        }
    };

    int output[10] = 
    {
        70, 101, 63, 6, 1139, 131, 1032, 46946, -86, 1
    };

    for (int i = 0; i < 10; i++)
    {
        int result = sumOfLargest(input[i][0], input[i][1], input[i][2]);

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