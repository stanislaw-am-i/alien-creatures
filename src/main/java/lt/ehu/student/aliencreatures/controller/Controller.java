package lt.ehu.student.aliencreatures.controller;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lt.ehu.student.aliencreatures.command.Command;
import lt.ehu.student.aliencreatures.command.CommandType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(name = "helloServlet", value = "/controller")
public class Controller extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(Controller.class);

    public void init() {
        LOGGER.info("The Alien Creatures Servlet has started.");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String numStr = request.getParameter("number");
        int num = Integer.parseInt(numStr);
        num *= 2;
        request.setAttribute("first", num);
        request.getRequestDispatcher("/jsp/main.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug(36);
        resp.setContentType("text/html");
        String commandStr = req.getParameter("command");
        Command command = CommandType.defineCommand(commandStr);
        String page = command.execute(req);
        LOGGER.debug(page);
        req.getRequestDispatcher(page).forward(req, resp);
    }

    public void destroy() {}
}