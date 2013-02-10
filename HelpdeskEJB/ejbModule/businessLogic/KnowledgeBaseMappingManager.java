package businessLogic;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

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
    	
    	
    	
    	
    	return allKnowledgeBaseMappings;
    }// end  public ArrayList<KnowledgeBaseMapping> findQuestion(String question){
    
    
    public String findKnowledge(KnowledgeBaseMapping maps){
    	String knowledge = "";
    	
    	return knowledge;
    }//end of public String findKnowledge(KnowledgeBaseMapping maps){
    
}
