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
 * Session Bean implementation class TrackingManager
 */
@Stateless
@LocalBean
public class TrackingManager {

    /**
     * Default constructor. 
     */
    public TrackingManager() {
        // TODO Auto-generated constructor stub
    }
/*
    public int createTracking(String user_id,int query_id)
    {
    	Connection con;
    	String sql_query;
    	PreparedStatement pstmt;
    	ResultSet rs = null;
    	int insert = 0;
    	int t_iVersion = 0;
    	
    	DatabaseConnection dbconnect = new DatabaseConnection();
    	con = dbconnect.getConnection();
    	
    	sql_query = "Insert INTO tracking VALUES(?,?,?,?,?)";
    	
    	try {
    		pstmt = con.prepareStatement(sql_query, PreparedStatement.RETURN_GENERATED_KEYS);
    		
    		pstmt.setInt(1, 0);
    		pstmt.setDate(2, null);
    		pstmt.setInt(3, 0);
    		pstmt.setInt(4, query_id);
    		pstmt.setString(5, user_id);
    		
    		
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
    }//end public boolean createTracking()

    public ArrayList<Tracking> getAllTracking(){
    		Connection con;
    		Statement stmt;
    		ResultSet rs;
    		Tracking temporaryTracking;
    		ArrayList<Tracking> allTracking = new ArrayList<Tracking>();
    		String query = "SELECT * FROM tracking";
    		
    		DatabaseConnection dbconnect = new DatabaseConnection();
    		con = dbconnect.getConnection();
    		
    		try {
    			stmt = con.createStatement();
    			rs = stmt.executeQuery(query);
    			
    			while(rs.next()){
    				
    				temporaryTracking = new Tracking();
    				
    				temporaryTracking.setTracking_id(rs.getInt("tracking_id"));	
    				temporaryTracking.setDate(rs.getString("date"));	
    				temporaryTracking.setStatus(rs.getInt("status"));	
    				temporaryTracking.setQuery_id(rs.getInt("query_id"));	
    				temporaryTracking.setUser_id(rs.getString("user_id"));	
    				
    				allTracking.add(temporaryTracking);
    				
    			}//end while(rs.next())
    			
    			
    			rs.close();
    			stmt.close();
    			con.close();
    			
    			return allTracking;
    			
    		} catch (SQLException e) {
    			
    			//load errorpage
    		}	
    		
    		return null;		
    }//end  public ArrayList<Tracking> getAllTracking(){
    
    public Tracking findTracking(int trackingId){
    	Connection con;
    	String query;
    	PreparedStatement pstmt;
    	ResultSet rs = null;
    	Tracking temporaryTracking = null;
    	
    	DatabaseConnection dbconnect = new DatabaseConnection();
    	con = dbconnect.getConnection();
    	
    	query = "SELECT * FROM tracking WHERE tracking_id = ?";
    	
    	try {
    		pstmt = con.prepareStatement(query);
    		
			pstmt.setInt(1, trackingId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				temporaryTracking = new Tracking();
				temporaryTracking.setTracking_id(rs.getInt("tracking_id"));
				temporaryTracking.setDate(rs.getString("date"));
				temporaryTracking.setStatus(rs.getInt("status"));
				temporaryTracking.setQuery_id(rs.getInt("query_id"));
				temporaryTracking.setUser_id(rs.getString("user_id"));
			}//end while
			
			
			rs.close();
    		pstmt.close();
    		con.close();
    		return temporaryTracking;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   	
    	
    	
		return null;    	
    }//end  public Tracking findTracking(){
    */
}



