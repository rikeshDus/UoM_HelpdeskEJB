package businessLogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class TrackingManager
 */
@Stateless
@LocalBean
public class TrackingManager {

    /**
     * Default constructor. 
     */
    public TrackingManager() {
        // TODO Auto-generated constructor stub
    }

    public int createTracking(String user_id,int query_id)
    {
    	Connection con;
    	String sql_query;
    	PreparedStatement pstmt;
    	ResultSet rs = null;
    	int insert = 0;
    	int t_iVersion = 0;
    	
    	DatabaseConnection dbconnect = new DatabaseConnection();
    	con = dbconnect.getConnection();
    	
    	sql_query = "Insert INTO tracking VALUES(?,?,?,?,?)";
    	
    	try {
    		pstmt = con.prepareStatement(sql_query, PreparedStatement.RETURN_GENERATED_KEYS);
    		
    		pstmt.setInt(1, 0);
    		pstmt.setDate(2, null);
    		pstmt.setInt(3, 0);
    		pstmt.setInt(4, query_id);
    		pstmt.setString(5, user_id);
    		
    		
    		insert = pstmt.executeUpdate();
    		rs = pstmt.getGeneratedKeys();
    		rs.next();
    		t_iVersion = rs.getInt(1);  
    		
    		
    		con.close();
    		rs.close();
			pstmt.close();
			
			//create tracking log
			
			
    	}catch(SQLException e){
    		//load erropage
    	}
    	
    	return t_iVersion;
    }//end public boolean createTracking()
}
