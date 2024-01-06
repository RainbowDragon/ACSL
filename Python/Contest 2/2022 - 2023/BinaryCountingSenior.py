#
# ACSL 2022-2023 - Contest 2 - Binary Counting - Senior Division
#
#

#
# Complete the 'findLastOctal' function below.
#
# The function is expected to return an INTEGER.
# The function accepts STRING s as parameter.
#
def findLastOctal(s):

    concatenated_str = ""
    for k in range(len(s)):
        binary_str = getBinaryStringForChar(s[k])
        concatenated_str += binary_str

    number = 0
    found = False
    while not found:
        number_str = getBinaryStringForInt(number)
        len_number_str = len(number_str)

        first = concatenated_str.find(number_str)
        if first != -1:
            concatenated_str = concatenated_str[0:first] + concatenated_str[(first+len_number_str):]

        last = concatenated_str.rfind(number_str)
        if last != -1:
            concatenated_str = concatenated_str[0:last] + concatenated_str[(last+len_number_str):]

        if first == -1 and last == -1:
            found = True
        else:
            number += 1

    concatenated_str = getOctalStringForInt(int(concatenated_str, 2))

    number = 0
    found = False
    while not found:
        number_str = getOctalStringForInt(number)
        len_number_str = len(number_str)

        first = concatenated_str.find(number_str)
        if first != -1:
            concatenated_str = concatenated_str[0:first] + concatenated_str[(first+len_number_str):]

        last = concatenated_str.rfind(number_str)
        if last != -1:
            concatenated_str = concatenated_str[0:last] + concatenated_str[(last+len_number_str):]

        if first == -1 and last == -1:
            number -= 1
            found = True
        else:
            number += 1

    return number


def getBinaryStringForChar(c):

    char_str = getBinaryStringForInt(ord(c))
    len_char_str = len(char_str)
    if len_char_str < 8:
        char_str = ("0" * (8 - len_char_str)) + char_str

    return char_str


def getBinaryStringForInt(num):

    return str(bin(num)[2:])


def getOctalStringForInt(num):

    return str(oct(num)[2:])


test_input = [""] * 10
test_input[0] = "Roses are red."
test_input[1] = "A is for Alpha; B is for Bravo; C is for Charlie."
test_input[2] = "A stitch in time saves nine."
test_input[3] = "1, 2: Buckle my shoe! 3, 4: Shut the door!"
test_input[4] = "The quick brown fox jumped over the lazy dogs."
test_input[5] = "ACSL is 45 years old and going strong."
test_input[6] = "What was the first computer programming language you learned?"
test_input[7] = "Lions and Tigers and Bears, Oh My! This is from The Wizard of Oz."
test_input[8] = "zyxwvutsrqponmlkjihgfedcba"
test_input[9] = "~{w|x|y|z}"

test_output = [4, 9, 8, 6, 5, 6, 9, 9, 2, -1]

for i in range(10):
    test_result = findLastOctal(test_input[i])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
