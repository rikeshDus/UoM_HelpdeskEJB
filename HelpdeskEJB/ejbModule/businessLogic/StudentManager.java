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
 * Session Bean implementation class StudentManager
 */
@Stateless
@LocalBean
public class StudentManager {

    /**
     * Default constructor. 
     */
    public StudentManager() {
        // TODO Auto-generated constructor stub
    }
    
    public Student findStudent(String id){
    	Connection con;
    	String query;
    	PreparedStatement pstmt;
    	ResultSet rs = null;
    	Student temporaryStudent = null;
    	
    	DatabaseConnection dbconnect = new DatabaseConnection();
    	con = dbconnect.getConnection();
    	
    	query = "SELECT * FROM student WHERE student_id = ?";
    	try{
    		pstmt = con.prepareStatement(query);
    		pstmt.setString(1, id);
    		
    		rs = pstmt.executeQuery();
    		
    		if(rs.next()){
    			temporaryStudent = new Student();
    			
    			temporaryStudent.setCourse_code(rs.getString("course_code"));
    			temporaryStudent.setCpa(rs.getInt("cpa"));
    			temporaryStudent.setGpa(rs.getInt("gpa"));
    			temporaryStudent.setLpa(rs.getInt("lpa"));
    			temporaryStudent.setStudent_id(rs.getString("student_id"));
    			temporaryStudent.setUser_id(rs.getString("user_id"));
    			temporaryStudent.setCredit(rs.getInt("credit"));
    			temporaryStudent.setYear(rs.getInt("year"));
    			
    			rs.close();
        		pstmt.close();
        		con.close();
    			return temporaryStudent;
    		}// end if(rs.next()){
    		rs.close();
    		pstmt.close();
    		con.close();
    		return null;
    	}//end of try
    	catch(SQLException e){
    		//load error page
    	}
    	
    	return null;
    }

    
    public ArrayList<Student> getAllStudent(){
		Connection con;
		Statement stmt;
		ResultSet rs;
		Student temporaryStudent;
		ArrayList<Student> allStudent = new ArrayList<Student>();
		String query = "SELECT * FROM student";
		
		DatabaseConnection dbconnect = new DatabaseConnection();
		con = dbconnect.getConnection();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()){
				
				temporaryStudent = new Student();
				
				temporaryStudent.setStudent_id(rs.getString("student_id"));
				temporaryStudent.setUser_id(rs.getString("user_id"));
				temporaryStudent.setCpa(rs.getInt("cpa"));
				temporaryStudent.setLpa(rs.getInt("lpa"));
				temporaryStudent.setGpa(rs.getInt("gpa"));
				temporaryStudent.setCourse_code(rs.getString("course_code"));
				temporaryStudent.setCredit(rs.getInt("credit"));
				temporaryStudent.setYear(rs.getInt("year"));
				
				
				allStudent.add(temporaryStudent);
				
			}//end while(rs.next())
			
			
			rs.close();
			stmt.close();
			con.close();
			
			return allStudent;
			
		} catch (SQLException e) {
			
			//load errorpage
		}	
		
		return null;		
}//end  public ArrayList<Tracking> getAllTracking(){
}
