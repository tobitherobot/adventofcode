with open("y15/r03.txt") as f:
    inp = f.readline().strip()

# star 1
st = set()
pos = [0,0]
st.add(str(pos))
for ch in inp:
    if ch == '^':
        pos[1] += 1
    elif ch == 'v':
        pos[1] -= 1
    elif ch == '<':
        pos[0] -= 1
    else: # >
        pos[0] += 1
    st.add(str(pos))
print(len(st))

# star 2
st1 = set()
st2 = set()
pos1 = [0,0]
pos2 = [0,0]
st.add(str(pos))
for i in range(len(inp)):
    if i%2 == 0:
        if inp[i] == '^':
            pos1[1] += 1
        elif inp[i] == 'v':
            pos1[1] -= 1
        elif inp[i] == '<':
            pos1[0] -= 1
        else: # >
            pos1[0] += 1
        st2.add(str(pos1))
    else:
        if inp[i] == '^':
            pos2[1] += 1
        elif inp[i] == 'v':
            pos2[1] -= 1
        elif inp[i] == '<':
            pos2[0] -= 1
        else: # >
            pos2[0] += 1
        st2.add(str(pos2))
print(len(st1) + len(st2))