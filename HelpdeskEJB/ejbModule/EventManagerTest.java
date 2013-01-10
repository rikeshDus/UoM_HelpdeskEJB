

import static org.junit.Assert.*;

import org.junit.Test;

import businessLogic.EventManager;

public class EventManagerTest {

	@Test
	public void testCreateEvent() {
		EventManager eventManager = new EventManager();
		int rs = eventManager.createEvent("title", "description", "type", "1010790");
		assertTrue(rs>0);
		
	}

}
