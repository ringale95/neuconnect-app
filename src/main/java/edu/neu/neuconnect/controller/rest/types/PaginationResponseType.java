package edu.neu.neuconnect.controller.rest.types;

import edu.neu.neuconnect.model.User;
import lombok.Data;

import java.util.List;

@Data
public class PaginationResponseType<T> {

    private List<T> records;
    private int pageNumber;
    private int totalPages;

    public PaginationResponseType(List<T> records, int pageNumber, int totalPages) {
        this.records = records;
        this.pageNumber = pageNumber;
        this.totalPages = totalPages;
    }

}
