with open('y16/r02.txt') as f:
    inp = [l.strip() for l in f.readlines()]

def get_number(keypad, moves):
    pos = [1,1]
    for ch in moves:
        if ch == 'U':
            pos[0] = pos[0]-1 if pos[0] > 0                     and keypad[pos[0]-1][pos[1]] != 0 else pos[0]
        elif ch == 'D':
            pos[0] = pos[0]+1 if pos[0] < len(keypad)-1         and keypad[pos[0]+1][pos[1]] != 0 else pos[0]
        elif ch == 'L':
            pos[1] = pos[1]-1 if pos[1] > 0                     and keypad[pos[0]][pos[1]-1] != 0 else pos[1]
        else:
            pos[1] = pos[1]+1 if pos[1] < len(keypad[pos[0]])-1 and keypad[pos[0]][pos[1]+1] != 0 else pos[1]
    return str(keypad[pos[0]][pos[1]])

# star 1
keypad = [[1,2,3],[4,5,6],[7,8,9]]
res = ''
for l in inp:
    res += get_number(keypad, l)
print(res)

# star 2
keypad = [[1,0,1,0,0],[0,2,3,4,0],[5,6,7,8,9],[0,'A','B','C',0],[0,0,'D',0,0]]
res = ''
for l in inp:
    res += get_number(keypad, l)
print(res)