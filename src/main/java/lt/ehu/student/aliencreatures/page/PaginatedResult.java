package lt.ehu.student.aliencreatures.page;

import java.util.List;

public class PaginatedResult<T> {
    private final List<T> items;
    private final int currentPage;
    private final int totalPages;
    private final int pageSize;

    public PaginatedResult(List<T> items, int currentPage, int totalPages, int pageSize) {
        this.items = items;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.pageSize = pageSize;
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

    public int getPageSize() {
        return pageSize;
    }
}
