/**
 *      ACSL 2023-2024 - Contest 2 - ACSL Book - Intermediate Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.regex.Pattern;

public class ACSLBookIntermediate {

    /*
     * Complete the 'decodeMessage' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     * 1. STRING text
     * 2. STRING message
     */
    static String decodeMessage (String text, String message) {

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

        String[] swclist = message.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String swc : swclist)
        {
            String[] numbers = swc.split(Pattern.quote("."));
            int ns = Integer.parseInt(numbers[0]) - 1;
            int nw = Integer.parseInt(numbers[1]) - 1;
            int nc = Integer.parseInt(numbers[2]) - 1;

            if (ns >= n || nw >= wordlist[ns].size() || nc >= wordlist[ns].get(nw).length()) {
                sb.append(" ");
            }
            else {
                sb.append(wordlist[ns].get(nw).charAt(nc));
            }
        }

        return sb.toString();
    }

    public static void main (String [] args) {

        String[][] input = new String[10][];
        input[0] = new String[]{
                "ACSL, or the American Computer Science League, is an international computer science competition among more than 300 schools.  " +
                "Originally founded in 1978 as the Rhode Island Computer Science League, it then became the New England Computer Science League.  " +
                "With countrywide and worldwide participants, it became the American Computer Science League.  " +
                "It has been in continuous existence since 1978.  " +
                "Each yearly competition consists of 4 regular-season contests.  " +
                "All students at each school may compete but the team score is the sum of the best 3 or 5 top scores.  " +
                "Each contest consists of two parts: a written section (called shorts) and a programming section.  " +
                "Written topics tested include what does this program do, digital electronics, Boolean algebra, computer numbering systems, recursive functions, data structures (primarily dealing with heaps, binary search trees, stacks, and queues), Lisp programming, regular expressions and Finite State Automata, bit string flicking, graph theory, assembly programming and prefix/postfix/infix notation.",
                "3.5.1 8.21.9 1.10.8 2.7.2 7.15.6 5.4.3 4.10.3 6.18.1"
        };
        input[1] = new String[]{
                "To be or not to be, that is the question-a quote by William Shakespeare.  " +
                "2B or not 2B-a hexadecimal equivalent!  " +
                "How would you write it?",
                "2.1.2 1.3.1 1.4.2 2.7.7 1.12.5 2.5.1 2.3.1 1.16.2 1.14.5 1.10.4 4.1.1 1.14.6 2.7.7 3.2.1 1.14.6 3.3.1 1.15.6 3.3.4 1.1.1 1.15.10 2.7.3 1.10.3"
        };
        input[2] = new String[]{
                "Various programming languages are: Java, Python, Visual BASIC, C++, Lisp, C#, FORTRAN, R, SQL.  " +
                "Javascript is the language of the Internet!  " +
                "HTML stands for Hypertext Markup Language and is not really a coding language!  " +
                "There are over 300 languages.  " +
                "Which one do you like best?",
                "2.1.1 1.2.6 2.1.3 1.3.6 1.11.3 2.1.8 2.2.2 4.6.1 3.2.2 4.1.2 2.6.3 5.3.3 3.13.1 3.13.6 3.6.3 3.12.6 3.5.5 1.5.4 3.6.4 3.4.4 6.1.2 5.2.1 3.3.1 1.13.6 1.12.6 1.6.1 3.14.1 1.9.1 1.14.1"
        };
        input[3] = new String[]{
                "Four score and seven years ago our fathers brought forth on this continent, a new nation, conceived in Liberty, and dedicated to the proposition that all men are created equal.  " +
                "Now we are engaged in a great civil war, testing whether that nation, or any nation so conceived and so dedicated, can long endure.  " +
                "We are met on a great battle-field of that war.  " +
                "We have come to dedicate a portion of that field, as a final resting place for those who here gave their lives that that nation might live.  " +
                "It is altogether fitting and proper that we should do this.  " +
                "This was written by Abraham Lincoln on 11/19/1863!",
                "2.1.1 1.2.3 8.6.3 6.9.1 1.31.1 1.8.7 5.6.1 1.13.7 1.15.2 2.18.1 2.12.2 8.10.4 6.7.1 4.8.2 7.4.1 1.16.2 3.8.4 4.10.4 3.12.1 1.21.7 2.5.1 6.5.7 1.13.7"
        };
        input[4] = new String[]{
                "There are 10 kinds of people in the world: those who know binary and those who don't!  " +
                "Thus learn binary.",
                "1.12.4 2.1.2 2.2.3 1.18.1 3.1.4 1.2.1 1.9.3 1.10.5 1.20.1 1.13.6 1.16.3 2.1.3"
        };
        input[5] = new String[]{
                "ACSL, or the American Computer Science League, is an international computer science competition among more than 300 schools.  " +
                "Originally founded in 1978 as the Rhode Island Computer Science League, it then became the New England Computer Science League.  " +
                "With countrywide and worldwide participants, it became the American Computer Science League.  " +
                "It has been in continuous existence since 1978.  " +
                "Each yearly competition consists of 4 regular-season contests.  " +
                "All students at each school may compete but the team score is the sum of the best 3 or 5 top scores.  " +
                "Each contest consists of two parts: a written section (called shorts) and a programming section.  " +
                "Written topics tested include what does this program do, digital electronics, Boolean algebra, computer numbering systems, recursive functions, data structures (primarily dealing with heaps, binary search trees, stacks, and queues), Lisp programming, regular expressions and Finite State Automata, bit string flicking, graph theory, assembly programming and prefix/postfix/infix notation.",
                "1.1.1 1.5.3 1.5.7 1.10.5 1.10.9 1.12.6 1.16.3 1.13.11 9.1.1 2.18.1 1.13.2 1.15.1 1.13.4 2.2.3 1.11.6 1.7.2 2.1.2 1.19.1 2.19.1 1.12.2 1.12.3 1.7.6 1.13.11 2.10.2 1.12.4 2.3.3 1.7.1 1.5.7 1.10.7 2.17.3 2.11.5 1.7.6 4.8.5 1.4.1 2.9.1 2.19.1 1.7.1 4.7.8 5.3.7 1.18.7 5.5.3 2.2.1 1.7.5 1.9.2"
        };
        input[6] = new String[]{
                "We hold these truths to be self-evident, that all men are created equal, that they are endowed by their Creator with certain unalienable Rights, that among these are Life, Liberty and the pursuit of Happiness.  " +
                "That to secure these rights, Governments are instituted among Men, deriving their just powers from the consent of the governed...",
                "1.30.1 1.20.4 1.8.2 2.12.3 3.1.1 1.14.5 1.25.2 1.35.2 1.6.2 3.4.5 1.36.3 1.24.1 1.17.2 1.28.4 2.8.7 1.8.5 1.37.1 1.22.4 1.23.5 1.36.3 1.36.4 1.23.6 1.24.2 1.8.1 1.36.8 1.36.9 2.21.1 1.31.3 1.7.2 2.2.3 1.35.2 1.17.2 1.7.2 1.8.1"
        };
        input[7] = new String[]{
                "The upheavals [of Artificial Intelligence] can escalate quickly and become scarier and even cataclysmic.  " +
                "Imagine how a medical robot, originally programmed to rid cancer, could conclude that the best way to obliterate cancer is to exterminate humans who are genetically prone to the disease.",
                "1.4.1 2.1.1 3.5.2 2.6.3 1.2.9 1.15.6 2.2.1 2.7.9 1.11.7 2.30.7 2.31.5 1.7.7 1.3.1 3.2.1 1.2.9 1.14.3 2.3.1 2.16.3"
        };
        input[8] = new String[]{
                "I'm increasingly inclined to think that there should be some regulatory oversight, maybe at the national and international level, just to make sure that we don't do something very foolish.  " +
                "I mean with artificial intelligence we're summoning the demon.  " +
                "This was said by Elon Musk at MIT's AeroAstro Centennial Symposium.",
                "3.10.1 2.1.1 4.1.2 1.4.1 3.9.1 1.33.3 1.8.5 1.31.1 3.10.2 1.4.6 2.11.1 3.3.1 2.4.7 3.7.1 2.7.1 3.4.2 2.11.7 1.32.1 2.10.4 3.10.8 1.2.2 1.1.1 3.1.1 3.9.2 2.5.8 3.11.5 1.17.1 3.1.3 1.12.4 3.6.3 2.5.12 1.11.1"
        };
        input[9] = new String[]{
                "The quadratic equation x^2-6x-7=0 can be solved by factoring it into (x-7)(x+1)=0.  " +
                "By setting x-7=0 or x+1=0, the result is that x = 7 or x =-1.  " +
                "However, if 3x^2-5x+8=0, factoring is not possible because 25-4*3*8<0 so the roots of this equation are 2 imaginary numbers.  " +
                "In the 1st case, the function crosses the x-axis at 7 and-1.  " +
                "In the 2nd case, the graph is completely above the x-axis!",
                "1.1.1 2.6.2 5.8.10 5.13.5 3.6.1 1.6.2 2.9.2 1.11.1 1.2.1 4.6.2 5.4.2 1.2.5 5.2.3 4.13.3 1.4.2 1.15.1 4.4.3 6.1.1 4.3.1 1.7.1 3.17.1"
        };

        String[] output = {
                "python 3",
                "Boolean is always True",
                "Java is the language of AP CS",
                "No 1 speech of all time",
                "what are you",
                "American Computer Science League ACSL is fun",
                "Live life pursue happiness be free",
                "AI is here to stay",
                "AI is even scary for IT geniuses",
                "Try 8x squared is 170"
        };

        for (int i = 0; i < 10; i++)
        {
            String result = decodeMessage(input[i][0], input[i][1]);

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