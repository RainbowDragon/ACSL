/**
 *      ACSL 2021 Final - Problem 2 - Sentences - Junior / Intermediate Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class Sentences {

    /*
     * Complete the 'generate_sentences' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING_ARRAY dictionary
     *  2. STRING sentences
     */
    static String generate_sentences (List<String> dictionary, String sentences) {

        HashMap<Character, List<String>> map = new HashMap<>();
        HashMap<Character, Integer> index = new HashMap<>();
        for (int i = 0; i < dictionary.size(); i++)
        {
            String str = dictionary.get(i);
            char key = str.charAt(0);
            String[] words = str.substring(2).split(" ");
            map.put(key, Arrays.asList(words));
            index.put(key, 0);
        }

        String[] sentenceList = sentences.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sentenceList.length; i++)
        {
            StringBuilder ssb = new StringBuilder();
            String sentence = sentenceList[i];

            char first = sentence.charAt(0);
            if (first == 'Q') {
                ssb.append("What");
            }

            for (int j = 1; j < sentence.length(); j++)
            {
                char c = sentence.charAt(j);
                String word = "";
                if (c == 'T') {
                    word = "the";
                }
                else if (c == 'A') {
                    char next = sentence.charAt(j+1);
                    String nextWord = map.get(next).get(index.get(next));
                    if (startWithVowel(nextWord)) {
                        word = "an";
                    }
                    else {
                        word = "a";
                    }
                }
                else {
                    List<String> wordList = map.get(c);
                    int wordIndex = index.get(c);
                    word = wordList.get(wordIndex);
                    wordIndex = (wordIndex + 1) % wordList.size();
                    index.put(c, wordIndex);
                }

                if (ssb.length() > 0) {
                    ssb.append(" ");
                }
                ssb.append(word);
            }

            if (first == 'Q') {
                ssb.append("?");
            }
            else if (first == 'E') {
                ssb.append("!");
            }
            else {
                ssb.append(".");
            }

            String current = ssb.toString();
            current = current.substring(0, 1).toUpperCase() + current.substring(1);
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(current);
        }

        return sb.toString();
    }

    static boolean startWithVowel (String word) {
        char first = word.charAt(0);
        return first == 'a' || first == 'e' || first == 'i' || first == 'o' || first == 'u';
    }

    public static void main (String [] args) {

        String[][] input = {
                {
                        "N money tree sky",
                        "C grow fall",
                        "P on from",
                        "ICNPTN ICPTN"
                },
                {
                        "J ageless",
                        "N money tree",
                        "P on from",
                        "V grows falls",
                        "QNVPAJN"
                },
                {
                        "N money tree",
                        "B quickly",
                        "C take collect",
                        "V grows falls runs",
                        "J indigenous free",
                        "P on from",
                        "QNVPAN DNVPAJN ECTNPTNB ICTJNB"
                },
                {
                        "N dog skateboard oriole",
                        "J small brown orange big",
                        "V struck saw took",
                        "P on",
                        "B suddenly restlessly",
                        "ETJJNPTNVTJNB"
                },
                {
                        "V saw bit took",
                        "J small orange",
                        "B very restlessly",
                        "N dog fish oriole waves",
                        "C help",
                        "DTJNVTJNBB ICTN"
                },
                {
                        "N dog fish bird waves",
                        "C stop help fix",
                        "J angry small orange",
                        "ICTJN ICTJJN ICTJN"
                },
                {
                        "J big small",
                        "B suddenly tirelessly",
                        "N seed orange oriole desk",
                        "C stop code help fix",
                        "V fed saw bit",
                        "P in on to at of",
                        "QJNPTNVTJN ICB ECBPTJN"
                },
                {
                        "N apples trees birds clouds frogs",
                        "B freely happily very",
                        "J tall green",
                        "V grow fly",
                        "P on below upon above over",
                        "DNVPJJN QNVBPTN DNVB EVPN QNVBJ DNVBPJN"
                },
                {
                        "C jump catch",
                        "V struck saw sunk took",
                        "N dog fish oriole waves",
                        "J green small big orange",
                        "B nearly suddenly restlessly",
                        "P over above",
                        "EAJNBVAJNB QBVAN DTNBVTJN ECBPTN ICANBPTN"
                },
                {
                        "N dog fish oriole wave",
                        "C help catch ride",
                        "V caught saw bit took",
                        "J green small big orange",
                        "B suddenly slowly",
                        "P through on over",
                        "DANVAJN EANVAJN QVTNB ICTNCAJJN ECBPTJNPAN"
                },
                {
                        "N dog fish worm oriole",
                        "C stop code help fix",
                        "V bit saw",
                        "B suddenly restlessly",
                        "P on over through",
                        "J orange small big",
                        "ICAN QNVAJN DAJNVAJN DANVPAJN"
                },
                {
                        "V told",
                        "J big orange small",
                        "B suddenly restlessly",
                        "C code stop help fix",
                        "P on from about",
                        "N message man airplane",
                        "ICAN EC ICANPTJJN DANPTNVPAN"
                },
                {
                        "V stops",
                        "B quickly freely abruptly",
                        "C stop",
                        "ICB QV ECB IC QVB EC IC QV ECB IC"
                }
        };

        String[] output = {
                "Grow money on the tree. Fall from the sky.",
                "What money grows on an ageless tree?",
                "What money grows on a tree? Money falls from an indigenous tree. Take the money on the tree quickly! Collect the free money quickly.",
                "The small brown dog on the skateboard struck the orange oriole suddenly!",
                "The small dog saw the orange fish very restlessly. Help the oriole.",
                "Stop the angry dog. Help the small orange fish. Fix the angry bird.",
                "What big seed in the orange fed the small oriole? Stop suddenly. Code tirelessly on the big desk!",
                "Apples grow on tall green trees. What birds fly freely below the clouds? Frogs grow happily. Fly upon apples! What trees grow very tall? Birds fly freely above green clouds.",
                "A green dog nearly struck a small fish suddenly! What restlessly saw an oriole? The waves nearly sunk the big dog. Jump suddenly over the fish! Catch an oriole restlessly above the waves.",
                "A dog caught a green fish. An oriole saw a small wave! What bit the dog suddenly? Help the fish catch a big orange oriole. Ride slowly through the green wave on a dog!",
                "Stop a dog. What fish bit an orange worm? A small oriole saw a big dog. A fish bit on an orange worm.",
                "Code a message. Stop! Help a man on the big orange airplane. A message from the man told about an airplane.",
                "Stop quickly. What stops? Stop freely! Stop. What stops abruptly? Stop! Stop. What stops? Stop quickly! Stop."
        };

        for (int i = 0; i < 13; i++)
        {
            String result = generate_sentences(
                    Arrays.asList(Arrays.copyOfRange(input[i], 0, input[i].length-1)),
                    input[i][input[i].length-1]);

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