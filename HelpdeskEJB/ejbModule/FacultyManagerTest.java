import static org.junit.Assert.*;

import org.junit.Test;

import businessLogic.*;


public class FacultyManagerTest {

	@Test
	public void testGetAllFaculty() {
		FacultyManager facultyManager = new FacultyManager();
		assertTrue(facultyManager.getAllFaculty().get(0).getFaculty_id()==1);
		
	}

}
