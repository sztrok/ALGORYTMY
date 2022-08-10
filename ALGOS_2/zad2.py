from pulp import *

cost_e12 = 2
cost_e23 = 4
cost_e13 = 1

time_e12 = 2
time_e23 = 1
time_e13 = 4

T = 5

model = LpProblem("Shortest path", LpMinimize)

e12 = LpVariable("edge from 1 to 2", lowBound=0, upBound=1, cat='Integer')
e23 = LpVariable("edge from 2 to 3", lowBound=0, upBound=1, cat='Integer')
e13 = LpVariable("edge from 1 to 3", lowBound=0, upBound=1, cat='Integer')

model += e12 * cost_e12 + e23 * cost_e23 + e13 * cost_e13
model += e12 + e13 == 1
model += e23 + e13 == 1
model += e12 * time_e12 + e23 * time_e23 + e13 * time_e13 <= T

model.solve()

print(value(e12), " ", value(e23), " ", value(e13))
