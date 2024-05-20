#
# ACSL 2019-2020 - Contest 2 - String Differences - Intermediate Division
#
#

def sameness_factor(s1, s2):

    done = False

    while not done:
        done = True

        str1 = ""
        str2 = ""

        min_len = min(len(s1), len(s2))
        for k in range(min_len):
            if s1[k] != s2[k]:
                str1 += s1[k]
                str2 += s2[k]
            else:
                done = False

        s1 = str1 + s1[min_len:]
        s2 = str2 + s2[min_len:]

        min_len = min(len(s1), len(s2))
        for k in range(min_len):
            if k + 1 < len(s2) and s1[k] == s2[k+1]:
                s2 = s2[0:k] + s2[k+1:]
                done = False
                break
            elif k + 1 < len(s1) and s1[k+1] == s2[k]:
                s1 = s1[0:k] + s1[k+1:]
                done = False
                break

    result = 0
    min_len = min(len(s1), len(s2))
    for k in range(min_len):
        result += ord(s1[k]) - ord(s2[k])

    result += len(s1) + len(s2) - 2 * min_len

    return result


test_input = [
    ["BLAMEABLENESSES", "BLAMELESSNESSES"],
    ["MEZZAMINES", "RAZZMATAZZ"],
    ["ABBREVIATIONS", "ABBREVIATORS"],
    ["ABCDEFGHIJKLMNO", "ABKCLDZZHQJWWLX"],
    ["ABCDEFGHIJKL", "ABXEWFRRH"],
    ["MYARTLOLLIPOPS", "MYLARBALLOONS"],
    ["MASSACHUSETTSBAYCOLONY", "MINUTEMANNATIONALHISTORICALPARK"],
    ["LOWERMACTOWNSHIPPA", "CRANBERRYTOWNSHIPPA"],
    ["AMERICANCOMPUTERSCIENCELEAGUE", "NATIONALACADEMICGAMESLEAGUE"],
    ["ABCDEFGHIJK", "ABDCEFGKILKJMN"]
]

test_output = [
    -35, -5, -4, -86, -52, 23, 27, 11, 68, -9
]

for i in range(10):
    test_result = sameness_factor(test_input[i][0], test_input[i][1])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
