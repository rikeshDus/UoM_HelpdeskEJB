package businessLogic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class StudentEvidenceManager
 */
@Stateless
@LocalBean
public class StudentEvidenceManager {

    /**
     * Default constructor. 
     */
    public StudentEvidenceManager() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<StudentEvidence> getAllStudentEvidence(){
    	Connection con =  null;
    	StudentEvidence studentEvidence;
    	ArrayList<StudentEvidence> allStudentEvidence = new ArrayList<StudentEvidence>();
    	Statement stmt;
    	ResultSet rs;
    	String query = "SELECT * FROM student_evidence";
    	
    	try{
    		DatabaseConnection dbconnect = new DatabaseConnection(); 
    		con = dbconnect.getConnection();
    		
    		stmt = con.createStatement();
    		rs = stmt.executeQuery(query);
    		    		
    		while(rs.next()){
    			
    			studentEvidence = new StudentEvidence();
    			
    			studentEvidence.setStudent_id(rs.getString("student_id"));
    			studentEvidence.setEvidence(rs.getString("evidence"));
    			studentEvidence.setUrl(rs.getString("url"));
    			studentEvidence.setDate(rs.getDate("date"));
    			studentEvidence.setStatus(rs.getString("status"));
    			studentEvidence.setExam_period(rs.getString("exam_period"));
    			
    			allStudentEvidence.add(studentEvidence);
    			
    		}//end while(rs.next())
    		
    		stmt.close();
    		rs.close();
    		con.close();
    		return allStudentEvidence;
    	}//end of try
    	catch(SQLException sqlE){
    		//load error page
    	}//end 
		return null;    	
    	
    }//end public ArrayList<StudentEvidence> getAllStudentEvidence(){
    
}
