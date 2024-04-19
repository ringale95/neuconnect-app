package edu.neu.neuconnect.controller.rest.options;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class SortOption {
    private String key;
    private String type;

    public static List<String> getSortOptions(){
        List<String> options = new ArrayList<>();
        options.add("karma - ASC");
        options.add("karma - DESC");
        return options;
    }
}
