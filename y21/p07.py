with open("y21/r07.txt") as f:
    input = [int(x) for x in f.readline().strip().split(",")]
input.sort()

# star 1
m = (input[len(input)//2] + input[len(input)//2-1]) // 2
sm = 0
for i in input:
    sm += abs(m - i)
print(sm)

# star 2
m = sum(input) // len(input)
sm = 0
for i in input:
    n = abs(m - i)
    sm += (n*n + n) // 2
print(sm)