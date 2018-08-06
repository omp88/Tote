package by.epam.tote.command;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import by.epam.tote.constant.ClientConstant;
import by.epam.tote.constant.PageConstant;
import by.epam.tote.constant.SessionConstant;
import by.epam.tote.entity.Client;
import by.epam.tote.router.RouteType;
import by.epam.tote.router.Router;
import by.epam.tote.service.ClientService;
import by.epam.tote.validation.ToteValidator;

public class RegisteredCommand implements ActionCommand {

	/**
	 * Register client command
	 *
	 * @param request
	 * @return router
	 */
	@Override
	public Router execute(HttpServletRequest request) {
		Client client = new Client();
		HashMap<String, String> clientParams = new HashMap<>();
		clientParams.put(ClientConstant.FIRST_NAME, request.getParameter(ClientConstant.FIRST_NAME));
		clientParams.put(ClientConstant.LAST_NAME, request.getParameter(ClientConstant.LAST_NAME));
		clientParams.put(ClientConstant.LOGIN, request.getParameter(ClientConstant.LOGIN));
		clientParams.put(ClientConstant.EMAIL, request.getParameter(ClientConstant.EMAIL));
		clientParams.put(ClientConstant.PASSWORD, request.getParameter(ClientConstant.PASSWORD));
		clientParams.put(ClientConstant.PASSPORT, request.getParameter(ClientConstant.PASSPORT));
		ToteValidator validator = new ToteValidator();

		if (validator.validateClientsParameters(clientParams)) {

			client.setFirstName(clientParams.get(ClientConstant.FIRST_NAME));
			client.setLastName(clientParams.get(ClientConstant.LAST_NAME));
			client.setLogin(clientParams.get(ClientConstant.LOGIN));
			client.setEmail(clientParams.get(ClientConstant.EMAIL));
			client.setPassword(clientParams.get(ClientConstant.PASSWORD));
			client.setPassportId(clientParams.get(ClientConstant.PASSPORT));
			ClientService service = new ClientService();
			service.createNewClient(client);
			request.getSession().setAttribute(SessionConstant.MESSAGE, "message.registration_success");
			return new Router(RouteType.REDIRECT, PageConstant.LOGIN_PAGE);

		} else {
			request.getSession().setAttribute(SessionConstant.ERROR, "error.registration_fail");
			return new Router(RouteType.REDIRECT, PageConstant.REGISTER_PAGE);
		}
	}

}
