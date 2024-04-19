package edu.neu.neuconnect.controller.rest.options;

import com.sun.istack.NotNull;
import edu.neu.neuconnect.model.NotificationStatus;
import edu.neu.neuconnect.model.ServiceRequest;
import edu.neu.neuconnect.model.ServiceType;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FilterOption {
    private String searchKey = "";
    private boolean admin;
    private boolean student;
    private boolean authority;
    private NotificationStatus status;
    private ServiceType type;
    private int karmaMin;
    private int karmaMax;

    public boolean isEmpty() {
        return karmaMax == 0 && karmaMin == 0 && this.type == null && searchKey.equals("");
    }

}
