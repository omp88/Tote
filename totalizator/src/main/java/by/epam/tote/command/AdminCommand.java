package by.epam.tote.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.tote.constant.PageConstant;
import by.epam.tote.constant.SessionConstant;
import by.epam.tote.entity.Admin;
import by.epam.tote.entity.RoleEnum;
import by.epam.tote.router.RouteType;
import by.epam.tote.router.Router;
import by.epam.tote.service.AdminService;

public class AdminCommand implements ActionCommand {

	private static final String PASSWORD = "password";
	private static final String LOGIN = "login";

	/**
	 * Administrator authorization
	 *
	 * @param request
	 * @return router
	 */
	@Override
	public Router execute(HttpServletRequest request) {

		HttpSession session = request.getSession();
		String role = (String) session.getAttribute(SessionConstant.ROLE);

		if (RoleEnum.ADMIN.name().equalsIgnoreCase(role)) {
			return new Router(RouteType.FORWARD, PageConstant.ADMIN_PAGE);
		} else {
			String login = request.getParameter(LOGIN);
			String password = request.getParameter(PASSWORD);
			AdminService service = new AdminService();
			Admin admin = service.findAdmin(login, password);
			if (admin == null) {
				request.getSession().setAttribute(SessionConstant.ERROR, "error.login_fail");
				return new Router(RouteType.REDIRECT, PageConstant.LOGIN_PAGE);
			}
			session.setAttribute(SessionConstant.ROLE, SessionConstant.ADMIN);
			return new Router(RouteType.FORWARD, PageConstant.ADMIN_PAGE);
		}
	}

}
