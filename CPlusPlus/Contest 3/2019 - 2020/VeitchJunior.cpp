/**
 *      ACSL 2019-2020 - Contest 3 - Veitch - Junior Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

int checkMatch(int number, int mask, string expression, string & result)
{
    if ((number & mask) == mask)
    {
        if (!result.empty())
        {
            result += "+";
        }
        result += expression;

        number -= mask;
    }

    return number;
}

string getBooleanExpression(string veitchDiagram)
{
    int masksInOrder[26] =
    {
        240, 15, 204, 102, 51, 153, 
        192, 96, 48, 12, 6, 3,
        136, 68, 34, 17, 144, 9,
        128, 64, 32, 16,
        8, 4, 2, 1
    };

    string booleanExpressionsInOrder[26] = 
    {
        "B", "~B", "A", "C", "~A", "~C",
        "AB", "BC", "~AB", "A~B", "~BC", "~A~B",
        "A~C", "AC", "~AC", "~A~C", "B~C", "~B~C",
        "AB~C", "ABC", "~ABC", "~AB~C",
        "A~B~C", "A~BC", "~A~BC", "~A~B~C"
    };

    int number = stoi(veitchDiagram, 0, 16);
    string result = "";

    for (int i = 0; i < 26; i++)
    {
        number = checkMatch(number, masksInOrder[i], booleanExpressionsInOrder[i], result);
    }

    return result;
}

int main()
{
    string input[10] = 
    {
        "33", "3C", "94", "77", "95", "F0", "1D", "9D", "E9", "E7"
    };

    string output[10] = 
    {
        "~A", "~AB+A~B", "B~C+A~BC", "C+~A~C", "~A~C+AB~C+A~BC",
        "B", "A~B+~A~C", "~C+A~BC", "AB+~B~C+~ABC", "C+AB~C+~A~B~C"
    };

    for (int i = 0; i < 10; i++)
    {
        string result = getBooleanExpression(input[i]);

        if (output[i].compare(result) == 0) 
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