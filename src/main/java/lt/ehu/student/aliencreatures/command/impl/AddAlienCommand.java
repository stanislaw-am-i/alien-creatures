package lt.ehu.student.aliencreatures.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import lt.ehu.student.aliencreatures.command.Command;
import lt.ehu.student.aliencreatures.entity.Alien;
import lt.ehu.student.aliencreatures.service.AlienService;
import lt.ehu.student.aliencreatures.service.impl.AlienServiceImpl;

import java.util.List;

public class AddAlienCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        String lor = request.getParameter("lor");
        AlienService alienService = new AlienServiceImpl();
        String page = "jsp/aliens.jsp";
        if (isNullOrEmpty(name) || isNullOrEmpty(lor)) {
            request.setAttribute("errorPassMessage", "Provided params are not valid");
            return page;
        }

        boolean isExists = alienService.checkDuplicate(name, lor);
        if (isExists) {
            request.setAttribute("errorPassMessage", "This alien has already been added");
        } else if (alienService.addNewCharacter(name, lor)) {
            List<Alien> aliens = alienService.fetchListOfCharacters();
            request.setAttribute("aliensList", aliens);
        } else {
            request.setAttribute("errorPassMessage", "Failed to save the alien.");
        }

        return page;
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
