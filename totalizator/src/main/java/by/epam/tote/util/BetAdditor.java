package by.epam.tote.util;

import by.epam.tote.entity.BetCondition;
import by.epam.tote.entity.FootballEvent;


public class BetAdditor {

	/**
	 * Score coefficient calculate.
	 *
	 * @param team1Score
	 * @param team2Score
	 * @param event
	 * @return double coeff
	 */
	public static double scoreCoefficientCalculate(int team1Score, int team2Score, FootballEvent event) {

		double result = 0;

		if (team1Score == team2Score) {
			result = Double.sum(event.getDrawCoef(), 5.0d);
		} else {
			result = team1Score > team2Score ? Double.sum(event.getTeam1WinCoef(), 5.0d)
					: Double.sum(event.getTeam2WinCoef(), 5.0d);
		}
		return result;
	}

	/**
	 * Condition coefficient calculate.
	 *
	 * @param condition
	 * @param event
	 * @return double coeff
	 */
	public static double conditionCoefficientCalculate(BetCondition condition, FootballEvent event) {

		double result = 0;

		switch (condition) {

		case FIRST:
			result = event.getTeam1WinCoef();
			break;
		case SECOND:
			result = event.getTeam2WinCoef();
			break;
		case DRAW:
			result = event.getDrawCoef();
			break;
		default:
			break;
		}
		return result;
	}

}
