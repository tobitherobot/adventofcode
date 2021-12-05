import sys

with open("y20/r13.txt") as f:
    time = int(f.readline().strip())
    input = f.readline().strip().split(",")
schedule = {}
for i in input:
    try:
        schedule[int(i)] = input.index(i)
    except:
        pass

# star 1
mn = sys.maxsize
res = -1
for bus in schedule:
    wait = bus - time%bus
    if wait < mn:
        mn = wait
        res = bus * wait
print(res)

# star 2
res = 1
busses = []
for bus in schedule:
    busses.append(bus)
busses.sort()
busses = busses[::-1]

'''
(res + idx) % bus = 0

x * bus + idx = res
'''