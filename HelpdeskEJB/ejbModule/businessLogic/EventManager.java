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
    
    public Event findEvent(int event_id){
    	Connection con;
    	String query;
    	PreparedStatement pstmt;
    	ResultSet rs = null;
    	Event temporaryEvent = null;
    	
    	DatabaseConnection dbconnect = new DatabaseConnection();
    	con = dbconnect.getConnection();
    	
    	query = "SELECT * FROM event WHERE event_id = ?";
    	
    	try {
    		pstmt = con.prepareStatement(query);
    		
			pstmt.setInt(1, event_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				temporaryEvent = new Event();
				
				temporaryEvent.setDescroiption(rs.getString("description"));
				temporaryEvent.setEvent_id(rs.getInt("event_id"));
				temporaryEvent.setTitle(rs.getString("title"));
				temporaryEvent.setType(rs.getString("type"));
				temporaryEvent.setUser_id(rs.getString("user_id"));
				
				
			}//end while
			
			
			rs.close();
    		pstmt.close();
    		con.close();
    		return temporaryEvent;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return null;
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
    
    public ArrayList<Event> getAllEvent(){
    	
    	Connection con;
		Statement stmt;
		ResultSet rs;
		Event temporaryEvent;
		ArrayList<Event> allEvent = new ArrayList<Event>();
		String query = "SELECT * FROM event";
		
		DatabaseConnection dbconnect = new DatabaseConnection();
		con = dbconnect.getConnection();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()){
				
				temporaryEvent = new Event();
				
				temporaryEvent.setDescroiption(rs.getString("description"));
				temporaryEvent.setEvent_id(rs.getInt("event_id"));
				temporaryEvent.setTitle(rs.getString("title"));
				temporaryEvent.setType(rs.getString("type"));
				temporaryEvent.setUser_id(rs.getString("user_id"));
				
				allEvent.add(temporaryEvent);
				
			}//end while(rs.next())
			
			
			rs.close();
			stmt.close();
			con.close();
			
			return allEvent;
			
		} catch (SQLException e) {
			
			//load errorpage
		}	
    	
    	return null;
    }//end public ArrayList<Event> getAllEvent(){

    
    public boolean deleteEvent(int event_id){
    	Connection con;
    	PreparedStatement pstmt;
    	String query_event,query_schedule;
    	int confrimTransaction;
    	
    	//query
    	query_event  = "DELETE FROM event WHERE event_id = ?";
    	query_schedule = "DELETE FROM schedule WHERE event_id = ?";
    	
    	//connection
    	DatabaseConnection dbconnect = new DatabaseConnection();
    	con = dbconnect.getConnection();
    	
    	
    	try{
    		pstmt = con.prepareStatement(query_event);
    		
    		pstmt.setInt(1, event_id);
    		
    		confrimTransaction = pstmt.executeUpdate();
    		//check if transaction complete event
    		if(confrimTransaction == 1){
    			return true;
    		}//end if(confrimTransaction == 0){
    		else{
    			
    			return false;
    		}//else
    		
    		
    	}catch(SQLException se){
    		//load error page
    		return false;
    	}
    }//end public boolean deleteEvent(int event_id){
    
    
    public boolean updateEvent(Event event){
    	Connection con;
 	   String query;
 	   int update;
 	   ResultSet rs = null;
 	   PreparedStatement pstmt;
 	   
 	   query = "UPDATE event SET title = ?, description = ? WHERE event_id = ? ";
 	   
 	   DatabaseConnection dbconnect = new DatabaseConnection();
 	   con = dbconnect.getConnection();
 	   
 	   try{
 		   pstmt = con.prepareStatement(query);
 		   
 		   pstmt.setString(1, event.getTitle());
 		   pstmt.setString(2, event.getDescroiption());
 		   pstmt.setInt(3, event.getEvent_id()); 		   
 		   
 		   update = pstmt.executeUpdate();
 		   
 		   
 		   
 		   if(update == 1)
 			   return true;
 		   else
 			   return false;
 		   
 	   }catch(SQLException se){
 		   
 	   }
    	
    	return false;
    }//end of public boolean updateEvent(){
    
}//end class
