package businessLogic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class CourseManager
 */
@Stateless
@LocalBean
public class CourseManager {

    /**
     * Default constructor. 
     */
    public CourseManager() {
        // TODO Auto-generated constructor stub
    }
    
    
    public Course findCourse(){
		return null;
    }
    
    public ArrayList<Course> getAllCourse(){
    	Connection con =  null;
    	Course course;
    	ArrayList<Course> allCourse = new ArrayList<Course>();
    	Statement stmt;
    	ResultSet rs;
    	String query = "SELECT * FROM course";
    	
    	try{
    		DatabaseConnection dbconnect = new DatabaseConnection(); 
    		con = dbconnect.getConnection();
    		
    		stmt = con.createStatement();
    		rs = stmt.executeQuery(query);
    		    		
    		while(rs.next()){
    			
    			course = new Course();
    			
    			course.setCourse_code(rs.getString("course_code"));
    			course.setName(rs.getString("name"));
    			course.setDescrition(rs.getString("description"));
    			course.setType(rs.getString("type"));
    			course.setDiploma_exit(rs.getInt("diploma_exit"));
    			course.setDegree_exit(rs.getInt("degree_exit"));
    			
    			allCourse.add(course);
    			
    		}//end while(rs.next())
    		
    		stmt.close();
    		rs.close();
    		con.close();
    		return allCourse;
    	}//end of try
    	catch(SQLException sqlE){
    		//load error page
    	}//end 
		return null;    	
    }

}
