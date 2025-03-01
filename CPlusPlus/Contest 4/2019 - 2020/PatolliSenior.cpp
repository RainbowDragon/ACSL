/**
 *      ACSL 2019-2020 - Contest 4 - Patolli - Senior Division
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

    vector<int> player1(3);
    for (int i = 0; i < 3; i++)
    {
        iss >> player1[i];
    }

    vector<int> player2(3);
    for (int i = 0; i < 3; i++)
    {
        iss >> player2[i];
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
        sort(player1.begin(), player1.end());
        sort(player2.begin(), player2.end());

        unordered_set<int> markers;

        for (int j = 0; j < 3; j++)
        {
            if (player1[j] != 52)
            {
                markers.insert(player1[j]);
            }
            if (player2[j] != 52)
            {
                markers.insert(player2[j]);
            }
        }

        if (i % 2 == 0)
        {
            markers.erase(player1[0]);
            player1[0] = move(player1[0], rolls[i], markers);
        }
        else
        {
            markers.erase(player2[0]);
            player2[0] = move(player2[0], rolls[i], markers);
        }
    }

    int sum1 = 0;
    int sum2 = 0;
    for (int i = 0; i < 3; i++)
    {
        if (player1[i] != 52)
        {
            sum1 += player1[i];
        }
        if (player2[i] != 52)
        {
            sum2 += player2[i];
        }
    }

    return to_string(sum1) + " " + to_string(sum2);
}

int main()
{
    string input[10] = 
    {
        "3 15 18 5 13 21 4 4 5 4 4",
        "1 11 20 3 7 16 8 3 5 6 4 6 6 6 1",
        "18 22 15 6 10 5 6 3 2 6 5 1 4",
        "12 20 15 40 35 30 5 1 2 3 4 5",
        "25 20 15 12 9 6 7 6 5 4 5 3 1 6",
        "1 9 18 3 10 17 8 3 1 6 4 6 6 5 5",
        "40 44 48 50 30 45 12 5 3 1 2 4 6 5 4 3 2 1 6",
        "38 41 48 34 42 46 10 6 6 5 1 6 3 5 1 5 2",
        "4 20 38 12 23 44 10 5 6 4 6 3 6 3 4 4 3",
        "17 34 41 15 29 39 16 6 1 5 1 4 6 2 3 5 1 5 5 5 3 3 6"
    };

    string output[10] = 
    {
        "49 46", "51 34", "55 37", "54 111", "71 33",
        "31 44", "144 138", "145 135", "85 113", "138 124"
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