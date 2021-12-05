numbers = [15,5,1,4,7,0]

# star 1
for i in range(2020-len(numbers)):
    if numbers[-1] in numbers[:-1]:
        last_idx = len(numbers) - numbers[::-1][1:].index(numbers[-1]) - 2
        numbers.append(len(numbers) - last_idx - 1)
    else:
        numbers.append(0)
print(numbers[-1])

# star 2

