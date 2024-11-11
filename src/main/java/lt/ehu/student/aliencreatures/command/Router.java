package lt.ehu.student.aliencreatures.command;

/**
 *
 *  The redirect is protection from F5
 *
 * */
public class Router {
    private String page = CommandConstant.INDEX_PAGE;
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
