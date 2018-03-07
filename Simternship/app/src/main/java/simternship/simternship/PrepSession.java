package simternship.simternship;


import java.util.LinkedList;

public class PrepSession {
	public LinkedList<PrepQuestion> questions;
	public PrepSession(LinkedList<PrepQuestion> questions){ this.questions = questions;}
	public PrepSession(){

	}
	public LinkedList<PrepQuestion> getQuestionList(){
		return getQuestionList(2);
	}
	public LinkedList<PrepQuestion> getQuestionList(int questionType){
		if (questionType == 0){ //behavioural
			PrepQuestion q1 = new PrepQuestion("Question 1: What motivated you to apply for this position?", "Your response should cover two main areas: 1) Why this opportunity appeals" +
					"to you and 2) How your skills and career goals will contribute to the employer." +
					"Thorough research of the employer and position is key to articulating why" +
					"they appeal to you. The employer wants to know that you are genuinely" +
					"interested. To describe how your skills and career goals fit with the position," +
					"review the job description and develop examples to demonstrate how your" +
					"skills and experience meet the employer’s needs.");
			PrepQuestion q2 = new PrepQuestion("Question 2: Describe your current or most recent job, internship, or leadership experience.", "It’s easy to get lost in describing the day-to-day details of your most recent" +
					"experience. What employers really want to know is how this experience" +
					"makes you qualified for the position. Focus on accomplishments and" +
					"achievements, using specific examples (quantify when possible), to highlight" +
					"your ability to contribute to the employer.");
			questions = new LinkedList<PrepQuestion>();
			questions.add(q1);
			questions.add(q2);
		}
		else { //technical
			PrepQuestion q1 = new PrepQuestion("Question 1: What is polymorphism?", "polymorphism is the ability (in programming) to present the same interface for differing underlying forms (data types).");
			PrepQuestion q2 = new PrepQuestion("Question 2: Find the common elements of 2 int arrays", "think about sorting first");
			questions = new LinkedList<PrepQuestion>();
			questions.add(q1);
			questions.add(q2);
		}
		return questions;
	}
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