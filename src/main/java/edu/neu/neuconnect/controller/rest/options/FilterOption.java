package edu.neu.neuconnect.controller.rest.options;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FilterOption {
    private String searchKey = "";
    private boolean admin;
    private boolean student;
    private boolean authority;
}
