package csc309.simternship;
import java.util.*;

public class PrepSession {
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
	}
}