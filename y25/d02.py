import math

with open("y25/r02.txt") as f:
    ranges = [[int(r.split('-')[0]), int(r.split('-')[1])] for r in f.readline().strip().split(',')]

def is_valid1(n):
    if int(math.log10(n) + 1) % 2 == 0:
        s = str(n)
        if (s[0:len(s)//2]) == (s[len(s)//2:len(s)]):
            return False
    return True

def is_valid2(n):
    s = str(n)
    for div in range(2, len(s)+1):
        if len(s) % div == 0:
            is_invalid = True
            length = len(s) // div
            for i in range(div-1):
                s1 = s[(i * length):(i + 1) * length]
                s2 = s[(i + 1) * length:(i + 2) * length]
                if s1 != s2:
                    is_invalid = False
                    break
            if is_invalid:
                return False
    return True

# star 1
sum = 0
for r in ranges:
    for i in range(r[0], r[1]+1):
        if not is_valid1(i):
            sum += i
print(sum)

# star 2
sum = 0
for r in ranges:
    for i in range(r[0], r[1]+1):
        if not is_valid2(i):
            sum += i
print(sum)