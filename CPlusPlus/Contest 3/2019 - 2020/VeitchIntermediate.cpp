/**
 *      ACSL 2019-2020 - Contest 3 - Veitch - Intermediate Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

string convertExpression(string expression)
{
    string input1[4] = {"~A", "~B", "~C", "~D"};
    string input2[4] = {"A", "B", "C", "D"};
    string result = "";

    for (int i = 0; i < 4; i++)
    {
        if (expression.find(input1[i]) != string::npos)
        {
            result += "0";
        }
        else if (expression.find(input2[i]) != string::npos)
        {
            result += "1";
        }
        else
        {
            result += "*";
        }
    }

    return result;
}

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

string getVeitchDiagram(string booleanExpression)
{
    int veitchDiagram[16];
    memset(veitchDiagram, 0, sizeof veitchDiagram);

    string grid[16] =
    {
        "1100", "1110", "0110", "0100", "1101", "1111", "0111", "0101",
        "1001", "1011", "0011", "0001", "1000", "1010", "0010", "0000"        
    };

    stringstream ss(booleanExpression);
    string expression;
    while (getline(ss, expression, '+'))
    {
        string binaryExpression = convertExpression(expression);
        for (int i = 0; i < 16; i++)
        {
            if (checkMatch(binaryExpression, grid[i]))
            {
                veitchDiagram[i] = 1;
            }
        }
    }

    string result = "";
    string hexString = "0123456789ABCDEF";
    for (int i = 0; i < 16; i  = i + 4)
    {
        int value = 8*veitchDiagram[i] + 4*veitchDiagram[i+1] + 2*veitchDiagram[i+2] + veitchDiagram[i+3];
        result += hexString[value];
    }

    return result;
}

int main()
{
    string input[10] = 
    {
        "AB+~AB+~A~B",
        "AB~C~D+AB~CD+~A~B~CD",
        "AB~C~D+~AB~C~D+A~B~C~D",
        "B~D+~B~D",
        "~A~BD+~A~B~D",
        "B~D+~A~BD+A~B~C",
        "~B~C+BCD+B~C~D",
        "A~C+ACD+~A~CD",
        "AB~D+~ABD+A~BD+~A~B~D",
        "B~D+~A~CD+~A~B~C~D"
    };

    string output[10] = 
    {
        "FF33", "8810", "9008", "F00F", "0033", "F0B8", "9699", "8DD8", "C3C3", "F111"
    };

    for (int i = 0; i < 10; i++)
    {
        string result = getVeitchDiagram(input[i]);

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