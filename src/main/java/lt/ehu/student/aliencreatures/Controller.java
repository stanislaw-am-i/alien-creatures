package lt.ehu.student.aliencreatures;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class Controller extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(Controller.class);

    public Controller() {
        init();
    }

    public void init() {
        LOGGER.debug("Init alien creatures.");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String numStr = request.getParameter("number");
        int num = Integer.parseInt(numStr);
        num *= 2;
        request.setAttribute("first", num);
        request.getRequestDispatcher("/jsp/main.jsp").forward(request, response);
    }

    public void destroy() {}
}