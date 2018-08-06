package by.epam.tote.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.tote.constant.EventConstant;
import by.epam.tote.constant.PageConstant;
import by.epam.tote.entity.FootballEvent;
import by.epam.tote.router.RouteType;
import by.epam.tote.router.Router;
import by.epam.tote.service.ClientService;

public class BetCommand implements ActionCommand {

	/**
	 * Routing to bet page
	 *
	 * @param request
	 * @return router
	 */
	@Override
	public Router execute(HttpServletRequest request) {

		int eventId = Integer.parseInt(request.getParameter(EventConstant.EVENT_ID));
		ClientService service = new ClientService();
		FootballEvent event = service.findFooballEvent(eventId);
		request.setAttribute(EventConstant.EVENT, event);
		return new Router(RouteType.FORWARD, PageConstant.BET_PAGE);
	}

}
