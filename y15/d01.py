with open("y15/r01.txt") as f:
    s = f.readline().strip()

# star 1
count = 0
first = -1
for i in range(len(s)):
    if s[i] == '(':
        count += 1
    else:
        count -= 1
        if first < 0 and count < 0:
            first = i+1
print(count)

# star 2
print(first)