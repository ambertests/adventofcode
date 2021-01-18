import re

R, M = open("input/day19.txt").read().split('\n\n')

REPLACE = {}
for r in R.split('\n'):
    s, f = r.strip().split(' => ')
    if s in REPLACE:
        REPLACE[s].append(f)
    else:
        REPLACE[s] = [f]

def get_all_replacements(mol, match_limit=0):
    molecules = set()
    for key in REPLACE:
        # re.finditer finds the starting index of each
        # key located within the molecule
        for i in [m.start() for m in re.finditer(key, mol)]:
            # once the beginning of the string is set
            # we don't want to mess with it any more
            if i < match_limit: continue
            for r in REPLACE[key]:
                molecules.add(mol[:i] + r + mol[i+len(key):])
    return molecules

def calibrate():
    molecules = get_all_replacements(M)
    return len(molecules)

def modify(start):
    # the general strategy is to get all the replacement values
    # for the starting molecule, then get all the replacement values
    # for each of those, etc, etc, until the target is matched
    molecules = get_all_replacements(start)
    checked = set()
    mod_count = 1
    match_limit = 0
    while M not in molecules:
        new_mol = set()
        for mol in molecules:
            # there won't be any replacements before the match_limit
            # so the completed section of the string is stable
            new_mol.update(get_all_replacements(mol, match_limit))
        matched = {}
        for nm in new_mol:
            # We have to prune the molecule list or the method will 
            # take too long. The strategy is to track how much of 
            # the target molecule is matched by the sub-molecule,
            # going from left to right
            mx = 0
            for i in range(len(nm)):
                if i > len(M) - 1: break
                if nm[i] == M[i]:
                    mx += 1
                else: break
            if mx in matched: matched[mx].append(nm)
            else: matched[mx] = [nm]
        # Once the new molecules are grouped by match length,
        # we remove all but the top two sets to run the next
        # iteration. With this aggressive pruning, the method
        # finishes in < 5s
        match_len = list(matched.keys())
        match_len.sort(reverse=True)
        if len(matched) > 2:
            keep = match_len[:2]
            # continuously increasing the match_limit also keeps
            # the best matched part of the string stable and reduces
            # clutter
            match_limit = keep[-1] - 1
            for k in matched:
                if k not in keep:
                    new_mol = new_mol.difference(set(matched[k]))
        else:
            match_limit = match_len[-1] - 1

        checked.update(molecules)
        molecules = new_mol.difference(checked)
        mod_count += 1
    return mod_count
        
print("Part 1:", calibrate())
print("Part 2:", modify('e'))