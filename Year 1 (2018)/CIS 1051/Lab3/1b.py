import random
import time

Mile = 5280
Track = 2*(Mile)

MaxSp = 40
MinSp = 4
def main():
	print("Please select which version to run:")
	print("Speed Run: 1")
	print("Visual Run: 2")
	ui = eval(input("...: "))

	if(ui == 1):
		Speed()
	else:
		Visual()

def Speed():
	total = 0
	sec = 0
	while(total < Track):
		sec = sec + 1
		secSp = random.randrange(MinSp,(MaxSp+1))
		total = total + secSp
		#print("Sec: " + str(sec) + "| Sec Speed: " + str(secSp) + "| Total Distance: " + str(total))
	print("Total Seconds: " + str(sec))

def Visual():
	total = 0
	sec = 0
	qtr = (5)*(528)
	time.sleep(.3)
	print("ON YOUR MARKS")
	time.sleep(.3)
	print("GET SET")
	time.sleep(.3)
	print("GO")
	print("Progress: " , end="")
	while(total < Track):
		time.sleep(0.05)
		sec = sec + 1
		secSp = random.randrange(MinSp,(MaxSp+1))
		if(secSp < 10):
			print(".",flush = True,end="")
		elif(secSp < 20):
			print("-",flush = True,end="")
		elif(secSp < 30):
			print("__", flush=True,end="")
		else:
			print("||||",flush=True ,end="")
		total = total + secSp
		#print("Sec: " + str(sec) + "| Sec Speed: " + str(secSp) + "| Total Distance: " + str(total))
	print("~~~~FINISH~~~~")
	print(" ")
	time.sleep(.5)
	print("Total Seconds: " + str(sec))

main()
