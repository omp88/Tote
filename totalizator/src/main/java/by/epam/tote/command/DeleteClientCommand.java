package by.epam.tote.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.tote.constant.ClientConstant;
import by.epam.tote.constant.PageConstant;
import by.epam.tote.constant.SessionConstant;
import by.epam.tote.router.RouteType;
import by.epam.tote.router.Router;
import by.epam.tote.service.AdminService;

public class DeleteClientCommand implements ActionCommand {

	/**
	 * Delete client command
	 *
	 * @param request
	 * @return router
	 */
	@Override
	public Router execute(HttpServletRequest request) {

		AdminService service = new AdminService();
		int clientId = Integer.parseInt(request.getParameter(ClientConstant.CLIENT_ID));
		if (!service.isClientExist(clientId)) {
			request.getSession().setAttribute(SessionConstant.ERROR, "error.no_client_with_this_id");
			return new Router(RouteType.REDIRECT, PageConstant.ADMIN_PAGE);
		}
		service.deleteClient(clientId);
		request.getSession().setAttribute(SessionConstant.MESSAGE, "message.client_delete");
		return new Router(RouteType.FORWARD, PageConstant.ADMIN_PAGE);
	}

}
