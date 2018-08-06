package by.epam.tote.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.tote.constant.PageConstant;
import by.epam.tote.constant.SessionConstant;
import by.epam.tote.entity.RoleEnum;

@WebFilter("/*")
public class UnauthorizedBetAccessFilter implements Filter {

	private static final String COMMAND = "command";
	private static final String BET = "bet";

	/**
	 * Prevent unauthorized access to bet page
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String command = req.getParameter(COMMAND);
		if (command != null && BET.equals(command)) {
			HttpSession session = req.getSession();
			String role = (String) session.getAttribute(SessionConstant.ROLE);
			if (RoleEnum.CLIENT.toString().equalsIgnoreCase(role)) {
				chain.doFilter(request, response);
			} else {
				req.getSession().setAttribute(SessionConstant.ERROR, "error.bet_access_error");
				resp.sendRedirect(req.getContextPath() + PageConstant.MAIN_PAGE);
				return;
			}
		}
		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig arg) throws ServletException {

	}

	@Override
	public void destroy() {

	}

}
