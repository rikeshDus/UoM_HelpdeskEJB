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
 * Session Bean implementation class TeachManager
 */
@Stateless
@LocalBean
public class TeachManager {

    /**
     * Default constructor. 
     */
    public TeachManager() {
        // TODO Auto-generated constructor stub
    }
    
   public ArrayList<String> getModuleFromStaffId(String id){
	   Connection con;
	   String query;
	   PreparedStatement pstmt;
	   ResultSet rs = null;
	   ArrayList <String> allCourse = new ArrayList<String>();
	   
	   DatabaseConnection dbconnect = new DatabaseConnection();
	   con = dbconnect.getConnection();
	   	
	   query = "SELECT * FROM teach WHERE staff_id = ?";
	   
	   try{
		   pstmt = con.prepareStatement(query);
		   pstmt.setString(1, id);
		   
		   rs = pstmt.executeQuery();
		   
		   while(rs.next()){
			   allCourse.add(rs.getString("module_code"));
		   }//end while(rs.next()){
		   
		   rs.close();
		   pstmt.close();
		   con.close();
		   return allCourse;
	   }catch(SQLException e){
		   
	   }
	   	
	   return null;
   }// end public String getModuleFromStaffId(String id){

   public ArrayList<Teach> getAllTeach(){
		Connection con;
		Statement stmt;
		ResultSet rs;
		Teach temporaryTeach;
		ArrayList<Teach> Teach = new ArrayList<Teach>();
		String query = "SELECT * FROM teach";
		
		DatabaseConnection dbconnect = new DatabaseConnection();
		con = dbconnect.getConnection();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()){
				
				temporaryTeach = new Teach();
				
				temporaryTeach.setTeach_id(rs.getInt("teach_id"));
				temporaryTeach.setStaff_id(rs.getString("staff_id"));
				temporaryTeach.setModule_code(rs.getString("module_code"));
				temporaryTeach.setDate(rs.getString("date"));
				
				Teach.add(temporaryTeach);
				
			}//end while(rs.next())
			
			
			rs.close();
			stmt.close();
			con.close();
			
			return Teach;
			
		} catch (SQLException e) {
			
			//load errorpage
		}	
		
		return null;		
}//end  public ArrayList<Tracking> getAllTracking(){


}
