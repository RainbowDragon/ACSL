#
# ACSL 2022-2023 - Contest 2 - Binary Counting - Junior Division
#
#

#
# Complete the 'findLastBinary' function below.
#
# The function is expected to return an INTEGER.
# The function accepts STRING s as parameter.
#
def findLastBinary(s):

    concatenated_str = ""
    for k in range(len(s)):
        binary_str = getBinaryStringForChar(s[k])
        concatenated_str += binary_str

    number = 0
    found = False
    while not found:
        number_str = getBinaryStringForInt(number)
        if number_str in concatenated_str:
            number += 1
        else:
            number -= 1
            found = True

    return number


def getBinaryStringForChar(c):

    char_str = getBinaryStringForInt(ord(c))
    len_char_str = len(char_str)
    if len_char_str < 8:
        char_str = ("0" * (8 - len_char_str)) + char_str

    return char_str


def getBinaryStringForInt(num):

    return str(bin(num)[2:])


test_input = [""] * 10
test_input[0] = "Roses are red."
test_input[1] = "A is Alpha; B is Bravo; C is Charlie."
test_input[2] = "A stitch in time saves nine."
test_input[3] = "1, 2: Buckle my shoe! 3, 4: Shut the door!"
test_input[4] = "Is HackerRank the platform used by ACSL?"
test_input[5] = "What was the first computer programming language you learned?"
test_input[6] = "Lions and Tigers and Bears, Oh My! This is from The Wizard of Oz."
test_input[7] = "Knock, knock. Who is there? Hawaii. Hawaii who? I am good, Hawaii you?"
test_input[8] = "How do you use HackerRank to do each ACSL competition?"
test_input[9] = "~{w|x|y|z}"

test_output = [16, 20, 14, 30, 61, 61, 30, 64, 33, 20]

for i in range(10):
    test_result = findLastBinary(test_input[i])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
