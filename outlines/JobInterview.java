import java.util.*;

public class JobInterview {
	private String companyName;
	private InterviewQuestion[] questions;
	private int score = 0;
	private int currentQuestion = 0;

	public JobInterview(String companyName) {
		this.companyName = companyName;
		questions = new InterviewQuestion[10];
	}

	public String getCompanyName() {
		return companyName;
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