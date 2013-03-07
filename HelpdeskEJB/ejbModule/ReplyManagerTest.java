import static org.junit.Assert.*;
import businessLogic.*;
import org.junit.Test;


public class ReplyManagerTest {
	ReplyManager replyManager =new ReplyManager();
	@Test
	public void testCreateReply() {
		int x =replyManager.createReply(65, "1010790", 25, "answer");
		assertTrue(x>0);
	}

}
