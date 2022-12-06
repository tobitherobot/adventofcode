with open("y22/r06.txt") as f:
    s = f.readline().strip()

# star 1
for i in range(len(s)-3):
    if s[i]!=s[i+1] and s[i]!=s[i+2] and s[i]!=s[i+3] and s[i+1]!=s[i+2] and s[i+1]!=s[i+3] and s[i+2]!=s[i+3]:
        print(i+4)
        break

# star 2
for i in range(len(s)-14):
    st = set()
    for j in range(i, i+14):
        st.add(s[j])
    if len(st) == 14:
        print(i+14)
        break