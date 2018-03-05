package simternship.simternship;


import java.util.LinkedList;

public class PrepSession {
	public LinkedList<PrepQuestion> questions;
	public PrepSession(LinkedList<PrepQuestion> questions){ this.questions = questions;}
	public PrepSession(){
		PrepQuestion q1 = new PrepQuestion("Question 1: first question", "first answer");
		PrepQuestion q2 = new PrepQuestion("Question 2: second question", "second answer");
		questions = new LinkedList<PrepQuestion>();
		questions.add(q1);
		questions.add(q2);
	}
	public LinkedList<PrepQuestion> getQuestionList(){return questions;}
	/*
	private String companyName;
	private PrepQuestion[] questions;
	private int score = 0;
	private int currentQuestionIndex = 0;

	public PrepSession(String companyName) {
		this.companyName = companyName;
		questions = new PrepQuestion[10];
	}

	public String getCompanyName() {
		return companyName;
	}

	public int getScore() {
		return score;
	}

	public PrepQuestion getNextQuestion() {
		currentQuestionIndex++;
		return questions[currentQuestionIndex-1];
	}

	public boolean submitAnswer(String answer) {
		PrepQuestion currentQuestion = questions[currentQuestionIndex - 1];
		if (currentQuestion.getAnswers()[currentQuestion.getCorrectAnswerIndex()] == answer) {
			score++;
			return true;
		}
		else {
			return false;
		}
	}*/
}