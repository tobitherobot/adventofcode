with open("y20/r04.txt", "r") as f:
    input = [x.strip() for x in f.readlines()]    

# star 1
passes = []
obj = []
for l in input:
    if len(l)<2:
        passes.append(obj)
        obj = []
    else:
        for x in l.split(" "):
            obj.append(x.split(":")[0])
passes.append(obj)
count = 0
for obj in passes:
    if "byr" in obj and "iyr" in obj and "eyr" in obj and "hgt" in obj and "hcl" in obj and "ecl" in obj and "pid" in obj:
        count += 1
print(count)

# star 2
passes = []
obj = {"byr":None,"iyr":None,"eyr":None,"hgt":None,"hcl":None,"ecl":None,"pid":None}
for l in input:
    if len(l)<2:
        passes.append(obj)
        obj = {"byr":None,"iyr":None,"eyr":None,"hgt":None,"hcl":None,"ecl":None,"pid":None}
    else:
        for x in l.split(" "):
            val = x.split(":")
            obj[val[0]] = val[1]
passes.append(obj)
count = 0
for obj in passes:
    try:
        f1 = 1920<=int(obj["byr"]) and int(obj["byr"])<=2002
        f2 = 2010<=int(obj["iyr"]) and int(obj["iyr"])<=2020
        f3 = 2020<=int(obj["eyr"]) and int(obj["eyr"])<=2030
        f41 = 150<=int(obj["hgt"][:-2]) and int(obj["hgt"][:-2])<=193 and obj["hgt"][-2:]=="cm"
        f42 = 59<=int(obj["hgt"][:-2]) and int(obj["hgt"][:-2])<=76 and obj["hgt"][-2:]=="in"
        f5 = len(obj["hcl"])==7 and obj["hcl"][0]=='#'
        f6 = obj["ecl"]=="amb" or obj["ecl"]=="blu" or obj["ecl"]=="brn" or obj["ecl"]=="gry" or obj["ecl"]=="grn" or obj["ecl"]=="hzl" or obj["ecl"]=="oth"
        f7 = len(obj["pid"])==9 and bool(int(obj["pid"]))
        if f1 and f2 and f3 and (f41 or f42) and f5 and f6 and f7:
            count += 1
    except:
        continue
print(count)
