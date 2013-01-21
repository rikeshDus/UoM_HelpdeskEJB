package businessLogic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

/**
 * Session Bean implementation class QueryManager
 */
@Stateless
@LocalBean
public class QueryManager {

    /**
     * Default constructor. 
     */
    public QueryManager() {
        // TODO Auto-generated constructor stub
    }
    
    public int createQuery(String user_id,String description,String type)
    {
    	Connection con;
    	String sql_query;
    	PreparedStatement pstmt;
    	int insert = 0;
    	int t_iVersion = 0;
    	
    	DatabaseConnection dbconnect = new DatabaseConnection();
    	con = dbconnect.getConnection();
    	
    	sql_query = "Insert INTO query VALUES(?,?,?,?,?)";
    	
    	try {
    		pstmt = con.prepareStatement(sql_query, PreparedStatement.RETURN_GENERATED_KEYS);
    		
    		pstmt.setInt(1, 0);
    		pstmt.setString(2, description);
    		pstmt.setDate(3, null);
    		pstmt.setString(4, user_id);
    		pstmt.setString(5, type);
    		
    		insert = pstmt.executeUpdate();
    		ResultSet t_resultset = pstmt.getGeneratedKeys();
    		t_resultset.next();
    		t_iVersion = t_resultset.getInt(1);  
    		con.close();
			t_resultset.close();
			pstmt.close();
    	}catch(SQLException e){
    		//load erropage
    	}
    	
    	return t_iVersion;
    }//end public boolean createQuery()
    
    public TrackingLog forwardQuery(String user_id,String description,String type ){
    	
    	int query_id,tracking_id,question_id,tracking_log_id;
    	ArrayList <Answer> allAnswer = new ArrayList<Answer>();
    	User receiver = new User();
    	TrackingLog trackingLog =  new TrackingLog(); 
    	TrackingManager trackingManager = new TrackingManager();
    	QuestionManager questionManager = new QuestionManager();
    	TrackingLogManager trackingLogManager = new TrackingLogManager();
    
    	//create query
    	query_id = createQuery(user_id, description, type);
    	
    	if(query_id!=0){
    		
    		//tracking
    		tracking_id = trackingManager.createTracking(user_id, query_id);
    		
    		//create question
    		question_id = questionManager.CreateQuestion(description);
    		
    		//get receiver
    		receiver = getReceiver(description);
    		
    		//save track log
    		tracking_log_id = trackingLogManager.createTrackingLog(tracking_id, question_id,receiver.getUser_id());
    		
    		//find tracking_log
    		trackingLog = trackingLogManager.findTrackingLog(tracking_log_id);
    		
    		
    	}// if(query_id!=0){
    	
    	return trackingLog;
    }//end public String processQuery(String user_id,String description,String type ){
    
    public ArrayList<Question> getSolution(String question){
    	/*
    	 * use of lucene to find a relevant set of question related 
    	 * to the query. 
    	 */
    	
    	Question quest;
    	Connection con;
	    String sql_query;
		Statement stmt;
		ResultSet rs = null;
		
		ArrayList<Question> allQuestion = new ArrayList<Question>();
		allQuestion.clear();
    	//use a simple analyser
    	SimpleAnalyzer analyzer = new SimpleAnalyzer(Version.LUCENE_40);
    	
    	//create Index
    	 Directory index = new RAMDirectory();

    	 IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_40, analyzer);

    	 try {
			IndexWriter writer = new IndexWriter(index, config);
			
			//add document to corpus
			
			
			DatabaseConnection dbconnect = new DatabaseConnection();
			con = dbconnect.getConnection();
			
			sql_query = "SELECT * FROM question";
					
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql_query);
			
			
			while(rs.next()){
				//adding result as document in lucene
				addDoc(writer, rs.getString("question"), rs.getInt("question_id")+"");
				
			}
			con.close();
			stmt.close();
			rs.close();
			
			//close writer
			writer.close();
			
			//create lucene query
			Query lucene_query = new QueryParser(Version.LUCENE_40, "question", analyzer).parse(question);
			
