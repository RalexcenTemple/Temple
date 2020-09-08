import os
##os.system("text")


#for i in range(6):
#	for j in range(6):
#		for k in range(3):
#			terminal = "./matrixMult " + str((j+1) * 100) + " " + str(i)
#			os.system(terminal);

for i in range(6):
	terminal = "./matrixMult 5000 " + str(i)
	os.system(terminal)
