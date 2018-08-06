package by.epam.tote.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.epam.tote.constant.PageConstant;
import by.epam.tote.constant.SessionConstant;
import by.epam.tote.entity.Client;
import by.epam.tote.entity.FootballBet;
import by.epam.tote.router.RouteType;
import by.epam.tote.router.Router;
import by.epam.tote.service.ClientService;

public class ClientInfoCommand implements ActionCommand {

	private static final String STAND_BETS = "stand_bets";
	private static final String DONE_BETS = "done_bets";

	/**
	 * Routing to client profile
	 *
	 * @param request
	 * @return router
	 */
	@Override
	public Router execute(HttpServletRequest request) {

		int clientId = ((Client) request.getSession().getAttribute(SessionConstant.CLIENT)).getId();
		ClientService service = new ClientService();
		Client client = service.findClientsById(clientId);
		request.setAttribute(SessionConstant.CLIENT, client);
		List<FootballBet> doneBets = service.findDoneClientBets(clientId);
		request.setAttribute(DONE_BETS, doneBets);
		List<FootballBet> standBets = service.findStandClientBets(clientId);
		request.setAttribute(STAND_BETS, standBets);
		return new Router(RouteType.FORWARD, PageConstant.CLIENT_INFO_PAGE);
	}

}
