package by.epam.tote.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.tote.constant.PageConstant;
import by.epam.tote.constant.SessionConstant;
import by.epam.tote.entity.FootballEvent;
import by.epam.tote.router.RouteType;
import by.epam.tote.router.Router;
import by.epam.tote.service.ClientService;

public class LogoutCommand implements ActionCommand {

	/**
	 * Log out from system
	 *
	 * @param  request
	 * @return  router
	 */
	@Override
	public Router execute(HttpServletRequest request) {

		HttpSession session = request.getSession();
		session.removeAttribute(SessionConstant.ROLE);
		session.removeAttribute(SessionConstant.CLIENT);
		session.invalidate();
		ClientService service = new ClientService();
		List<FootballEvent> events = service.findFootballEvents();
		request.getSession().setAttribute(SessionConstant.FOOTBALL, events);
		request.getSession().setAttribute(SessionConstant.MESSAGE, "message.logout_success");
		return new Router(RouteType.FORWARD, PageConstant.MAIN_PAGE);
	}

}
