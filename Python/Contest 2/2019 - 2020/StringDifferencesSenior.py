#
# ACSL 2019-2020 - Contest 2 - String Differences - Senior Division
#
#

def difference_factor(s1, s2):

    s1 = get_upper_case_string(s1)
    s2 = get_upper_case_string(s2)

    return get_difference_factor(s1, s2)


def get_difference_factor(s1, s2):

    str_common = get_longest_common_substring(s1, s2)
    len_common = len(str_common)

    if len_common == 0:
        return 0
    else:
        i1 = s1.index(str_common)
        i2 = s2.index(str_common)

        left_count = get_difference_factor(s1[0:i1], s2[0:i2])
        right_count = get_difference_factor(s1[i1+len_common:], s2[i2+len_common:])

        return left_count + len_common + right_count


def get_longest_common_substring(s1, s2):

    str_longest_common = ""

    for x in range(len(s1)):
        for y in range(x+1, len(s1)+1):
            str_sub = s1[x:y]
            if str_sub in s2:
                if len(str_sub) > len(str_longest_common):
                    str_longest_common = str_sub
                elif len(str_sub) == len(str_longest_common) and str_sub < str_longest_common:
                    str_longest_common = str_sub

    return str_longest_common


def get_upper_case_string(s):

    str_upper = ""

    s = s.upper()
    for j in range(len(s)):
        if s[j].isalpha():
            str_upper += s[j]

    return str_upper


test_input = [
    [
        "I am going home now",
        "I will go home now"
    ],
    [
        "The big black bear bit a big black bug",
        "The big black bug bled black blood"
    ],
    [
        "Complementary angle measures sum to 90 degrees.",
        "The measures of supplementary angles add to 180 degrees."
    ],
    [
        "A Tale of Two Cities was published by Dickens in 1859.",
        "In 1839, Charles Dickens published Nicholas Nickleby."
    ],
    [
        "Connecticut is The Constitution State.",
        "Hartford is the capital of Connecticut."
    ],
    [
        "To be or not to be, that is the question.",
        "To err is human; to really foul things up requires a computer."
    ],
    [
        "The Pythagorean Theorem says that the sum of the squares of the two legs equals the square of the hypotenuse.",
        "To find a leg using the Pythagorean Theorem, take the square root of the hypotenuse squared minus the other leg squared."
    ],
    [
        "Uncle Tom's Cabin was published by Harriet Beecher Stowe in 1852.",
        "In 1876, Mark Twain published The Adventures of Tom Sawyer."
    ],
    [
        "Once upon a midnight dreary while I pondered weak and weary,",
        "Over many a quaint and curious volume of forgotten lore."
    ],
    [
        "A tutor who tooted the flute tried to tutor two tooters to toot!",
        "Is it harder to toot or to tutor two tooters to toot?"
    ]
]

test_output = [10, 19, 26, 18, 11, 14, 50, 20, 9, 31]

for i in range(10):
    test_result = difference_factor(test_input[i][0], test_input[i][1])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
