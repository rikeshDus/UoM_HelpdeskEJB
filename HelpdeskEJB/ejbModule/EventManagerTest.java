

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import businessLogic.Event;
import businessLogic.EventManager;

public class EventManagerTest {

	//@Test
	public void testCreateEvent() {
		EventManager eventManager = new EventManager();
		int rs = eventManager.createEvent("title", "description", "type", "1010790");
		assertTrue(rs>0);
		
	}
	
	//@Test
	public void testGetAllEvent(){
		EventManager eventManager = new EventManager();
		ArrayList<Event> allEvents = eventManager.getAllEvent();
		for (int i = 0; i < allEvents.size(); i++) {
			if(allEvents.get(i).getUser_id().equals("P"))
			System.out.println(allEvents.get(i).getEvent_id());
		}
		
		assertTrue(allEvents !=   null);
	}
	
	@Test
	public void testUpdateEvent(){
		EventManager eventManager = new EventManager();
		Event event = new Event();
		
		event.setDescroiption("re tesst");
		event.setTitle("re title");
		event.setEvent_id(34);
		
		assertTrue(eventManager.updateEvent(event));
	}
	
	//@Test
	public void testDeleteEvent(){
		EventManager eventManager = new EventManager();
		boolean result = eventManager.deleteEvent(27);
		
		assertTrue(result);
		
	}// end public boolean deleteEvent(int event_id){
}
