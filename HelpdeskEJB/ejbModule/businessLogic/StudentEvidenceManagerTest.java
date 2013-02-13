package businessLogic;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class StudentEvidenceManagerTest {
	StudentEvidenceManager studentEvidenceManager = new StudentEvidenceManager();
	
	@Test
	public void testGetAllStudentEvidence() {
		ArrayList<StudentEvidence> allStudentEvidences = studentEvidenceManager.getAllStudentEvidence();
		
		for (int i = 0; i < allStudentEvidences.size(); i++) {
			System.out.println(allStudentEvidences.get(i).getStudent_id());
		}
		
		assertTrue(allStudentEvidences != null);
		
		
	}

}