			//search
		    int hitsPerPage = 10;
		    IndexReader reader = DirectoryReader.open(index);
		    IndexSearcher searcher = new IndexSearcher(reader);
		    TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
		    searcher.search(lucene_query, collector);
		    ScoreDoc[] hits = collector.topDocs().scoreDocs;
		  
			//creating arralist of question to return
		    for(int i=0;i<hits.length;++i) {
		        int docId = hits[i].doc;
		        Document d = searcher.doc(docId);
		        quest = new Question();
		        quest.setQuestion(d.get("question"));
		        quest.setQuestion_id(Integer.parseInt(d.get("id")));
		        
		        allQuestion.add(quest);
		        
		     }//end for(int i=0;i<hits.length;++i) {
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
    	return allQuestion;
    }// end public ArrayList<Answer> getSolution(String query){
    
    
    private static void addDoc(IndexWriter w, String question, String id) throws IOException {
        Document doc = new Document();
        doc.add(new TextField("question", question, Field.Store.YES));

        // use a string field for isbn because we don't want it tokenized
        doc.add(new StringField("id", id, Field.Store.YES));
        w.addDocument(doc);
    }
 
    private String formatQuery(String wildCard, String fuzzy,String proximity,String firstRange,String secondRange,String boosting,String excludeWords,String booleanQuery){
    	/*
    	 * Check for null parameter
    	 * if parameter is not null format the parameter 
    	 * and concatenate to full query
    	 */
    	
    	String fullQuery = "";
    	char tempCharac = 'n';
    	int tempNum = 0;
    	String allParam[] = { wildCard,  fuzzy, proximity, firstRange, secondRange, boosting, excludeWords, booleanQuery};
    	
    	//remove first character if it is an escaping character
		if(wildCard != null && (
		   wildCard.startsWith("+")  || wildCard.startsWith("-") || wildCard.startsWith("!") || wildCard.startsWith("(") || wildCard.startsWith("&") || wildCard.startsWith("|") ||
		   wildCard.startsWith(")")  || wildCard.startsWith("{") || wildCard.startsWith("}") || wildCard.startsWith("[") || wildCard.startsWith("]") || wildCard.startsWith("^") || 
		   wildCard.startsWith("\"") || wildCard.startsWith("~") || wildCard.startsWith("*") || wildCard.startsWith("?") || wildCard.startsWith(":") || wildCard.startsWith("\\") 
		   ))
		{
			//remove the character 0
			wildCard = wildCard.substring(1);
			
		}//end if(wildCard.startsWith("*") || wildCard.startsWith("?")){
    	
		//interated in each parameter
    	for (int i = 0; i < allParam.length; i++) {
    		
    		if(allParam[i] != null){
    	
	    		//interated in each parameter character
	    		for (int j = 0; j < allParam[i].length(); j++) {
	    			
	    			//get each char 
	    			tempCharac = allParam[i].charAt(j);
	    			//check for escape chracter
	    			if(tempCharac == '+'  || tempCharac == '-' || tempCharac == '!' || tempCharac == '(' || 
	    			   tempCharac == ')'  || tempCharac == '{' || tempCharac == '}' || tempCharac == '[' || 
	    			   tempCharac == ']'  || tempCharac == '^' || tempCharac == '\"'|| tempCharac == '~' || 
	    			   tempCharac == '*'  || tempCharac == '?' || tempCharac == ':' || tempCharac == '\\' ){
	    				
	    				//add \ before escaping character    				
	    				fullQuery += allParam[i].substring(tempNum,j) + "\\";
	    				tempNum = j;
	    				
	    				
	    			}//end if(tempCharac == '+'  || tempCharac == '-' || tempCharac == '!' || tempCharac == '(' || 
	    			else if(tempCharac == '&' || tempCharac == '|'){
	    				//add \ before escaping character   
	    				fullQuery += allParam[i].substring(tempNum,i) + "\\";
	    				tempNum = j;
	    				// move one character ahead because we need to escape && or ||
	    				j++;
	    			}//end else if(tempCharac == '&' || tempCharac == '|'){
	    			
	    			
	    		}//for (int j = 0; j < allParam[i].length(); j++) {
	    		//add the end of the String
    			fullQuery += allParam[i].substring(tempNum);
    			
    			//leave space between each param
    			fullQuery += " AND ";
    			
			}//end if(allParam[i] != null){
    		    		
    	}//end of for (int i = 0; i < allParam.length; i++) {
    	fullQuery = fullQuery.substring(0,(fullQuery.length()-4));
		System.out.println("testing value  " + fullQuery);
    	
    	
    	
    	return fullQuery;
    	
    	
    	
    	
    	/*if(tempCharac == '+'  || tempCharac == '-' || tempCharac == '!' || tempCharac == '(' || 
 			   tempCharac == ')'  || tempCharac == '{' || tempCharac == '}' || tempCharac == '[' || 
 			   tempCharac == ']'  || tempCharac == '^' || tempCharac == '\"'|| tempCharac == '~' || 
 			   tempCharac == '*'  || tempCharac == '?' || tempCharac == ':' || tempCharac == '\\' )*/
    	
    }//end private String formatQuery(String question,String wildCard, String fuzzy,String proximity,String firstRange,String secondRange,String boosting,String excludeWords,String booleanQuery){
    
    
    
    public User getReceiver(String query){
    	/*
    	 * match keywords of the query to each staff work description
    	 * get the best match staff
    	 * verify hierarchical position of staff
    	 * if first best match staff is not good hierarchically position , check for second best staff and so on
    	 * get staff user id
    	 * return the user
    	 */
    	
    	Connection con;
	    String sql_query;
		Statement stmt;
		ResultSet rs = null;
    	Staff staff =null;
    	ArrayList <Staff> staffPopulation =  new ArrayList<Staff>();
    	
    	
    	
    	//match keyword and get staff
    	
    	
    	//use a simple analyser
    	SimpleAnalyzer analyzer = new SimpleAnalyzer(Version.LUCENE_40);
    	
    	//create Index
    	 Directory index = new RAMDirectory();

    	 IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_40, analyzer);
    	
    	 
    	 
    	 try {
 			IndexWriter writer = new IndexWriter(index, config);
 			
 			//add document to corpus
 			
 			
 			DatabaseConnection dbconnect = new DatabaseConnection();
 			con = dbconnect.getConnection();
 			
 			sql_query = "SELECT * FROM staff";
 					
 			stmt = con.createStatement();
 			rs = stmt.executeQuery(sql_query);
 			
 			
 			while(rs.next()){
 				
 				//adding result as document in lucene
 				Document doc = new Document();
 				doc.add(new TextField("working_description", rs.getString("working_description"), Field.Store.YES));
 				doc.add(new TextField("position", rs.getString("position"), Field.Store.YES));

 			    // use a string field for isbn because we don't want it tokenized
 			    doc.add(new StringField("user_id", rs.getString("user_id"), Field.Store.YES));
 			    writer.addDocument(doc);
 				
 			}//end while(rs.next()){ 
 			con.close();
 			stmt.close();
 			rs.close();
 			
 			//close writer
 			writer.close();
 			
 			
 			//create lucene query
			Query lucene_query = new QueryParser(Version.LUCENE_40, "working_description", analyzer).parse(query+"*");
			
			//search and get best match staff
		    int hitsPerPage = 10;
		    IndexReader reader = DirectoryReader.open(index);
		    IndexSearcher searcher = new IndexSearcher(reader);
		    TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
		    searcher.search(lucene_query, collector);
		    ScoreDoc[] hits = collector.topDocs().scoreDocs;
		    
		    //creating arralist of question to return
		    for(int i=0;i<hits.length;++i) {
		        int docId = hits[i].doc;
		        Document d = searcher.doc(docId);
		        staff = new Staff();
		        staff.setWorking_description(d.get("working_description"));
		        staff.setPosition(d.get("position"));
		        staff.setUser_id(d.get("user_id"));
		        
		        staffPopulation.add(staff);
		        
		     }//end for(int i=0;i<hits.length;++i) {
 			
 			
    	 }//end try
    	 catch(SQLException se){
    		 
    	 } catch (IOException e) {
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	return new UserManager().findUser( staffPopulation.get(0).getUser_id());	
    }//end of public User getReceiver(String query){
    
}//end of class
