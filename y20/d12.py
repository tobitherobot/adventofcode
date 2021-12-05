with open("y20/r12.txt") as f:
    input = [x.strip() for x in f.readlines()]

# star 1
ship = [0, 0]
dir = 1
for line in input:
    cmd = line[0]
    val = int(line[1:])
    
    if cmd=='N':
        ship[1] -= val
    elif cmd=='E':
        ship[0] += val
    elif cmd=='S':
        ship[1] += val
    elif cmd=='W':
        ship[0] -= val
    elif cmd=='L':
        dir = (dir + (4 - val//90)) % 4
    elif cmd=='R':
        dir = (dir + val//90) % 4
    elif cmd=='F':
        if dir==0:
            ship[1] -= val
        elif dir==1:
            ship[0] += val
        elif dir==2:
            ship[1] += val
        elif dir==3:
            ship[0] -= val
print(abs(ship[0]) + abs(ship[1]))

# star 2
ship = [0, 0]
wp = [10, -1]
dir = 1
for line in input:
    cmd = line[0]
    val = int(line[1:])

    if cmd=='N':
        wp[1] -= val
    elif cmd=='E':
        wp[0] += val
    elif cmd=='S':
        wp[1] += val
    elif cmd=='W':
        wp[0] -= val
    elif cmd=='L':
        for i in range(val//90):
            wp = [wp[1], -wp[0]]
    elif cmd=='R':
        for i in range(4 - val//90):
            wp = [wp[1], -wp[0]]
    elif cmd=='F':
        ship[0] += wp[0] * val
        ship[1] += wp[1] * val
print(abs(ship[0]) + abs(ship[1]))