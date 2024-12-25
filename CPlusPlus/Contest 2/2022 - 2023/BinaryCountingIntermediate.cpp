/**
 *      ACSL 2022-2023 - Contest 2 - Binary Counting - Intermediate Division
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

string getBinaryStringForInt(int number)
{
    if (number == 0)
    {
        return "0";
    }

    string result = "";
    while (number > 0)
    {
        int remainder = number % 2;
        number /= 2;
        result = to_string(remainder) + result;
    }

    return result;
}

/*
 * Complete the 'findLastBinary' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts STRING s as parameter.
 */
int findLastBinary(string s)
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
        string numberString = getBinaryStringForInt(number);
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
        "A is Alpha; B is Bravo; C is Charlie.",
        "A stitch in time saves nine.",
        "1, 2: Buckle my shoe! 3, 4: Shut the door!",
        "Is HackerRank the platform used by ACSL?",
        "The quick brown fox jumped over the lazy dogs.",
        "ACSL is 45 years old and going strong.",
        "What was the first computer programming language you learned?",
        "Supercalifragilisticexpialidocious!",
        "Lions and Tigers and Bears, Oh My! This is from The Wizard of Oz."
    };

    int output[10] = 
    {
        12, 20, 14, 22, 27, 29, 27, 30, 20, 26
    };

    for (int i = 0; i < 10; i++)
    {
        int result = findLastBinary(input[i]);

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