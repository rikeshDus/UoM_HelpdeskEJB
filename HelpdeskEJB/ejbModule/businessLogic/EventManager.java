package businessLogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
    
    public boolean createEvent(String title, String description, String type, String user_id){
    	Connection con;
    	String query;
    	PreparedStatement pstmt;
    	int insert=0;
    	
    	DatabaseConnection dbconnect = new DatabaseConnection();
    	con = dbconnect.getConnection();
    	
    	query = "INSERT INTO event VALUES('',?,?,?,?)";
    	
    	try{
    		pstmt = con.prepareStatement(query);
    		
    		pstmt.setString(1, title);
    		pstmt.setString(2, description);
    		pstmt.setString(3, type);
    		pstmt.setString(4, user_id);
    		
    		insert = pstmt.executeUpdate();
    		
    	}
    	catch(SQLException sqle){
    		//load error page
    	}    	
    	
    	if(insert>0){
    		return true;
		}    	
		return false;    		
    }//end

}
