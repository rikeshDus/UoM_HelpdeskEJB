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
 * Session Bean implementation class QuestionManager
 */
@Stateless
@LocalBean
public class QuestionManager {

    /**
     * Default constructor. 
     */
    public QuestionManager() {
        // TODO Auto-generated constructor stub
    }
    
    
    public ArrayList<Question> getAllQuestion(){
		Connection con;
		Statement stmt;
		ResultSet rs;
		Question temporaryQuestion;
		ArrayList<Question> Question = new ArrayList<Question>();
		String query = "SELECT * FROM question";
		
		DatabaseConnection dbconnect = new DatabaseConnection();
		con = dbconnect.getConnection();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()){
				
				temporaryQuestion = new Question();
				
				temporaryQuestion.setQuestion_id(rs.getInt("question_id"));
				temporaryQuestion.setQuestion(rs.getString("question"));
				
				Question.add(temporaryQuestion);
				
			}//end while(rs.next())
			
			
			rs.close();
			stmt.close();
			con.close();
			
			return Question;
			
		} catch (SQLException e) {
			
			//load errorpage
		}	
		
		return null;		
}//end  public ArrayList<Tracking> getAllTracking(){
    
    
    public int CreateQuestion(String question){
    	Connection con;
    	String sql_query;
    	PreparedStatement pstmt;
    	ResultSet rs = null;
    	int insert = 0;
    	int t_iVersion = 0;
    	
    	DatabaseConnection dbconnect = new DatabaseConnection();
    	con = dbconnect.getConnection();
    	
    	sql_query = "Insert INTO question VALUES(?,?)";
    	
    	try {
    		pstmt = con.prepareStatement(sql_query, PreparedStatement.RETURN_GENERATED_KEYS);
    		
    		pstmt.setInt(1, 0);
    		pstmt.setString(2, question);
    		
    		
    		insert = pstmt.executeUpdate();
    		rs = pstmt.getGeneratedKeys();
    		rs.next();
    		t_iVersion = rs.getInt(1);  
    		
    		
    		con.close();
    		rs.close();
			pstmt.close();
			
			//create tracking log
			
			
    	}catch(SQLException e){
    		//load erropage
    	}
    	
    	return t_iVersion;
    }//end public int CreateQuestion(){


    public Question findQuestion(int question_id){
    	Connection con;
    	String query;
    	PreparedStatement pstmt;
    	ResultSet rs = null;
    	Question temporaryQuestion = null;
    	
    	DatabaseConnection dbconnect = new DatabaseConnection();
    	con = dbconnect.getConnection();
    	
    	query = "SELECT * FROM question WHERE question_id = ?";
    	
    	try {
    		pstmt = con.prepareStatement(query);
    		
			pstmt.setInt(1, question_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				temporaryQuestion = new Question();
				temporaryQuestion.setQuestion_id(rs.getInt("question_id"));
				temporaryQuestion.setQuestion(rs.getString("question"));
			}//end while
			
			
			rs.close();
    		pstmt.close();
    		con.close();
    		return temporaryQuestion;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   	
    	
    	
		return null;    	
    }//end public Question findQuestion(int question_id){

}//end of class
