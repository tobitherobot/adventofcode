with open('y16/r04.txt') as f:
    inp = [x.strip() for x in f.readlines()]

global reads
reals = []

def check(l):
    s = l[:l.rfind('-')]
    id = int(l[l.rfind('-')+1:l.rfind('[')])
    checksum = l[l.rfind('[')+1:l.rfind(']')]

    counts = [0] * 26
    for ch in s:
        n = ord(ch) - ord('a')
        if 0 <= n and n < 26:
            counts[ord(ch) - ord('a')] += 1

    res = ''
    while len(res) < 5:
        mx = 0
        for c in counts:
            mx = max(mx, c)
        for i in range(26):
            if counts[i] == mx:
                res += chr(i + ord('a'))
                counts[i] = 0

    if len(res) > 5:
        res = res[:5]
    if res == checksum:
        reals.append([s, id])
        return id
    return 0

# star 1
sm = 0
for l in inp:
    sm += check(l)
print(sm)

# star 2
for r in reals:
    s = ''
    offs = r[1] % 26
    for ch in r[0]:
        if ch == '-':
            s += ' '
        else:
            s += chr(((ord(ch) - ord('a') + offs) % 26) + ord('a'))
    if 'north' in s:
        print(r[1])