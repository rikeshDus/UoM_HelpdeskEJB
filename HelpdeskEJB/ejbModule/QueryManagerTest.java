

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import businessLogic.QueryManager;
import businessLogic.Question;

public class QueryManagerTest {
	QueryManager queryManager = new QueryManager();

	@Test
	public void testCreateQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testProcessQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSolution() {
		//formatQuery(String question,String wildCard, String fuzzy,String proximity,String firstRange,String secondRange,String boosting,String excludeWords,String booleanQuery)
		ArrayList<Question> allquestion = queryManager.getSolution("\"will email\"~3");
		for (int i = 0; i < allquestion.size(); i++) {
			System.out.println(allquestion.get(i).getQuestion_id() + ". "+allquestion.get(i).getQuestion());
		}
		/*allquestion = queryManager.getSolution("email");
		for (int i = 0; i < allquestion.size(); i++) {
			System.out.println(allquestion.get(i).getQuestion_id() + ". "+allquestion.get(i).getQuestion());
		}*/
		assertTrue(allquestion!=null);
	}

	@Test
	public void testGetReceiver() {
		fail("Not yet implemented");
	}

}
