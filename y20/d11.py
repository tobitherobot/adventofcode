with open("y20/r11.txt", "r") as f:
    input = [x.strip() for x in f.readlines()]

def do_round(seats):
    copy = []
    for r in range(len(seats)):
        row = ""
        for c in range(len(seats[r])):
            if seats[r][c]=='.':
                row += '.'
            elif seats[r][c]=='L':
                count = 0
                for i in range(r-1, r+2):
                    for j in range(c-1, c+2):
                        if i!=r or j!=c:
                            try:
                                count += 1 if seats[i][j]=='#' else 0
                            except:
                                pass
                if count==0:
                    row += '#'
                else:
                    row += 'L'
            else:
                count = 0
                for i in range(r-1, r+2):
                    for j in range(c-1, c+2):
                        if i!=r or j!=c:
                            try:
                                count += 1 if seats[i][j]=='#' else 0
                            except:
                                pass
                if count>=4:
                    row += 'L'
                else:
                    row += '#'
        copy.append(row)
    return copy

# star 1
for l in input:
    print(l)

for i in range(2):
    input = do_round(input)
    print("")

    for l in input:
        print(l)