/**
 *      ACSL 2022-2023 - Contest 1 - Next Base - Senior Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

/*
 * Complete the 'findModeCount' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts following parameters:
 * 1. INTEGER num
 * 2. INTEGER base
 * 3. STRING start
 */
int findModeCount(int num, int base, string start)
{
    int result = 0;
    int counts[base];
    memset(counts, 0, sizeof counts);
    int startValue = stoi(start, nullptr, base);

    for (int i = 0; i < num; i++)
    {
        int number = startValue + i;

        while (number > 0) 
        {
            counts[number % base]++;
            number /= base;
        }
    }

    for (int i = 0; i < base; i++)
    {
        result = max(result, counts[i]);
    }

    return result;
}

int main()
{
    string input[10][3] = 
    {
        {"15", "8", "2"}, {"25", "2", "1111011"}, {"20", "12", "9AB"}, {"10", "16", "ABCDEF"}, {"1000", "2", "1"},
        {"50", "4", "12"}, {"75", "9", "384"}, {"500", "14", "9CBA"}, {"700", "11", "AAA0"}, {"25", "2", "110000111010"}
    };

    int output[10] = 
    {
        9, 105, 14, 10, 4938, 42, 88, 336, 940, 165
    };

    for (int i = 0; i < 10; i++)
    {
        int result = findModeCount(stoi(input[i][0]), stoi(input[i][1]), input[i][2]);

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