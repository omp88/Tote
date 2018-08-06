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

import by.epam.tote.constant.SessionConstant;

@WebFilter("/*")
public class SessionLocaleFilter implements Filter {

	/**
	 * Filter for localization
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;

		if (req.getParameter(SessionConstant.SESSION_LOCALE) != null) {
			req.getSession().setAttribute(SessionConstant.LANG, req.getParameter(SessionConstant.SESSION_LOCALE));
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
