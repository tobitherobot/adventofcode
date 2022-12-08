with open('y16/r03.txt') as f:
    inp = [[int(x) for x in ' '.join(l.strip().split()).split(' ')] for l in f.readlines()]

# star 1
count = 0
for l in inp:
    count += 1 if (l[0]<l[1]+l[2]) and (l[1]<l[0]+l[2]) and (l[2]<l[0]+l[1]) else 0
print(count)

# star 2
count = 0
for x in range(3):
    for i in range(0, len(inp), 3):
        count += 1 if (inp[i][x]<inp[i+1][x]+inp[i+2][x]) and (inp[i+1][x]<inp[i][x]+inp[i+2][x]) and (inp[i+2][x]<inp[i][x]+inp[i+1][x]) else 0
print(count)