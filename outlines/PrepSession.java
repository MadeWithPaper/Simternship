import java.util.*;

public class PrepSession {
	private PrepQuestion[] questions;
	private int score = 0;
	private int currentQuestion = 0;

	public PrepSession() {
		questions = new PrepQuestion[10];
	}

	public int getScore() {
		return score;
	}

	public InterviewQuestion getNextQuestion() {
		currentQuestion++;
		return questions[currentQuestion-1];
	}

	public boolean submitAnswer(String answer) {
		if (questions[currentQuestion-1].answer == answer) {
			score++;
			return true;
		}
		else {
			return false;
		}
	}
}