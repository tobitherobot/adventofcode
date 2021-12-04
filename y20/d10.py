with open("y20/r10.txt", "r") as f:
    input = [int(x.strip()) for x in f.readlines()]

input.append(0)
input.sort()
input.append(input[-1]+3)

# star 1
diff1 = 0
diff3 = 0
for i in range(len(input)-1):
    if input[i+1]-input[i]==1:
        diff1 += 1
    elif input[i+1]-input[i]==3:
        diff3 += 1
print(diff1*diff3)

# star 2
count = {}
count[input[-1]] = 1
for i in range(len(input)-2, -1, -1):
    c = 0
    if input[i]+1 in count:
        c += count[input[i]+1]
    if input[i]+2 in count:
        c += count[input[i]+2]
    if input[i]+3 in count:
        c += count[input[i]+3]
    count[input[i]] = c
print(count[0])