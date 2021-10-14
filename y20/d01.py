from pathlib import Path

p = Path(__file__).with_name('r01.txt')
with p.open('r') as f:
    input = [int(x.strip()) for x in f.readlines()]

# star 1
for a in input[:-1]:
    for b in input[input.index(a)+1:]:
        if a+b==2020:
            print(a*b)
            break
    else:
        continue

#star 2 
for a in input[:-2]:
    for b in input[input.index(a)+1:-1]:
        for c in input[input.index(b)+1:]:
            if a+b+c==2020:
                print(a*b*c)
                break
        else:
            continue
    else: 
        continue