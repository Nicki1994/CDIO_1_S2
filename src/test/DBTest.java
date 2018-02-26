package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import data.IUserDAO;
import data.UserDAO;
import data.UserDTO;
import data.IUserDAO.DALException;

public class DBTest {

	
	
//	test the ability to make a user
	@Test
	public void test1() {
		IUserDAO iDAO = new UserDAO();
		UserDTO newUser = new UserDTO();
		newUser.setCpr(12);
		newUser.setPassword("abe");
		newUser.addRole("Admin");
		newUser.setUserName("testName");
		newUser.setUserId(0);
		try {
			iDAO.createUser(newUser);
			assertEquals("UserDTO [userId=0, userName=testNameCPR=12Pass=abe, ini=TEME, roles=[Admin]]\n",getUsers(iDAO));
		} catch (DALException e) {
			e.printStackTrace();
		}	
	}
	
	
	
	
	
	
	
	
	
	//helpermethod
	private static String getUsers(IUserDAO iDAO) {
		String str="";
		try {
			List<UserDTO> userList = iDAO.getUserList();
			for (UserDTO userDTO : userList) {
				str += userDTO.toString()+"\n";
			}
			return str;
		} catch (DALException e) {
			e.printStackTrace();
		}
		return str;
	}

}
