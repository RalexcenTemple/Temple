
public class testTest {
	public static void main(String args[]) {
		//question(int points,int difficulty, int answerSpace, String questionText)
	    	objectiveQuestion q1 = new objectiveQuestion(5,1,5,"What color is blue","blue");
	    	question q2 = new question(5,1,5,"Why is the sky blue");
	    	fillInTheBlankQuestion q3 = new fillInTheBlankQuestion(5,1,5,"The sky is the color ____","The sky is the color __Blue__");
	    	multipleChoiceQuestion q4 = new multipleChoiceQuestion(5,1,5,"The color of the sky is","Red\nBlue\nGreen\nYellow","Red\n***Blue***\nGreen\nYellow");
	    	objectiveQuestion q5 = new objectiveQuestion(5,1,5,"what is 2 + 3","5");
	    	fillInTheBlankQuestion q6 = new fillInTheBlankQuestion(5,1,5,"______ was the 16th US President.","___Abraham Lincoln___ was the 16th US President.");
	    	multipleChoiceQuestion q7 = new multipleChoiceQuestion(5,1,5,"Who lives in a pineapple under the sea?","Peter Griffin\nScooby Door\nSpongebob Squarepants\nEric Cartman","Peter Griffin\nScooby Door\n****Spongebob Squarepants****\nEric Cartman");
	    	question[] questions = new question[]{q1,q2,q3,q4,q5,q6,q7};
	    	Test temp = new Test(questions);
	    	//System.out.println(temp.getPoints());
	    	temp.printTest();
	    	System.out.println("**********************************");
	    	temp.printTestA();
	    }
}
