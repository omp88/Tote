package by.epam.tote.command;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import by.epam.tote.constant.ClientConstant;
import by.epam.tote.constant.PageConstant;
import by.epam.tote.constant.SessionConstant;
import by.epam.tote.entity.Client;
import by.epam.tote.router.RouteType;
import by.epam.tote.router.Router;
import by.epam.tote.service.ClientService;
import by.epam.tote.validation.ToteValidator;

public class AddMoneyCommand implements ActionCommand {

	/**
	 * Adding money to client account
	 *
	 * @param request
	 * @return router
	 */
	@Override
	public Router execute(HttpServletRequest request) {

		ClientService service = new ClientService();
		ToteValidator validator = new ToteValidator();
		String amountParam = request.getParameter(ClientConstant.AMOUNT_OF_MONEY);
		if (!validator.validateMoneyAmount(amountParam)) {
			request.getSession().setAttribute(SessionConstant.ERROR, "error.invalid_money_amount");
			return new Router(RouteType.REDIRECT, PageConstant.MAIN_PAGE);
		}
		BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(amountParam));
		int clientId = ((Client) request.getSession().getAttribute(SessionConstant.CLIENT)).getId();
		Client client = service.findClientsById(clientId);
		BigDecimal newAmmount = client.getMoney().add(amount);
		service.updateClientMoney(clientId, newAmmount);
		client.setMoney(newAmmount);
		request.getSession().setAttribute(SessionConstant.CLIENT, client);
		request.getSession().setAttribute(SessionConstant.MESSAGE, "message.add_money_success");
		return new Router(RouteType.REDIRECT, PageConstant.MAIN_PAGE);
	}

}
