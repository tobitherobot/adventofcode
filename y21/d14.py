with open("y21/r14.txt") as f:
    initial = f.readline()
    f.readline()
    rules = {}
    for l in f.readlines():
        s = l.strip().split(" -> ")
        rules[s[0]] = s[1]

# star 1
n = list(initial)
m = initial
for a in range(10):
    offset = 0
    for i in range(len(m)-2):
        c = m[i:i+2]
        if c in rules:
            n.insert(i+1+offset, rules[c])
            offset += 1
    m = ''.join(n)
    n = list(m)
mx = 0
mn = 9999999
for i in range(26):
    c = chr(ord('A')+i)
    if c in n:
        mx = max(mx, n.count(c))
        mn = min(mn, n.count(c))
print(mx-mn)

# star 2  
