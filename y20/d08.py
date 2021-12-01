with open("y20/r08.txt", "r") as f:
    input = [x.strip().split(" ") for x in f.readlines()]

# star 1
visited = set()
acc = 0
pos = 0
while pos not in visited:
    visited.add(pos)
    if input[pos][0]=='acc':
        acc += int(input[pos][1])
        pos += 1
    elif input[pos][0]=='jmp':
        pos += int(input[pos][1])
    else:
        pos += 1
print(acc)

# star 2
for i in range(0, len(input)):
    copy = input.copy()
    if copy[i][0]=='jmp':
        copy[i] = ['nop', copy[i][1]]
    elif copy[i][0]=='nop':
        copy[i] = ['jmp', copy[i][1]]
    else:
        continue
    
    visited = set()
    acc = 0
    pos = 0
    while pos not in visited and pos!=len(input):
        visited.add(pos)
        if copy[pos][0]=='acc':
            acc += int(copy[pos][1])
            pos += 1
        elif copy[pos][0]=='jmp':
            pos += int(copy[pos][1])
        else:
            pos += 1
    
    if pos==len(input):
        print(acc)
        break

