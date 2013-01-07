

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.Test;

import businessLogic.ScheduleManager;
import businessLogic.Timetable;

public class ScheduleManagerTest {

	@Test
	public void testGenerateNonSportSchedule() {
		ArrayList<Timestamp> allFreeSlot = new ArrayList<Timestamp>();
		
		ScheduleManager scheduleManager = new ScheduleManager();
	 	int[] faculties = {1};
		String course[] = {"E311"};
	    Date endDate = new Date((2013-1900), 0, 12);
		Date startDate = new Date((2013-1900), 0, 7);
		allFreeSlot = scheduleManager.generateNonSportSchedule(faculties , course, startDate, endDate); 
		assertTrue(allFreeSlot.get(0)!=null);
		
	}
	
	
	@Test
	public void testCreateSchedule(){
		ScheduleManager scheduleManager = new ScheduleManager();
		Date date = new Date(0, 0, 0);
	//	boolean rs = scheduleManager.createSchedule( 0, date, "00:00:00", 1);
		
	//	assertTrue(rs);
		
	}//end ublic void createSchedule(String id, int duration, Date date, String time,String event_id){

}
