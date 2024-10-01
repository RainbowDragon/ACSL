/**
 *      ACSL 2022-2023 - Contest 1 - Next Base - Intermediate Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

/*
 * Complete the 'countLargestDigit' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts following parameters:
 * 1. INTEGER num
 * 2. INTEGER base
 * 3. INTEGER start
 */
int countLargestDigit(int num, int base, long long start)
{
    int result = 0;
    int largestDigit = base - 1;
    long long startValue = stoll(to_string(start), nullptr, base);

    for (int i = 0; i < num; i++)
    {
        long long number = startValue + i;

        while (number > 0) 
        {
            if (number % base == largestDigit) 
            {
                result++;
            }
            number /= base;
        }
    }

    return result;
}

int main()
{
    string input[10][3] = 
    {
        {"15", "8", "2"}, {"20", "3", "12"}, {"25", "5", "324"}, {"13", "9", "1652"}, {"45", "2", "1111011"},
        {"1000", "8", "10"}, {"50", "4", "13"}, {"75", "9", "384"}, {"100", "6", "555"}, {"25", "2", "110000111010"}
    };

    int output[10] = 
    {
        2, 21, 24, 1, 170, 357, 34, 13, 31, 135
    };

    for (int i = 0; i < 10; i++)
    {
        int result = countLargestDigit(stoi(input[i][0]), stoi(input[i][1]), stoll(input[i][2]));

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