#
# ACSL 2022-2023 - Contest 3 - Create A Tree - Senior Division
#
#

#
# Complete the 'getTraversals' function below.
#
# The function is expected to return a STRING.
# The function accepts STRING input as parameter.
#
def get_traversals(s):

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

    left_str = preorder(letters, values, 0, len(s)-1, 0)
    right_str = postorder(letters, values, 0, len(s)-1, 0)

    return left_str + " " + right_str


def preorder(letters, values, left, right, level):

    found = False
    index = left
    while index <= right:
        if values[index] == level:
            found = True
            break
        index += 1

    if not found:
        return ""

    left_str = preorder(letters, values, left, index-1, level+1)
    right_str = preorder(letters, values, index+1, right, level+1)

    return letters[index] + left_str + right_str


def postorder(letters, values, left, right, level):
    found = False
    index = left
    while index <= right:
        if values[index] == level:
            found = True
            break
        index += 1

    if not found:
        return ""

    left_str = postorder(letters, values, left, index - 1, level + 1)
    right_str = postorder(letters, values, index + 1, right, level + 1)

    return left_str + right_str + letters[index]


test_input = [
    "PYTHONN", "BINARYSEARCHTREE", "CORONAVIRUS", "FINALSFORACSL", "HACKERRANKPLATFORM",
    "MOTHER", "ACSLCONTEST", "SUPERCALIFRAGILISTIC", "JAVAPROGRAMMING", "ABDFHKMOQTVWYZ"
]

test_output = [
    "PHONNYT NNOHTYP", "BAAIECEEHNRRRYST AAEECHERRTSYRNIB",
    "CAOONIRRVUS AINORSUVROC", "FAAFCINLLSORS ACFALLSROSNIF",
    "HAAACEFKKRRNLMPORT AAFECAKMLORPNRTRKH", "MHEOTR EHRTOM",
    "ACCSLEONSTT CENSOLTTSCA", "SPECAACLIFGIIILRRSUT ACACIIIGFLILERSRPTUS",
    "JAAAGGIVPOMMNRR AAGIGAMNMORRPVJ", "ABDFHKMOQTVWYZ ZYWVTQOMKHFDBA"
]

for i in range(10):
    test_result = get_traversals(test_input[i])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + test_output[i])
        print("Your output: " + test_result)
