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
 * Session Bean implementation class CourseStructureManager
 */
@Stateless
@LocalBean
public class CourseStructureManager {

    /**
     * Default constructor. 
     */
    public CourseStructureManager() {
        // TODO Auto-generated constructor stub
    }
    
    public ArrayList<CourseStructure> getAllCourseStructureByCourse(String course[]){
    	Connection con;
    	String query;
    	PreparedStatement pstmt;
    	ResultSet rs = null;
    	ArrayList<CourseStructure > allCourseStructure = new ArrayList<CourseStructure>();
    	CourseStructure tempCourseStructure = null;
    	
    	DatabaseConnection dbconnect = new DatabaseConnection();
    	con = dbconnect.getConnection();
    	
    	query = "SELECT * FROM course_structure WHERE course_code = ?";
    	try{
    		pstmt = con.prepareStatement(query);
    		for(int i=0;i<(course.length);i++){
    			
    			pstmt.setString(1, course[i]);
    			
    			rs = pstmt.executeQuery();
    			
    			while(rs.next()){
    				tempCourseStructure = new CourseStructure();
    				
    				tempCourseStructure.setCourse_code(rs.getString("course_code"));
    				tempCourseStructure.setCourse_structure_id(rs.getInt("course_structure_id"));
    				tempCourseStructure.setModule_code(rs.getString("module_code"));
    				
    				allCourseStructure.add(tempCourseStructure);
    				
    			}//end while(rs.next())			
    			
    		}//end for(int i=0;i<faculty_id.length;i++)
    		
    		rs.close();
    		pstmt.close();
    		con.close();
    		return allCourseStructure;
    	}//end try
    	catch(SQLException sqle){
    		//load error page
    	}
    	
    	return null;
    }//end public ArrayList<CourseStructure> getAllCourseStructureByCourse()
    
    public ArrayList<CourseStructure> getAllCourseStructure(){
		Connection con;
		Statement stmt;
		ResultSet rs;
		CourseStructure temporaryCourseStructure;
		ArrayList<CourseStructure> allCourseStructure = new ArrayList<CourseStructure>();
		String query = "SELECT * FROM course_structure";
		
		DatabaseConnection dbconnect = new DatabaseConnection();
		con = dbconnect.getConnection();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()){
				
				temporaryCourseStructure = new CourseStructure();
				
				temporaryCourseStructure.setCourse_structure_id(rs.getInt("course_structure_id"));
				temporaryCourseStructure.setModule_code(rs.getString("module_code"));
				temporaryCourseStructure.setCourse_code(rs.getString("course_code"));
				
				allCourseStructure.add(temporaryCourseStructure);
				
			}//end while(rs.next())
			
			
			rs.close();
			stmt.close();
			con.close();
			
			return allCourseStructure;
			
		} catch (SQLException e) {
			
			//load errorpage
		}	
		
		return null;		
}//end  public ArrayList<Tracking> getAllTracking(){
    
    
    public ArrayList<CourseStructure> getAllCourseStructureByModule(ArrayList<String> moduleCode){
    	Connection con;
    	String query;
    	PreparedStatement pstmt;
    	ResultSet rs = null;
    	ArrayList<CourseStructure > allCourseStructure = new ArrayList<CourseStructure>();
    	CourseStructure tempCourseStructure = null;
    	
    	DatabaseConnection dbconnect = new DatabaseConnection();
    	con = dbconnect.getConnection();
    	
    	query = "SELECT * FROM course_structure WHERE module_code = ?";
    	try{
    		pstmt = con.prepareStatement(query);
    		for(int i=0;i<(moduleCode.size());i++){
    			
    			pstmt.setString(1, moduleCode.get(i));
    			
    			rs = pstmt.executeQuery();
    			
    			while(rs.next()){
    				tempCourseStructure = new CourseStructure();
    				
    				tempCourseStructure.setCourse_code(rs.getString("course_code"));
    				tempCourseStructure.setCourse_structure_id(rs.getInt("course_structure_id"));
    				tempCourseStructure.setModule_code(rs.getString("module_code"));
    				
    				allCourseStructure.add(tempCourseStructure);
    				
    			}//end while(rs.next())			
    			
    		}//end for(int i=0;i<faculty_id.length;i++)
    		
    		rs.close();
    		pstmt.close();
    		con.close();
    		return allCourseStructure;
    	}//end try
    	catch(SQLException sqle){
    		//load error page
    	}
    	
    	return null;
    }//end public ArrayList<CourseStructure> getAllCourseStructureByCourse()
    
}
