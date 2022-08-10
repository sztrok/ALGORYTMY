from pulp import *
import numpy as np

model = LpProblem(name='Plane fueling optimization', sense=LpMinimize)

company_numb = 0
airport_numb = 0
variables = {}
costs = {}
companies_max_supply = {}
airports_fuel_needs = {}
statement = {}

with open('ALGOS_ZAD1_DANE.txt') as reader:
    data = np.loadtxt(fname='ALGOS_ZAD1_DANE.txt', delimiter=',')

    #     ilość firm - ilość lotnisk - koszty na lotniskach - zapotrzebowanie na paliwo -
    #     - max ilość paliwa od fabryk

    company_numb = int(data[0])
    airport_numb = int(data[1])

    axc = company_numb * airport_numb
    for i in range(0, company_numb):
        for j in range(0, airport_numb):
            name = 'c' + str(i) + str(j)
            variables[name] = LpVariable(name, lowBound=0, cat='Continuous')

    for i in range(0, company_numb):
        for j in range(0, airport_numb):
            name = 'cost_' + str(i) + str(j)
            costs[name] = data[2 + i + j * 3]

    for i in range(0, airport_numb):
        name = 'airport' + str(i) + '_fuel_needs'
        airports_fuel_needs[name] = data[2 + axc + i]

    for i in range(0, company_numb):
        name = 'max_supply_company_' + str(i)
        companies_max_supply[name] = data[2 + axc + airport_numb + i]

main_func = 0
for i in range(0, company_numb):
    for j in range(0, airport_numb):
        main_func += variables['c' + str(i) + str(j)] * costs['cost_' + str(i) + str(j)]

model += main_func

for i in range(0, airport_numb):
    constr_airport = 0

    for j in range(0, company_numb):
        constr_airport += variables['c' + str(j) + str(i)]

    constr_airport_numb = airports_fuel_needs['airport' + str(i) + '_fuel_needs']
    model += constr_airport == constr_airport_numb

for i in range(0, company_numb):
    constr_comp_supply = 0
    for j in range(0, airport_numb):
        constr_comp_supply += variables['c' + str(i) + str(j)]
    constr_comp_supply_numb = companies_max_supply['max_supply_company_' + str(i)]
    model += constr_comp_supply <= constr_comp_supply_numb

model.solve()
