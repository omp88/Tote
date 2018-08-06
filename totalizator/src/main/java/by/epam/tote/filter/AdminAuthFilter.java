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

@WebFilter(urlPatterns = { "/pages/admin.jsp" })
public class AdminAuthFilter implements Filter {

	/**
	 * Prevent unauthorized access to admin page
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		String role = (String) session.getAttribute(SessionConstant.ROLE);
		if (role != null && RoleEnum.ADMIN.name().equalsIgnoreCase(role)) {

			chain.doFilter(request, response);
		} else {
			resp.sendRedirect(req.getContextPath() + PageConstant.MAIN_PAGE);
		}
	}

	@Override
	public void init(FilterConfig arg) throws ServletException {

	}

	@Override
	public void destroy() {
	}
}
