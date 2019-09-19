'''This project uses a Monte Carlo simulation to predict the outcome 
of the 3 Dragon's Den presenters to finish in the bottom 3 of the Irish presidential election 2019.'''
#By Dean Connell

from random import uniform


def odds_to_percentages(x: int, y: int) -> float:
    return float(y / (x + y))  # defined a function to change odds


Michael = odds_to_percentages(1, 25)
Peter = odds_to_percentages(10, 1)
Sean = odds_to_percentages(66, 1)
Liadh = odds_to_percentages(80, 1)
Joan = odds_to_percentages(250, 1)
Gavin = odds_to_percentages(500, 1)

dragons = ["Peter", "Sean", "Gavin"]  # used for comparison
dragons.sort()
dragons_counter = 0  # initialised variable for amount of times 3 dragons lose
test_range = 10000  # amount of monte carlo simulations

for i in range(test_range):
    candidate_val = [Michael, Peter, Sean, Liadh, Joan, Gavin]  # outputs of functions
    candidates = ["Michael", "Peter", "Sean", "Liadh", "Joan", "Gavin"]  # this resets list to full list of candidates
    running_order = []  # resets list to be filled
    counter = 0
    total = 0
    while len(running_order) != 6:  # ensures all candidates are ranked before loop terminates
        random = uniform(0,
                         sum(candidate_val))  # getting a random float in the range of values from 0 to sum, every time
        total += candidate_val[counter]  # getting a running total of remaining candidates in list
        # print(total)
        if total > random:
            candidate_val.remove(candidate_val[counter])  # counter increments everytime total not > random, therefore removing the value that pushed it over
            running_order.append(candidates[counter])  # attaching corresponding candidate to list of winners to losers
            candidates.remove(candidates[counter])  # takes them out of next loop
            counter = 0  # resets counter to 0 so we start from the first value in altered list
            total = 0  # reset
        else:
            counter += 1  # moving to next index to be added to total
        # print(running_order)
        # print(random)
        # print(counter)
        # print("NEXT")

    last_place = []
    for j in range(3, 6):  # final 3 indices
        last_place.append(running_order[j])  # forming list of last 3 positions
    last_place.sort()  # sorting to match dragons list (to compare)
    if last_place == dragons:
        dragons_counter += 1  # getting number of cases that dragons fill last three places

var1 = ((dragons_counter / test_range) * 100)  # getting percentage
dragons_counter = 0

# exact same code with reversed arrays and switched variables


for k in range(test_range):
    candidate_val = [Michael, Peter, Sean, Liadh, Joan, Gavin]  # outputs of functions
    candidate_val.reverse()
    candidates = ["Michael", "Peter", "Sean", "Liadh", "Joan", "Gavin"]  # this resets list to full list of candidates
    candidates.reverse()
    running_order = []  # resets list to be filled
    counter = 0
    total = 0
    while len(running_order) != 6:  # ensures all candidates are ranked before loop terminates
        random = uniform(0, sum(candidate_val))  # getting a random float in the range of values from 0 to sum, every time
        total += candidate_val[counter]  # getting a running total of remaining candidates in list
        # print(total)
        if total > random:
            candidate_val.remove(candidate_val[counter])  # counter increments everytime total not > random, therefore removing the value that pushed it over
            running_order.append(candidates[counter])  # attaching corresponding candidate to list of winners to losers
            candidates.remove(candidates[counter])  # takes them out of next loop
            counter = 0  # resets counter to 0 so we start from the first value in altered list
            total = 0  # reset
        else:
            counter += 1  # moving to next index to be added to total
        # print(running_order)
        # print(random)
        # print(counter)
        # print("NEXT")

    last_place = []
    for l in range(3, 6):  # final 3 indices
        last_place.append(running_order[l])  # forming list of last 3 positions
    last_place.sort()  # sorting to match dragons list (to compare)
    if last_place == dragons:
        dragons_counter += 1  # getting number of cases that dragons fill last three places

var2 = ((dragons_counter / test_range) * 100)  # getting percentage

print((var1 + var2) / 2)