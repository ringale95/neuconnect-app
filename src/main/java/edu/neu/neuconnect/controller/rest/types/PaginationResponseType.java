package edu.neu.neuconnect.controller.rest.types;

import edu.neu.neuconnect.model.User;
import java.util.List;

public class PaginationResponseType {

    private List<User> records;
    private int pageNumber;
    private int count;

    public PaginationResponseType(List<User> records, int pageNumber, int pageSize) {
        this.records = records;
        this.pageNumber = pageNumber;
        this.count = pageSize;
    }


    public List<User> getRecords() {
        return records;
    }

    public void setRecords(List<User> records) {
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

    public String toString(){
        return "{\"pageNumber\":"+ this.getPageNumber()
                +",\"records\":\""+ this.getRecords()
                +",\"count\":" + this.getCount()+"}";
    }
}
