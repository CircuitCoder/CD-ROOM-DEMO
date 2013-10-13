package demo.cdroom;

import java.io.IOException;
import java.util.HashSet;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A filter that forbid all invalid request
 * @author circuitcoder
 *
 */
public class Blocker implements Filter {

	HashSet<Pattern> patterns;
	
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		if(req instanceof HttpServletRequest) {
			HttpServletRequest request=(HttpServletRequest) req;
			HttpServletResponse response=(HttpServletResponse) resp;
			String URI=request.getRequestURI();
			for(Pattern pattern:patterns) {
				if(pattern.matcher(URI).matches()) {
					response.sendError(HttpServletResponse.SC_FORBIDDEN);
					return;
				}
			}
		}
				
		chain.doFilter(req, resp);
	}

	public void init(FilterConfig config) throws ServletException {
		patterns.add(Pattern.compile("jsp"));
	}

}
