import static org.junit.Assert.*;
import businessLogic.*;
import org.junit.Test;


public class TeachManagerTest {

	@Test
	public void testGetModuleFromStaffId() {
		TeachManager teachManager = new TeachManager();
		
		assertTrue(teachManager.getModuleFromStaffId("P 0001").get(0).equals("CSE 3019"));
	}

}
