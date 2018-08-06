package by.epam.tote.validator;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import by.epam.tote.constant.ClientConstant;
import by.epam.tote.constant.EventConstant;
import by.epam.tote.validation.ToteValidator;

public class ToteValidatorTest {

	ToteValidator validator;

	@BeforeTest
	public void setUp() {

		validator = new ToteValidator();
	}
	
	@DataProvider(name = "test")
	public Object[][] createData1() {
	 return new Object[][] {
	   { "100.22", true },
	   { "-12.2", false},
	   { "0", false},
	   { "11.21212", false},
	   { "144404004040404040404422222", false},
	   { "23.22", true},
	   { "0.55", true},
	   { "10.4", true},
	   { "-19", false},
	 };
	}

	@Test(dataProvider = "test")
	public void validateMoneyAmountTest(String amount, boolean expected) {
		
	
		Assert.assertEquals(validator.validateFootballBetAmount(amount), expected);
	}
	@Test
	public void validateFootballBetAmountTestPositive() {

		String score = "100.1";

		Assert.assertTrue(validator.validateFootballBetAmount(score));
	}

	@Test
	public void validateFootballBetAmountTestNegative() {

		String score = "10022222222222222222,1";

		Assert.assertFalse(validator.validateFootballBetAmount(score));
	}

	@Test
	public void validateFootballScoreTestPositive() {

		String score1 = "1";
		String score2 = "2";

		Assert.assertTrue(validator.validateFootballScore(score1, score2));
	}

	@Test
	public void validateFootballScoreTestNegative() {

		String score1 = "-1";
		String score2 = "2222";

		Assert.assertFalse(validator.validateFootballScore(score1, score2));
	}

	@Test
	public void validateFootballEventTestPositive() {
		
		HashMap<String, String> eventParams = new HashMap<>();
		eventParams.put(EventConstant.EVENT_NAME, "match123");
		eventParams.put(EventConstant.EVENT_START, "2018-12-31T23:59");
		eventParams.put(EventConstant.TEAM_1_NAME, "red");
		eventParams.put(EventConstant.TEAM_2_NAME, "blue");
		eventParams.put(EventConstant.TEAM_1_WIN_COEFF, "1.2");
		eventParams.put(EventConstant.TEAM_2_WIN_COEFF, "2.4");
		eventParams.put(EventConstant.DRAW_COEFF, "2.3");

		Assert.assertTrue(validator.validateFootballEvent(eventParams));
	}

	@Test
	public void validateFootballEventTestNegative() {

		HashMap<String, String> eventParams = new HashMap<>();
		eventParams.put(EventConstant.EVENT_NAME, "match123");
		eventParams.put(EventConstant.EVENT_START, "9999-12-31 23:59");
		eventParams.put(EventConstant.TEAM_1_NAME, "red");
		eventParams.put(EventConstant.TEAM_2_NAME, "blue");
		eventParams.put(EventConstant.TEAM_1_WIN_COEFF, "1.2");
		eventParams.put(EventConstant.TEAM_2_WIN_COEFF, "2,4");
		eventParams.put(EventConstant.DRAW_COEFF, "2.3");

		Assert.assertFalse(validator.validateFootballEvent(eventParams));
	}

	@Test
	public void validateClientParamsTestPositive() {

		HashMap<String, String> clientsParams = new HashMap<>();
		clientsParams.put(ClientConstant.FIRST_NAME, "Richi");
		clientsParams.put(ClientConstant.LAST_NAME, "Smullen");
		clientsParams.put(ClientConstant.EMAIL, "red@tut.by");
		clientsParams.put(ClientConstant.LOGIN, "red");
		clientsParams.put(ClientConstant.PASSWORD, "redred");
		clientsParams.put(ClientConstant.PASSPORT, "22222");

		Assert.assertTrue(validator.validateClientsParameters(clientsParams));
	}

	@Test
	public void validateClientParamsTestNegative() {

		HashMap<String, String> clientsParams = new HashMap<>();
		clientsParams.put(ClientConstant.FIRST_NAME, "Rich222i");
		clientsParams.put(ClientConstant.LAST_NAME, "Smullen");
		clientsParams.put(ClientConstant.EMAIL, "red@tut.byyy");
		clientsParams.put(ClientConstant.LOGIN, "red");
		clientsParams.put(ClientConstant.PASSWORD, "redred");
		clientsParams.put(ClientConstant.PASSPORT, "22222");

		Assert.assertFalse(validator.validateClientsParameters(clientsParams));
	}

}
