with open('y15/r14.txt') as f:
    inp = []
    for x in f.readlines():
        l = x.strip().split(' ')
        inp.append([int(l[3]), int(l[6]), int(l[13])]) # kms sec rest

# star 1
sec = 2503
mx = 0
for rein in inp:
    # calc full cycle
    dist = (sec // (rein[1]+rein[2])) * rein[1] * rein[0]
    rest = sec % (rein[1]+rein[2])
    # calc part cycle
    if rein[1] <= rest:
        dist += rein[1]*rein[0]
    else:
        dist += rest*rein[0]
    mx = max(mx, dist)
print(mx)

# star 2
scores = [0] * len(inp)
dist = [0] * len(inp)
for i in range(sec):
    for j in range(len(inp)):
        dist[j] += inp[j][0] if i % (inp[j][1]+inp[j][2]) < inp[j][1] else 0
    mx = max(dist)
    for j in range(len(scores)):
        if dist[j] == mx:
            scores[j] += 1
print(max(scores))