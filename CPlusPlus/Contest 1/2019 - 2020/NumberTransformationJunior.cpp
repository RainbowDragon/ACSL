/**
 *      ACSL 2019-2020 - Contest 1 - Number Transformation - Junior Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

long long transformNumber(long long num, int pos, int del)
{
    long long result = 0;

    long long digits[20];
    int index = 0;
    while (num > 0)
    {
        digits[index] = num % 10;
        num /= 10;
        index++;        
    }

    pos -= 1;
    long long digit = digits[pos];
    if (digit < 5) 
    {
        digit += del;
        digit %= 10; 
    }
    else
    {
        digit -= del;
        if (digit < 0) 
        {
            digit *= -1;
        }
            
        while (digit > 9) 
        {
            digit /= 10;
        }
    }
    digits[pos] = digit;

    for (int i = 0; i < pos; i++)
    {
        digits[i] = 0;
    }

    long long base = 1;
    for (int i = 0; i < index; i++)
    {
        result += digits[i] * base;
        base *= 10;
    }

    return result;
}

int main()
{
    long long input[10][3] = 
    {
        {124987LL, 2, 3}, {540670LL, 3, 9}, {7145042LL, 2, 8}, {124987LL, 2, 523}, {4386709LL, 1, 2},
        {4318762LL, 4, 3}, {72431685LL, 1, 7}, {123456789LL, 7, 8}, {9876543210LL, 10, 25}, {314159265358LL, 8, 428}
    };

    long long output[10] = 
    {
        124950LL, 540300LL, 7145020LL, 124950LL, 4386707LL,
        4315000LL, 72431682LL, 121000000LL, 1000000000LL, 314140000000LL
    };

    for (int i = 0; i < 10; i++)
    {
        long long result = transformNumber(input[i][0], (int)input[i][1], (int)input[i][2]);

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