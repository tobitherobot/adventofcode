with open("y20/r11.txt", "r") as f:
    input = [x.strip() for x in f.readlines()]

def do_round_star1(seats):
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
                        if (i!=r or j!=c) and 0<=i and 0<=j and i<len(seats) and j<len(seats[r]):
                            count += 1 if seats[i][j]=='#' else 0
                if count==0:
                    row += '#'
                else:
                    row += 'L'
            else:
                count = 0
                for i in range(r-1, r+2):
                    for j in range(c-1, c+2):
                        if (i!=r or j!=c) and 0<=i and 0<=j and i<len(seats) and j<len(seats[r]):
                            count += 1 if seats[i][j]=='#' else 0
                if count>=4:
                    row += 'L'
                else:
                    row += '#'
        copy.append(row)
    return copy

def do_round_star2(seats):
    copy = []
    for r in range(len(seats)):
        row = ""
        for c in range(len(seats[r])):
            if seats[r][c]=='.':
                row += '.'
            elif seats[r][c]=='L':
                count = 0
                for i in range(-1, 2):
                    for j in range(-1, 2):
                        for m in range(1, len(seats)):
                            if r+(i*m)<0 or c+(j*m)<0 or len(seats)<=r+(i*m) or len(seats[r])<=c+(j*m):
                                break
                            elif seats[r+(i*m)][c+(j*m)]=='#' or seats[r+(i*m)][c+(j*m)]=='L':
                                count += 1 if seats[r+(i*m)][c+(j*m)]=='#' else 0
                                break
                if count==0:
                    row += '#'
                else:
                    row += 'L'
            else:
                count = 0
                for i in range(-1, 2):
                    for j in range(-1, 2):
                        for m in range(1, len(seats)):
                            if r+(i*m)<0 or c+(j*m)<0 or len(seats)<=r+(i*m) or len(seats[r])<=c+(j*m):
                                break
                            elif seats[r+(i*m)][c+(j*m)]=='#' or seats[r+(i*m)][c+(j*m)]=='L':
                                #print(str(r) + "/" + str(c) + ": [" +  str(r) + "+(" + str(i) + "*" + str(m) + ")][" + str(c) + "+(" + str(j) + "*" + str(m) + ")]")
                                print(str(r) + "/" + str(c) + ": [" +  str(r+(i*m)) + "][" + str(c+(j*m)) + "] " + str(1 if seats[r+(i*m)][c+(j*m)]=='#' else 0))
                                count += 1 if seats[r+(i*m)][c+(j*m)]=='#' else 0
                                break
                if count>=5:
                    row += 'L'
                else:
                    row += '#'
        copy.append(row)
    return copy

# star 1
states = set()
seats = input.copy()
while str(seats) not in states:
    states.add(str(seats))
    seats = do_round_star1(seats)
count = 0
for r in seats:
    for c in r:
        if c=='#':
            count += 1
print(count)

# star 2
seats = input.copy()
print("")
for s in seats:
    print(s)

for i in range(2):   
    seats = do_round_star2(seats)

    print("")
    for s in seats:
        print(s)
    
