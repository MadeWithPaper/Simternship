//Dustyn Zierman-Felix
//Simternship
//Tester class for InterviewQuestion and PrepQuestion classes

public class Tester 
{
	public static void main(String[] args) 
	{
		PrepQuestion pq = new PrepQuestion();
		int size = pq.getPrepQuestionMapSize();
		
		for(int i = 0; i < size; i++)
			pq.getQuestion();
		
		pq.getStats();
		
		/*
		System.out.println();
		
		InterviewQuestion iq = new InterviewQuestion();
		size = iq.getInterviewQuestionMapSize();
		
		for(int i = 0; i < size; i++)
			iq.getQuestion();
		
		iq.getStats();*/
		
		//pq.viewQuestions();
	}
}
