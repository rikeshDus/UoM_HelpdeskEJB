import static org.junit.Assert.*;

import java.util.ArrayList;

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
	
	@Test
	public void testGetAllUser(){
		businessLogic.UserManager userManager = new UserManager();
		ArrayList<businessLogic.User> allUser = userManager.getAllUser();
		
		String id = allUser.get(0).getUser_id();
		
		assertTrue(id.equals("1010790"));
	}

}
