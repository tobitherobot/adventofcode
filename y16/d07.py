with open('y16/r07.txt') as f:
    inp = [x.strip() for x in f.readlines()]

def check_abba(ip):
    isbracket = False
    hasabba = False
    for i in range(len(ip)-3):
        if ip[i] == '[':
            isbracket = True
        elif ip[i] == ']':
            isbracket = False
        elif ip[i]==ip[i+3] and ip[i+1]==ip[i+2] and ip[i]!=ip[i+1]:
            if isbracket:
                return False
            else:
                hasabba = True
    return hasabba

def check_aba(ip):
    isbracket = False
    abas = []
    babs = []
    for i in range(len(ip)-2):
        if ip[i] == '[':
            isbracket = True
        elif ip[i] == ']':
            isbracket = False
        elif ip[i]==ip[i+2] and ip[i]!=ip[i+1]:
            if isbracket:
                babs.append(ip[i:i+3])
            else:
                abas.append(ip[i:i+3])
    for aba in abas:
        bab = aba[1] + aba[0] + aba[1]
        if bab in babs:
            return True
    return False

# star 1
count = 0
for l in inp:
    count += 1 if check_abba(l) else 0
print(count)

# star 1
count = 0
for l in inp:
    count += 1 if check_aba(l) else 0
print(count)