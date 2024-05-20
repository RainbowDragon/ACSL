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


test_input = [
    "PYTHONN", "BINARYSEARCHTREE", "CORONAVIRUS", "FINALSFORACSL", "HACKERRANKPLATFORM",
    "MOTHER", "ACSLCONTEST", "SUPERCALIFRAGILISTIC", "JAVAPROGRAMMING", "ABDFHKMOQTVWYZ"
]

test_output = [
    "PHYOTNN", "BAIAENCHRERYERST", "CAOORNRVIUS", "FAIAFNCLSLORS", "HAKACKRAERTFNLPMOR",
    "MHOETR", "ACCSLTEOTNS", "SPUERTCLRSAIACFLGIII", "JAVAGPAGIORMRMN", "ABDFHKMOQTVWYZ"
]

for i in range(10):
    test_result = list_by_value(test_input[i])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + test_output[i])
        print("Your output: " + test_result)
