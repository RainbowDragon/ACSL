/**
 *      ACSL 2019-2020 - Contest 2 - String Differences - Intermediate Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

int samenessFactor(string s1, string s2)
{
    bool done = false;

    while (!done)
    {
        done = true;

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
            else
            {
                done = false;
            }
        }

        s1 = ss1 + s1.substr(len);
        s2 = ss2 + s2.substr(len);

        len = min(s1.length(), s2.length());
        for (int i = 0; i < len; i++)
        {
            if (i + 1 < s2.length() && s1[i] == s2[i+1])
            {
                s2 = s2.substr(0, i) + s2.substr(i+1);
                done = false;
                break;
            }
            else if (i + 1 < s1.length() && s1[i+1] == s2[i])
            {
                s1 = s1.substr(0, i) + s1.substr(i+1);
                done = false;
                break;
            }
        }
    }

    int result = 0;
    int len = min(s1.length(), s2.length());
    for (int i = 0; i < len; i++)
    {
        result += s1[i] - s2[i];
    }
    result += s1.length() + s2.length() - 2 * len;

    return result;
}

int main()
{
    string input[10][2] = 
    {
        {"BLAMEABLENESSES", "BLAMELESSNESSES"},
        {"MEZZAMINES", "RAZZMATAZZ"},
        {"ABBREVIATIONS", "ABBREVIATORS"},
        {"ABCDEFGHIJKLMNO", "ABKCLDZZHQJWWLX"},
        {"ABCDEFGHIJKL", "ABXEWFRRH"},
        {"MYARTLOLLIPOPS", "MYLARBALLOONS"},
        {"MASSACHUSETTSBAYCOLONY", "MINUTEMANNATIONALHISTORICALPARK"},
        {"LOWERMACTOWNSHIPPA", "CRANBERRYTOWNSHIPPA"},
        {"AMERICANCOMPUTERSCIENCELEAGUE", "NATIONALACADEMICGAMESLEAGUE"},
        {"ABCDEFGHIJK", "ABDCEFGKILKJMN"}
    };

    int output[10] = 
    {
        -35, -5, -4, -86, -52, 23, 27, 11, 68, -9
    };

    for (int i = 0; i < 10; i++)
    {
        int result = samenessFactor(input[i][0], input[i][1]);

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