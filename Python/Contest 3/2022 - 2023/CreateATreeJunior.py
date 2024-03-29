#
# ACSL 2022-2023 - Contest 3 - Create A Tree - Junior Division
#
#

#
# Complete the 'listByValue' function below.
#
# The function is expected to return a STRING.
# The function accepts STRING input as parameter.
#
def list_by_value(s):

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

    zip_list = zip(values, letters)
    sort_list = sorted(zip_list)

    result = ""
    for item in sort_list:
        result += item[1]

    return result


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
test_output[0] = "PHYOTNN"
test_output[1] = "BAIAENCHRERYERST"
test_output[2] = "CAOORNRVIUS"
test_output[3] = "FAIAFNCLSLORS"
test_output[4] = "HAKACKRAERTFNLPMOR"
test_output[5] = "MHOETR"
test_output[6] = "ACCSLTEOTNS"
test_output[7] = "SPUERTCLRSAIACFLGIII"
test_output[8] = "JAVAGPAGIORMRMN"
test_output[9] = "ABDFHKMOQTVWYZ"

for i in range(10):
    test_result = list_by_value(test_input[i])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + test_output[i])
        print("Your output: " + test_result)
