/**
 *      ACSL 2024-2025 - Contest 1 - Rings - Intermediate Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

int getScore(char toss) 
{
    int score = 0;

    if (toss == 'A' || toss == 'R') 
    {
        score = 1;
    }
    else if (toss == 'O' || toss == 'G') 
    {
        score = 3;
    }
    else if (toss == 'B') 
    {
        score = 6;
    }

    return score;
}

int getScore(string tosses) 
{
    int score = 0;

    stringstream ss(tosses);
    string toss;
    while (getline(ss, toss, ' '))
    {
        score += getScore(toss[0]);
        if (toss.length() == 2)
        {
            score += getScore(toss[1]) + 1;
        }
    }

    return score;
}

/*
 * Complete the 'scoreTosses' function below.
 *
 * The function is expected to return a STRING.
 * The function accepts following parameters:
 *  1. STRING tosses1
 *  2. STRING tosses2
 *  3. STRING tosses3
 */
string scoreTosses(string tosses1, string tosses2, string tosses3)
{
    int scores[3];
    scores[0] = getScore(tosses1);
    scores[1] = getScore(tosses2);
    scores[2] = getScore(tosses3);

    sort(scores, scores + 3);

    return to_string(scores[2]) + " " + to_string(scores[1]) + " " + to_string(scores[0]);
}

int main()
{
    string input[12][3] = 
    {
        {
            "GR A B",
            "OB BG AO O",
            "R G"
        },
        {
            "A R O G B",
            "A A B B",
            "R O B"
        },
        {
            "AO OB B G A B BG GR",
            "R R G G AO AO BG BG B",
            "AO GR A B R G O BG OB"
        },
        {
            "A A A B B R O O O O",
            "BG BG GR GR O O O A B",
            "BG BG GR GR O O O B B G"
        },
        {
            "A B B O O G AO OB GR BG",
            "GR GR BG BG OB AO AO",
            "B B B G G G A A A GR AO"
        },
        {
            "GR BG OB AO A G B",
            "A O B AO GR GR BG O GR AO",
            "O O B G A A GR OB AO BG"
        },
        {
            "A R R B B B G G O",
            "G R A B B R A G O O",
            "A R B G O B G A"
        },
        {
            "AO AO BG GR BG OB OB OB AO",
            "BG BG BG BG AO AO AO GR OB OB",
            "BG GR AO AO GR BG OB OB"
        },
        {
            "B G O R A B G O O R A B G O R A B G O R A",
            "AO BG GR OB AO BG GR B A O BG GR OB",
            "B R A G B OB R A G B O G AO BG BG AO GR"
        },
        {
            "AO GR GR OB O B OB BG B G BG BG AO G R GR OB BG BG",
            "AO OB G R BG AO O B GR BG AO OB GR BG A GR BG AO OB GR BG",
            "AO OB GR BG AO OB G R BG R A G OB B AO"
        },
        {
            "AO OB GR BG A B R G O",
            "OB GR BG AO B R G O A",
            "GR BG AO OB R G O A B"
        },
        {
            "O BG GR OB OB B A GR BG BG BG OB",
            "O G A GR OB AO BG BG B G AO OB OB OB BG",
            "B B B B B B B B B B B B B B B"
        }
    };

    string output[12] = 
    {
        "28 12 4", "14 14 10", "46 44 44", "54 46 28", "52 50 40", "48 47 40",
        "30 28 24", "80 70 60", "85 79 59", "134 127 85", "44 44 44", "101 90 90"
    };

    for (int i = 0; i < 12; i++)
    {
        string result = scoreTosses(input[i][0], input[i][1], input[i][2]);

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