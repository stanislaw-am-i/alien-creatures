package lt.ehu.student.aliencreatures.controller.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionListener;
import jakarta.servlet.http.HttpSessionEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class SessionCreateListenerImpl implements HttpSessionListener {
    private static final Logger LOGGER = LogManager.getLogger(SessionCreateListenerImpl.class);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        LOGGER.debug("----------> sessionCreated :{}", se.getSession().getId());

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        LOGGER.debug("----------> sessionDestroyed :{}", se.getSession().getId());
    }
}
