from pulp import *
import numpy as np

model = LpProblem(name='Plane fueling optimization', sense=LpMinimize)

cost_a1 = 10
cost_a2 = 10
cost_a3 = 9
cost_a4 = 11
cost_b1 = 7
cost_b2 = 11
cost_b3 = 12
cost_b4 = 13
cost_c1 = 8
cost_c2 = 14
cost_c3 = 4
cost_c4 = 9

airport1_fuel_needs = 110000
airport2_fuel_needs = 220000
airport3_fuel_needs = 330000
airport4_fuel_needs = 440000

max_supply_company_a = 275000
max_supply_company_b = 550000
max_supply_company_c = 660000

a1 = LpVariable('a1', lowBound=0, cat='Continuous')
a2 = LpVariable('a2', lowBound=0, cat='Continuous')
a3 = LpVariable('a3', lowBound=0, cat='Continuous')
a4 = LpVariable('a4', lowBound=0, cat='Continuous')
b1 = LpVariable('b1', lowBound=0, cat='Continuous')
b2 = LpVariable('b2', lowBound=0, cat='Continuous')
b3 = LpVariable('b3', lowBound=0, cat='Continuous')
b4 = LpVariable('b4', lowBound=0, cat='Continuous')
c1 = LpVariable('c1', lowBound=0, cat='Continuous')
c2 = LpVariable('c2', lowBound=0, cat='Continuous')
c3 = LpVariable('c3', lowBound=0, cat='Continuous')
c4 = LpVariable('c4', lowBound=0, cat='Continuous')

model += a1 * cost_a1 + a2 * cost_a2 + a3 * cost_a3 + a4 * cost_a4 + b1 * cost_b1 + b2 * cost_b2 + b3 * cost_b3 + b4 * cost_b4 + c1 * cost_c1 + c2 * cost_c2 + c3 * cost_c3 + c4 * cost_c4

# model +=

model += a1 + b1 + c1 == airport1_fuel_needs
model += a2 + b2 + c2 == airport2_fuel_needs
model += a3 + b3 + c3 == airport3_fuel_needs
model += a4 + b4 + c4 == airport4_fuel_needs

model += a1 + a2 + a3 + a4 <= max_supply_company_a
model += b1 + b2 + b3 + b4 <= max_supply_company_b
model += c1 + c2 + c3 + c4 <= max_supply_company_c

model.solve()

print("Airport 1: ", value(a1), " ", value(b1), " ", value(c1))
print("Airport 2: ", value(a2), " ", value(b2), " ", value(c2))
print("Airport 3: ", value(a3), " ", value(b3), " ", value(c3))
print("Airport 4: ", value(a4), " ", value(b4), " ", value(c4))

print("Company 1: "+str(value(a1)+value(a2)+value(a3)+value(a4)))
print("Company 2: "+str(value(b1)+value(b2)+value(b3)+value(b4)))
print("Company 3: "+str(value(c1)+value(c2)+value(c3)+value(c4)))
