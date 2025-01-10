package lt.ehu.student.aliencreatures.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import lt.ehu.student.aliencreatures.controller.Parameter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.IOException;

/* chainresponsobility pattern */
@WebFilter(filterName = "EncodingAndContentTypeFilter", urlPatterns = "/*")
public class EncodingAndContentTypeFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(EncodingAndContentTypeFilter.class);
    public void init(FilterConfig config) throws ServletException {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        response.setContentType(Parameter.CONTENT_TYPE_HTML);
        response.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }

    public void destroy() {}
}
