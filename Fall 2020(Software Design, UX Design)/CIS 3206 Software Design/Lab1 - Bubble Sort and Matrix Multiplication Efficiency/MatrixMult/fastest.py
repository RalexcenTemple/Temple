import os

for i in range(6):
	terminal = "./matrixMult " + str(((i + 1)*1000)) + " 1 &"
	os.system(terminal)
