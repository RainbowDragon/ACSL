/**
 *      ACSL 2023-2024 - Contest 2 - ACSL Book - Junior Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

/*
 * Complete the 'decodeMessage' function below.
 *
 * The function is expected to return a STRING.
 * The function accepts following parameters:
 * 1. STRING text
 * 2. STRING message
 */
string decodeMessage(string text, string message)
{
    vector<string> sentenceList;
    stringstream ss(text);
    string token;
    while (getline(ss, token, '.'))
    {
        sentenceList.push_back(token);
    }
    int n = sentenceList.size();
    vector<string> wordlist[n];
    for (int i = 0; i < n; i++)
    {
        stringstream ws(sentenceList[i]);
        string word;
        while (getline(ws, word, ' '))
        {
            int first = word.find_first_not_of(' ');
            if (first != string::npos)
            {
                word = word.substr(first);
            }
            else
            {
                word = "";
            }
            int last = word.find_last_not_of(' ');
            if (last != string::npos)
            {
                word = word.substr(0, last + 1);
            }
            else
            {
                word = "";
            }

            if (word.length() > 0)
            {
                wordlist[i].push_back(word);
            }
        }
    }

    vector<string> swcList;
    stringstream ms(message);
    string swc;
    while (getline(ms, swc, ' '))
    {
        swcList.push_back(swc);
    }
    string result = "";
    for (int i = 0; i < swcList.size(); i++)
    {
        int pos1 = swcList[i].find('.');
        int ns = stoi(swcList[i].substr(0, pos1)) - 1;

        int pos2 = swcList[i].find('.', pos1+1);
        int nw = stoi(swcList[i].substr(pos1+1, pos2-pos1-1)) - 1;

        int nc = stoi(swcList[i].substr(pos2+1)) - 1;

        if (ns >= n || nw >= wordlist[ns].size() || nc >= wordlist[ns][nw].length())
        {
            result += " ";
        }
        else
        {
            result += wordlist[ns][nw][nc];
        }
    }

    return result;
}

