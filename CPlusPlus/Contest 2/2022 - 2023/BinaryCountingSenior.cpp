/**
 *      ACSL 2022-2023 - Contest 2 - Binary Counting - Senior Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

string getBinaryStringForChar(char c)
{
    string result = "00000000";

    int number = c;
    int index = 7;
    while (number > 0)
    {
        result[index] = (number % 2 == 0) ? '0' : '1';
        number /= 2;
        index--;
    }

    return result;
}

string getBaseStringForInt(int number, int base)
{
    if (number == 0)
    {
        return "0";
    }

    string result = "";
    while (number > 0)
    {
        int remainder = number % base;
        number /= base;
        result = to_string(remainder) + result;
    }

    return result;
}

string getOctalStringFromBinaryString(string binaryString)
{
    int length = binaryString.length();
    if (length % 3 == 1) 
    {
        binaryString = "00" + binaryString;
    }
    else if (length % 3 == 2) 
    {
        binaryString = "0" + binaryString;
    }

    string octalString = "";
    for (int i = 0; i < binaryString.length(); i += 3)
    {
        string numberString = binaryString.substr(i, 3);
        int number = 0;
        for (int j = 0; j < 3; j++)
        {
            number = number * 2 + (numberString[j] - '0');
        }
        octalString += to_string(number);
    }

    return octalString;
}

/*
 * Complete the 'findLastOctal' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts STRING s as parameter.
 */
int findLastOctal(string s)
{
    string binaryString = "";
    for (int i = 0; i < s.length(); i++)
    {
        string charBinaryString = getBinaryStringForChar(s[i]);
        binaryString += charBinaryString;
    }
   
    int number = 0;
    bool found = false;
    while (!found)
    {
        string numberString = getBaseStringForInt(number, 2);
        int length = numberString.length();

        int first = binaryString.find(numberString);
        if (first != string::npos)
        {
            binaryString = binaryString.substr(0, first) + binaryString.substr(first+length);
        }

        int last = binaryString.rfind(numberString);
        if (last != string::npos)
        {
            binaryString = binaryString.substr(0, last) + binaryString.substr(last+length);
        }

        if (first == string::npos && last == string::npos)
        {
            found = true;
        }
        else
        {
            number++;
        }
    }

    string octalString = getOctalStringFromBinaryString(binaryString);

    number = 0;
    found = false;
    while (!found)
    {
        string numberString = getBaseStringForInt(number, 8);
        int length = numberString.length();

        int first = octalString.find(numberString);
        if (first != string::npos)
        {
            octalString = octalString.substr(0, first) + octalString.substr(first+length);
        }

        int last = octalString.rfind(numberString);
        if (last != string::npos)
        {
            octalString = octalString.substr(0, last) + octalString.substr(last+length);
        }

        if (first == string::npos && last == string::npos)
        {
            found = true;
            number--;
        }
        else
        {
            number++;
        }
    }

    return number;
}

int main()
{
    string input[10] = 
    {
        "Roses are red.",
        "A is for Alpha; B is for Bravo; C is for Charlie.",
        "A stitch in time saves nine.",
        "1, 2: Buckle my shoe! 3, 4: Shut the door!",
        "The quick brown fox jumped over the lazy dogs.",
        "ACSL is 45 years old and going strong.",
        "What was the first computer programming language you learned?",
        "Lions and Tigers and Bears, Oh My! This is from The Wizard of Oz.",
        "zyxwvutsrqponmlkjihgfedcba",
        "~{w|x|y|z}"
    };

    int output[10] = 
    {
        4, 9, 8, 6, 5, 6, 9, 9, 2, -1
    };

    for (int i = 0; i < 10; i++)
    {
        int result = findLastOctal(input[i]);

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