import itertools

with open('y15/r13.txt') as f:
    scores = {}
    names = []
    for x in f.readlines():
        l = x.strip().split(' ')
        if l[0] not in scores:
            scores[l[0]] = {}
            names.append(l[0])
        person = l[-1][:-1]
        value = int(l[3]) if l[2]=='gain' else -int(l[3])
        scores[l[0]][person] = value

# star 1
mx = 0
for perm in itertools.permutations(names):
    sm = 0
    for i in range(len(perm)):
        sm += scores[perm[i]][perm[i-1]] + scores[perm[i]][perm[(i+1)%len(perm)]]
    mx = max(mx, sm)
print(mx)

# star 2
me = 'me'
scores[me] = {}
for name in names:
    scores[me][name] = 0
    scores[name][me] = 0
names.append(me)
mx = 0
for perm in itertools.permutations(names):
    sm = 0
    for i in range(len(perm)):
        sm += scores[perm[i]][perm[i-1]] + scores[perm[i]][perm[(i+1)%len(perm)]]
    mx = max(mx, sm)
print(mx)