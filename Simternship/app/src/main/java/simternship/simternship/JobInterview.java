package simternship.simternship;

import java.util.Arrays;
import java.util.List;
import java.util.Iterator;

public class JobInterview implements QuestionSession<InterviewQuestion> {
	private String companyName;
	private List<InterviewQuestion> questions;
	private Iterator<InterviewQuestion> iterator;
	private InterviewQuestion current;
	private int score = 0;

	public JobInterview(String companyName, List<InterviewQuestion> questions) {
		this.companyName = companyName;
		this.questions = questions;
	}

	public JobInterview(String companyName) { //constructor to test interview
		this(companyName, Arrays.asList(
				new InterviewQuestion("What is your first name?", GameState.getInstance().getFirstName())
		));
	}

	public String getCompanyName() {
		return companyName;
	}

	public int getScore() {
		return score;
	}

	@Override
	public boolean hasHints() {
		return false;
	}

	@Override
	public void begin() {
		current = null;
		iterator = questions.iterator();
	}

	@Override
	public List<InterviewQuestion> getQuestions() {
		return questions;
	}

	@Override
	public void submitAnswer(String answer) {
		if (current.getAnswer().equals(answer)) {
			score++;
		}
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public InterviewQuestion next() {
		current = iterator.next();
		return current;
	}
}