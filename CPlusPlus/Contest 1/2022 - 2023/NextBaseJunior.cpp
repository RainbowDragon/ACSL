/**
 *      ACSL 2022-2023 - Contest 1 - Next Base - Junior Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

/*
 * Complete the 'findDigitSum' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts following parameters:
 * 1. INTEGER num
 * 2. INTEGER base
 * 3. INTEGER start
 */
int findDigitSum(int num, int base, long long start)
{
    int result = 0;
    long long startValue = stoll(to_string(start), nullptr, base);

    for (int i = 0; i < num; i++)
    {
        long long number = startValue + i;
        int sum = 0;

        while (number > 0) 
        {
            sum += (int)(number % base);
            number /= base;
        }

        result += sum;
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
        65, 64, 189, 212, 170, 10948, 225, 876, 675, 135
    };

    for (int i = 0; i < 10; i++)
    {
        int result = findDigitSum(stoi(input[i][0]), stoi(input[i][1]), stoll(input[i][2]));

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