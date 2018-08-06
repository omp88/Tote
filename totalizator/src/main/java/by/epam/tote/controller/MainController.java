package by.epam.tote.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.tote.command.ActionCommand;
import by.epam.tote.command.ActionFactory;
import by.epam.tote.constant.PageConstant;
import by.epam.tote.router.RouteType;
import by.epam.tote.router.Router;

import java.io.IOException;

@WebServlet(name = "Controller", urlPatterns = "/")
public class MainController extends HttpServlet {

	private static final String COMMAND = "command";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		performAction(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		performAction(req, resp);
	}

	/**
	 * Perform action.
	 *
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException    Signals that an I/O exception has occurred.
	 */
	public void performAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String page = PageConstant.ERROR_PAGE;
		Router router = null;

		ActionFactory client = new ActionFactory();
		ActionCommand command = client.defineCommand(req.getParameter(COMMAND));
		router = command.execute(req);

		if (router.getRoute() != null && router.getType() == RouteType.FORWARD) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(router.getRoute());
			dispatcher.forward(req, resp);

		} else if (router.getRoute() != null && router.getType() == RouteType.REDIRECT) {

			resp.sendRedirect(req.getContextPath() + router.getRoute());
		} else {

			resp.sendRedirect(req.getContextPath() + page);
		}

	}

}
