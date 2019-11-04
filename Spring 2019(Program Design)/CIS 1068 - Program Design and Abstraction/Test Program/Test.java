//Reed Ceniviva
//Temple University
//4/12/19
public class Test {
	question[] Qs = new question[]{};
	int totalPoints;
	Test(question[] Qs) {
		this.Qs = Qs;
		int points = 0;
		for(int i = 0; i < Qs.length;i++) {
			points += Qs[i].getPoints();
		}
		this.totalPoints = points;
	}
	public int getPoints() {
		return this.totalPoints;
	}
	//these are easily converted to produce strings rather than to print it out
	//by changing the println's to instead concatenate to a single string
	//but the goal is to print out the test so I'll leave it like this
	public void printTest() {
		System.out.println("Test:");
		System.out.println("Total Points: " + this.getPoints());
		System.out.println("");
		for(int i = 0; i < this.Qs.length;i++) {
			System.out.println(this.Qs[i].toString());
		}
	}
	//was going to overload the printTest function but wasnt really useful
	public void printTestA() {
			System.out.println("Test:Total Points: " + this.getPoints());
			System.out.println("");
			for(int i = 0; i < this.Qs.length;i++) {
				System.out.println(this.Qs[i].toStringA());
				System.out.println("\n\n");
			}
		}
}

class question{
	int MIN_DIFFICULTY,MAX_DIFFICULTY;//the difficulty of the questions is never used
	int points;
	int difficulty;
	int answerSpace;
	String questionText;
	public question(int points,int difficulty, int answerSpace, String questionText){
		this.points = points;
		this.difficulty = difficulty;
		this.answerSpace = answerSpace;
		this.questionText = questionText;
		MIN_DIFFICULTY = 1;
		MAX_DIFFICULTY = 10;
	}
	
	public int getPoints() {
		return this.points;
	}
	
	public String getSpace(int answerSpace) {
		String temp = "";
		for(int i = 0; i < answerSpace; i++) {
			temp = temp +"\n";
		}
		return temp;
	}
	public String toString() {
		String text = ("Points(" + this.points + ")\n");
		text = text + ("Question:\n");
		text = text + (this.questionText + "\n");
		text = text + getSpace(answerSpace);
		return text;
	}
	//couldnt get the answers inherited toString functions to work overloaded without one in the base
	public String toStringA() {
		String text = ("Points(" + this.points + ")\n");
		text = text + ("Question:\n");
		text = text + (this.questionText + "\n");
		text = text + getSpace(answerSpace);
		return text;
	}
}

class objectiveQuestion extends question{
	String correctAnswer;
	public objectiveQuestion(int points, int difficulty, int answerSpace, String questionText,String correctAnswer) {
		super(points, difficulty, answerSpace, questionText);
		this.correctAnswer = correctAnswer;
	}
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
	public String toString() {
		String text = ("Points(" + this.points + ")\n");
		text = text + ("Question:\n");
		text = text + (this.questionText + "\n");
		text = text + getSpace(answerSpace);
		return text;
	}
	
	public String toStringA() {
			String text = ("Points(" + this.points + ")\n");
			text = text + ("Question:\n");
			text = text + (this.questionText + "\n");
			text = text + ("ANSWER:\n" + this.correctAnswer + "\n");
			return text;
	}
}

class fillInTheBlankQuestion extends question{
	String correctAnswer;
	public fillInTheBlankQuestion(int points, int difficulty, int answerSpace, String questionText,String correctAnswer) {
		super(points, difficulty, answerSpace, questionText);
		this.correctAnswer = correctAnswer;
	}
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
	public String toString() {
		String text = ("Points(" + this.points + ")\n");
		text = text + ("Question:\n");
		text = text + (this.questionText + "\n");
		text = text + getSpace(answerSpace);
		return text;
	}
	
	public String toStringA() {
			String text = ("Points(" + this.points + ")\n");
			text = text + ("Question:\n");
			text = text + (this.questionText + "\n");
			text = text + ("ANSWER:\n" + this.correctAnswer + "\n");
			return text;
	}
}

class multipleChoiceQuestion extends question{
	String correctAnswer;
	String possibleAnswers;
	public multipleChoiceQuestion(int points, int difficulty, int answerSpace, String questionText,String possibleAnswers,String correctAnswer) {
		super(points, difficulty, answerSpace, questionText);
		this.correctAnswer = correctAnswer;
		this.possibleAnswers = possibleAnswers;
	}
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
	public String toString() {
		String text = ("Points(" + this.points + ")\n");
		text = text + ("Question:\n");
		text = text + (this.questionText + "\n");
		text = text + (this.possibleAnswers + "\n");
		text = text + getSpace(answerSpace) + "\n";
		return text;
	}
	
	public String toStringA() {
			String text = ("Points(" + this.points + ")\n");
			text = text + ("Question:\n");
			text = text + (this.questionText + "\n");
			text = text + ("ANSWER:\n" + this.correctAnswer + "\n");
			return text;
	}
}
