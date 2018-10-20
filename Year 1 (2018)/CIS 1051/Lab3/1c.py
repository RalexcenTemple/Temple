import random
import time

Mile = 5280
Track = 2*(Mile)

MaxSp = 40
MinSp = 4
def main():
	#print("Please select which version to run:")
	#print("Speed Run: 1")
	#ui = eval(input("...: "))
	
	#if(ui == 1):
	Speed()

def Speed():
	overAll = 0
	for i in range(0,1000):
		total = 0
		sec = 0
		while(total < Track):
			sec = sec + 1
			secSp = random.randrange(MinSp,(MaxSp+1))
			total = total + secSp
			#print("Sec: " + str(sec) + "| Sec Speed: " + str(secSp) + "| Total Distance: " + str(total))
		#print("Total Seconds: " + str(sec))
		overAll = overAll + sec
	allAve = (overAll/1000)
	print("Average Seconds: " + str(allAve))

main()
