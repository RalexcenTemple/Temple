def main():
   ids = []
   scores = []
   idNum = 1
   
   #takes in information from user using 0 to stop the loop
   while idNum != 0:
      idNum = eval(input("Please enter an ID number, enter 0 if complete: "))
      if idNum == 0:
         break
      else:
         idScore = eval(input("Please enter a score between 0.0 and 100.0 for " + str(idNum) + ": "))
         ids.append(idNum)
         scores.append(idScore)
   
   #prints the average of the scores
   average = findAve(ids,scores)
   print("The average of the scores was: " + str(average))
   
   grades = []
   grades = getGrades(scores,average)
   
   #print results
   for x in range(0,len(grades)):
      print(str(ids[x]) + " | " + str(scores[x]) + " | " + grades[x])
   
   

#function that handles finding the average of the scores
def findAve(ids,scores):
   average = 0
   for x in range(0,len(scores)):
      average += scores[x]
   result = (average/len(scores))
   return result

def getGrades(scores,average):
   grades = []
   for x in range(0,len(scores)):
      if(scores[x] > (average + 10)):
         grades.append(("A"))
      elif(scores[x] > (average + 5)):
         grades.append(("B"))
      elif(scores[x] > (average - 5)):
         grades.append(("C"))
      elif(scores[x] > (average - 10)):
         grades.append(("D"))
      else:
         grades.append(("F"))
   return grades

main()
