package simternship.simternship;

//Dustyn Zierman-Felix
//Simternship

public class PrepQuestion implements Question
{
	private String question;
	private String answer;
	public PrepQuestion(String q, String a){
		this.question = q;
		this.answer = a;
	}
	public String getQuestion(){return this.question;}
	public String getHint(){return this.answer;}
}