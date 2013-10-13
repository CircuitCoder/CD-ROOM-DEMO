package demo.cdroom;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import demo.cdroom.data.UserData;
import demo.cdroom.data.UserData.User;

/**
 * A filter that gets user's information from the database, based on the session ID
 * @author circuitcoder
 *
 */
public class Auther implements Filter {
	
	/**
	 * The database
	 */
	UserData users;

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		if(req instanceof HttpServletRequest) {
			HttpSession session=((HttpServletRequest) req).getSession();
			User user=users.getUserInfo(session.getId());
			req.setAttribute("userinfo", user);
		}
		
		chain.doFilter(req, resp);
	}

	public void init(FilterConfig config) throws ServletException {
		users=UserData.getData();
		//TODO: add password file
	}

}
