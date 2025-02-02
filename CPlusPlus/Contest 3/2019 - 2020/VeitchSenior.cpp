/**
 *      ACSL 2019-2020 - Contest 3 - Veitch - Senior Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

bool checkMatch(string binaryExpression, string cell)
{
    for (int i = 0; i < 4; i++)
    {
        if (binaryExpression[i] != '*' && binaryExpression[i] != cell[i])
        {
            return false;
        }
    }

    return true;
}

string getBooleanExpressionFromBinaryExpression(string binaryExpression)
{
    string result = "";
    string inputs = "ABCD";

    for (int i = 0; i < 4; i++)
    {
        if (binaryExpression[i] == '1')
        {
            result += inputs[i];
        }
        else if (binaryExpression[i] == '0')
        {
            result += "~";
            result += inputs[i];
        }
    }

    return result;
}

int getIntegerFromBinaryExpression(string binaryExpression, string* grid)
{
    int result = 0;
    for (int i = 0; i < 16; i++)
    {
        if (checkMatch(binaryExpression, grid[i]))
        {
            result += 1;
        }

        result <<= 1;
    }

    return result >> 1;
}

string getBooleanExpression(string veitchDiagram)
{
    string binaryExpressionsInOrder[80] = 
    {
        "*1**", "***1", "*0**", "1***", "**1*", "0***", "**0*", "***0",
        "*1*0", "*1*1", "*0*1", "*0*0", "1*0*", "1*1*", "0*1*", "0*0*",
        "11**", "*11*", "01**", "1**1", "**11", "0**1", "10**", "*01*",
        "00**", "*10*", "**01", "*00*", "1**0", "**10", "0**0", "**00",
        "11*0", "*110", "01*0", "11*1", "*111", "01*1", "10*1", "*011",
        "00*1", "10*0", "*010", "00*0", "110*", "1*01", "100*", "111*",
        "1*11", "101*", "011*", "0*11", "001*", "010*", "0*01", "000*",
        "*100", "*101", "*001", "*000", "1*00", "1*10", "0*10", "0*00",
        "1100", "1110", "0110", "0100", "1101", "1111", "0111", "0101",
        "1001", "1011", "0011", "0001", "1000", "1010", "0010", "0000"        
    };

    string grid[16] =
    {
        "1100", "1110", "0110", "0100", "1101", "1111", "0111", "0101",
        "1001", "1011", "0011", "0001", "1000", "1010", "0010", "0000"        
    };

    int number = stoi(veitchDiagram, 0, 16);

    string result = "";

    for (int i = 0; i < 80; i++)
    {
        int mask = getIntegerFromBinaryExpression(binaryExpressionsInOrder[i], grid);

        if ((number & mask) == mask)
        {
            if (!result.empty())
            {
                result += "+";
            }
            result += getBooleanExpressionFromBinaryExpression(binaryExpressionsInOrder[i]);
            number -= mask;
        }
    }

    return result;
}

int main()
{
    string input[10] = 
    {
        "FF33", "00CC", "6090", "8810", "9008", "F0B8", "9699", "8DD8", "C3C3", "F111"
    };

    string output[10] = 
    {
        "B+~A~B", "A~B", "BC~D+~B~CD", "AB~C+~A~B~CD", "B~C~D+A~B~C~D",
        "B~D+~A~BD+A~B~C", "~B~C+BCD+B~C~D", "A~C+ACD+~A~CD", "AB~D+~ABD+A~BD+~A~B~D", "B~D+~A~CD+~A~B~C~D"
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