package entities;

public class Table {

	private int id;
	private int polliD;
	private String title;
	
	//Questions and Answers
	private Question[] questions;
	private Answer[] answers;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPolliD() {
		return polliD;
	}
	public void setPolliD(int polliD) {
		this.polliD = polliD;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Question[] getQuestions() {
		return questions;
	}
	public void setQuestions(Question[] questions) {
		this.questions = questions;
	}
	public Answer[] getAnswers() {
		return answers;
	}
	public void setAnswers(Answer[] answers) {
		this.answers = answers;
	}
	
		
	
}
