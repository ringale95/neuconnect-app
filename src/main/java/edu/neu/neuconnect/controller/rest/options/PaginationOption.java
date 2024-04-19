package edu.neu.neuconnect.controller.rest.options;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationOption {
    private int pageSize;
    private int pageNumber;
    private FilterOption filterOption;
    private SortOption sortOption;
}
