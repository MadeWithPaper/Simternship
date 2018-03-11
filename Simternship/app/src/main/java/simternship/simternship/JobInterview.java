package simternship.simternship;

import java.util.Arrays;
import java.util.List;
import java.util.Iterator;

public class JobInterview implements QuestionSession<InterviewQuestion> {
	private Company company;
	private List<InterviewQuestion> questions;
	private Iterator<InterviewQuestion> iterator;
	private InterviewQuestion current;
	private int score = 0;

	public JobInterview(Company company, List<InterviewQuestion> questions) {
		this.company = company;
		this.questions = questions;
	}

	/*
	public JobInterview( companyName) { //constructor to test interview
		this(companyName, Arrays.asList(
				new InterviewQuestion("What is your first name?", GameState.getInstance().getFirstName())
		));
	}*/

	public Company getCompany() {
		return company;
	}

	void setScore(int score) {
		this.score = score;
	}

	public String getCompanyName() {
		return company.getCompanyName();
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