int main()
{
    string input[10][2] =
        {
            {
                "ACSL is an international computer science competition among more than 300 schools.  "
                    "With countrywide and worldwide participants it became the American Computer Science League.  "
                    "It has been in continuous existence since 1978.  "
                    "Each yearly competition consists of 4 regular season contests.  "
                    "All students at each school may compete but the team score is the sum of the best 3 or 5 top scores.  "
                    "Each contest consists of a written section and a programming section.",                                                                                   
                "1.5.4 4.2.6 1.10.1 3.2.1 6.11.6 2.9.8 4.10.3 5.18.1"
            },
            {
                "To be or not to be that is the question is a quote by William Shakespeare.  "
                    "2B or not 2B is also a Boolean expression.  "
                    "Write it both ways.",
                "2.1.2 1.3.1 1.4.2 2.8.4 1.13.5 2.7.1 2.3.1 1.17.2 1.15.5 1.10.4 4.1.1 1.15.6 2.8.4 3.4.1 1.7.3 3.4.3 1.16.6 3.3.5 1.1.1 1.3.2 1.13.2 1.10.3"
            },
            {
                "Programming languages are Java Python Visual BASIC Lisp FORTRAN C SQL.  "
                    "Javascript is the language of the Internet.  "
                    "HTML stands for Hypertext Markup Language and is not really a coding language.  "
                    "There are over 300 languages.  "
                    "Pick the one you like best.",
                "2.1.1 1.2.6 2.1.3 1.3.1 1.12.3 2.1.8 2.2.2 4.6.1 3.2.2 4.1.2 2.6.3 5.3.4 3.13.1 3.13.6 3.6.3 3.12.6 3.5.5 1.6.5 3.6.4 3.4.4 6.1.2 4.3.1 3.3.1 1.13.6 1.9.6 1.5.1 3.14.1 1.10.1 1.11.1"
            },
            {
                "Four score and seven years ago our fathers brought forth on this continent a new nation conceived in Liberty and dedicated to the proposition that all men are created equal.  "
                    "Now we are engaged in a great civil war testing whether that nation or any nation so conceived and so dedicated can long endure.  "
                    "We are met on a great battlefield of that war.  "
                    "We have come to dedicate a portion of that field as a final resting place for those who here gave their lives that that nation might live.  "
                    "It is altogether fitting and proper that we should do this.  "
                    "This was written by Abraham Lincoln in November 1863.",
                "2.1.1 1.2.3 8.6.3 6.9.1 1.31.1 1.8.7 5.6.1 1.13.7 1.15.2 2.18.1 2.12.2 8.10.4 6.6.5 4.8.2 7.4.1 1.16.2 3.7.5 4.10.4 3.12.1 1.21.7 2.5.1 6.5.7 1.13.7"
            },
            {
                "There are 10 kinds of people in the world who are those who know binary and those who do not.  "
                    "Thus you should learn binary.",
                "1.18.1 2.3.2 2.5.4 1.20.3 3.1.4 1.2.1 1.9.3 1.12.5 1.21.1 2.5.6 1.5.1 2.1.3"
            },
            {
                "ACSL or the American Computer Science League is an international computer science competition among more than 300 schools.  "
                    "Originally founded in 1978 as the Rhode Island Computer Science League it then became the New England Computer Science League.  "
                    "With countrywide and worldwide participants it became the American Computer Science League.  "
                    "Each yearly competition consists of four regular season contests.  "
                    "All students at each school may compete but the team score is the sum of the best 3 or 5 top scores.  "
                    "Each contest consists of a written section and a programming section.  "
                    "Written topics tested include 12 different CS concepts.",
                "1.1.1 1.5.3 1.5.7 1.10.5 1.10.9 1.12.6 1.16.3 1.13.11 9.1.1 2.18.1 1.13.2 1.15.1 1.13.4 2.2.3 1.11.6 1.7.2 2.1.2 1.19.1 2.19.1 1.12.2 1.12.3 1.7.6 1.13.11 2.10.2 1.12.4 2.3.3 1.7.1 1.5.7 1.10.7 2.17.3 2.11.5 1.7.6 4.10.5 1.4.1 2.9.1 2.19.1 1.7.1 4.7.8 5.12.1 1.18.7 5.6.4 2.2.1 1.7.5 1.9.2"
            },
            {
                "We hold these truths to be self evident that all men are created equal that they are endowed by their Creator with certain unalienable Rights that among these are Life Liberty and the pursuit of Happiness.  "
                    "That to secure these rights Governments are instituted among Men deriving their just powers from the consent of the governed.",
                "1.30.1 1.20.4 1.8.2 2.12.3 3.1.1 1.14.5 1.25.2 1.35.2 1.6.2 3.4.5 1.36.3 1.24.1 1.17.2 1.28.4 2.8.7 1.8.5 1.37.1 1.22.4 1.23.5 1.36.3 1.36.4 1.23.6 1.24.2 1.8.1 1.36.8 1.36.9 2.21.1 1.31.3 1.7.2 2.2.3 1.35.2 1.17.2 1.7.2 1.8.1"
            },
            {
                "The upheavals of Artificial Intelligence can escalate quickly and become scarier and even cataclysmic.  "
                    "Imagine how a medical robot originally programmed to rid cancer could conclude that the best way to obliterate cancer is to exterminate humans who are genetically prone to the disease.",
                "1.4.1 2.1.1 3.5.2 2.6.3 1.2.9 1.15.6 2.2.1 2.7.9 1.11.7 2.30.7 2.31.5 1.7.7 1.3.1 3.2.1 1.2.9 1.14.3 2.3.1 2.16.3"
            },
            {
                "I am increasingly inclined to think that there should be some regulatory oversight maybe at the national and international level just to make sure that we do not do something very foolish.  "
                    "I mean with artificial intelligence we are summoning the demon.  "
                    "This was said by Elon Musk at the MIT AeroAstro Centennial Symposium.",
                "3.10.1 2.1.1 4.1.2 1.4.1 3.12.6 1.33.3 1.8.5 1.31.1 3.10.2 1.4.6 2.11.1 3.3.1 2.4.7 3.7.1 2.7.2 3.4.2 2.11.7 1.32.1 2.10.4 3.10.8 1.1.2 1.1.1 3.1.1 3.9.4 2.5.8 3.11.5 1.17.1 3.1.3 1.12.4 3.6.3 2.5.12 1.11.1"
            },
            {
                "Quadratic equations can be solved by factoring if the discriminant is a perfect square like 49 or 36.  "
                    "If it is not then there are two irrational roots.  "
                    "However if the discriminant is less than 0 the roots are 2 imaginary numbers.  "
                    "In the 1st two cases the parabola crosses the x axis in 1 or 2 places and in the 2nd case it does not.",
                "4.7.5 5.4.3 1.2.9 1.14.2 1.2.3 3.4.10 2.9.3 3.1.6 1.1.4 1.19.5 3.14.3 2.2.1 3.7.4 1.14.3 4.3.2 2.1.3 1.16.1 4.7.8 3.4.4 5.10.1 2.9.1 4.8.7 4.24.4 4.13.1 1.18.2 1.16.2"
            }
        };

    string output[10] =
        {
            "python 3",
            "Boolean is always True",
            "Java is the language of AP CS",
            "No 1 speech of all time",
            "what are you",
            "American Computer Science League ACSL is fun",
            "Live life pursue happiness be free",
            "AI is here to stay",
            "AI is even scary for IT geniuses",
            "b squared minus 4ac is 169"
        };

    for (int i = 0; i < 10; i++)
    {
        string result = decodeMessage(input[i][0], input[i][1]);

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