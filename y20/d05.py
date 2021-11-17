from pathlib import Path

p = Path(__file__).with_name("r05.txt")
with p.open("r") as f:
    input = [x.strip() for x in f.readlines()]    

# star 1
mx = 0
for passport in input:
    row = 0
    seat = 0
    for i in range(7):
        row <<= 1
        if passport[i]=='B':
            row += 1
    for i in range(7,10):
        seat <<= 1
        if passport[i]=='R':
            seat += 1
    mx = max(mx, row*8 + seat)
print(mx)

# star 2
ids = []
for passport in input:
    row = 0
    seat = 0
    for i in range(7):
        row <<= 1
        if passport[i]=='B':
            row += 1
    for i in range(7,10):
        seat <<= 1
        if passport[i]=='R':
            seat += 1
    ids.append(row*8 + seat)
ids.sort()
for i in range(len(ids)-1):
    if ids[i+1] != ids[i]+1:
        print(ids[i]+1)
        break
