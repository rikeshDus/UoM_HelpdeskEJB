package businessLogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
