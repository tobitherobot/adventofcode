import time

with open("y21/r14.txt") as f:
    initial = f.readline().strip()
    f.readline()
    rules = {}
    for l in f.readlines():
        s = l.strip().split(" -> ")
        rules[s[0]] = s[1]


def get_letters(code, times, rules, counts):
    if times > 0 and code in rules:
        ch = rules[code] 
        counts[ch] += 1
        get_letters(code[0] + ch, times-1, rules, counts)
        get_letters(ch + code[1], times-1, rules, counts)

starttime = time.time()
# star 1
counts = {}
for i in range(26):
    counts[chr(ord('A')+i)] = 0
for l in initial:
    counts[l] += 1

for i in range(len(initial)-1):
    get_letters(initial[i:i+2], 10, rules, counts)

mx = 0
mn = 9999999
for l in counts:
    mx = max(mx, counts[l])
    if counts[l] != 0:
        mn = min(mn, counts[l])
print(mx-mn) # 1588
print("finished in " + str((time.time() - starttime) * 100000))

# star 2
counts = {}
for i in range(26):
    counts[chr(ord('A')+i)] = 0
for l in initial:
    counts[l] += 1

for i in range(len(initial)-1):
    get_letters(initial[i:i+2], 20, rules, counts)
    print("peep")

mx = 0
mn = 9999999
for l in counts:
    mx = max(mx, counts[l])
    if counts[l] != 0:
        mn = min(mn, counts[l])
print(mx-mn)        

# NN
# NCN
# NBCCN
# NBBBCNCCN

# CN
# CCN
# XXCCN
# XXXXXCCN