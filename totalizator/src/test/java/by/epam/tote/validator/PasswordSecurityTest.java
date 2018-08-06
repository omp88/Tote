package by.epam.tote.validator;

import org.mindrot.jbcrypt.BCrypt;
import org.testng.Assert;
import org.testng.annotations.Test;

import by.epam.tote.util.PasswordSecurity;

public class PasswordSecurityTest {
	
	@Test
	public void securityTestPositive(){
		
		String pass = "Fringilla";
		String hash  = PasswordSecurity.makePassword(pass);
		Assert.assertTrue(BCrypt.checkpw(pass, hash));
	}
	@Test
	public void securityTestNegative(){
		
		String pass = "Fringilla";
		String hash  = "Fringilla";
		Assert.assertFalse(BCrypt.checkpw(pass, hash));
	}

}
