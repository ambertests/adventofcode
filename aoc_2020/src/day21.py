input = [line.strip()[:-1] for line in open("input/day21.txt").readlines()]
allergens = {}
ingredients = []
for line in input:
    ing, alg = line.split(' (contains ')
    ingr = ing.split(' ')
    ingredients.extend(ingr)
    for a in alg.split(', '):
        if a not in allergens:
            allergens[a] = set(ingr)
        else:
            allergens[a] = allergens[a].intersection(set(ingr))

has_allergen = set()
for a in allergens:
    has_allergen.update(allergens[a])

part1 = 0
for i in ingredients:
    if i not in has_allergen: part1 += 1
print("Part 1:", part1)

A = {}
while len(A) < len(allergens):
    for al in allergens:
        if len(allergens[al]) == 1:
            ing = list(allergens[al])[0]
            A[al] = ing
            for alg in allergens:
                if ing in allergens[alg]:
                    allergens[alg].remove(ing)

keys = list(A.keys())
keys.sort()
danger = []
for k in keys:
    danger.append(A[k])
print("Part 2:", ",".join(danger))