/**
 *      ACSL 2019-2020 - Contest 4 - Patolli - Intermediate Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

int startOfTurn[9] = {6, 11, 16, 21, 26, 34, 39, 44, 49};

bool willMoveHorizontalAndVertical(int current, int next)
{
    for (int i = 0; i < 9; i++)
    {
        if (current <= startOfTurn[i] && next >= startOfTurn[i] + 2)
        {
            return true;
        }
    }

    return false;
}

bool isPerfectSquare(int number)
{
    for (int i = 1; i < number; i++)
    {
        if (i * i == number)
        {
            return true;
        }
    }

    return false;
}

bool isPrime(int number)
{
    for (int i = 2; i < number; i++)
    {
        if (number % i == 0)
        {
            return false;
        }
    }

    return true;
}

int move(int current, int roll, unordered_set<int> & markers)
{
    int next = current + roll;

    if (current == 52 || next > 52 || markers.find(next) != markers.end())
    {
        next = current;
    }
    else if (isPrime(next))
    {
        for (int i = 0; i < 6; i++)
        {
            if (markers.find(next+1) != markers.end())
            {
                break;
            }
            else
            {
                next++;
            }
        }
    }
    else if (isPerfectSquare(next))
    {
        for (int i = 0; i < 6; i++)
        {
            if (markers.find(next-1) != markers.end())
            {
                break;
            }
            else
            {
                next--;
            }
        }        
    }
    else if (willMoveHorizontalAndVertical(current, next))
    {
        for (int i = next; i >= current; i--)
        {
            next = i;
            if (i % roll == 0 && markers.find(i) == markers.end())
            {
                break;
            }
        }
    }

    return next;
}

string getFinalLocation(string str)
{
    istringstream iss(str);
    unordered_set<int> markers;
    for (int i = 0; i < 3; i++)
    {
        int marker;
        iss >> marker;
        markers.insert(marker);
    }

    vector<int> starts(3);
    for (int i = 0; i < 3; i++)
    {
        iss >> starts[i];
    }   

    int numberOfRolls;
    iss >> numberOfRolls;
    int rolls[numberOfRolls];
    for (int i = 0; i < numberOfRolls; i++)
    {
        iss >> rolls[i];
    }

    for (int i = 0; i < numberOfRolls; i++)
    {
        sort(starts.begin(), starts.end());
        markers.erase(starts[0]);
        markers.insert(starts[1]);
        markers.insert(starts[2]);
        starts[0] = move(starts[0], rolls[i], markers);
        markers.erase(starts[1]);
        markers.erase(starts[2]);
    }

    vector<int> ends;
    for (int i = 0; i < 3; i++)
    {
        if (starts[i] != 52)
        {
            ends.push_back(starts[i]);
        }
    }
    sort(ends.begin(), ends.end());

    if (ends.empty())
    {
        return "GAME OVER";
    }
    else
    {
        string result = "";
        for (int i= 0; i < ends.size(); i++)
        {
            if (i > 0)
            {
                result += " ";
            }
            result += to_string(ends[i]);
        }

        return result;
    }
}

int main()
{
    string input[10] = 
    {
        "4 14 24 1 8 12 6 6 3 5 1 5 6",
        "14 28 31 10 20 24 7 6 6 5 5 6 2 4",
        "5 30 33 3 20 24 8 6 6 6 5 6 3 4 6",
        "4 11 15 2 8 20 5 5 2 5 1 6",
        "45 50 48 42 46 40 6 3 2 6 5 4 1",
        "37 41 47 35 43 48 6 5 5 6 5 4 2",
        "13 29 39 15 21 31 10 4 5 2 4 6 6 5 5 6 5",
        "43 47 40 28 30 32 10 5 3 2 6 1 5 2 6 3 2",
        "1 5 10 2 12 8 12 5 5 4 4 3 3 2 2 1 1 6 6",
        "20 25 15 30 18 24 16 6 6 4 5 2 1 6 4 2 3 6 5 4 5 3 1"
    };

    string output[10] = 
    {
        "13 15 18", "26 29 30", "20 23 24", "14 16 20", "44 46 47",
        "49 50", "34 35 36", "37 38 39", "9 11 12", "32 33 35"
    };

    for (int i = 0; i < 10; i++)
    {
        string result = getFinalLocation(input[i]);

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