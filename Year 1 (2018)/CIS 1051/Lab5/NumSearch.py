import math
import random

def main():
	print("welcome to the random number finder")
	print("please think of a number between 1 and 100")
	print("if the computers guess is larger than your number,")
	print("Enter: <")
	print("if the computers guess is smaller than your number,")
	print("Enter: >")
	print("Of the computer gets your number correctly,")
	print("Enter: =")
	print('~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~')
	print("please select the way you'd like to run the program")
	print("1) play a single game with the computer")
	print("2) have the computer play itself a number of times")
	selct = eval(input("...: "))
	print(selct)
	if selct == 1:
		print("calling player game")
		playerGame()
	elif selct == 2:
		print("would you like see the results of each game?")
		print("1) Yes")
		print("2) No")
		step = eval(input("...: "))
		if step == 1:
			t = True
			l = []
			while t:
				l = compGame(l)
				print("Average Turns: " + str(averageTurns(l)))
				print("run another game?")
				print("1) Yes")
				print("2) No")
				loop = eval(input("...: "))
				if loop == 2:
					t = False
		elif step == 2:
			print("Enter the number of games you want the computers to face off in")
			games = eval(input("...: "))
			avs = []
			for i in range(games):
				avs = compGame(avs)
			print("Average Turns: " + str(averageTurns(avs)))
			
	else:
		print("none selected")

def averageTurns(avs):
	SUM = 0
	for i in range(len(avs)):
		SUM += avs[i]
	av = SUM/len(avs)
	return av	

def compGame(avs = []):
	print("comp game started")
	MIN = 1
	MAX = 100
	gue = random.randint(MIN,MAX)
	n = random.randint(MIN,MAX)
	print("Computer 2's num: " + str(gue))
	turn = 0
	c = False
	while not c:
		print("Computer 1 guess: " + str(n))
		#print("Computer 2 num: " + str(gue))
		if gue == n:
			print("The Computer 2's  number was " + str(n))
			print("it took: " + str(turn) + " turns")
			avs += [turn]
			c = True
		elif gue > n:
			MIN = (n+1)
			print("MIN: "+str(MIN))
			print("MAX: "+str(MAX))
			n = random.randint(MIN,MAX)
		elif gue < n:
			MAX = (n-1)
			print("MIN: "+str(MIN))
			print("MAX: "+str(MAX))
			n = random.randint(MIN,MAX)
		else:
			print("please enter the described proper input from the begining of the program")
		turn += 1
	print("game has ended")
	return avs

def playerGame():
	print("entered player game")
	MIN = 1
	MAX = 100
	n = random.randint(MIN,MAX)
	c = False
	turn = 0
	while not c:
		print("Computer Guess: " + str(n))
		ui = input("User Input: ")
		if ui == "=":
			print("The users number was" + str(n))
			print("it took: " + str(turn) + " turns")
			c = True
		elif ui == ">":
			MIN = (n+1)
			print("MIN: "+str(MIN))
			print("MAX: "+str(MAX))
			n = random.randint(MIN,MAX)
		elif ui == "<":
			MAX = (n-1)
			print("MIN: "+str(MIN))
			print("MAX: "+str(MAX))
			n = random.randint(MIN,MAX)
		else:
			print("please enter the described proper input from the begining of the program")
		turn += 1
	print("game has ended")

main()
