with open("y21/r01.txt", "r") as f:
    input = [int(x.strip()) for x in f.readlines()]

# star 1
count = 0
for i in range(1, len(input)):
    if int(input[i-1])<int(input[i]):
        count += 1
print(count)

# star 2
count = 0
for i in range(1, len(input)-2):
    if sum([int(input[x]) for x in range(i-1,i+2)]) < sum([int(input[x]) for x in range(i,i+3)]):
        count += 1
print(count)
