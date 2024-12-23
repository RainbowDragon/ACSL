/**
 *      ACSL 2019-2020 - Contest 2 - String Differences - Junior Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

string deleteDouble(string s)
{
    string result = "";

    char last = ' ';
    for (int i = 0; i < s.length(); i++)
    {
        if (last != s[i]) 
        {
            result += s[i];
            last = s[i];
        }
    }

    return result;
}

string deleteVowels(string s)
{
    string result = "";

    for (int i = 0; i < s.length(); i++)
    {
        if (i == 0 || (s[i] != 'A' && s[i] != 'E' && s[i] != 'I' && s[i] != 'O' && s[i] != 'U')) 
        {
            result += s[i];
        }
    }

    return result;
}

string stringDifferences(string s1, string s2)
{
    s1 = deleteDouble(s1);
    s2 = deleteDouble(s2);

    s1 = deleteVowels(s1);
    s2 = deleteVowels(s2);

    string ss1 = "";
    string ss2 = "";
    int len = min(s1.length(), s2.length());
    for (int i = 0; i < len; i++)
    {
        if (s1[i] != s2[i]) 
        {
            ss1 += s1[i];
            ss2 += s2[i];
        }
    }
    ss1 += s1.substr(len);
    ss2 += s2.substr(len);

    s1 = ss1;
    s2 = ss2;
    ss1 = "";
    ss2 = "";
    int l1 = s1.length();
    int l2 = s2.length();
    len = min(l1, l2);
    for (int i = 1; i <= len; i++)
    {
        if (s1[l1-i] != s2[l2-i]) 
        {
            ss1 = s1[l1-i] + ss1;
            ss2 = s2[l2-i] + ss2;
        }
    }
    ss1 = s1.substr(0, l1-len) + ss1;
    ss2 = s2.substr(0, l2-len) + ss2;

    string result = "";
    if (ss1.length() < ss2.length()) 
    {
        result = ss1;
    }
    else if (ss1.length() > ss2.length()) 
    {
        result = ss2;
    }
    else 
    {
        result = ss1.compare(s2) < 0 ? ss1 : ss2;
    }

    return result;
}

int main()
{
    string input[10][2] = 
    {
        {"MISSISSIPPI", "MISSOURI"},
        {"CATHERINE", "KATHERYNE"},
        {"CONSTITUTIONAL", "CONVENTIONAL"},
        {"COMPARTMENTALIZATION", "SEMIAUTOBIOGRAPHICAL"},
        {"PHYSICIAN", "PHARMACY"},
        {"FEEFIFOFUM", "FIDDLEDEEDEE"},
        {"MYLOLLIPOPS", "MYLARBALLOONS"},
        {"CONNECTICUTCT", "CONSTITUTIONSTATE"},
        {"MASSACHUSETTSBAYCOLONY", "MINUTEMANNATIONALHISTORICALPARK"},
        {"AMERICANCOMPUTERSCIENCELEAGUE", "NATIONALACADEMICGAMESLEAGUE"}
    };

    string output[10] = 
    {
        "R", "C", "VN", "SBGRPHCL", "RMY", "DLDD", "LPP", "CCC", "SCSBYCLNY", "NTNLCDGM"
    };

    for (int i = 0; i < 10; i++)
    {
        string result = stringDifferences(input[i][0], input[i][1]);

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