import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import businessLogic.KnowledgeBaseMapping;
import businessLogic.KnowledgeBaseMappingManager;


public class KnowledgeBaseMappingManagerTest {
	
	 KnowledgeBaseMappingManager Manager = new KnowledgeBaseMappingManager(); 
	 
	@Test
	public void testFindQuestion() {
		ArrayList<KnowledgeBaseMapping> allMaps = Manager.findQuestion("diploma");
		System.out.println(allMaps.get(0).getAnswerFormat());
		assertTrue(allMaps != null);
	}

	@Test
	public void testFindKnowledge() {
		fail("Not yet implemented");
	}

}
