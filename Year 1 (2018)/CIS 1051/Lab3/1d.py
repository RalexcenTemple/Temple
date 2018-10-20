import random
import time

Mile = 5280
Track = 2*(Mile)

MaxSp = 40
MinSp = 4
def main():
	print("Please enter the number of horses running:")
	ui = eval(input("...: "))
	Speed(ui)
	

def Speed(i):
	hNum = i
	horses=[]
	for x in range(0,(hNum)):
		horses.append(0)
	#print(horses)
	total = 0
	sec = 0
	done = False
	while(done != True):
		for i in range(len(horses)):
			horses[i] += random.randrange(MinSp,(MaxSp+1))
		for i in range(len(horses)):
			if(horses[i] >= Track and (horses[i] == max(horses))):
				print("Of the " + str(len(horses)) + " horses")
				print("The WINNER was: " + str(i))
				done = True
	#while(total < Track):
		#sec = sec + 1
		#secSp = random.randrange(MinSp,(MaxSp+1))
		#total = total + secSp
		#print("Sec: " + str(sec) + "| Sec Speed: " + str(secSp) + "| Total Distance: " + str(total))
	#print("Total Seconds: " + str(sec))


main()
