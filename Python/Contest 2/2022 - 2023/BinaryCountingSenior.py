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
def find_last_octal(s):

    concatenated_str = ""
    for k in range(len(s)):
        binary_str = get_binary_string_for_char(s[k])
        concatenated_str += binary_str

    number = 0
    found = False
    while not found:
        number_str = get_binary_string_for_int(number)
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

    concatenated_str = get_octal_string_for_int(int(concatenated_str, 2))

    number = 0
    found = False
    while not found:
        number_str = get_octal_string_for_int(number)
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


def get_binary_string_for_char(c):

    char_str = get_binary_string_for_int(ord(c))
    len_char_str = len(char_str)
    if len_char_str < 8:
        char_str = ("0" * (8 - len_char_str)) + char_str

    return char_str


def get_binary_string_for_int(num):

    return str(bin(num)[2:])


def get_octal_string_for_int(num):

    return str(oct(num)[2:])


test_input = [
    "Roses are red.",
    "A is for Alpha; B is for Bravo; C is for Charlie.",
    "A stitch in time saves nine.",
    "1, 2: Buckle my shoe! 3, 4: Shut the door!",
    "The quick brown fox jumped over the lazy dogs.",
    "ACSL is 45 years old and going strong.",
    "What was the first computer programming language you learned?",
    "Lions and Tigers and Bears, Oh My! This is from The Wizard of Oz.",
    "zyxwvutsrqponmlkjihgfedcba",
    "~{w|x|y|z}"
]

test_output = [
    4, 9, 8, 6, 5, 6, 9, 9, 2, -1
]

for i in range(10):
    test_result = find_last_octal(test_input[i])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
