package by.epam.tote.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.tote.constant.PageConstant;
import by.epam.tote.router.RouteType;
import by.epam.tote.router.Router;

public class RegisterCommand implements ActionCommand {
	
	/**
	 * Routing to register page
	 *
	 * @param  request
	 * @return  router
	 */
	@Override
	public Router execute(HttpServletRequest request) {

		return new Router(RouteType.FORWARD, PageConstant.REGISTER_PAGE);
	}

}
