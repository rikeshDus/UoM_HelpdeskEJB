package businessLogic;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class TimetableManager
 */
@Stateless
@LocalBean
public class TimetableManager {

    /**
     * Default constructor. 
     */
    public TimetableManager() {
        // TODO Auto-generated constructor stub
    }
    
    public ArrayList<Timetable> getAllTimetableByStructureId(int structure_Id,Date startDate, Date endDate){
    	Connection con;
    	String query;
    	PreparedStatement pstmt;
    	ResultSet rs = null;
    	Timetable tempTimetable = null;
    	ArrayList <Timetable> allTimetable = new ArrayList<Timetable>();
    	
    	DatabaseConnection dbconnect = new DatabaseConnection();
    	con = dbconnect.getConnection();
    	
    	
    	
    	query = "SELECT * FROM timetable WHERE structure_id = ?";
    	
    	try{
    		pstmt = con.prepareStatement(query);
    		
    		pstmt.setInt(1, structure_Id);
    		
    		rs = pstmt.executeQuery();
    		
    		while(rs.next()){   
    			tempTimetable = new Timetable(); 
    			
    			tempTimetable.setStructure_id(rs.getInt("structure_id"));
    			tempTimetable.setDay(rs.getString("day"));
    			tempTimetable.setDuration(rs.getInt("duration"));
    			tempTimetable.setLecture_class(rs.getString("class"));
    			tempTimetable.setStaff_id(rs.getString("staff_id"));
    			tempTimetable.setTime(rs.getTime("starttime"));
    			tempTimetable.setTimetable_id(rs.getInt("timetable_id"));
    			
    			allTimetable.add(tempTimetable);        			
    		}//end while(rs.next())
    		rs.close();
    		pstmt.close();
    		con.close();
    		return allTimetable;
    	}
    	catch(SQLException sqle){
    		//load error page
    	}//end catch(SQLException sqle)
    	
    	return null;
    }

}
