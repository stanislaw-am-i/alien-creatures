package lt.ehu.student.aliencreatures.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import lt.ehu.student.aliencreatures.command.Command;

public class MultiplyBy2Command implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String numStr = request.getParameter("number");
        int num = Integer.parseInt(numStr);
        num *= 2;
        request.setAttribute("result", num);
        return "jsp/multiply_result.jsp";
    }
}
