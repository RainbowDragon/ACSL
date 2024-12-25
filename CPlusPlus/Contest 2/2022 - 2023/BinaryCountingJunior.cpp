/**
 *      ACSL 2022-2023 - Contest 2 - Binary Counting - Junior Division
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
        if (binaryString.find(numberString) == string::npos)
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
        "What was the first computer programming language you learned?",
        "Lions and Tigers and Bears, Oh My! This is from The Wizard of Oz.",
        "Knock, knock. Who is there? Hawaii. Hawaii who? I am good, Hawaii you?",
        "How do you use HackerRank to do each ACSL competition?",
        "~{w|x|y|z}"
    };

    int output[10] = 
    {
        16, 20, 14, 30, 61, 61, 30, 64, 33, 20
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