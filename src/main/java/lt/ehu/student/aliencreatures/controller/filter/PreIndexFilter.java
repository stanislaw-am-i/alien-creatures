package lt.ehu.student.aliencreatures.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lt.ehu.student.aliencreatures.controller.listener.ServletContextListenerImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.IOException;

/* chainresponsobility pattern */
@WebFilter(filterName = "PreIndexFilter", urlPatterns = "/index.jsp")
public class PreIndexFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(PreIndexFilter.class);
    public void init(FilterConfig config) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession(false);
        LOGGER.debug("----------> Session In  PreIndexFilter:" + (session != null ? session.getId(): "sessionNotCreated" ));
        chain.doFilter(request, response);
    }
    public void destroy() {
    }
}
