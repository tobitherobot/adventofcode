with open("y22/r09.txt") as f:
    input = [x.strip().split(' ') for x in f.readlines()]

# star 1
head = [0,0]
tail = [0,0]
tail_pos = set()
tail_pos.add(str(tail))

for line in input:
    
    dir = [0,0]
    if line[0] == 'R':
        dir = [1,0]
    elif line[0] == 'L':
        dir = [-1,0]
    elif line[0] == 'U':
        dir = [0,1]
    else:
        dir = [0,-1]

    for loop in range(int(line[1])):
        head[0] += dir[0]
        head[1] += dir[1]

        if 2 < abs(head[0]-tail[0]) + abs(head[1]-tail[1]):
            # head is multiple directions away
            tail[0] += 1 if tail[0] < head[0] else -1
            tail[1] += 1 if tail[1] < head[1] else -1
        else:
            # head is single direction away
            if 1 < abs(head[0]-tail[0]):
                tail[0] += 1 if tail[0] < head[0] else -1
            elif 1 < abs(head[1]-tail[1]):
                tail[1] += 1 if tail[1] < head[1] else -1
        
        tail_pos.add(str(tail))

print(len(tail_pos))

# star 2
rope = {}
tail_pos = set()

for i in range(10):
    rope[i] = [0,0]

for line in input:
    
    dir = [0,0]
    if line[0] == 'R':
        dir = [1,0]
    elif line[0] == 'L':
        dir = [-1,0]
    elif line[0] == 'U':
        dir = [0,1]
    else:
        dir = [0,-1]

    for loop in range(int(line[1])):
        rope[0][0] += dir[0]
        rope[0][1] += dir[1]

        for i in range(1, 10):
            if 2 < abs(rope[i-1][0]-rope[i][0]) + abs(rope[i-1][1]-rope[i][1]):
                # previous knot is multiple directions away
                rope[i][0] += 1 if rope[i][0] < rope[i-1][0] else -1
                rope[i][1] += 1 if rope[i][1] < rope[i-1][1] else -1
            else:
                # previous knot is single direction away
                if 1 < abs(rope[i-1][0]-rope[i][0]):
                    rope[i][0] += 1 if rope[i][0] < rope[i-1][0] else -1
                elif 1 < abs(rope[i-1][1]-rope[i][1]):
                    rope[i][1] += 1 if rope[i][1] < rope[i-1][1] else -1
        
        tail_pos.add(str(rope[9]))

print(len(tail_pos))