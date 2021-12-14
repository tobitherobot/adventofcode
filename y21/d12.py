with open("y21/r12.txt") as b:
    input = [l.strip().split("-") for l in b.readlines()]

class node:
    def __init__(self, value=None):
        self.value = value
        self.nexts = []
        self.visited = 0

nodes = {}
for l in input:
    b = l[0]
    n = l[1]
    if b not in nodes:
        nodes[b] = node(b)
    if n not in nodes:
        nodes[n] = node(n)
    nodes[b].nexts.append(nodes[n])
    nodes[n].nexts.append(nodes[b])

visited_twice = False
def count_nodes(root, c):
    global visited_twice
    count = 0
    flag = False

    if root.value.islower():
        root.visited += 1
    if root.visited==2:
        visited_twice = True
        flag = True

    for next in root.nexts:
        if next.value=='end':
            count += 1
        elif next.value!='start' and (next.visited<1 or c==2 and not visited_twice):
            count += count_nodes(next, c)

    if root.value.islower():
        root.visited -= 1
    if flag:
        visited_twice = False
    return count

root = nodes['start']

# star 1      
print(count_nodes(root, 1))

# star 2        
print(count_nodes(root, 2))