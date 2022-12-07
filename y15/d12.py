import json

with open('y15/r12.txt') as f:
    inp = json.loads(f.readline().strip())

def get_sum(n):
    sm = 0
    if isinstance(n, dict):
        for k in n.keys():     
            sm += get_sum(n[k])
    elif isinstance(n, list):
        for v in n:
            sm += get_sum(v)
    elif isinstance(n, int):
        sm += n
    else:
        pass
    return sm

def get_sum_red(n):
    sm = 0
    if isinstance(n, dict):
        tmp = 0
        flag = True
        for k in n.keys():
            if (isinstance(n[k], str) and n[k] == 'red') or k == 'red':
                flag = False
                break 
            tmp += get_sum_red(n[k])
        if flag:
            sm += tmp
    elif isinstance(n, list):
        for v in n:
            sm += get_sum_red(v)
    elif isinstance(n, int):
        sm += n
    else:
        pass
    return sm

# star 1
print(get_sum(inp))

# star 2
print(get_sum_red(inp))