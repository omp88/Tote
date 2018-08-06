package by.epam.tote.command;

import java.sql.Timestamp;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import by.epam.tote.constant.EventConstant;
import by.epam.tote.constant.PageConstant;
import by.epam.tote.constant.SessionConstant;
import by.epam.tote.entity.FootballEvent;
import by.epam.tote.router.RouteType;
import by.epam.tote.router.Router;
import by.epam.tote.service.AdminService;
import by.epam.tote.validation.ToteValidator;

public class AddEventCommand implements ActionCommand {

	/**
	 * Adding new event.
	 *
	 * @param request
	 * @return router
	 */
	@Override
	public Router execute(HttpServletRequest request) {

		FootballEvent event = new FootballEvent();
		HashMap<String, String> eventParams = new HashMap<>();
		eventParams.put(EventConstant.EVENT_NAME, request.getParameter(EventConstant.EVENT_NAME));
		eventParams.put(EventConstant.EVENT_START, request.getParameter(EventConstant.EVENT_START));
		eventParams.put(EventConstant.TEAM_1_NAME, request.getParameter(EventConstant.TEAM_1_NAME));
		eventParams.put(EventConstant.TEAM_2_NAME, request.getParameter(EventConstant.TEAM_2_NAME));
		eventParams.put(EventConstant.TEAM_1_WIN_COEFF, request.getParameter(EventConstant.TEAM_1_WIN_COEFF));
		eventParams.put(EventConstant.TEAM_2_WIN_COEFF, request.getParameter(EventConstant.TEAM_2_WIN_COEFF));
		eventParams.put(EventConstant.DRAW_COEFF, request.getParameter(EventConstant.DRAW_COEFF));
		ToteValidator validator = new ToteValidator();
		boolean validateResult = validator.validateFootballEvent(eventParams);
		if (!validateResult) {
			request.getSession().setAttribute(SessionConstant.ERROR, "error.add_event_fail");
			return new Router(RouteType.REDIRECT, PageConstant.ADMIN_PAGE);
		}

		String parameter = (eventParams.get(EventConstant.EVENT_START)).replace("T", " ");
		String validStartTime = parameter.concat(":00");

		event.setFootballName(eventParams.get(EventConstant.EVENT_NAME));
		event.setStartTime(Timestamp.valueOf(validStartTime));
		event.setTeam1Name(eventParams.get(EventConstant.TEAM_1_NAME));
		event.setTeam2Name(eventParams.get(EventConstant.TEAM_2_NAME));
		event.setTeam1WinCoef(Double.parseDouble(EventConstant.TEAM_1_WIN_COEFF));
		event.setTeam2WinCoef(Double.parseDouble(EventConstant.TEAM_2_WIN_COEFF));
		event.setDrawCoef(Double.parseDouble(EventConstant.DRAW_COEFF));

		AdminService service = new AdminService();
		service.createNewEvent(event);
		request.getSession().setAttribute(SessionConstant.MESSAGE, "message.add_event_success");
		return new Router(RouteType.REDIRECT, PageConstant.ADMIN_PAGE);
	}

}
