package pl.coderslab.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "SessionBypass", urlPatterns = {"/*"}) // delete `, urlPatterns = {"/*"}` after testing
public class SessionBypass implements Filter
{
    public void destroy()
    {
    }

    /**
     * DUMMY SESSION FOR TESTING
     * Comment doFilter method after testing or delete this class
     *
     * @param req
     * @param resp
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException
    {
        System.err.println("*** SESSION IS BYPASSED! ***");

        HttpServletRequest request = (HttpServletRequest) req; // full request

        HttpSession session = request.getSession(true);

        session.setAttribute("admin_name", "admin");
        session.setAttribute("admin_pass", "coderslab");

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException
    {

    }

}
