package by.epam.tote.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.tote.constant.EventConstant;
import by.epam.tote.constant.PageConstant;
import by.epam.tote.constant.SessionConstant;
import by.epam.tote.entity.EventResultType;
import by.epam.tote.entity.FootballResult;
import by.epam.tote.router.RouteType;
import by.epam.tote.router.Router;
import by.epam.tote.service.AdminService;
import by.epam.tote.validation.ToteValidator;

public class AddResultCommand implements ActionCommand {

	/**
	 * Adding result to completed event and calculate bets
	 *
	 * @param request
	 * @return router
	 */
	@Override
	public Router execute(HttpServletRequest request) {

		AdminService service = new AdminService();
		int eventId = Integer.parseInt(request.getParameter(EventConstant.EVENT_ID));
		if(!service.isEventExist(eventId)) {
			request.getSession().setAttribute(SessionConstant.ERROR, "error.no_event_with_this_id");
			return new Router(RouteType.REDIRECT, PageConstant.ADMIN_PAGE);
		}
		FootballResult result = new FootballResult();
		result.setId(eventId);
		result.setType(EventResultType.valueOf(request.getParameter(EventConstant.EVENT_RESULT).toUpperCase()));
		String team1Score = request.getParameter(EventConstant.TEAM_1_SCORE);
		String team2Score = request.getParameter(EventConstant.TEAM_2_SCORE);
		ToteValidator validator = new ToteValidator();
		boolean validateFootballScore = validator.validateFootballScore(team1Score, team2Score);
		if (!validateFootballScore) {
			request.getSession().setAttribute(SessionConstant.ERROR, "error.invalid_score");
			return new Router(RouteType.REDIRECT, PageConstant.ADMIN_PAGE);
		}
		result.setTeam1Score(Integer.parseInt(team1Score));
		result.setTeam2Score(Integer.parseInt(team2Score));

		if (result.getType() == EventResultType.DRAW && result.getTeam1Score() != result.getTeam2Score()) {
			request.getSession().setAttribute(SessionConstant.ERROR, "error.add_result_invalid_draw_score");
			return new Router(RouteType.REDIRECT, PageConstant.ADMIN_PAGE);
		}
		if (result.getType() == EventResultType.FIRST && result.getTeam1Score() <= result.getTeam2Score()) {
			request.getSession().setAttribute(SessionConstant.ERROR, "error.invalid_score");
			return new Router(RouteType.REDIRECT, PageConstant.ADMIN_PAGE);
		}
		if (result.getType() == EventResultType.SECOND && result.getTeam1Score() >= result.getTeam2Score()) {
			request.getSession().setAttribute(SessionConstant.ERROR, "error.invalid_score");
			return new Router(RouteType.REDIRECT, PageConstant.ADMIN_PAGE);
		}
		service.addEventResult(result);
		request.getSession().setAttribute(SessionConstant.MESSAGE, "message.add_result_success");
		return new Router(RouteType.REDIRECT, PageConstant.ADMIN_PAGE);
	}

}
