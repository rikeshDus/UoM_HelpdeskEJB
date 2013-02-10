package businessLogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class AnswerManager
 */
@Stateless
@LocalBean
public class AnswerManager {

    /**
     * Default constructor. 
     */
    public AnswerManager() {
        // TODO Auto-generated constructor stub
    }
    
    public ArrayList<Answer> findAnswerByQuestion(Question quest){
    	Connection con;
    	String sql_query;
    	PreparedStatement pstmt;
    	ResultSet rs = null;
    	Answer answer =null;
    	ArrayList<Answer> allAnswer = new ArrayList<Answer>();
    	
    	DatabaseConnection dbconnect = new DatabaseConnection();
    	con = dbconnect.getConnection();
    	
    	sql_query = "SELECT * FROM answer WHERE question_id = ?";
    	try{
    		pstmt = con.prepareStatement(sql_query);
    		
    		pstmt.setInt(1, quest.getQuestion_id());
    		
    		rs = pstmt.executeQuery();
    		
    		while (rs.next()) {
				answer = new Answer();
				answer.setAnswer(rs.getString("answer"));
				answer.setAnswer_id(rs.getInt("answer_id"));
				answer.setQuestion_id(rs.getInt("question_id"));
				
				allAnswer.add(answer);
			}//end of while (rs.next()) {
    		
    	}
    	catch(SQLException se){
    		
    	}
    	
    	
    	return allAnswer;
    }//public ArrayList<Answer> findAnswerByQuestion(Question quest){


    public ArrayList<Answer> getAllAnswer(){
		Connection con;
		Statement stmt;
		ResultSet rs;
		Answer temporaryAnswer;
		ArrayList<Answer> allAnswer = new ArrayList<Answer>();
		String query = "SELECT * FROM answer";
		
		DatabaseConnection dbconnect = new DatabaseConnection();
		con = dbconnect.getConnection();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()){
				
				temporaryAnswer = new Answer();
				
				temporaryAnswer.setAnswer_id(rs.getInt("answer_id"));
				temporaryAnswer.setQuestion_id(rs.getInt("question_id"));
				temporaryAnswer.setAnswer(rs.getString("answer"));
				
				allAnswer.add(temporaryAnswer);
				
			}//end while(rs.next())
			
			
			rs.close();
			stmt.close();
			con.close();
			
			return allAnswer;
			
		} catch (SQLException e) {
			
			//load errorpage
		}	
		
		return null;		
}//end  public ArrayList<Tracking> getAllTracking(){

}
