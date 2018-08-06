package by.epam.tote.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.tote.constant.PageConstant;
import by.epam.tote.constant.SessionConstant;
import by.epam.tote.router.RouteType;
import by.epam.tote.router.Router;
import by.epam.tote.service.AdminService;

public class DeleteBetCommand implements ActionCommand {

	private static final String BET_ID = "bet_id";

	/**
	 * Delete bet command
	 *
	 * @param request
	 * @return router
	 */
	@Override
	public Router execute(HttpServletRequest request) {

		AdminService service = new AdminService();
		int betId = Integer.parseInt(request.getParameter(BET_ID));
		if (!service.isBetExist(betId)) {
			request.getSession().setAttribute(SessionConstant.ERROR, "error.no_bet_with_this_id");
			return new Router(RouteType.REDIRECT, PageConstant.ADMIN_PAGE);
		}
		service.deleteBet(betId);
		request.getSession().setAttribute(SessionConstant.MESSAGE, "message.bet_delete");
		return new Router(RouteType.FORWARD, PageConstant.ADMIN_PAGE);
	}
}
