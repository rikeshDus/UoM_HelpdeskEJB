package businessLogic;

public class KnowledgeBaseMapping {

	private int id;
	private String question;
	private String queryFormat;
	private String answerFormat;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getQueryFormat() {
		return queryFormat;
	}
	public void setQueryFormat(String queryFormat) {
		this.queryFormat = queryFormat;
	}
	public String getAnswerFormat() {
		return answerFormat;
	}
	public void setAnswerFormat(String answerFormat) {
		this.answerFormat = answerFormat;
	}
	
	
}
