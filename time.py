from os.path import exists
import time
import os

dir = os.path.dirname(os.path.realpath(__file__)) + "\\y{}\\d{}.py"
ymax = 23
board = [[0]*25 for i in range (ymax - 15)]

for y in range(15, ymax):
    for d in range(1, 26):
        day = ("0" if d < 10 else "") + str(d)
        path = dir.format(y, day) 
        if exists(path):
            crr = round(time.time() * 1000)
            os.system("python " + path)
            t = round(time.time() * 1000) - crr
            board[y-15][d-1] = t
            print("y{} d{}: {}ms".format(y, d, t))

with open("times.txt", "w") as f:
    for y in range(15, ymax):
        f.write("y{}:\n".format(y))
        for d in range(1, 26):
            day = ("0" if d < 10 else "") + str(d)
            if board[y-15][d-1] != 0:
                f.write(" d{}: {}ms\n".format(day, board[y-15][d-1]))