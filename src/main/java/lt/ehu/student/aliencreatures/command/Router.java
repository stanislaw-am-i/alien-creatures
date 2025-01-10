package lt.ehu.student.aliencreatures.command;

import lt.ehu.student.aliencreatures.controller.PagePath;

public class Router {
    private String page = PagePath.INDEX_PAGE;
    private Type type = Type.FORWARD;

    enum Type {
        FORWARD, REDIRECT
    }

    public Router() {}

    public Router(String page) {
        this.page = page;
    }

    public Router(String page, Type type) {
        this.page = page;
        this.type = type;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setRedirect() {
        this.type = Type.REDIRECT;
    }

    public boolean isRedirect() {
        return this.type == Type.REDIRECT;
    }
}
