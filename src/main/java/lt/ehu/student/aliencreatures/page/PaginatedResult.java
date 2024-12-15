package lt.ehu.student.aliencreatures.page;

import java.util.List;

public class PaginatedResult<T> {
    private final List<T> items;
    private final int currentPage;
    private final int totalPages;

    public PaginatedResult(List<T> items, int currentPage, int totalPages) {
        this.items = items;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }

    public List<T> getItems() {
        return items;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
