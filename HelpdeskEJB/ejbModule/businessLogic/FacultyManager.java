package businessLogic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class FacultyManager
 */
@Stateless
@LocalBean
public class FacultyManager {

    /**
     * Default constructor. 
     */
    public FacultyManager() {
        // TODO Auto-generated constructor stub
    }
    
    
    public ArrayList<Faculty> getAllFaculty(){
    	Connection con;
		Statement stmt;
		ResultSet rs;
		Faculty temporaryFaculty;
		ArrayList<Faculty> allFaculty = new ArrayList<Faculty>();
		String query = "SELECT * FROM faculty";
		
		DatabaseConnection dbconnect = new DatabaseConnection();
		con = dbconnect.getConnection();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()){
				
				temporaryFaculty = new Faculty();
				
				temporaryFaculty.setFaculty_id(rs.getInt("faculty_id"));
				temporaryFaculty.setName(rs.getString("name"));
				
				allFaculty.add(temporaryFaculty);
				
			}//end while(rs.next())
			
			
			rs.close();
			stmt.close();
			con.close();
			
			return allFaculty;
			
		} catch (SQLException e) {
			
			//load errorpage
		}	
		return null;
		
    }///end of  public ArrayList<Faculty> getAllFaculty(){

}
