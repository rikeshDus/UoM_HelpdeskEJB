package businessLogic;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

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
		ArrayList<Question> allquestion = queryManager.getSolution("email");
		for (int i = 0; i < allquestion.size(); i++) {
			System.out.println(allquestion.get(i).getQuestion_id() + ". "+allquestion.get(i).getQuestion());
		}
		
		assertTrue(allquestion!=null);
	}

	@Test
	public void testGetReceiver() {
		fail("Not yet implemented");
	}

}
