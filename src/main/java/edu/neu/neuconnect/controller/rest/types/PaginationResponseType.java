package edu.neu.neuconnect.controller.rest.types;

import edu.neu.neuconnect.model.User;
import java.util.List;

public class PaginationResponseType<T> {

    private List<T> records;
    private int pageNumber;
    private int count;

    public PaginationResponseType(List<T> records, int pageNumber, int pageSize) {
        this.records = records;
        this.pageNumber = pageNumber;
        this.count = pageSize;
    }


    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
