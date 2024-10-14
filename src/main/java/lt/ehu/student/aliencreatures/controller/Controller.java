package lt.ehu.student.aliencreatures.controller;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lt.ehu.student.aliencreatures.command.Command;
import lt.ehu.student.aliencreatures.command.CommandType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(name = "alienCreaturesServlet", value = {"/controller", "*.do"})
public class Controller extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(Controller.class);

    public void init() {
        LOGGER.info("Servlet Initialized.");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Received Get Request.");
        processRequest(req, resp);
        LOGGER.info("Get Request has been processed.");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Received Post Request.");
        processRequest(req, resp);
        LOGGER.info("Post Request has been processed.");
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String commandStr = req.getParameter("command");
        LOGGER.info("The Command {} is processed.", commandStr);
        Command command = CommandType.defineCommand(commandStr);
        String page = command.execute(req);
        req.getRequestDispatcher(page).forward(req, resp);
    }

    public void destroy() {
        LOGGER.info("Servlet Destroyed.");
    }
}