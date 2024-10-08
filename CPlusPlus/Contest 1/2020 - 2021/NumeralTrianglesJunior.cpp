/**
 *      ACSL 2020-2021 - Contest 1 - Numeral Triangles - Junior Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

int transformToSingleDigit(int number)
{
    while (number > 9)
    {
        int sum = 0;
        while (number > 0)
        {
            sum += number % 10;
            number /= 10;
        }
        number = sum;
    }

    return number; 
}

/*
 * Complete the 'sumOfLastRow' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts following parameters:
 *  1. INTEGER s
 *  2. INTEGER d
 *  3. INTEGER r
 */
int sumOfLastRow(int s, int d, int r)
{
    int sum = 0;
    int numbersToSkip = r * (r - 1) / 2;

    int firstNumberRthRow = transformToSingleDigit(s);
    for (int i = 0; i < numbersToSkip; i++)
    {
        firstNumberRthRow += d;
        firstNumberRthRow = transformToSingleDigit(firstNumberRthRow);
    }

    for (int i = 0; i < r; i++)
    {
        sum += transformToSingleDigit(firstNumberRthRow);
        firstNumberRthRow += d;
    }

    return sum;
}

int main()
{
    int input[10][3] = 
    {
        {2, 3, 5}, {221, 2, 4}, {184, 231, 35}, {71, 5, 27}, {1, 24, 100},
        {599, 23, 43}, {4326, 1234, 80}, {704, 1776, 200}, {6283, 185, 31}, {3141, 59, 26}
    };

    int output[10] = 
    {
        28, 17, 140, 135, 397, 218, 399, 1003, 154, 126
    };

    for (int i = 0; i < 10; i++)
    {
        int result = sumOfLastRow(input[i][0], (int)input[i][1], (int)input[i][2]);

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