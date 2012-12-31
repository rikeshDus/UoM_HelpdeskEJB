package businessLogic;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Session Bean implementation class UserManager
 */
@Stateful
@LocalBean
public class UserManager {
	private User user;
    /**
     * Default constructor. 
     */
    public UserManager() {
        // TODO Auto-generated constructor stub
    }
    
    
    public User findUser(String username){
    	Connection con;
    	String query;
    	PreparedStatement pstmt;
    	ResultSet rs = null;
    	User temporaryUser = null;
    	
    	DatabaseConnection dbconnect = new DatabaseConnection();
    	con = dbconnect.getConnection();
    	
    	query = "SELECT * FROM user WHERE user_id = ?";
    	
    	try {
    		pstmt = con.prepareStatement(query);
    		
			pstmt.setString(1, username);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				temporaryUser = new User();
				temporaryUser.setUser_id(rs.getString("user_id"));
				temporaryUser.setSurname(rs.getString("surname"));
				temporaryUser.setPassword(rs.getString("password"));
				temporaryUser.setName(rs.getString("name"));
				temporaryUser.setLocality(rs.getString("locality"));
				temporaryUser.setHouse_number(rs.getInt("house_number"));
				temporaryUser.setEmail(rs.getString("email"));
				temporaryUser.setDate_of_birth(rs.getDate("date_of_birth"));
				temporaryUser.setCountry(rs.getString("country"));			
			}//end while
			
			
			rs.close();
    		pstmt.close();
    		con.close();
    		return temporaryUser;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   	
    	
    	
		return null;    	
    }//end of  public User findUser(String username)
    
    public boolean Login(String username,String password){ 
    	
    	if(this.findUser(username) == null)
    		return false;//should load error page

    	if(this.findUser(username).getPassword().equals(password)){
    		this.user = this.findUser(username);
    		return true;
    	}//end of if(this.findUser(username).getPassword().equals(password))
    	
    	return false;
    }// end of public boolean Login(String username,String password){

}//end of classa
