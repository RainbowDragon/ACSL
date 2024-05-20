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
def find_last_binary(s):

    concatenated_str = ""
    for k in range(len(s)):
        binary_str = get_binary_string_for_char(s[k])
        concatenated_str += binary_str

    number = 0
    found = False
    while not found:
        number_str = get_binary_string_for_int(number)
        if number_str in concatenated_str:
            number += 1
        else:
            number -= 1
            found = True

    return number


def get_binary_string_for_char(c):

    char_str = get_binary_string_for_int(ord(c))
    len_char_str = len(char_str)
    if len_char_str < 8:
        char_str = ("0" * (8 - len_char_str)) + char_str

    return char_str


def get_binary_string_for_int(num):

    return str(bin(num)[2:])


test_input = [
    "Roses are red.",
    "A is Alpha; B is Bravo; C is Charlie.",
    "A stitch in time saves nine.",
    "1, 2: Buckle my shoe! 3, 4: Shut the door!",
    "Is HackerRank the platform used by ACSL?",
    "What was the first computer programming language you learned?",
    "Lions and Tigers and Bears, Oh My! This is from The Wizard of Oz.",
    "Knock, knock. Who is there? Hawaii. Hawaii who? I am good, Hawaii you?",
    "How do you use HackerRank to do each ACSL competition?",
    "~{w|x|y|z}"
]

test_output = [16, 20, 14, 30, 61, 61, 30, 64, 33, 20]

for i in range(10):
    test_result = find_last_binary(test_input[i])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
