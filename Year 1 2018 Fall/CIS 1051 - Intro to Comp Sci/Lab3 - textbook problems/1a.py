import random
ave = 0

for i in range(0,1001):
	temp = random.randrange(10,21)
	print(str(temp))
	ave = ave + temp
	
print(str((ave/1000)))

