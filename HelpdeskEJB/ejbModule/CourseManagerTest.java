import static org.junit.Assert.*;

import java.util.List;

import javax.validation.constraints.AssertTrue;

import org.junit.Test;

import businessLogic.CourseManager;


public class CourseManagerTest {

	@Test
	public void testGetAllCourse() {
		
		List<businessLogic.Course> allCourse;
		
		businessLogic.CourseManager courseManager = new CourseManager();
		
		allCourse = courseManager.getAllCourse();
		
		String courseCode = allCourse.get(0).getCourse_code();
		assertTrue(courseCode.equals("E311"));
	}

}
