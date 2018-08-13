package by.epam.tote.command;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import by.epam.tote.constant.ClientConstant;
import by.epam.tote.constant.EventConstant;
import by.epam.tote.constant.PageConstant;
import by.epam.tote.constant.SessionConstant;
import by.epam.tote.entity.BetCondition;
import by.epam.tote.entity.BetState;
import by.epam.tote.entity.Client;
import by.epam.tote.entity.FootballBet;
import by.epam.tote.entity.FootballEvent;
import by.epam.tote.router.RouteType;
import by.epam.tote.router.Router;
import by.epam.tote.service.ClientService;
import by.epam.tote.util.BetAdditor;
import by.epam.tote.validation.ToteValidator;

public class BetdoneCommand implements ActionCommand {

	/**
	 * Adding bet
	 *
	 * @param request
	 * @return router
	 */
	@Override
	public Router execute(HttpServletRequest request) {

		ClientService service = new ClientService();
		FootballBet bet = new FootballBet();
		FootballEvent event = new FootballEvent();
		ToteValidator validator = new ToteValidator();
		String amountParam = request.getParameter(ClientConstant.AMOUNT_OF_MONEY);
		if (!validator.validateFootballBetAmount(amountParam)) {
			FootballEvent forwardEvent = service.findFooballEvent(Integer.parseInt(request.getParameter(EventConstant.EVENT_ID)));
			request.setAttribute(EventConstant.EVENT, forwardEvent);
			request.getSession().setAttribute(SessionConstant.ERROR, "error.invalid_bet_amount");
			return new Router(RouteType.FORWARD, PageConstant.BET_PAGE);
		}
		event.setId(Integer.parseInt(request.getParameter(EventConstant.EVENT_ID)));
		event.setTeam1WinCoef(Double.parseDouble(request.getParameter(EventConstant.TEAM_1_WIN_COEFF)));
		event.setTeam2WinCoef(Double.parseDouble(request.getParameter(EventConstant.TEAM_2_WIN_COEFF)));
		event.setDrawCoef(Double.parseDouble(request.getParameter(EventConstant.DRAW_COEFF)));
		String team1Score = request.getParameter(EventConstant.TEAM_1_SCORE);
		String team2Score = request.getParameter(EventConstant.TEAM_2_SCORE);
		if (!team1Score.isEmpty() && !team2Score.isEmpty()) {

			if (!validator.validateFootballScore(team1Score, team2Score)) {
				FootballEvent forwardEvent = service
						.findFooballEvent(Integer.parseInt(request.getParameter(EventConstant.EVENT_ID)));
				request.setAttribute(EventConstant.EVENT, forwardEvent);
				request.getSession().setAttribute(SessionConstant.ERROR, "error.invalid_score");
				return new Router(RouteType.FORWARD, PageConstant.BET_PAGE);
			}
			int validTeam1Score = Integer.parseInt(team1Score);
			int validTeam2Score = Integer.parseInt(team2Score);
			bet.setCondition(BetCondition.SCORE);
			bet.setTeam1ExpectedScore(validTeam1Score);
			bet.setTeam2ExpectedScore(validTeam2Score);
			double scoreCoeff = BetAdditor.scoreCoefficientCalculate(validTeam1Score, validTeam2Score, event);
			bet.setCoeff(scoreCoeff);
		} else {
			BetCondition condition = BetCondition.valueOf(request.getParameter(EventConstant.CONDITION).toUpperCase());
			bet.setCondition(condition);
			double conditionCoeff = BetAdditor.conditionCoefficientCalculate(condition, event);
			bet.setCoeff(conditionCoeff);
		}

		bet.setEventId(event.getId());
		Client client = (Client) request.getSession().getAttribute(SessionConstant.CLIENT);
		bet.setClientId(client.getId());
		bet.setAmount(BigDecimal.valueOf(Double.parseDouble(amountParam)));
		bet.setBetTime(new Timestamp(System.currentTimeMillis()));
		bet.setState(BetState.STAND);

		boolean result = service.addBet(bet);
		
		if(!result) {
			FootballEvent forwardEvent = service
					.findFooballEvent(Integer.parseInt(request.getParameter(EventConstant.EVENT_ID)));
			request.setAttribute(EventConstant.EVENT, forwardEvent);
			request.getSession().setAttribute(SessionConstant.ERROR, "error.bet_fail");
			return new Router(RouteType.FORWARD, PageConstant.BET_PAGE);
		}
		client.setMoney(service.findClientsById(client.getId()).getMoney());
		request.setAttribute(SessionConstant.CLIENT, client);
		request.getSession().setAttribute(SessionConstant.MESSAGE, "message.bet_success");
		return new Router(RouteType.REDIRECT, PageConstant.MAIN_PAGE);

	}

}
