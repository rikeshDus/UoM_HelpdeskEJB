

import static org.junit.Assert.*;
import businessLogic.*;
import org.junit.Test;

public class StaffManagerTest {

	@Test
	public void testFindStaffByUserId() {
		StaffManager manager = new StaffManager();
		Staff staff = manager.findStaffByUserId("1010790");
		
		assertTrue(staff == null);
	}

}
