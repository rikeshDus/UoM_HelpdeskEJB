

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import businessLogic.Event;
import businessLogic.EventManager;

public class EventManagerTest {

	@Test
	public void testCreateEvent() {
		EventManager eventManager = new EventManager();
		int rs = eventManager.createEvent("title", "description", "type", "1010790");
		assertTrue(rs>0);
		
	}
	
	@Test
	public void testGetAllEvent(){
		EventManager eventManager = new EventManager();
		ArrayList<Event> allEvents = eventManager.getAllEvent();
		for (int i = 0; i < allEvents.size(); i++) {
			if(allEvents.get(i).getUser_id().equals("P"))
			System.out.println(allEvents.get(i).getEvent_id());
		}
		
		assertTrue(allEvents !=   null);
	}
}
