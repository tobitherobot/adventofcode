start = 138307
end = 654504
ct1 = 0
ct2 = 0

def get_next1(n):
    global ct1
    s = str(n)
    for i in range(len(s)-1):
        if s[i] > s[i+1]:
            s = s[:i+1] + s[i:i+1] + s[i+2:]
    for i in range(len(s)-1):
        if int(s) <= end and s[i] == s[i+1]:
            ct1 += 1
            break
    return int(s)

def get_next2(n):
    global ct2
    s = str(n)
    for i in range(len(s)-1):
        if s[i] > s[i+1]:
            s = s[:i+1] + s[i:i+1] + s[i+2:]
    pairs = {}
    for i in range(len(s)-1):
        if s[i] == s[i+1]:
            if s[i] in pairs:
                pairs[s[i]] = 99
            else:
                pairs[s[i]] = 1
    m = int(s)
    for ch in pairs:
        if pairs[ch] == 1 and m <= end:
            ct2 += 1
            break
    return m

# star 1
n = get_next1(start)
while n <= end:
    n = get_next1(n+1)
print(ct1)

# star 2
n = get_next2(start)
while n <= end:
    n = get_next2(n+1)
print(ct2)