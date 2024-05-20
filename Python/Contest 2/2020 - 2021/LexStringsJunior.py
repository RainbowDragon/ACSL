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
def rearranged_string(s):

    char_count = [0] * 26
    max_count = 0
    for k in range(len(s)):
        index = get_index_of_char(s[k])
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


def get_index_of_char(c):

    c = c.upper()
    if c.isalpha():
        return ord(c) - ord('A')
    else:
        return -1


test_input = [
    "A good sorting algorithm.",
    "Tennessee is the volunteer state.",
    "Einstein was a genius.",
    "Tom Sawyer & the Mississippi River",
    "She sells seashells by the seashore.",
    "Peter Piper picked a peck of pickled peppers.",
    "Computer Science Teachers Association had a virtual Conference in 2020.",
    "HackerRank.com was used for the ACSL Finals this past year.",
    "Programming languages include Java, Python, C++, BASIC, and Scratch.",
    "COVID-19 is a global pandemic and a virus that changed everything."
]

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
    "abcdeghilmnoprstuvyacdeghilnorstvacdeghintvadeinaia"
]

for i in range(10):
    test_result = rearranged_string(test_input[i])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
