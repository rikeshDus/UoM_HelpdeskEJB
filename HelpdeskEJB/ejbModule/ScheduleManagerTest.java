

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.Test;

import businessLogic.ScheduleManager;
import businessLogic.Timetable;

public class ScheduleManagerTest {

	@Test
	public void testGenerateNonSportSchedule() {
		ArrayList<Timetable> allFreeSlot = new ArrayList<Timetable>();
		
		ScheduleManager scheduleManager = new ScheduleManager();
	 	int[] faculties = {1};
	 	ArrayList<String> course = new ArrayList<String>();
		course.add("E311");
	    Date endDate = null;
		Date startDate = null;
		//allFreeSlot = scheduleManager.generateNonSportSchedule(faculties , course, startDate, endDate); 
	}

}
