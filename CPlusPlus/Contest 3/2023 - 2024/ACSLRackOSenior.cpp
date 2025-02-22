/**
 *      ACSL 2023-2024 - Contest 3 - ACSL Rack-O - Senior Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

bool isRackInOrder(vector<int> & rackList)
{
    for (int i = 1; i < rackList.size(); i++)
    {
        if (rackList[i] < rackList[i-1])
        {
            return false;
        }
    }
    return true;
}

string getRackAsString(vector<int> & rackList)
{
    string result = to_string(rackList[0]);
    for (int i = 1; i < rackList.size(); i++)
    {
        result += " ";
        result += to_string(rackList[i]);
    }
    return result;
}

int getRackStepDownValue(vector<int> & rackList)
{
    int result = 0;
    for (int i = 1; i < rackList.size(); i++)
    {
        if (rackList[i] < rackList[i-1])
        {
            result++;
        }
    }
    return result;
}

pair<int, int> useIdealSlot(vector<int> & rackList, int slots, int cards, int card)
{
    vector<int> copy(rackList);

    int size = cards / slots;
    if (cards % slots != 0)
    {
        size++;
    }

    int index = (card - 1) / size;
    int low = index * size + 1;
    int high = (index + 1) * size;
    if (index == slots - 1)
    {
        high = cards;
    }

    if (rackList[index] < low || rackList[index] > high)
    {
        copy[index] = card;
    }
    else
    {
        index = -1;
    }

    int value = getRackStepDownValue(copy);

    return {value, index};
}

pair<int, int> useAscendingOrder (vector<int> & rackList, int card)
{
    vector<int> copy(rackList);

    int index = -1;
    bool found = false;
    for (int i = 1; i < rackList.size() - 1; i++)
    {
        if (!(rackList[i] >= rackList[i-1] && rackList[i] <= rackList[i+1])) 
        {
            if (card <= rackList[i] && rackList[i] <= rackList[i+1]) 
            {
                index = i - 1;
                found = true;
            }
            else if (card >= rackList[i-1] && card <= rackList[i+1]) 
            {
                index = i;
                found = true;
            }
            else if (rackList[i-1] <= rackList[i] && rackList[i] <= card) 
            {
                index = i + 1;
                found = true;
            }
        }

        if (found)
        {
            copy[index] = card;
            break;
        }
    }

    int value = getRackStepDownValue(copy);

    return {value, index};
}

/*
 * Complete the 'playRackO' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts following parameters:
 * 1. STRING info
 * 2. STRING rack
 * 3. STRING pile
 */
string playRackO(string info, string rack, string pile)
{
    istringstream iss(info);
    int slots, cards;
    iss >> slots >> cards;

    vector<int> rackList;
    stringstream rs(rack);
    string token;
    while (getline(rs, token, ' '))
    {
        rackList.push_back(stoi(token));
    }

    if (isRackInOrder(rackList))
    {
        return getRackAsString(rackList);
    }

    int current = getRackStepDownValue(rackList);

    stringstream ps(pile);
    while (getline(ps, token, ' '))
    {
        int card = stoi(token);

        pair<int, int> result1 = useIdealSlot(rackList, slots, cards, card);
        pair<int, int> result2 = useAscendingOrder(rackList, card);

        if (result1.second != -1 && result2.second != -1)
        {
            if (min(result1.first, result2.first) <= current)
            {
                current = min(result1.first, result2.first);
                if (result1.first <= result2.first)
                {
                    rackList[result1.second] = card;
                }
                else
                {
                    rackList[result2.second] = card;
                }
            }
        }
        else
        {
            if (result1.second != -1 && result1.first <= current)
            {
                current = result1.first;
                rackList[result1.second] = card;
            }
            else if (result2.second != -1 && result2.first <= current)
            {
                current = result2.first;
                rackList[result2.second] = card;
            }
        }

        if (isRackInOrder(rackList))
        {
            return getRackAsString(rackList);
        }
    }

    return getRackAsString(rackList);
}

int main()
{
    string input[10][3] = 
    {
        {"9 70", "40 35 30 56 32 58 44 17 45", "31 37 10 28 21 62 7 64 16 12"},
        {"15 90", "15 12 18 9 28 17 46 51 7 53 65 70 74 84 47", "45 73 3 52 54 16 21 44 87 40 68 30 37"},
        {"12 130", "20 110 30 16 84 40 91 69 75 7 81 15", "33 47 114 55 35 71 25 123 51 23 34 10 100 77 36 115"},
        {"8 100", "6 13 47 62 32 70 76 12", "3 67 80 10 39 44 2 43 90 85 21 63 4 52"},
        {"12 110", "44 35 22 25 79 100 85 69 87 3 56 28", "97 10 48 43 42 21 81 47 86 88 80 54 24 50"},
        {"9 140", "74 135 61 45 92 122 14 98 138", "105 60 66 116 5 106 97 85 18 139 96"},
        {"15 75", "27 43 24 9 70 64 3 33 30 63 11 1 25 12 35", "69 15 10 2 34 66 21 49 23 51 5 57 38 40 53"},
        {"11 85", "68 52 8 25 22 18 29 16 74 48 34", "30 43 6 77 7 28 73 9 27 54 71 79 42 49"},
        {"15 60", "2 10 13 19 20 26 30 34 39 41 47 48 52 58 60", "9 31 50 59 1"},
        {"10 75", "70 65 60 55 50 45 40 35 30 20", "44 10 15 46 23 72 3 68 53 37 52 75 39 51 18 71 73 62"}
    };

    string output[10] = 
    {
        "7 10 21 31 32 37 44 62 64",
        "3 12 18 21 30 37 40 51 52 53 65 70 74 84 87",
        "10 25 30 34 35 40 47 69 75 100 114 123",
        "6 21 39 43 63 70 80 85",
        "10 35 81 86 88 24 42 80 87 97 56 28",
        "5 18 61 66 92 97 106 116 138",
        "2 43 53 9 23 64 66 10 30 49 51 57 25 34 35",
        "6 7 8 25 30 43 54 71 74 77 79",
        "2 10 13 19 20 26 30 34 39 41 47 48 52 58 60",
        "3 10 23 46 50 51 52 53 72 75"
    };

    for (int i = 0; i < 10; i++)
    {
        string result = playRackO(input[i][0], input[i][1], input[i][2]);

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