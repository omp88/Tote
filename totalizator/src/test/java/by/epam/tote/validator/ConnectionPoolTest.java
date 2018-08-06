package by.epam.tote.validator;

import org.testng.Assert;
import org.testng.annotations.Test;

import by.epam.tote.pool.ConnectionPool;

public class ConnectionPoolTest {
	
	@Test
	public void connectionPoolCreationTest() {
		
		ConnectionPool pool = ConnectionPool.getInstance();
		
		Assert.assertTrue(pool != null);
		
	}

}
