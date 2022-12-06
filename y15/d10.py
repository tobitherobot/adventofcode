inp = '1321131112'

def get_next(s):
    count = 1
    last = s[0]
    res = ''
    for i in range(1, len(s)):
        if s[i] != last:
            res += str(count) + last
            last = s[i]
            count = 1
        else:
            count += 1
    res += str(count) + last
    return res

# star 1
s = inp[:]
for i in range(40):
    s = get_next(s)
print(len(s))

# star 2
for i in range(10):
    s = get_next(s)
print(len(s))