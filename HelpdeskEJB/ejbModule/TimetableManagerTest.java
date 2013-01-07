import static org.junit.Assert.*;

import java.sql.Time;

import org.junit.Test;

import businessLogic.*;


public class TimetableManagerTest {

	@Test
	public void testFindTimetableByDayTime() {
		TimetableManager timetableManager = new TimetableManager();
		
		assertTrue(timetableManager.findTimetableByDayTime(new Time(10, 30, 00), "Monday", 1));
	}

	@Test
	public void testGetAllTimetableByStructureId() {
		fail("Not yet implemented");
	}

}
