from pathlib import Path

p = Path(__file__).with_name('r02.txt')
with p.open('r') as f:
    input = [x.strip() for x in f.readlines()]

# star 1
count = 0
for l in input:
    s = l.split()
    pos = [int(x) for x in s[0].split('-')]
    c = s[2].count(s[1][:-1])

    if pos[0]<=c and pos[1]>=c:
        count += 1
print(count)

# star 2
count = 0
for l in input:
    s = l.split()
    pos = [int(x) for x in s[0].split('-')]
    a = s[2][pos[0]-1]
    b = s[2][pos[1]-1]
    c = s[1][:-1]

    if (a==c) != (b==c):
        count += 1 
print(count)

'''
1-5 x: xwwwx
1-5 x: wxxxx
1-5 x: xxxxw
1-2 x: ww
'''