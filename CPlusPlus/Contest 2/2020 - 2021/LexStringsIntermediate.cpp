/**
 *      ACSL 2020-2021 - Contest 2 - Lex Strings - Intermediate Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

bool comparePair(pair<char, int>& a,  pair<char, int>& b)
{
    if (a.second == b.second)
    {
        return a.first < b.first;
    }
    else
    {
        return a.second > b.second;
    }
}

/*
 * Complete the 'rearrangeString' function below.
 *
 * The function is expected to return a STRING.
 * The function accepts following parameters:
 *  1. STRING s
 *  2. INTEGER n
 */
string rearrangeString(string s, int n)
{
    vector<pair<char, int>> blocks;

    char lastChar = ' ';
    int count = 0;
    for (int i = 0; i < s.length(); i++)
    {
        if (s[i] == lastChar)
        {
            count++;
        }
        else
        {
            count = 1;
        }

        if (i == s.length() - 1 || s[i] != s[i+1])
        {
            blocks.push_back({s[i], count});
        }

        lastChar = s[i];
    }

    sort(blocks.begin(), blocks.end(), comparePair);

    string result = "";
    lastChar = ' ';
    int maxLoop = n;
    for (int i = 0; i < blocks.size(); i++)
    {
        char curChar = blocks[i].first;
        if (curChar != lastChar)
        {
            maxLoop = n;
        }
        int loop = min(maxLoop, blocks[i].second);
        for (int j = 0; j < loop; j++)
        {
            result += curChar;
        }
        lastChar = curChar;
        maxLoop -= loop;
    }

    return result;
}

int main()
{
    string input[10][2] = 
    {
        {"MBAMMDXXMMMGGMMZ", "3"},
        {"MHHHHJLDDHHDDD", "3"},
        {"THETENNESSEEVOLUNTEERS", "2"},
        {"MISSISSIPPI", "3"},
        {"BOOOKEEEPEEERR", "4"},
        {"BOOOKEEEPEERBBBBUZZZOOKEEEEPEER", "2"},
        {"MASSACHUSETTSVSMISSISSIPPI", "2"},
        {"OOOOZESSPPOOOOOYYYSSSUPY", "4"},
        {"SHESELLSSEASHELLSANDBALLOONS", "3"},
        {"HHHGGRDDCFFFGGGTTTYUIKJHHH", "1"}
    };

    string output[10] = 
    {
        "MMMGGMMMXXABDMZ",
        "HHHDDDHHJLM",
        "EENNSSEEHLNORSTTUV",
        "PPSSSIIIM",
        "EEEEOOORRBKP",
        "BBEEOOZZEEOOBKKPPRRU",
        "PPSSTTAACEHIIMMSSUV",
        "OOOOSSSYYYPPSSEPUYZ",
        "LLLOOSSAAABDEEEHHNNSSS",
        "FGHTDGCIJKRUY"
    };

    for (int i = 0; i < 10; i++)
    {
        string result = rearrangeString(input[i][0], stoi(input[i][1]));

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