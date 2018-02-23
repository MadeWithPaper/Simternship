package simternship.simternship;

public class JobInterview {
	private String companyName;
	private InterviewQuestion[] questions;
	private int score = 0;
	private int currentQuestionIndex = 0;

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
		currentQuestionIndex++;
		return questions[currentQuestionIndex-1];
	}

	public boolean submitAnswer(String answer) {
		InterviewQuestion currentQuestion = questions[currentQuestionIndex - 1];
		if (currentQuestion.getAnswers()[currentQuestion.getCorrectAnswerIndex()] == answer) {
			score++;
			return true;
		}
		else {
			return false;
		}
	}
}