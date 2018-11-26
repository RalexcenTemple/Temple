import random

#a basic tic-tac-toe program was provided to us. we were tasked with:
#1) Print an appropriate message when either the human or the machine wins.
#2) Accept both upper case and lower case letters to indicate the row and column
#3) Shorten the print board function by using loops
#4) Use a dictionary to find the meaning of the letters T, M, B, L, R when they are entered.
#5) Use a better scheme for helping the machine choose its play.

def index2location(i): #0 <= i <= 8
  if type(i) != type(1):
    raise TypeError
  if not 0 <= i <= 8:
    raise ValueError
  row = i // 3
  col = i % 3 
  return (row, col)

def location2index(where): #where is a tuple (row, col)
  if type(where) != type((1,2)):
    raise TypeError
  if len(where) != 2:
    raise TypeError
  if not 0<=where[0] <= 2:
    raise ValueError
  if not 0 <= where[1] <= 2:
    raise ValueError
  row = where[0]
  col = where[1]
  retval = row * 3 + col
  return retval

def newTicTac():
  xs = []
  os = []
  empties = [0, 1, 2, 3, 4, 5, 6, 7, 8]
  return (xs, os, empties)

def makeMove(xory, row, col, xs, os, empties):
  if xory.upper() != 'X' and xory.upper() != 'O':
    raise ValueError("error wit the symbol: " + str(xory))#added some more helpful errors
  if not 0 <= row <= 2:
    raise ValueError("row value out of range: " + str(row))
  if not 0 <= col <= 2:
    raise ValueError("col value out of range: " + str(col))
  i = location2index((row, col))
  if i in empties:
    if xory == 'X':
      xs.append(i)
    elif xory == 'O':
      os.append(i)
    empties.remove(i)
  else:
    raise ValueError("spot index not in empty list: " + str(i))

def print_board(xs, os, empties):
  print('   L  M  R')
  print(' ---------')
  print('T|', end='')
  temp = [2,5]
  rows = ["M","B"," "]
  j = 0
  for i in range(9): #print the board using a loop
    if i in xs:
      next = 'X'
    elif i in os:
      next = 'O'
    else:
      next = ' '
    if i in temp:
      print(next,'|')
      print("---------")
      print(rows[j] + '|', end='')
      j+=1
    elif i == 8:
      print(next,'|')
      print("---------")
    else:
      print(next,"|",end="")
  
def won(xos):# xos is xs to check for x win, ys to check for y win
  if len(xos) < 3:
    return False
  if 0 in xos and 1 in xos and 2 in xos:
    return True
  if 3 in xos and 4 in xos and 5 in xos:
    return True
  if 6 in xos and 7 in xos and 8 in xos:
    return True
  if 0 in xos and 3 in xos and 6 in xos:
    return True
  if 1 in xos and 4 in xos and 7 in xos:
    return True
  if 2 in xos and 5 in xos and 8 in xos:
    return True
  if 0 in xos and 4 in xos and 8 in xos:
    return True
  if 2 in xos and 4 in xos and 6 in xos:
    return True
  return False

def isValid(xs, os, spaces):
  retval = True
  if not (len(xs) == len(os) or len(xs) == len(os)+1):
    retval = False
  if won(xs) and won(os):
    retval = False
  return retval


  
if __name__ == "__main__":
  xs, os, spaces = newTicTac()
  print_board(xs, os, spaces)
  moves_made = 0
  done = False
  RandC = {} #dict containing the possible row and col representation and their values
  RandC['T'] = 0
  RandC['M'] = 1
  RandC['B'] = 2
  RandC['L'] = 0
  RandC['R'] = 2
  while not done:
    found = False
    if 4 in spaces:
      found = True 
      where = 4 # take middle spot if open
    elif 4 in xs:
      corns = (0,2,6,8) #take corners if open
      for i in range(len(corns)):
        if corns[i] in spaces:
          found = True # should be the only useable change of the found variable
          where = corns[i]
          break #quit looking for corners once one is found
      perim = (1,3,5,7) #take the rest
      if not found: # dont look for another space if a corner was found
        for j in range(len(perim)):
          if perim[j] in spaces:
            found = True
            where = perim[j]
            break
    else: #worst case guess randomly of whats open
      where = random.choice(spaces)
    row, col = index2location(where)
    makeMove('X', row, col, xs, os, spaces)
    print("~~~~~~~ X's Move ~~~~~~")
    print_board(xs, os, spaces)
    if won(xs):
      print("X's Win")#print if X won
      done = True
    else:
      r = input('Which row for your O (T, M, or B):')
      r = r.upper()
      try:
        R = RandC[r]#use dict to get value of user input
      except KeyError:
        print("Learn to read directions")
        print("You lose")
        done = True
        exit(1)
      row = R
      c = input('Which row for your O (L, M, or R):')
      c = c.upper()
      try:
        C = RandC[c]
      except KeyError:#use exceptions to catch incorrect user input
        print("Learn to read directions")
        print("You lose")
        done = True
        exit(1)
      col = C
      if not done:
        makeMove('O', row, col, xs, os, spaces)  
        if won(os):
          print_board(xs, os, spaces)
          print("O's Win")#announce if O won
          done = True
        else:
          print("~~~~~~ O's Move ~~~~~~")
          print_board(xs, os, spaces)
