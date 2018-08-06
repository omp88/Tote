package by.epam.tote.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.epam.tote.constant.EventConstant;
import by.epam.tote.constant.PageConstant;
import by.epam.tote.constant.SessionConstant;
import by.epam.tote.entity.FootballBet;
import by.epam.tote.router.RouteType;
import by.epam.tote.router.Router;
import by.epam.tote.service.AdminService;

public class FindEventBetsCommand implements ActionCommand {

	private static final String EVENT_BETS = "eventBets";

	/**
	 * Find event bets command
	 *
	 * @param request
	 * @return router
	 */
	@Override
	public Router execute(HttpServletRequest request) {

		AdminService service = new AdminService();
		int eventId = Integer.parseInt(request.getParameter(EventConstant.EVENT_ID));
		if (!service.isEventExist(eventId)) {
			request.getSession().setAttribute(SessionConstant.ERROR, "error.invalid_event_id");
			return new Router(RouteType.REDIRECT, PageConstant.ADMIN_PAGE);
		}
		List<FootballBet> eventBets = service.findEventBets(eventId);
		request.setAttribute(EVENT_BETS, eventBets);
		return new Router(RouteType.FORWARD, PageConstant.ADMIN_RESULT_PAGE);
	}

}
