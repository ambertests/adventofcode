
def combat(p1, p2):
    while len(p1) > 0 and len(p2) > 0:
        p1_card = p1.pop(0)
        p2_card = p2.pop(0)
        if p1_card > p2_card:
            p1.extend([p1_card, p2_card])
        elif p2_card > p1_card:
            p2.extend([p2_card, p1_card])
        else:
            print("cards are equal")
    return p1 if len(p1) > 0 else p2


def recursive_combat(p1, p2):
    # initially had prev_hands as an array and the game took
    # ~30s to complete. changing to set cuts it down to ~3s
    prev_hands = set()
    p1_win = True
    while len(p1) > 0 and len(p2) > 0:
        hands = (tuple(p1), tuple(p2))
        if hands in prev_hands: 
            p1_win = True
            break
        prev_hands.add(hands)
        p1_card = p1.pop(0)
        p2_card = p2.pop(0)
        if(p1_card <= len(p1) and p2_card <= len(p2)):
            new_deck_1 = p1[:p1_card]
            new_deck_2 = p2[:p2_card]
            p1_win,_ = recursive_combat(new_deck_1, new_deck_2)
        else:
            p1_win = p1_card > p2_card

        if p1_win:
            p1.extend([p1_card, p2_card])
        else:
            p2.extend([p2_card, p1_card])
    
    win_deck = p1 if p1_win else p2
    return p1_win, win_deck

def get_score(deck):
    return sum([deck[i]*(len(deck)-i) for i in range(len(deck))])

def deal(input):
    return [[int(i) for i in d.strip().split('\n')[1:]] 
                    for d in open(input).read().split('\n\n')]

def solve(input):
    p1, p2 = deal(input)
    winner = combat(p1, p2)
    print("Part 1:", get_score(winner))

    p1, p2 = deal(input)
    _,winner = recursive_combat(p1, p2)
    print("Part 2:", get_score(winner))

solve("input/day22.txt")

