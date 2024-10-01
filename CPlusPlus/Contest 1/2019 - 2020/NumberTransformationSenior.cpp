/**
 *      ACSL 2019-2020 - Contest 1 - Number Transformation - Senior Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

int countPrimeFactors (long long num)
{
    int count = 0;

    if (num % 2 == 0) 
    {
        count++;

        while (num % 2 == 0) 
        {
            num /= 2;
        }
    }

    for (long long i = 3; i <= sqrt(num); i += 2)
    {
        if (num % i == 0) 
        {
            count++;

            while (num % i == 0) 
            {
                num /= i;
            }
        }
    }

    if (num > 2) 
    {
        count++;
    }

    return count;
}

long long transformNumber(long long num, int pos)
{
    long long result = 0;

    long long digits[20];
    int count = countPrimeFactors(num);
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
    digits[pos] = count;

    long long base = 1;
    for (int i = 0; i < index; i++)
    {
        result += digits[i] * base;
        base *= 10;
        if (digits[i] > 9) 
        {
            base *= 10;
        }
    }

    return result;
}

int main()
{
    long long input[10][2] = 
    {
        {102438LL, 3}, {4329LL, 1}, {6710LL, 2}, {16807LL, 1}, {60098065452LL, 7},
        {43287LL, 3}, {72431685LL, 1}, {246897531573LL, 12}, {96783LL, 5}, {16058314729LL, 3}
    };

    long long output[10] = 
    {
        546414LL, 1312113LL, 7841LL, 8131571LL, 1488173823436LL,
        65365LL, 12798611133LL, 424675311351LL, 23216LL, 8137121510811152LL
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