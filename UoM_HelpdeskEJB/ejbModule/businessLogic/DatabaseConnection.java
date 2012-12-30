package businessLogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	public DatabaseConnection(){
		
	}
	
	//select
	public Connection getConnection(){
		
		Connection con = null;

        String url = "jdbc:mysql://localhost:3306/uom_helpdesk";
        String user = "root";
        String password = "";

        try {
        	 Class.forName("com.mysql.jdbc.Driver");
        	 con = DriverManager.getConnection(url, user, password);
        	 
        	 return con;
        }//end of try
        catch(ClassNotFoundException e){
        	
        }//end of catch(ClassNotFoundException e)
        catch (SQLException e) {
        	
			e.printStackTrace();
			
		}//end of  catch (SQLException e)
        
		return null;		
	}//end of public Connection getConnection(){
	
	
	
}
