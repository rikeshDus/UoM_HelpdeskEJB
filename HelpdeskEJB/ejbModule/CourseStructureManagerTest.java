

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import businessLogic.CourseStructure;
import businessLogic.CourseStructureManager;

public class CourseStructureManagerTest {

	@Test
	public void testGetAllCourseStructureByCourse() {
		ArrayList<CourseStructure > tempCourseStructure = new ArrayList<CourseStructure>();
		CourseStructureManager coursestructure = new CourseStructureManager();
		String[] course = {"E311"};
		tempCourseStructure = coursestructure.getAllCourseStructureByCourse(course );
		assertTrue(tempCourseStructure.get(0).getCourse_code().equals("E311"));
	}

}
