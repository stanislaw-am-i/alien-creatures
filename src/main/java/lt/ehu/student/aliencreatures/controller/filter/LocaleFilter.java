package lt.ehu.student.aliencreatures.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.util.Locale;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter(filterName = "LocaleFilter", urlPatterns = "/*")
public class LocaleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpRequest.getSession();

        String localeParam = httpRequest.getParameter("lang");
        Locale locale;
        if (localeParam != null) {
            if ("be".equalsIgnoreCase(localeParam)) {
                locale = new Locale("be");
            } else {
                locale = Locale.ENGLISH;
            }
            session.setAttribute("userLocale", locale);
        }

        if (session.getAttribute("userLocale") == null) {
            session.setAttribute("userLocale", Locale.ENGLISH);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {}
}
