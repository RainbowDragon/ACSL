#
# ACSL 2021-2022 - Contest 2 - Fibonacci Cypher - Junior Division
#
#

#
# Complete the 'fibCypher' function below.
#
# The function is expected to return a STRING.
# The function accepts following parameters:
# 1. CHARACTER key
# 2. STRING msg
#
def fibCypher(key, msg):

    n = len(msg)

    fib_numbers = [0] * n
    fib_numbers[0] = 1
    fib_numbers[1] = 2
    for j in range(n-2):
        fib_numbers[j+2] = fib_numbers[j+1] + fib_numbers[j]

    result = ""
    for k in range(n):
        offset = (fib_numbers[k] + ord(key) - ord('a')) % 26 + ord('a')
        encoded = offset + ord(msg[k])
        result += str(encoded)
        if k < n - 1:
            result += " "

    return result


test_input = [[""] * 2] * 10
test_input[0] = ["h", "ACSL c2"]
test_input[1] = ["s", "Python Programming"]
test_input[2] = ["a", "Fibonacci Numbers"]
test_input[3] = ["j", "Help ME!"]
test_input[4] = ["z", "It is 9:30 in the morning."]
test_input[5] = ["h", "ACSL Contest 2"]
test_input[6] = ["b", "Madam, I'm Adam!"]
test_input[7] = ["a", "Java programs are the best."]
test_input[8] = ["d", "What are we really doing?"]
test_input[9] = ["g", "This is the Fibonacci Cypher!"]

test_output = [
    "170 173 190 185 144 216 149",
    "196 238 234 224 208 212 142 177 232 211 206 228 199 210 223 205 209 213",
    "168 204 198 213 215 207 217 204 205 140 189 239 219 207 223 222 222",
    "179 209 217 223 146 196 170 147",
    "170 214 131 206 219 141 174 162 150 155 142 226 219 140 237 211 207 149 210 233 215 211 211 221 224 156",
    "170 173 190 185 144 184 210 222 223 216 233 219 149 166",
    "176 197 201 200 215 155 151 179 140 218 144 162 211 207 206 142",
    "172 196 218 199 137 222 232 216 203 222 208 231 225 141 219 222 208 150 218 201 203 134 205 213 237 227 156",
    "188 206 200 221 140 210 235 209 135 230 215 131 227 213 196 219 218 242 137 200 216 210 220 218 162",
    "188 209 211 223 143 221 213 143 222 218 218 134 186 220 200 225 223 195 207 202 213 140 180 239 214 221 217 218 150"]

for i in range(10):
    test_result = fibCypher(test_input[i][0][0], test_input[i][1])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
