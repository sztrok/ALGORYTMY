from pulp import *

model = LpProblem("Policja nam dokucza", LpMinimize)

s11 = LpVariable("shift 1 p1", lowBound=2, upBound=3, cat='Integer')
s12 = LpVariable("shift 1 p2", lowBound=3, upBound=5, cat='Integer')
s13 = LpVariable("shift 1 p3", lowBound=5, upBound=8, cat='Integer')
s21 = LpVariable("shift 2 p1", lowBound=4, upBound=7, cat='Integer')
s22 = LpVariable("shift 2 p2", lowBound=6, upBound=7, cat='Integer')
s23 = LpVariable("shift 2 p3", lowBound=7, upBound=12, cat='Integer')
s31 = LpVariable("shift 3 p1", lowBound=3, upBound=5, cat='Integer')
s32 = LpVariable("shift 3 p2", lowBound=5, upBound=10, cat='Integer')
s33 = LpVariable("shift 3 p3", lowBound=6, upBound=10, cat='Integer')

model += s11 + s12 + s13 + s21 + s22 + s23 + s31 + s32 + s33

model += s11 + s12 + s13 >= 10
model += s21 + s22 + s23 >= 20
model += s31 + s32 + s33 >= 18

model += s11 + s21 + s31 >= 10
model += s12 + s22 + s32 >= 20
model += s13 + s23 + s33 >= 13


model.solve()

print(value(s11), value(s12), value(s13))
print(value(s21), value(s22), value(s23))
print(value(s31), value(s32), value(s33))
