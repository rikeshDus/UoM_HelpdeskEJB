package businessLogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
