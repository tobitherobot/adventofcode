with open("y19/r03.txt") as f:
    inp = [[x.strip() for x in f.readline().split(',')],[x.strip() for x in f.readline().split(',')]]

# star 1
inters = []
points = []
pos = [0,0]
for instr in inp[0]:
    val = int(instr[1:])
    if instr[0] == 'U':
        for i in range(val):
            pos[1] -= 1
            points.append(pos[:])
    elif instr[0] == 'D':
        for i in range(val):
            pos[1] += 1
            points.append(pos[:])
    elif instr[0] == 'R':
        for i in range(val):
            pos[0] += 1
            points.append(pos[:])
    else: # left
        for i in range(val):
            pos[0] -= 1
            points.append(pos[:])

pos = [0,0]
for instr in inp[1]:
    val = int(instr[1:])
    print(pos)
    if instr[0] == 'U':
        for i in range(val):
            pos[1] -= 1
            if pos not in points:
                inters.append(pos[:])
    elif instr[0] == 'D':
        for i in range(val):
            pos[1] += 1
            if pos not in points:
                inters.append(pos[:])
    elif instr[0] == 'R':
        for i in range(val):
            pos[0] += 1
            if pos not in points:
                inters.append(pos[:])
    else: # left
        for i in range(val):
            pos[0] -= 1
            if pos not in points:
                inters.append(pos[:])
print(inters)