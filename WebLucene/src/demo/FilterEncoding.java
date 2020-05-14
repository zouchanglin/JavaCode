package demo;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FilterEncoding implements Filter{
private FilterConfig config ;
	
	public void destroy() {
		this.config = null ;
	}

	public void doFilter(ServletRequest req, ServletResponse reqs,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)req ;
		HttpServletResponse response = (HttpServletResponse)reqs ;
		
		String encoding = config.getInitParameter("encoding") ;
		//System.out.println("设置编码为: "+encoding);
		
		
		if (encoding != null && request.getCharacterEncoding() == null) {
			response.setContentType("text/html");
			response.setCharacterEncoding(encoding);
			request.setCharacterEncoding(encoding);
		}
		//System.out.println("request给的是："+request.getCharacterEncoding());
		chain.doFilter(request, response) ;
		
	}

	public void init(FilterConfig config) throws ServletException {
		this.config = config ;
	}

}
