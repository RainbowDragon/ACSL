#
# ACSL 2019-2020 - Contest 2 - String Differences - Junior Division
#
#

def string_differences(s1, s2):

    s1 = delete_double(s1)
    s2 = delete_double(s2)

    s1 = delete_vowels(s1)
    s2 = delete_vowels(s2)
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


def delete_double(s):

    str_single = ""
    last = ' '
    for j in range(len(s)):
        if last != s[j]:
            str_single += s[j]
            last = s[j]

    return str_single


def delete_vowels(s):

    str_non_vowel = ""
    for j in range(len(s)):
        if j == 0:
            str_non_vowel += s[j]
        elif not s[j] in "AEIOU":
            str_non_vowel += s[j]

    return str_non_vowel


test_input = [
    ["MISSISSIPPI", "MISSOURI"],
    ["CATHERINE", "KATHERYNE"],
    ["CONSTITUTIONAL", "CONVENTIONAL"],
    ["COMPARTMENTALIZATION", "SEMIAUTOBIOGRAPHICAL"],
    ["PHYSICIAN", "PHARMACY"],
    ["FEEFIFOFUM", "FIDDLEDEEDEE"],
    ["MYLOLLIPOPS", "MYLARBALLOONS"],
    ["CONNECTICUTCT", "CONSTITUTIONSTATE"],
    ["MASSACHUSETTSBAYCOLONY", "MINUTEMANNATIONALHISTORICALPARK"],
    ["AMERICANCOMPUTERSCIENCELEAGUE", "NATIONALACADEMICGAMESLEAGUE"]
]

test_output = ["R", "C", "VN", "SBGRPHCL", "RMY", "DLDD", "LPP", "CCC", "SCSBYCLNY", "NTNLCDGM"]

for i in range(10):
    test_result = string_differences(test_input[i][0], test_input[i][1])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
