with open('y16/r08.txt') as f:
    inp = [x.strip().split(' ') for x in f.readlines()]

def do_instr(instr, screen):
    if instr[0] == 'rect':
        spl = instr[1].split('x')
        a = int(spl[0])
        b = int(spl[1])
        for i in range(b):
            for j in range(a):
                screen[i][j] = '#'
    elif instr[1] == 'row':
        row = int(instr[2][2:])
        val = int(instr[4]) % 50
        

# star 1
screen = [['.' for i in range(50)] for j in range(6)]
for instr in inp:
    do_instr(instr, screen)
    for l in screen:
        print(''.join(l))
    input()