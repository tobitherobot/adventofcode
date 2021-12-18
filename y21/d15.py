with open("y21/r15.txt") as f:
    input = [[int(c) for c in x.strip()] for x in f.readlines()]

# star 1
risk = [[99999]*len(input[0]) for i in range(len(input))]
cmp = 0
risk[0][0] = 0
flag = True
while flag:
    flag = False
    for i in range(len(risk)):
        for j in range(len(risk[i])):
            cmp += 1
            if i==0 and j==0:
                continue
            mn = 99999
            if 0<i:
                mn = min(mn, risk[i-1][j])
            if 0<j:
                mn = min(mn, risk[i][j-1])
            if i<len(input)-1:
                mn = min(mn, risk[i+1][j])
            if j<len(input[i])-1:
                mn = min(mn, risk[i][j+1])
            mn += input[i][j]
            if risk[i][j]!=mn:
                risk[i][j] = mn
                flag = True
print(risk[-1][-1])

# star 2
