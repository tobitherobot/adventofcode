with open('y16/r06.txt') as f:
    inp = [x.strip() for x in f.readlines()]

# star 1
res = ''
for i in range(len(inp[0])):
    count = [0] * 26
    for l in inp:
        count[ord(l[i]) - ord('a')] += 1
    mx = 0
    for c in count:
        mx = max(mx, c)
    for i in range(26):
        if count[i] == mx:
            res += chr(i + ord('a'))
            break
print(res)

# star 2
res = ''
for i in range(len(inp[0])):
    count = [0] * 26
    for l in inp:
        count[ord(l[i]) - ord('a')] += 1
    mn = 999
    for c in count:
        mn = min(mn, c)
    for i in range(26):
        if count[i] == mn:
            res += chr(i + ord('a'))
            break
print(res)