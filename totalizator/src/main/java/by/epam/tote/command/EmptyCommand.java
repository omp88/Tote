package by.epam.tote.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.tote.constant.PageConstant;
import by.epam.tote.constant.SessionConstant;
import by.epam.tote.router.RouteType;
import by.epam.tote.router.Router;

public class EmptyCommand implements ActionCommand {

	/**
	 * Routing to error page
	 *
	 * @param  request
	 * @return  router
	 */
	@Override
	public Router execute(HttpServletRequest request) {

		request.getSession().setAttribute(SessionConstant.ERROR, "error.error_page_text");
		return new Router(RouteType.FORWARD, PageConstant.ERROR_PAGE);
	}

}
