with open('y16/r01.txt') as f:
    inp = f.readline().strip().split(', ')

# star 1
pos = [0,0]
facing = 0  # n=0 o=1 s=2 w=3
for step in inp:
    val = int(step[1:])
    if step[0] == 'L':
        facing = (facing + 3) % 4
    else:
        facing = (facing + 1) % 4
    if facing == 0:
        pos[1] += val
    elif facing == 1:
        pos[0] += val
    elif facing == 2:
        pos[1] -= val
    else:
        pos[0] -= val
print(abs(pos[0]) + abs(pos[1]))

# star 2
pos = [0,0]
lst = [str(pos)]
facing = 0  # n=0 o=1 s=2 w=3
res = ''
for step in inp:
    flag = False
    val = int(step[1:])
    if step[0] == 'L':
        facing = (facing + 3) % 4
    else:
        facing = (facing + 1) % 4
    dir = [0,0]
    if facing == 0:
        dir[1] = 1
    elif facing == 1:
        dir[0] = 1
    elif facing == 2:
        dir[1] = -1
    else:
        dir[0] = -1
    for i in range(val):
        pos = [pos[0] + dir[0], pos[1] + dir[1]]
        if str(pos) in lst:
            res = abs(pos[0]) + abs(pos[1])
            flag = True
            break
        else:
            lst.append(str(pos))
    if flag:
        break
print(res)