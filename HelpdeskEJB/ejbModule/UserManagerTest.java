import static org.junit.Assert.*;

import javax.validation.constraints.AssertTrue;

import org.junit.Test;

import businessLogic.UserManager;


public class UserManagerTest {

	@Test
	public void testFindUser() {
		String user_id="1010790";
		businessLogic.UserManager userManager = new UserManager();
		businessLogic.User user = userManager.findUser(user_id);
		
		assertTrue(user.getPassword().equals("1010790"));
	}
	
	@Test
	public void testGetUser(){
		String username = "1010790";
		String password = "1010790";
		
		businessLogic.UserManager userManager = new UserManager();
		boolean validLogin = userManager.Login(username, password);
		
		String checkPassword = userManager.getUser().getPassword();
		
		assertTrue(checkPassword.equals(password));
	}

	@Test
	public void testLogin() {
		String username = "1010790";
		String password = "1010790";
		
		businessLogic.UserManager userManager = new UserManager();
		boolean validLogin = userManager.Login(username, password);
		
		assertTrue(validLogin);
	}

}
