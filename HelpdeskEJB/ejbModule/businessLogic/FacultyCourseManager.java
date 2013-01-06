package businessLogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class FacultyCourseManager
 */
@Stateless
@LocalBean
public class FacultyCourseManager {

    /**
     * Default constructor. 
     */
    public FacultyCourseManager() {
        // TODO Auto-generated constructor stub
    }
    
    public ArrayList<CourseStructure> getCourseByFaculty(int faculty_id[]){
    	Connection con;
    	String query;
    	PreparedStatement pstmt;
    	ResultSet rs = null;
    	ArrayList<CourseStructure > allCourseStructure = new ArrayList<CourseStructure>();
    	CourseStructure tempCourseStructure = null;
    	
    	DatabaseConnection dbconnect = new DatabaseConnection();
    	con = dbconnect.getConnection();
    	
    	query = "SELECT * FROM faculty_course,course_structure WHERE faculty_id = ? AND faculty_course.course_code = course_structure.course_code";
    	try{
    		pstmt = con.prepareStatement(query);
    		
    		for(int i=0;i<faculty_id.length;i++){
    			
    			pstmt.setInt(1, faculty_id[i]);
    			
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
    }//end public ArrayList<String> getCourseByFaculty(int faculty_id)

}
