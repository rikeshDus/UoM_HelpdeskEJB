package businessLogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class StaffManager
 */
@Stateless
@LocalBean
public class StaffManager {

    /**
     * Default constructor. 
     */
    public StaffManager() {
        // TODO Auto-generated constructor stub
    }
    public Staff findStaffByUserId(String userId){
    	Connection con;
    	String query;
    	PreparedStatement pstmt;
    	ResultSet rs = null;
    	Staff temporaryStaff = null;
    	
    	DatabaseConnection dbconnect = new DatabaseConnection();
    	con = dbconnect.getConnection();
    	
    	query = "SELECT * FROM staff WHERE user_id = ?";
    	
    	try {
    		pstmt = con.prepareStatement(query);
    		
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				temporaryStaff = new Staff();
				
				temporaryStaff.setPosition(rs.getString("position"));
				temporaryStaff.setSalary(rs.getInt("salary"));
				temporaryStaff.setStaff_id(rs.getString("staff_id"));
				temporaryStaff.setUser_id(rs.getString("user_id"));
				temporaryStaff.setWorking_description(rs.getString("working_description"));
				
			}//end while
			
			
			rs.close();
    		pstmt.close();
    		con.close();
    		return temporaryStaff;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return null;
    }//end public Staff findStaffByUserId(String userId){

}
