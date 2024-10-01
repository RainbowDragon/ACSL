/**
 *      ACSL 2020-2021 - Contest 1 - Numeral Triangles - Senior Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

int hex2decimal(string hexStr)
{
    int number = 0;

    for (int i = 0; i < hexStr.length(); i++)
    {
        char c = hexStr[i];
        int digit;
        if (c >= '0' && c <= '9') 
        {
            digit = c - '0';
        }    
        else 
        {
            digit = c - 'A' + 10;
        }
        number *= 16;
        number += digit;
    }

    return number;
}

int transformToSingleHexDigit(int number)
{
    while (number > 15)
    {
        int sum = 0;
        while (number > 0)
        {
            sum += number % 16;
            number /= 16;
        }
        number = sum;
    }

    return number;
}

/*
 * Complete the 'sumOfLastRow' function below.
 *
 * The function is expected to return a CHARACTER.
 * The function accepts following parameters:
 *  1. STRING s
 *  2. STRING d
 *  3. INTEGER r
 */
string sumOfLastRow(string s, string d, int r)
{
    int sum = 0;
    int numbersToSkip = r * (r - 1) / 2;

    int startingNumber = hex2decimal(s);
    int delta = hex2decimal(d);
    int firstNumberRthRow = startingNumber + delta * numbersToSkip;

    for (int i = 0; i < r; i++)
    {
        sum += transformToSingleHexDigit(firstNumberRthRow);
        firstNumberRthRow += delta;
    }
    sum = transformToSingleHexDigit(sum);

    string hexDigit[16] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
        
    return hexDigit[sum];
}

int main()
{
    string input[10][3] = 
    {
        {"A", "9", "5"}, {"ABC", "F", "4"}, {"BAD", "50", "10"}, {"FED", "ABC", "25"}, {"184", "231", "35"},
        {"ABE", "CAB", "40"}, {"31415", "92653", "60"}, {"DEAF", "BED", "72"}, {"BAD", "DAD", "100"}, {"704", "1776", "244"}
    };

    string output[10] = 
    {
        "5", "C", "A", "F", "5", "5", "F", "3", "A", "E"
    };

    for (int i = 0; i < 10; i++)
    {
        string result = sumOfLastRow(input[i][0], input[i][1], stoi(input[i][2]));

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