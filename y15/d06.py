with open("y15/r06.txt") as f:
    inp = []
    for l in f.readlines():
        spl = l.split(' ')
        idx = 0
        if spl[0] == 'turn':
            idx += 1
        
        a = [int(x) for x in spl[idx+1].split(',')]
        b = [int(x) for x in spl[idx+3].split(',')]
        inp.append([spl[idx], a, b])

# star 1
lights = [[False] * 1000 for i in range(1000)]
for l in inp:
    if l[0] == 'on':
        for i in range(l[1][0], l[2][0]+1):
            for j in range(l[1][1], l[2][1]+1):
                lights[i][j] = True
    elif l[0] == 'off':
        for i in range(l[1][0], l[2][0]+1):
            for j in range(l[1][1], l[2][1]+1):
                lights[i][j] = False
    else:
        for i in range(l[1][0], l[2][0]+1):
            for j in range(l[1][1], l[2][1]+1):
                lights[i][j] = not lights[i][j]
count = 0
for i in range(1000):
    for j in range(1000):
        if lights[i][j]:
            count += 1
print(count)

# star 2
lights = [[0] * 1000 for i in range(1000)]
for l in inp:
    if l[0] == 'on':
        for i in range(l[1][0], l[2][0]+1):
            for j in range(l[1][1], l[2][1]+1):
                lights[i][j] += 1
    elif l[0] == 'off':
        for i in range(l[1][0], l[2][0]+1):
            for j in range(l[1][1], l[2][1]+1):
                lights[i][j] -= 1 if lights[i][j] > 0 else 0
    else:
        for i in range(l[1][0], l[2][0]+1):
            for j in range(l[1][1], l[2][1]+1):
                lights[i][j] += 2
bright = 0
for i in range(1000):
    for j in range(1000):
        bright += lights[i][j]
print(bright)