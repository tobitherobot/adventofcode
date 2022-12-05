def get_piles(ls):
    piles = []
    idx = 1
    while idx < len(ls[0]):
        pile = []
        for l in ls:
            if l[idx] == ' ':
                pass
            else:
                pile.append(l[idx])
        piles.append(pile)
        idx += 4
    return piles

with open("y22/r05.txt") as f:
    ls = []
    l = f.readline()[:-1]
    while '[' in l:
        ls.append(l)
        l = f.readline()[:-1]
    piles = get_piles(ls)
    f.readline() # empty line
    instr = []
    for l in f.readlines():
        spl = l.strip().split(' ')
        instr.append([int(spl[1]), int(spl[3]), int(spl[5])])

# star 1
ps = [p[:] for p in piles]
for ins in instr:
    mv = ps[ins[1]-1][:ins[0]]
    ps[ins[1]-1] = ps[ins[1]-1][ins[0]:]
    ps[ins[2]-1] = mv[::-1] + ps[ins[2]-1]
s = ''
for p in ps:
    s += p[0]
print(s)

# star 2
ps = [p[:] for p in piles]
for ins in instr:
    mv = ps[ins[1]-1][:ins[0]]
    ps[ins[1]-1] = ps[ins[1]-1][ins[0]:]
    ps[ins[2]-1] = mv + ps[ins[2]-1]
s = ''
for p in ps:
    s += p[0]
print(s)