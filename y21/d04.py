import re

with open("y21/r04.txt") as f:
    calls = [int(x) for x in f.readline().split(",")]
    f.readline()
    boards = []
    board = []
    for line in f:
        if len(line)<2:
            boards.append(board.copy())
            board.clear()
        else:
            board.append([int(x) for x in re.sub(' +', ' ', line.strip()).split(" ")])
    boards.append(board)

def is_bingo(check):
    for i in range(5):
        hor = 0
        ver = 0
        for j in range(5):
            hor += 1 if check[i][j] else 0
            ver += 1 if check[j][i] else 0
        if hor==5 or ver==5:
            return True
    return False

def get_score(board, calls):
    sm = []
    for i in board:
        for j in i:
            sm.append(j)
    for call in calls:
        if call in sm:
            sm.remove(call)
    return sum(sm) * calls[-1]

# star 1
min_calls = 99999
res = -1
for board in boards:
    check = [[False for x in range(5)] for x in range(5)]
    flag = False
    for call in calls:
        for i in range(5):
            for j in range(5):
                if board[i][j]==call:
                    check[i][j] = True
                    if min_calls<calls.index(call):
                        flag = True
                        break
                    elif is_bingo(check):
                        res = get_score(board, calls[:calls.index(call)+1])
                        min_calls = calls.index(call)
                        flag = True
                        break
            if flag:
                break
        if flag:
            break
print(res)

# star 2
max_calls = 0
res = -1
for board in boards:
    check = [[False for x in range(5)] for x in range(5)]
    flag = False
    for call in calls:
        for i in range(5):
            for j in range(5):
                if board[i][j]==call:
                    check[i][j] = True
                    if is_bingo(check):
                        if max_calls<calls.index(call):
                            res = get_score(board, calls[:calls.index(call)+1])
                            max_calls = calls.index(call)
                        flag = True
                        break
            if flag:
                break
        if flag:
            break
print(res)