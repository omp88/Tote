package by.epam.tote.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.tote.constant.EventConstant;
import by.epam.tote.constant.PageConstant;
import by.epam.tote.constant.SessionConstant;
import by.epam.tote.router.RouteType;
import by.epam.tote.router.Router;
import by.epam.tote.service.AdminService;

public class DeleteEventCommand implements ActionCommand {

	/**
	 * Delete event command
	 *
	 * @param request
	 * @return router
	 */
	@Override
	public Router execute(HttpServletRequest request) {

		AdminService service = new AdminService();
		int eventId = Integer.parseInt(request.getParameter(EventConstant.EVENT_ID));
		if (!service.isEventExist(eventId)) {
			request.getSession().setAttribute(SessionConstant.ERROR, "error.no_event_with_this_id");
			return new Router(RouteType.REDIRECT, PageConstant.ADMIN_PAGE);
		}
		service.deleteEvent(eventId);
		request.getSession().setAttribute(SessionConstant.MESSAGE, "message.event_delete");
		return new Router(RouteType.REDIRECT, PageConstant.ADMIN_PAGE);
	}

}
