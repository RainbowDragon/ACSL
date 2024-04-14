/**
 *      ACSL 2023-2024 - Contest 2 - ACSL Book - Senior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.regex.Pattern;

public class ACSLBookSenior {

    /*
     * Complete the 'encodeMessage' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     * 1. STRING text
     * 2. STRING message
     */
    static String encodeMessage (String text, String message) {

        String[] sentenceList = text.split("[!?.]");
        int n = sentenceList.length;
        ArrayList<String>[] wordlist = new ArrayList[n];
        for (int i = 0; i < n; i++)
        {
            String[] words = sentenceList[i].trim().split("\\W+");
            wordlist[i] = new ArrayList<>();
            for (String word : words)
            {
                wordlist[i].add(word);
            }
        }

        ArrayList<String>[] keyList = new ArrayList[128];
        for (int i = 0; i < 128; i++)
        {
            keyList[i] = new ArrayList<>();
        }

        for (int ns = 0; ns < n; ns++)
            for (int nw = 0; nw < wordlist[ns].size(); nw++)
                for (int nc = 0; nc < wordlist[ns].get(nw).length(); nc++)
                {
                    int index = wordlist[ns].get(nw).charAt(nc);
                    String key = (ns+1) + "." + (nw+1) + "." + (nc+1);
                    keyList[index].add(key);
                }

        StringBuilder sb = new StringBuilder();
        int encodeCount = 0;
        boolean needSpace = false;
        for (int i = 0; i < message.length(); i++)
        {
            char c = message.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                if (needSpace) {
                    sb.append(" ");
                }
                encodeCount++;
                int index = encodeCount;
                while (index > keyList[c].size()) {
                    index /= 2;
                }
                sb.append(keyList[c].get(index-1));
                needSpace = true;
            }
            else if (c == ' ') {
                sb.append("_");
                needSpace = false;
            }
            else {
                sb.append(c);
                needSpace = false;
            }
        }

        return sb.toString();
    }

    public static void main (String [] args) {

        String[][] input = new String[10][];
        input[0] = new String[]{
                "ACSL, or the American Computer Science League, is an international computer science competition among more than 300 schools.  " +
                "Originally founded in 1978 as the Rhode Island Computer Science League, it then became the New England Computer Science League.",
                "American Computer Science League (ACSL) is fun!"
        };
        input[1] = new String[]{
                "To be or not to be, that is the question- a quote by William Shakespeare.  " +
                "2B or not 2B- a hexadecimal equivalent!  " +
                "How would you write it?",
                "Boolean is always True!"
        };
        input[2] = new String[]{
                "Various programming languages are: Java, Python, Visual BASIC, C++, Lisp, C#, FORTRAN, R, SQL.  " +
                "Javascript is the language of the Internet!  " +
                "HTML stands for Hypertext Markup Language and is not really a coding language!  " +
                "There are over 300 languages.  " +
                "Which one do you like best?",
                "Java is the language of AP CS! Where is Lisp used?"
        };
        input[3] = new String[]{
                "Four score and seven years ago our fathers brought forth on this continent, a new nation, conceived in Liberty, and dedicated to the proposition that all men are created equal.  " +
                "Now we are engaged in a great civil war, testing whether that nation, or any nation so conceived and so dedicated, can long endure.  " +
                "We are met on a great battle-field of that war.  " +
                "We have come to dedicate a portion of that field, as a final resting place for those who here gave their lives that that nation might live.  " +
                "It is altogether fitting and proper that we should do this.  " +
                "This was written by Abraham Lincoln on 11/19/1863!",
                "The #1 speech of all time was less than 8 minutes long!"
        };
        input[4] = new String[]{
                "There are 10 kinds of people in the world: those who know binary and those who don't!  " +
                "Make sure you learn binary.  " +
                "Computers all use it.",
                "Could you be the 0 kind or the 1 kind?"
        };
        input[5] = new String[]{
                "ACSL, or the American Computer Science League, is an international computer science competition among more than 300 schools.  " +
                "Originally founded in 1978 as the Rhode Island Computer Science League, it then became the New England Computer Science League.  " +
                "With countrywide and worldwide participants, it became the American Computer Science League.  " +
                "It has been in continuous existence since 1978.  " +
                "Each yearly competition consists of four regular-season contests.  " +
                "All students at each school may compete, but the team score is the sum of the best 3 or 5 top scores.  " +
                "Each contest consists of 2 parts: a written section (called shorts) and a programming section.  " +
                "Written topics tested include what does this program do, digital electronics, Boolean algebra, computer numbering systems, recursive functions, data structures (primarily dealing with heaps, binary search trees, stacks, and queues), Lisp programming, regular expressions and Finite State Automata, bit string flicking, graph theory, assembly language programming, and prefix/postfix/infix notation.",
                "ACSL (American Computer Science League) is forty-six years old in '23!"
        };
        input[6] = new String[]{
                "We hold these truths to be self-evident, that all men are created equal, that they are endowed by their Creator with certain unalienable Rights, that among these are Life, Liberty and the pursuit of Happiness.  " +
                "That to secure these rights, Governments are instituted among Men, deriving their just powers from the consent of the governed...",
                "Live life; pursue happiness; be free!"
        };
        input[7] = new String[]{
                "The upheavals [of Artificial Intelligence] can escalate quickly and become scarier and even cataclysmic.  " +
                "Imagine how a medical robot, originally programmed to rid cancer, could conclude that the best way to obliterate cancer is to exterminate humans who are genetically prone to the disease.  " +
                "Nick Bilton is a tech columnist who wrote in the New York Times.",
                "AI is here to stay! If so, how good will it get?"
        };
        input[8] = new String[]{
                "I'm increasingly inclined to think that there should be some regulatory oversight, maybe at the national and international level, just to make sure that we don't do something very foolish.  " +
                "I mean with artificial intelligence we're summoning the demon.  " +
                "This was said by Elon Musk at MIT's AeroAstro Centennial Symposium.",
                "AI is even scary for IT geniuses. Is it to you?"
        };
        input[9] = new String[]{
                "The quadratic equation x^2-6x-7=0 can be solved by factoring it into (x-7)(x+1)=0.  " +
                "By setting x-7=0 or x+1=0, the result is that x = 7 or x =-1.  " +
                "However, if 3x^2-5x+9=0, factoring is not possible because 25-4*3*9<0 so the roots of this equation are 2 imaginary numbers.  " +
                "In the 1st case, the function crosses the x-axis at 7 and -1.  " +
                "In the 2nd case, the graph is completely above the x-axis!",
                "The roots of x^2+9=0 are +3i and -3i, 2 imaginary numbers."
        };

        String[] output = new String[10];
        output[0] = "1.1.1 1.5.3 1.5.7 1.10.5 1.10.9 1.12.6 1.16.3 1.13.11_2.18.1 1.18.5 1.14.2 1.13.4 2.11.5 2.18.6 2.6.3 2.9.8_2.19.1 2.10.2 2.1.3 2.11.2 1.16.4 2.14.3 2.14.2_2.11.1 2.15.3 2.17.5 2.20.4 2.18.5 2.19.7_(1.1.1 2.9.1 2.19.1 2.20.1)_1.13.9 1.18.7_2.2.1 2.2.3 2.19.5!";
        output[1] = "2.1.2 1.3.1 1.4.2 2.7.7 1.12.5 2.5.1 2.3.1_3.5.1 1.10.4_1.15.9 3.2.4 3.4.1 2.5.1 1.13.2 1.15.6_1.1.1 3.4.2 3.2.3 2.6.2!";
        output[2] = "1.5.1 1.2.6 4.3.2 1.3.6_2.1.8 2.2.2_3.2.2 4.1.2 3.4.7_3.10.5 2.4.2 3.13.3 4.5.7 3.13.5 3.6.2 3.6.7 4.5.8_5.2.1 3.3.1_1.12.6 1.6.1_1.9.1 1.14.1!_5.1.1 5.1.5 4.1.3 4.3.4 4.1.5_3.8.1 3.2.1_3.1.4 3.12.4 3.2.6 3.4.3_4.5.5 3.8.2 5.2.3 5.3.1?";
        output[3] = "6.1.1 1.9.6 1.4.4_#6.10.1_1.12.4 5.6.4 1.15.2 1.17.5 2.18.4 3.10.2_1.22.2 4.10.1_1.30.4 5.9.5 6.6.6_2.7.5 2.21.4 4.26.1 2.2.2_6.3.1 2.15.1 4.14.3_4.22.1 2.10.2 4.17.4 4.22.5_3.7.3 4.18.2 3.7.2 4.13.3_6.10.2_4.26.1 6.1.3 5.4.6 1.30.3 4.14.4 3.7.6 2.20.1_4.13.5 2.18.2 2.15.2 4.20.1!";
        output[4] = "3.1.1 1.6.3 3.1.5 3.2.2 1.9.5_2.5.6 1.15.3 3.3.1_2.5.1 2.2.4_3.1.6 1.16.2 3.3.3_1.3.2_2.1.3 2.5.2 2.5.3 1.17.1_1.17.2 2.2.3_3.1.6 1.15.2 2.4.2_1.3.1_2.1.3 1.13.2 1.17.3 1.14.3?";
        output[5] = "1.1.1 1.5.1 2.10.1 2.20.1_(8.38.1 1.15.1 1.7.6 2.9.8 2.1.3 2.10.6 2.11.3 2.2.4_2.9.1 2.18.2 6.7.3 8.14.4 6.8.2 3.5.11 2.10.7 6.19.2_3.11.1 4.7.4 4.4.1 2.14.6 4.3.4 5.9.1 2.18.7_2.11.1 2.19.7 7.1.2 8.22.7 6.2.3 3.4.9)_7.14.9 7.11.6_8.49.5 7.4.1 8.21.2 6.17.4 8.44.8-8.11.11 8.11.9 8.50.5_6.6.3 4.6.1 8.22.3 8.34.4 8.20.10_8.14.2 8.45.1 7.12.3_8.32.9 8.29.2_'7.5.1 1.17.1!";
        output[6] = "1.30.1 1.20.4 2.11.5 1.6.2_1.14.5 1.25.2 1.35.2 1.11.2;_2.14.1 1.34.5 1.34.3 2.6.11 2.3.4 1.17.3_2.5.4 1.36.2 2.14.1 2.14.1 1.34.6 2.6.6 1.24.11 2.5.6 2.5.6;_1.24.9 1.30.4_1.35.2 2.5.1 1.36.7 2.3.2!";
        output[7] = "1.4.1 2.1.1_1.4.8 1.14.8_2.14.2 1.7.1 2.7.5 1.10.2_2.14.1 2.12.2_3.6.8 2.18.5 2.3.1 2.6.10!_1.5.1 1.4.5_2.30.3 3.6.2,_3.5.4 3.8.3 3.8.1_2.26.1 2.17.2 2.18.1 2.11.5_3.11.3 2.18.4 2.26.9 2.26.9_2.22.7 2.22.3_2.7.4 3.5.2 2.26.5?";
        output[8] = "3.10.1 2.1.1_1.4.1 1.13.5_1.10.2 1.31.1 1.12.2 1.18.2_2.8.1 1.4.3 1.23.2 3.10.8 3.12.2_1.32.1 2.10.4 1.31.3_2.1.1 3.8.3_1.30.9 2.5.4 3.11.3 3.12.7 2.8.2 3.3.1 2.9.3 3.6.3._3.8.2 3.9.1_2.4.6 1.25.1_1.25.1 3.5.3_1.31.4 3.10.4 1.24.2?";
        output[9] = "1.1.1 2.10.2 1.10.2_2.11.1 2.6.1 2.16.1 2.2.4 3.12.6_3.10.2 4.6.1_4.9.1^3.13.1+3.6.1=2.5.1_4.11.1 3.20.1 4.2.3_+3.15.1 5.12.3_5.12.1 3.27.1 1.11.6_-3.15.1 3.22.3,_3.13.1_3.23.6 5.8.3 4.10.1 3.8.9 3.26.5 5.1.2 4.13.1 3.20.1 5.8.10_3.23.8 3.12.5 3.27.3 3.12.1 4.5.3 3.26.8 5.7.2.";


        for (int i = 0; i < 10; i++)
        {
            String result = encodeMessage(input[i][0], input[i][1]);

            if (output[i].equals(result)) {
                System.out.println("Test Case " + i + ": Passed!");
            }
            else {
                System.out.println("Test Case " + i + ": Failed!");
                System.out.println("Expected output: " + output[i]);
                System.out.println("Your output: " + result);
            }
        }
    }
}