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

	@Override
	public PrepQuestion next() {
		return this.iterator.next();
	}

	@Override
	public boolean hasNext() {
		return this.iterator.hasNext();
	}

	@Override
	public void remove() {
		//ignore
	}
}