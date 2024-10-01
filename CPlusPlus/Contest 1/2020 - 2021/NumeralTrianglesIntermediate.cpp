/**
 *      ACSL 2020-2021 - Contest 1 - Numeral Triangles - Intermediate Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

int oct2decimal(int octNumber)
{
    int number = 0;
    int base = 1;

    while (octNumber > 0)
    {
        int digit = octNumber % 10;
        number += digit * base;
        base *= 8;
        octNumber /= 10;
    }

    return number; 
}

int sumOfOctDigit(int number)
{
    int sum = 0;

    while (number > 0) 
    {
        sum += number % 8;
        number /= 8;
    }

    return sum;
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

    int startingNumber = oct2decimal(s);
    int delta = oct2decimal(d);
    int firstNumberRthRow = startingNumber + delta * numbersToSkip;

    for (int i = 0; i < r; i++)
    {
        sum += sumOfOctDigit(firstNumberRthRow);
        firstNumberRthRow += delta;
    }

    return sum;
}

int main()
{
    int input[10][3] = 
    {
        {2, 3, 5}, {221, 2, 4}, {1, 4, 20}, {10, 10, 10}, {3245, 5, 11},
        {4567, 7, 65}, {3141, 5, 26}, {765, 43, 21}, {704, 1776, 20}, {77, 7, 100}
    };

    int output[10] = 
    {
        36, 38, 230, 99, 178, 1038, 429, 329, 374, 1547
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