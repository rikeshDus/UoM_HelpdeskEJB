

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import businessLogic.Schedule;
import businessLogic.ScheduleManager;
import businessLogic.Timetable;

public class ScheduleManagerTest {
	
	//@Test
	public void testGetScheduleByEvent(){
		ScheduleManager mgr = new ScheduleManager();
		
		Schedule sch = mgr.getScheduleByEvent(31);
		
		System.out.println(sch.getDate());
		assertTrue(false);
	}
	
	@Test
	public void testupdateSchedule(){
		ScheduleManager scheduleManager = new ScheduleManager();
		Schedule schedule = new Schedule();
		schedule.setDate(new Date(113, 01, 01));
		schedule.setDuration(10);
		schedule.setEvent_id(22);
		schedule.setSchedule_id(6);
		schedule.setTime("08:00:00");
		
		assertTrue( scheduleManager.updateSchedule(schedule));
		
	}
	
	
		//@Test
	public void testGenerateSportSchedule() {
		ScheduleManager scheduleManager = new ScheduleManager();
		HashMap<String, String[]> teamsHashMap = new HashMap<String, String[]>();
		HashMap<String, List<String>> result = new HashMap<String, List<String>>();
		List<String> match = new ArrayList<String>();
		Date endDate = new Date((2013-1900), 0, 12);
		Date startDate = new Date((2013-1900), 0, 7);
		String[] id = {"1010790,1010790"};
		String[] profId = {"P 0001,P 0001"};
		teamsHashMap.put("team1", profId);
		teamsHashMap.put("team2", id);
		teamsHashMap.put("team3", id);
		teamsHashMap.put("team4", id);
		teamsHashMap.put("team5", id);
		teamsHashMap.put("team6", profId);
		teamsHashMap.put("team7", id);
		teamsHashMap.put("team8", id);
		result = scheduleManager.generateSportSchedule(teamsHashMap, startDate, endDate);
		int x =1;
		for(int i=0;i<result.size();i++){
			System.out.println("round "+x);
			match = result.get("round "+x);
			x++;
			
			for(int j=0;j<match.size();j++){
				System.out.println(match.get(j)+"  ");
			}
			System.out.println("\n");
		}
		
		
		assertTrue( result != null);
	}
	
	//@Test
	public void testGenerateNonSportSchedule() {
		ArrayList<Timestamp> allFreeSlot = new ArrayList<Timestamp>();
		
		ScheduleManager scheduleManager = new ScheduleManager();
	 	int[] faculties = {1};
		String course[] = {"E311"};
	    Date endDate = new Date((2013-1900), 0, 12);
		Date startDate = new Date((2013-1900), 0, 7);
		//allFreeSlot = scheduleManager.generateNonSportSchedule(faculties , course, startDate, endDate); 
		//assertTrue(allFreeSlot.get(0)!=null);
		
	}
	
	
	//@Test
	public void testCreateSchedule(){
		ScheduleManager scheduleManager = new ScheduleManager();
		Date date = new Date(0, 0, 0);
	//	boolean rs = scheduleManager.createSchedule( 0, date, "00:00:00", 1);
		
	//	assertTrue(rs);
		
	}//end ublic void createSchedule(String id, int duration, Date date, String time,String event_id){

}
