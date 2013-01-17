package businessLogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class EventManager
 */
@Stateless
@LocalBean
public class EventManager {

    /**
     * Default constructor. 
     */
    public EventManager() {
        // TODO Auto-generated constructor stub
    }
    
    public int createEvent(String title, String description, String type, String user_id){
    	Connection con;
    	String query;
    	PreparedStatement pstmt;
    	int insert=0;
    	int t_iVersion = 0;
    	
    	DatabaseConnection dbconnect = new DatabaseConnection();
    	con = dbconnect.getConnection();
    	
    	query = "INSERT INTO event VALUES (null,?,?,?,?)";
    	
    	try{
    		pstmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
    		/*
    		 * , PreparedStatement.RETURN_GENERATED_KEYS);  
				int rownum = t_pstmt.executeUpdate();  
				t_resultset = t_pstmt.getGeneratedKeys();  
				if( rownum != 0 && !t_resultset.next()) {  
				      t_iVersion = t_resultset.getInt(1);  
				}  
    		 */
    		
    		pstmt.setString(1, title);
    		pstmt.setString(2, description);
    		pstmt.setString(3, type);
    		pstmt.setString(4, user_id);
    		
    		insert = pstmt.executeUpdate();
    		
    		ResultSet t_resultset = pstmt.getGeneratedKeys();
    		t_resultset.next();
    		/*System.out.println(t_resultset.getInt(1));
			if( insert != 0 && !t_resultset.next()) {  
			*/      	t_iVersion = t_resultset.getInt(1);  
			      	
			//}  
			
			con.close();
			t_resultset.close();
			pstmt.close();
    	}
    	catch(SQLException sqle){
    		//load error page
    	}    	
    	
    	return t_iVersion; 		
    }//end

}
