import itertools

with open("y21/r08.txt") as f:
    patterns = []
    output = []
    for l in f.readlines():
        s = l.split(' | ')
        patterns.append(s[0].strip().split(' '))
        output.append(s[1].strip().split(' '))

# star 1
count = 0
for out in output:
    for e in out:
        if len(e) == 2 or len(e) == 4 or len(e) == 3 or len(e) == 7:
            count += 1
print(count)

# star 2 TODO
val = [119, 36, 93, 109, 46, 107, 123, 37, 127, 111]
for i in range(len(patterns)):
    patt = patterns[i]
    orig = [0, 1, 2, 3, 4, 5, 6]
    for perm in itertools.permutations(orig):
        sm = 0
        for p in patt:
            for l in p:
                sm += pow(2, perm[ord(l) - ord('a')])
        #print("{}: {}".format(sum(val), sm))
        if sm == sum(val):
            print(perm)
            
# a b c d  e  f  g
# 1 2 4 8 16 32 64 = 127
# 0: 127-8
# 1: 4+32
# 2: 127-2-32
# 3: 127-2-16
# 4: 127-1-16-64
# 5: 127-4-16
# 6: 127-4
# 7: 1+4++32
# 8: 127
# 9: 127-16



# 0 1 2 3 4 5 6 7 8 9
# 