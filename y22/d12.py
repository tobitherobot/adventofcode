with open('y22/r12.txt') as f:
    input = [x.strip() for x in f.readlines()]

h = len(input)
w = len(input[0])
start = None
end = None

for i in range(len(input)):
    if input[i].count('S') == 1:
        start = [i, input[i].index('S')]
    elif input[i].count('E') == 1:
        end = [i, input[i].index('E')]

# star 1
print('\n'.join(input))

steps = [[-1] * w] * h
for s in steps:
    print(' '.join(str(x) for x in s))

def get_steps(x, y):
    if x==end[0] and y==end[1]:
        return 0
    elif steps[x][y] != -1:
        return steps[x][y]
    else:
        up = get_steps(x-1, y) if 0 <= x-1 else 100000
        down = get_steps(x+1, y) if x+1 < h else 100000
        left = get_steps(x, y-1) if 0 <= y-1 else 100000
        right = get_steps(x, y+1) if y+1 < w else 100000
        res = min(min(up, down), min(left, right))
        
        steps[x][y] = res
        return res
    
print(get_steps(start[0], end[0]))
