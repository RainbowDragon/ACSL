#
# ACSL 2022-2023 - Contest 3 - Create A Tree - Intermediate Division
#
#

#
# Complete the 'onlyLeftOrRight' function below.
#
# The function is expected to return a STRING.
# The function accepts STRING input as parameter.
#
def only_left_or_right(s):

    letters = []
    values = []

    for k in range(len(s)):
        if k == 0:
            letters.append(s[k])
            values.append(0)
        else:
            pos = 0
            for letter in letters:
                if ord(s[k]) > ord(letter):
                    pos += 1
            letters.insert(pos, s[k])

            index = letters.index(s[k])
            if index == 0:
                values.insert(index, values[0]+1)
            elif index == len(values):
                values.insert(index, values[index-1]+1)
            else:
                values.insert(index, max(values[index-1], values[index])+1)

    left = []
    right = []
    for k in range(len(s)):
        c = letters[k]
        v = values[k]

        find_left = False
        index = k - 1
        while index >= 0 and values[index] >= v:
            if values[index] == v + 1:
                find_left = True
                break
            index -= 1

        find_right = False
        index = k + 1
        while index < len(letters) and values[index] >= v:
            if values[index] == v + 1:
                find_right = True
                break
            index += 1

        if find_left and not find_right:
            left.append(c)
        if find_right and not find_left:
            right.append(c)

    if len(left) == 0:
        left.append("NONE")
    if len(right) == 0:
        right.append("NONE")

    return "".join(left) + " " + "".join(right)


test_input = [
    "PYTHONN", "BINARYSEARCHTREE", "CORONAVIRUS", "FINALSFORACSL", "HACKERRANKPLATFORM",
    "MOTHER", "ACSLCONTEST", "SUPERCALIFRAGILISTIC", "JAVAPROGRAMMING", "ABDFHKMOQTVWYZ"
]

test_output = [
    "NOY H", "AERY CNS", "NOUV NONE", "FLS IOR", "AR CEL",
    "HT O", "T A", "CIILU FG", "AORV NONE", "NONE ABDFHKMOQTVWY"
]

for i in range(10):
    test_result = only_left_or_right(test_input[i])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + test_output[i])
        print("Your output: " + test_result)
