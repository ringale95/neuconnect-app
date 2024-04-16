package edu.neu.neuconnect.controller.rest.options;

public class PaginationOption {
    private int pageSize;
    private FilterOption filterOption;

    public PaginationOption(int pageSize, int pageNumber, FilterOption filterOption) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.filterOption = filterOption;
    }

    public FilterOption getFilterOption() {
        return filterOption;
    }

    public void setFilterOption(FilterOption filterOption) {
        this.filterOption = filterOption;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    private int pageNumber;
}
