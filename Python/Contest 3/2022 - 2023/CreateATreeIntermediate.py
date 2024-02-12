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
def onlyLeftOrRight(s):

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


test_input = [""] * 10
test_input[0] = "PYTHONN"
test_input[1] = "BINARYSEARCHTREE"
test_input[2] = "CORONAVIRUS"
test_input[3] = "FINALSFORACSL"
test_input[4] = "HACKERRANKPLATFORM"
test_input[5] = "MOTHER"
test_input[6] = "ACSLCONTEST"
test_input[7] = "SUPERCALIFRAGILISTIC"
test_input[8] = "JAVAPROGRAMMING"
test_input[9] = "ABDFHKMOQTVWYZ"

test_output = [""] * 10
test_output[0] = "NOY H"
test_output[1] = "AERY CNS"
test_output[2] = "NOUV NONE"
test_output[3] = "FLS IOR"
test_output[4] = "AR CEL"
test_output[5] = "HT O"
test_output[6] = "T A"
test_output[7] = "CIILU FG"
test_output[8] = "AORV NONE"
test_output[9] = "NONE ABDFHKMOQTVWY"

for i in range(10):
    test_result = onlyLeftOrRight(test_input[i])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + test_output[i])
        print("Your output: " + test_result)
