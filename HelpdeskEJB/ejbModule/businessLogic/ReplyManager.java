package businessLogic;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class ReplyManager
 */
@Stateless
@LocalBean
public class ReplyManager {

    /**
     * Default constructor. 
     */
    public ReplyManager() {
        // TODO Auto-generated constructor stub
    }
    
    public int createReply( int queryId, String userId, int trackingId, String answer ){
    	Connection con;
    	String sql_query;
    	PreparedStatement pstmt;
    	int insert = 0;
    	
    	DatabaseConnection dbconnect = new DatabaseConnection();
    	con = dbconnect.getConnection();
    	
    	sql_query = "Insert INTO reply VALUES(?,?,?,?,?)";
    	
    	java.util.Calendar cal = java.util.Calendar.getInstance();
		java.util.Date utilDate = cal.getTime();
		java.sql.Date sqlDate = new Date(utilDate.getTime());
    	
    	try {
    		pstmt = con.prepareStatement(sql_query);
    		
    		pstmt.setInt(1, trackingId);
    		pstmt.setString(2, userId);
    		pstmt.setInt(3, queryId);
    		pstmt.setDate(4, sqlDate);
    		pstmt.setString(5, answer);
    		
    		
    		insert = pstmt.executeUpdate();
    		
    		
    		con.close();
			pstmt.close();
			
			return insert;
    	}catch(SQLException e){
    		//load erropage
    	}
    	
    	return insert;
    }// end of public int createReply( int queryId, String userId, int trackingId, String answer ){
    
    private static java.sql.Timestamp getCurrentTimeStamp() {
    	 
    	java.util.Date today = new java.util.Date();
    	return new java.sql.Timestamp(today.getTime());
     
    }
    
}
