/**
 *      ACSL 2019-2020 - Contest 4 - Patolli - Junior Division
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

    int current;
    int numberOfRolls;
    iss >> current >> numberOfRolls;
    int rolls[numberOfRolls];
    for (int i = 0; i < numberOfRolls; i++)
    {
        iss >> rolls[i];
    }

    for (int i = 0; i < numberOfRolls; i++)
    {
        current = move(current, rolls[i], markers);
    }

    if (current == 52)
    {
        return "GAME OVER";
    }
    else
    {
        return to_string(current);
    }
}

int main()
{
    string input[10] = 
    {
        "4 6 8 1 5 6 3 5 1 1",
        "10 24 32 8 4 4 4 3 5",
        "10 22 32 8 7 4 4 3 5 5 5 6",
        "17 20 27 16 7 3 5 4 6 5 1 4",
        "43 46 50 40 5 3 1 2 4 4",
        "25 27 49 22 7 2 2 6 6 5 3 6",
        "50 41 38 45 9 4 2 5 3 1 6 4 3 1",
        "21 26 30 19 6 6 4 6 1 2 3",
        "5 14 18 2 7 2 5 4 5 2 1 6",
        "10 17 20 9 12 4 5 3 1 6 2 3 3 5 4 1 6"
    };

    string output[10] = 
    {
        "17", "23", "33", "34", "GAME OVER", "42", "GAME OVER", "27", "15", "48"
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