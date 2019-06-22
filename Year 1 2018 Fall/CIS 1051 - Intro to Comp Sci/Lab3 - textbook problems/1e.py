import random
import time

Mile = 5280
Track = 2*(Mile)
names = []
dists = []
MaxSp = 40
MinSp = 4
def main():
	ui = ""
	while(ui != "XXX"):
		print("Please enter a horse name or 'XXX' to start the race:")
		ui = input("...: ")
		if(ui == "XXX"):
			Speed(names)
		else:
			names.append(ui)


def Speed(i):
	hNum = i
	horses=[]
	for x in range(0,(len(i))):
		horses.append(0)
	#print(horses)
	total = 0
	sec = 0
	done = False
	while(done != True):
		sec = sec + 1
		#print(str(sec))
		for i in range(len(horses)):
			horses[i] += random.randrange(MinSp,(MaxSp+1))
		for i in range(len(horses)):
			if(horses[i] >= Track and (horses[i] == max(horses))):
				print("WINNER: " + names[i])
				done = True
		if(sec%10 == 0):
			print("~~~~~" + str(sec) + " SECOND MARK~~~~~")
			for i in range(len(horses)):
				print(names[i] + ": " + str(horses[i]) + "ft")
		

main()
