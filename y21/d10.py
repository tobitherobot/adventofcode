with open("y21/r10.txt") as f:
    input = [x.strip() for x in f.readlines()]

# star 1
score = {')': 3, ']': 57, '}': 1197, '>': 25137}
rev = {'(': ')', '[': ']', '{': '}', '<': '>'}
sm = 0
for l in input:
    buffer = []
    for c in l:
        if c in rev:
            buffer.append(rev[c])
        elif buffer[-1]==c:
            buffer = buffer[:-1]
        else:
            sm += score[c]
            break
print(sm)

# star 2
score = {')': 1, ']': 2, '}': 3, '>': 4}
rev = {'(': ')', '[': ']', '{': '}', '<': '>'}
res = []
for l in input:
    buffer = []
    incompl = True
    for c in l:
        if c in rev:
            buffer.append(rev[c])
        elif buffer[-1]==c:
            buffer = buffer[:-1]
        else:
            incompl = False
            break
    if incompl:
        sm = 0
        buffer = buffer[::-1]
        for b in buffer:
            sm *= 5
            sm += score[b]
        res.append(sm)
res.sort()
print(res[len(res)//2])