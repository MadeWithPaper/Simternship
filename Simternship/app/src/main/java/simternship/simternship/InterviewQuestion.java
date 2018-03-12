package simternship.simternship;
//Dustyn Zierman-Felix
//Simternship

public class InterviewQuestion implements Question
{
	private String question;
	private String answer;


	public InterviewQuestion(String question, String answer) {
		this.question = question;
		this.answer = answer;
	}

	@Override
	public String getHint() {
		return null;
	}

	public String getQuestion() {
		return this.question;
	}

	public String getAnswer() {
		return this.answer;
	}

}
