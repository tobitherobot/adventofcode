with open("y17/r01.txt") as f:
    input = f.readline()

# star 1
sum = 0
for i in range(len(input)):
    if input[i] == input[i-1]:
        sum += (ord(input[i]) - ord('0'))
print(sum)

# star 2
sum = 0
for i in range(len(input)):
    if input[i] == input[i - (len(input) // 2)]:
        sum += (ord(input[i]) - ord('0'))
print(sum)