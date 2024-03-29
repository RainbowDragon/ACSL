#
# ACSL 2020-2021 - Contest 2 - Lex Strings - Senior Division
#
#

#
# Complete the 'rearrangedString' function below.
#
# The function is expected to return a STRING.
# The function accepts following parameters:
#  1. STRING s
#
def rearranged_string(s):

    char_count = [0] * 128
    max_count = 0
    for k in range(len(s)):
        index = get_index_of_char(s[k])
        if index != -1:
            char_count[index] += 1
            max_count = max(max_count, char_count[index])

    blocks = [""] * (max_count + 1)
    for k in range(128):
        if char_count[k] > 0:
            blocks[char_count[k]] += chr(k)

    result = ""
    sign = 1
    is_first = True
    for k in reversed(range(max_count + 1)):
        if blocks[k] != "":
            if not is_first:
                result += ","

            result += str(k)

            if sign > 0:
                result += blocks[k]
            else:
                result += blocks[k][::-1]

            is_first = False
            sign *= -1

    return result


def get_index_of_char(c):

    if c.isalnum():
        return ord(c)
    else:
        return -1


test_input = [""] * 10
test_input[0] = "This is an Example of Sorting an interesting string"
test_input[1] = "HackerRank.com was used for the ACSL Finals this year."
test_input[2] = "The digits of PI are 3.141592653."
test_input[3] = "She sells seashells by the seashore."
test_input[4] = "Programming languages include Java, Python, C++, Visual BASIC, Ruby, and Scratch."
test_input[5] = "COVID-19 is a global pandemic and a virus that changed everything in the entire world."
test_input[6] = "The Computer Science Teacher Association had a virtual Conference in 2020."
test_input[7] = "The digits of PI are 3.14159265358979323846264778327, not rounded."
test_input[8] = "Peter Piper picked a peck of pickled peppers. How many pickled peppers did Peter Piper pick?"
test_input[9] = "There are 10 kinds of people: those who know binary and those who don't."

test_output = [
    "6in,4ts,3aegr,2o,1ESTfhlmpx",
    "5a,4se,3r,2tonkihc,1ACFHLRSdflmuwy",
    "2135ei,1tsrohgfdaTPI9642",
    "7es,4lh,2a,1ytrobS",
    "8a,5n,4gu,3rlic,2CPSdehmosty,1vbVRJIBA",
    "7ae,6ni,5t,4rhd,3gl,2vsoc,119CDIOVbmpuwy",
    "9e,5nica,4or,3th,202CTsu,1vpmlfdSA",
    "53,472,345689deo,2trni1,1IPTafghsu",
    "14e,13p,7i,6r,5cdk,4P,2alost,1ywnmfH",
    "8o,7e,5hn,3wtsrda,2ikp,1ylfbT10"
]

for i in range(10):
    test_result = rearranged_string(test_input[i])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
