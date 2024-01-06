#
# ACSL 2019-2020 - Contest 2 - String Differences - Junior Division
#
#

def stringDifferences(s1, s2):

    s1 = deleteDouble(s1)
    s2 = deleteDouble(s2)

    s1 = deleteVowels(s1)
    s2 = deleteVowels(s2)
    str_s1 = ""
    str_s2 = ""
    min_len = min(len(s1), len(s2))
    for k in range(min_len):
        if s1[k] != s2[k]:
            str_s1 += s1[k]
            str_s2 += s2[k]

    str_s1 += s1[min_len:]
    str_s2 += s2[min_len:]

    s1 = str_s1[::-1]
    s2 = str_s2[::-1]

    str_s1 = ""
    str_s2 = ""
    min_len = min(len(s1), len(s2))
    for k in range(min_len):
        if s1[k] != s2[k]:
            str_s1 += s1[k]
            str_s2 += s2[k]

    str_s1 += s1[min_len:]
    str_s2 += s2[min_len:]

    s1 = str_s1[::-1]
    s2 = str_s2[::-1]

    if len(s1) < len(s2):
        return s1
    elif len(s1) > len(s2):
        return s2
    else:
        if s1 < s2:
            return s1
        else:
            return s2


def deleteDouble(s):

    str_single = ""
    last = ' '
    for j in range(len(s)):
        if last != s[j]:
            str_single += s[j]
            last = s[j]

    return str_single


def deleteVowels(s):

    str_non_vowel = ""
    for j in range(len(s)):
        if j == 0:
            str_non_vowel += s[j]
        elif not s[j] in "AEIOU":
            str_non_vowel += s[j]

    return str_non_vowel


test_input = [[""] * 2] * 10
test_input[0] = ["MISSISSIPPI", "MISSOURI"]
test_input[1] = ["CATHERINE", "KATHERYNE"]
test_input[2] = ["CONSTITUTIONAL", "CONVENTIONAL"]
test_input[3] = ["COMPARTMENTALIZATION", "SEMIAUTOBIOGRAPHICAL"]
test_input[4] = ["PHYSICIAN", "PHARMACY"]
test_input[5] = ["FEEFIFOFUM", "FIDDLEDEEDEE"]
test_input[6] = ["MYLOLLIPOPS", "MYLARBALLOONS"]
test_input[7] = ["CONNECTICUTCT", "CONSTITUTIONSTATE"]
test_input[8] = ["MASSACHUSETTSBAYCOLONY", "MINUTEMANNATIONALHISTORICALPARK"]
test_input[9] = ["AMERICANCOMPUTERSCIENCELEAGUE", "NATIONALACADEMICGAMESLEAGUE"]

test_output = ["R", "C", "VN", "SBGRPHCL", "RMY", "DLDD", "LPP", "CCC", "SCSBYCLNY", "NTNLCDGM"]

for i in range(10):
    test_result = stringDifferences(test_input[i][0], test_input[i][1])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
