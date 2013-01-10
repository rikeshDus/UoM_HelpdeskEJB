import static org.junit.Assert.*;
import businessLogic.*;
import org.junit.Test;


public class StudentManagerTest {

	@Test
	public void testFindStudent() {
		StudentManager studentManager = new StudentManager();
		//assertTrue(studentManager.findStudent("1010790").getCourse_code().equals("E311"));
		assertTrue(studentManager.findStudent("101079") == null);
	}

}
