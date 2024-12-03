package lt.ehu.student.aliencreatures.controller;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lt.ehu.student.aliencreatures.command.Command;
import lt.ehu.student.aliencreatures.command.CommandType;
import lt.ehu.student.aliencreatures.command.Router;
import lt.ehu.student.aliencreatures.exception.CommandException;
import lt.ehu.student.aliencreatures.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(name = "alienCreaturesServlet", value = {"/controller", "*.do"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // Files larger than 2MB will be written to disk.
        maxFileSize = 1024 * 1024 * 10,      // 10MB is maximum size of an individual uploaded file.
        maxRequestSize = 1024 * 1024 * 50    // 50MB is maximum size of the entire request, including all files and form data.
)
public class Controller extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(Controller.class);

    public void init() {
        LOGGER.info("Servlet Initialized.");
        ConnectionPool.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("Received Get Request.");
        processRequest(req, resp);
        LOGGER.debug("Get Request has been processed.");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("Received Post Request.");
        processRequest(req, resp);
        LOGGER.debug("Post Request has been processed.");
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(ControllerConstant.CONTENT_TYPE_HTML);
        String commandStr = req.getParameter(ControllerConstant.COMMAND_PARAM);
        LOGGER.debug("The Command {} is processed.", commandStr);
        Command command = CommandType.defineCommand(commandStr);
        try {
            Router router = command.execute(req);
            if (router.isRedirect()) {
                resp.sendRedirect(router.getPage());
            } else {
                req.getRequestDispatcher(router.getPage()).forward(req, resp);
            }
        } catch (CommandException e) {
            req.setAttribute(ControllerConstant.ATTR_ERROR_MESSAGE, e.getCause());
            req.getRequestDispatcher(ControllerConstant.ERROR_500_PAGE).forward(req, resp);
        }
    }

    public void destroy() {
        ConnectionPool.getInstance().destroyPool();
        LOGGER.info("Servlet Destroyed.");
    }
}