#
# ACSL 2020-2021 - Contest 2 - Lex Strings - Junior Division
#
#

#
# Complete the 'rearrangedString' function below.
#
# The function is expected to return a STRING.
# The function accepts STRING s as parameter.
#
def rearrangedString(s):

    char_count = [0] * 26
    max_count = 0
    for k in range(len(s)):
        index = getIndexOfChar(s[k])
        if index != -1:
            char_count[index] += 1
            max_count = max(max_count, char_count[index])

    result = ""
    last_char = ' '
    for j in range(max_count):
        for k in range(26):
            if char_count[k] > j:
                cur_char = chr(k + ord('a'))
                if cur_char != last_char:
                    result += cur_char
                    last_char = cur_char

    return result


def getIndexOfChar(c):

    c = c.upper()
    if c.isalpha():
        return ord(c) - ord('A')
    else:
        return -1


test_input = [""] * 10
test_input[0] = "A good sorting algorithm."
test_input[1] = "Tennessee is the volunteer state."
test_input[2] = "Einstein was a genius."
test_input[3] = "Tom Sawyer & the Mississippi River"
test_input[4] = "She sells seashells by the seashore."
test_input[5] = "Peter Piper picked a peck of pickled peppers."
test_input[6] = "Computer Science Teachers Association had a virtual Conference in 2020."
test_input[7] = "HackerRank.com was used for the ACSL Finals this past year."
test_input[8] = "Programming languages include Java, Python, C++, BASIC, and Scratch."
test_input[9] = "COVID-19 is a global pandemic and a virus that changed everything."

test_output = [
    "adghilmnorstagiortgo",
    "aehilnorstuvenstenstestete",
    "aeginstuwaeinseins",
    "aehimoprstvwyeimprsteirsisis",
    "abehlorstyaehlsehlsehlseseses",
    "acdefikloprstcdeikprceikprepepepepep",
    "acdefhilmnoprstuvacehinorstuaceinorstaceinorstaceinacece",
    "acdefhiklmnoprstuwyacefhiklnorstacehrstaersasasa",
    "abcdeghijlmnoprstuvyacdeghilmnoprstuacginrsacgnacna",
    "abcdeghilmnoprstuvyacdeghilnorstvacdeghintvadeinaia"]

for i in range(10):
    test_result = rearrangedString(test_input[i])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
