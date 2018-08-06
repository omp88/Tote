package by.epam.tote.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.epam.tote.constant.PageConstant;
import by.epam.tote.constant.SessionConstant;
import by.epam.tote.entity.FootballEvent;
import by.epam.tote.router.RouteType;
import by.epam.tote.router.Router;
import by.epam.tote.service.ClientService;

public class WelcomeCommand implements ActionCommand {

	/**
	 * Routing to main page
	 *
	 * @param  request
	 * @return  router
	 */
	@Override
	public Router execute(HttpServletRequest request) {

		ClientService service = new ClientService();
		List<FootballEvent> events = service.findFootballEvents();
		request.getSession().setAttribute(SessionConstant.FOOTBALL, events);
		return new Router(RouteType.FORWARD, PageConstant.MAIN_PAGE);
	}

}
