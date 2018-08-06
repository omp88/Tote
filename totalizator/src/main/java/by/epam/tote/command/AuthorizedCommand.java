package by.epam.tote.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.tote.constant.PageConstant;
import by.epam.tote.constant.SessionConstant;
import by.epam.tote.entity.Client;
import by.epam.tote.router.RouteType;
import by.epam.tote.router.Router;
import by.epam.tote.service.ClientService;

public class AuthorizedCommand implements ActionCommand {

	private static final String PASSWORD = "password";
	private static final String LOGIN = "login";

	/**
	 * Client authorization
	 *
	 * @param request
	 * @return router
	 */
	@Override
	public Router execute(HttpServletRequest request) {

		String login = request.getParameter(LOGIN);
		String password = request.getParameter(PASSWORD);
		ClientService service = new ClientService();
		Client client = service.findClient(login, password);
		if (client == null) {
			request.getSession().setAttribute(SessionConstant.ERROR, "error.login_fail");
			return new Router(RouteType.REDIRECT, PageConstant.LOGIN_PAGE);
		}
		HttpSession session = request.getSession();
		session.setAttribute(SessionConstant.ROLE, SessionConstant.CLIENT);
		session.setAttribute(SessionConstant.CLIENT, client);
		return new Router(RouteType.FORWARD, PageConstant.MAIN_PAGE);
	}

}
