import matplotlib
from pulp import *
import numpy as np

# ilość zmian - ilość dzielnic - ogr. dolne górne - dostępne dla zmiany - przypisane do dzielnic

model = LpProblem("Policja nam dokucza", LpMinimize)

shifts_numb = 0
precinct_numb = 0
variables = {}
cost_function = {}

with open('ALGOS_ZAD2_DANE.txt') as reader:
    data = np.loadtxt(fname='ALGOS_ZAD2_DANE.txt', delimiter=',')

    shifts_numb = int(data[0])
    precinct_numb = int(data[1])

    sxp = shifts_numb * precinct_numb

    for i in range(0, shifts_numb):
        for j in range(0, precinct_numb):
            name = 's' + str(i) + str(j)
            variables[name] = LpVariable(name, lowBound=data[j * 2 + 2 + i * 2 * shifts_numb],
                                         upBound=data[j * 2 + 3 + i * 2 * shifts_numb], cat='Integer')
            # print(variables[name].lowBound, variables[name].upBound)
            # print(i*6 + 2 + j*2, j*2 + 3 + i*6)

    cost = 0
    for i in range(0, shifts_numb):
        for j in range(0, precinct_numb):
            name = 's' + str(i) + str(j)
            cost += variables[name]
    cost_function['cost'] = cost
    model += cost_function['cost']
    # print(cost_function['cost'])

    for i in range(0, shifts_numb):
        expression = 0
        value = 0
        for j in range(0, precinct_numb):
            expression += variables['s' + str(i) + str(j)]
        value = data[i + 2 + 2 * sxp]
        # print(value,expression)
        model += expression >= value

    for i in range(0, precinct_numb):
        expression = 0
        value = 0
        for j in range(0, shifts_numb):
            expression += variables['s' + str(j) + str(i)]
        value = data[i + 2 + 2 * sxp + shifts_numb]
        # print(expression,'>=',value)
        model += expression >= value

model.solve()
