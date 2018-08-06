package by.epam.tote.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.epam.tote.constant.ClientConstant;
import by.epam.tote.constant.PageConstant;
import by.epam.tote.constant.SessionConstant;
import by.epam.tote.entity.FootballBet;
import by.epam.tote.router.RouteType;
import by.epam.tote.router.Router;
import by.epam.tote.service.AdminService;

public class FindClientBetsCommand implements ActionCommand {

	private static final String CLIENT_BETS = "clientBets";

	/**
	 * Find stand bets of client
	 *
	 * @param request
	 * @return router
	 */
	@Override
	public Router execute(HttpServletRequest request) {

		AdminService service = new AdminService();
		int clientId = Integer.parseInt(request.getParameter(ClientConstant.CLIENT_ID));
		if (!service.isClientExist(clientId)) {
			request.getSession().setAttribute(SessionConstant.ERROR, "error.invalid_client_id");
			return new Router(RouteType.REDIRECT, PageConstant.ADMIN_PAGE);
		}
		List<FootballBet> clientBets = service.findClientBets(clientId);
		request.setAttribute(CLIENT_BETS, clientBets);
		return new Router(RouteType.FORWARD, PageConstant.ADMIN_RESULT_PAGE);
	}

}
