import static org.junit.Assert.*;
import businessLogic.*;
import org.junit.Test;


public class TrackingLogManagerTest {
	TrackingLogManager trackingLogManager= new TrackingLogManager();
	
	
//	@Test
	public void testCreateTrackingLog() {
		fail("Not yet implemented");
	}

	//@Test
	public void testFindTrackingLog() {
		TrackingLog log = trackingLogManager.findTrackingLog(5);
		System.out.println(log.getInitial_receiver_id());
		assertTrue(log != null);
	}
	@Test
	public void testUpdateTrackingLog(){
		
		TrackingLog trackingLog = trackingLogManager.findTrackingLog(25);
		trackingLog.setStatus(100);
		
		System.out.println(trackingLog.getStatus());
		
		int statusUpdateId = trackingLogManager.updateTrackingLog(trackingLog);
		assertTrue(statusUpdateId>0);
	}

}
