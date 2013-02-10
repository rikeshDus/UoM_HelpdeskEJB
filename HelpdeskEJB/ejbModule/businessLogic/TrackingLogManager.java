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
 * Session Bean implementation class TrackingLogManager
 */
@Stateless
@LocalBean
public class TrackingLogManager {

    /**
     * Default constructor. 
     */
    public TrackingLogManager() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<TrackingLog> getAllTrackingLogManager(){
		Connection con;
		Statement stmt;
		ResultSet rs;
		TrackingLog temporaryTrackingLog;
		ArrayList<TrackingLog> allTrackingLog = new ArrayList<TrackingLog>();
		String query = "SELECT * FROM tracking_log";
		
		DatabaseConnection dbconnect = new DatabaseConnection();
		con = dbconnect.getConnection();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()){
				
				temporaryTrackingLog = new TrackingLog();
				
				temporaryTrackingLog.setTracking_log_id(rs.getInt("tracking_log_id"));
				temporaryTrackingLog.setTracking_id(rs.getInt("tracking_id"));
				temporaryTrackingLog.setQuestion(rs.getInt("question_id"));
				temporaryTrackingLog.setDate(rs.getDate("date"));
				temporaryTrackingLog.setReciever(rs.getString("receiver"));
				
				allTrackingLog.add(temporaryTrackingLog);
				
			}//end while(rs.next())
			
			
			rs.close();
			stmt.close();
			con.close();
			
			return allTrackingLog;
			
		} catch (SQLException e) {
			
			//load errorpage
		}	
		
		return null;		
}//end  public ArrayList<Tracking> getAllTracking(){
    
    
    
    
    public int createTrackingLog(int tracking_id,int question_id,String receiver)
    {
    	Connection con;
    	String sql_query;
    	PreparedStatement pstmt;
    	ResultSet rs = null;
    	int insert = 0;
    	int t_iVersion = 0;
    	
    	DatabaseConnection dbconnect = new DatabaseConnection();
    	con = dbconnect.getConnection();
    	
    	sql_query = "Insert INTO tracking_log VALUES(?,?,?,?,?)";
    	
    	try {
    		pstmt = con.prepareStatement(sql_query, PreparedStatement.RETURN_GENERATED_KEYS);
    		
    		pstmt.setInt(1, 0);
    		pstmt.setInt(2, tracking_id);
    		pstmt.setInt(3, question_id);
    		pstmt.setDate(4, null);
    		pstmt.setString(5, receiver);
    		
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
    }//end public boolean createTrackingLog()
    
    public TrackingLog findTrackingLog(int trackingLogId){
    	
    	Connection con;
    	String sql_query;
    	PreparedStatement pstmt;
    	ResultSet rs = null;
    	TrackingLog trackingLog = null;
    	
    	DatabaseConnection dbconnect = new DatabaseConnection();
    	con = dbconnect.getConnection();
    	
    	sql_query = "SELECT * FROM tracking_log WHERE tracking_log_id = ?";
    	try{
    		pstmt = con.prepareStatement(sql_query);
    		
    		pstmt.setInt(1, trackingLogId);
    		
    		rs = pstmt.executeQuery();
    		
    		while(rs.next()){
    			
    			trackingLog = new TrackingLog();
    			trackingLog.setDate(rs.getDate("date"));
    			trackingLog.setQuestion(rs.getInt("question_id"));
    			trackingLog.setTracking_id(rs.getInt("tracking_id"));
    			trackingLog.setTracking_log_id(rs.getInt("tracking_log_id"));  			
    			trackingLog.setReciever(rs.getString("receiver"));
    			
    			rs.close();
        		pstmt.close();
        		con.close();
        		return trackingLog;
    		}//end while(rs.next()){
    		
    	}//end try
    	catch(SQLException se){
    		//load error page
    	}
    	
    	
    	return null;
    }//public TrackingLog findTrackingLog(int trackingLogId){
}
    

