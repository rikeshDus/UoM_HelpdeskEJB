

import static org.junit.Assert.*;

import org.junit.Test;

import businessLogic.FacultyCourseManager;

public class FacultyCourseManagerTest {

	@Test
	public void testGetCourseByFaculty() {
		FacultyCourseManager facultyManager = new FacultyCourseManager();
		int[] faculty={1};
		assertTrue(facultyManager.getCourseByFaculty(faculty).get(0).getCourse_code().equals("E311"));
	}

}
