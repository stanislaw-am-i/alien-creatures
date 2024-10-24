package lt.ehu.student.aliencreatures.controller.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class SessionAttributeListenerImpl implements HttpSessionAttributeListener {
    private static final Logger LOGGER = LogManager.getLogger(SessionAttributeListenerImpl.class);

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        LOGGER.debug("###<<<<----------> attributeAdded :" + sbe.getSession().getAttribute("user_name"));
        LOGGER.debug( "###<<<<----------> attributeAdded :" + sbe.getSession().getAttribute("current_page"));
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is removed from a session. */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        LOGGER.debug("###<<<<----------> attributeReplaced :" + sbe.getSession().getAttribute("user_name"));
        LOGGER.debug( "###<<<<----------> attributeReplaced :" + sbe.getSession().getAttribute("current_page"));
    }
}
