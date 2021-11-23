from pathlib import Path

p = Path(__file__).with_name("r06.txt")
with p.open("r") as f:
    input = [x.strip() for x in f.readlines()]    

# star 1
questions = set()
sum = 0
for line in input:
    if not line:
        sum += len(questions)
        questions = set()
    else:
        for c in line:
            questions.add(c)
sum += len(questions)
print(sum)

# star 2
sum = 0
counts = [0 for i in range(26)]
questions = 0

for line in input:
    if not line:
        for count in counts:
            if 0<count and count==questions:
                sum += 1
        counts = [0 for i in range(26)]
        questions = 0
    else:
        questions += 1
        for c in line:
            counts[ord(c)-97] += 1

for count in counts:
    if 0<count and count==questions:
        sum += 1
print(sum)

