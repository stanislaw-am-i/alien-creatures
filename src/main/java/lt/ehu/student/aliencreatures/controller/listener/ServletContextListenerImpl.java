package lt.ehu.student.aliencreatures.controller.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.*;

import lt.ehu.student.aliencreatures.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class ServletContextListenerImpl implements ServletContextListener {
    private static final Logger LOGGER = LogManager.getLogger(ServletContextListenerImpl.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.debug("+++++++++ contextInitialized :" + sce.getServletContext().getServerInfo());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOGGER.debug("+++++++++ contextDestroyed :" + sce.getServletContext().getServerInfo());
        ConnectionPool.getInstance().destroyPool();
    }
}
