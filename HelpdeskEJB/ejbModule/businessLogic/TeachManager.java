package businessLogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

}
