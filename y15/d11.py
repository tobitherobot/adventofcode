inp = 'cqjxjnds'

def check(n):
    flag = False
    for i in range(8): # i o l
        if n[i] == ord('i')-ord('a') or n[i] == ord('o')-ord('a') or n[i] == ord('l')-ord('a'):
            n[i] += 1
            return check(n)
    for i in range(6): # 3 increasing 
        if n[i]+1 == n[i+1] and n[i+1]+1 == n[i+2]:
            flag = True
            break
    if not flag:
        return False
    
    idx = 0
    pairs = 0
    last = ''
    while idx < 7: # 2 unique pairs
        if n[idx] == n[idx+1]:
            if pairs == 1:
                if last != n[idx]:
                    return flag
            else:
                pairs += 1
                last = n[idx]
            idx += 2
        else:
            idx += 1
    return False

def get_next(s):
    n = [[0] for i in range(8)]
    for i in range(8):
        n[i] = ord(s[i]) - ord('a')
    start = True
    while not check(n) or start:
        carry = True
        start = False
        for i in range(1, 9):
            if carry:
                n[-i] = (n[-i] + 1) % 26
                carry = True if n[-i] == 0 else False
    res = ''
    for ch in n:
        res += chr(ch + ord('a'))
    return res

# star 1
s = get_next(inp)
print(s)

# star 2
print(get_next(s))