import static org.junit.Assert.*;
import businessLogic.*;
import org.junit.Test;


public class TrackingLogManagerTest {
	TrackingLogManager trackingLogManager= new TrackingLogManager();
	
	
	@Test
	public void testCreateTrackingLog() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindTrackingLog() {
		TrackingLog log = trackingLogManager.findTrackingLog(5);
		System.out.println(log.getReciever());
		assertTrue(log != null);
	}

}
