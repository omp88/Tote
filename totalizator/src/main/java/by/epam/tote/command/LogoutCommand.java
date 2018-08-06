package by.epam.tote.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.tote.constant.PageConstant;
import by.epam.tote.constant.SessionConstant;
import by.epam.tote.router.RouteType;
import by.epam.tote.router.Router;

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
		request.getSession().setAttribute(SessionConstant.MESSAGE, "message.logout_success");
		return new Router(RouteType.FORWARD, PageConstant.MAIN_PAGE);
	}

}
