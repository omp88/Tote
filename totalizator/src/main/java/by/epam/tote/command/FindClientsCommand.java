package by.epam.tote.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.epam.tote.constant.PageConstant;
import by.epam.tote.entity.Client;
import by.epam.tote.router.RouteType;
import by.epam.tote.router.Router;
import by.epam.tote.service.AdminService;

public class FindClientsCommand implements ActionCommand {

	private static final String CLIENTS = "clients";

	/**
	 * Find clients command
	 *
	 * @param request
	 * @return router
	 */
	@Override
	public Router execute(HttpServletRequest request) {

		AdminService service = new AdminService();
		List<Client> clients = service.findClients();
		request.setAttribute(CLIENTS, clients);
		return new Router(RouteType.FORWARD, PageConstant.ADMIN_RESULT_PAGE);
	}

}
