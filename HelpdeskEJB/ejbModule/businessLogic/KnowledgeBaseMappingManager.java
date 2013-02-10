package businessLogic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
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
 * Session Bean implementation class KnowledgeBaseMappingManager
 */
@Stateless
@LocalBean
public class KnowledgeBaseMappingManager {

    /**
     * Default constructor. 
     */
    public KnowledgeBaseMappingManager() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<KnowledgeBaseMapping> findQuestion(String question){
    	
    	ArrayList<KnowledgeBaseMapping> allKnowledgeBaseMappings = new ArrayList<KnowledgeBaseMapping>();
    	
    	Connection con;
	    String sql_query;
		Statement stmt;
		ResultSet rs = null;
		KnowledgeBaseMapping mapping;
    	
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
 			
 			sql_query = "SELECT * FROM knowledge_base_mapping";
 					
 			stmt = con.createStatement();
 			rs = stmt.executeQuery(sql_query);
 			
 			
 			while(rs.next()){
 				
 				//adding result as document in lucene
 				Document doc = new Document();
 				doc.add(new TextField("question", rs.getString("question"), Field.Store.YES));
 				doc.add(new TextField("query", rs.getString("query"), Field.Store.YES));

 			    // use a string field for isbn because we don't want it tokenized
 			    doc.add(new IntField("id", rs.getInt("id"), Field.Store.YES));
 			    writer.addDocument(doc);
 				
 			}//end while(rs.next()){ 
 			con.close();
 			stmt.close();
 			rs.close();
 			
 			//close writer
 			writer.close();
 			
 			
 			//create lucene query
			Query lucene_query = new QueryParser(Version.LUCENE_40, "question", analyzer).parse(question+"*");
			
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
		        mapping = new KnowledgeBaseMapping();
		        mapping.setQuestion(d.get("question"));
		        mapping.setQueryFormat(d.get("query"));
		        mapping.setId(Integer.parseInt(d.get("id")));
		        
		        allKnowledgeBaseMappings.add(mapping);
		        
		     }//end for(int i=0;i<hits.length;++i) {
 			
 			
    	 }//end try
    	 catch(SQLException se){
    		 
    	 } catch (IOException e) {
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
    	
    	return allKnowledgeBaseMappings;
    }// end  public ArrayList<KnowledgeBaseMapping> findQuestion(String question){
    
    
    public String findKnowledge(KnowledgeBaseMapping maps){
    	String knowledge = "";
    	
    	return knowledge;
    }//end of public String findKnowledge(KnowledgeBaseMapping maps){
    
}
