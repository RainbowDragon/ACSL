/**
 *      ACSL 2019-2020 - Contest 1 - Number Transformation - Intermediate Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

long long transformNumber(long long num, int pos)
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
    for (int i = 0; i < index; i++)
    {
        if (i > pos) 
        {
            digits[i] += digits[pos];
            digits[i] %= 10;
        }
        else if (i < pos) 
        {
            digits[i] -= digits[pos];
            if (digits[i] < 0) 
            {
                digits[i] *= -1;
            }
        }
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
    long long input[10][2] = 
    {
        {296351LL, 5}, {762184LL, 3}, {45873216LL, 7}, {19750418LL, 6}, {386257914LL, 5},
        {4318672LL, 4}, {35197545LL, 1}, {975318642LL, 9}, {9876543210LL, 5}, {314159265358LL, 10}
    };

    long long output[10] = 
    {
        193648LL, 873173LL, 95322341LL, 86727361LL, 831752441LL,
        2198216LL, 80642095LL, 924681357LL, 3210941234LL, 754315221114LL
    };

    for (int i = 0; i < 10; i++)
    {
        long long result = transformNumber(input[i][0], (int)input[i][1]);

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