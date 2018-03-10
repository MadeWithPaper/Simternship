package simternship.simternship;


import java.util.Iterator;
import java.util.List;

public class PrepSession implements QuestionSession<PrepQuestion> {
	private List<PrepQuestion> questions;
	private Iterator<PrepQuestion> iterator;


	public PrepSession(List<PrepQuestion> questions){
		this.questions = questions;
	}

	public List<PrepQuestion> getQuestions() {
		return this.questions;
	}

	public boolean hasHints() {
		return true;
	}

	public void submitAnswer(String answer) {
		//ignore the answer
	}

	public void begin() {
		this.iterator = this.questions.iterator();
	}

	public PrepQuestion next() {
		return this.iterator.next();
	}

	public boolean hasNext() {
		return this.iterator.hasNext();
	}

	public void remove() {
		//ignore
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