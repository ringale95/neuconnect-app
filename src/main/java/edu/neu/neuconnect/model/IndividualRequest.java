package edu.neu.neuconnect.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@PrimaryKeyJoinColumn(name = "individual_id")
public class IndividualRequest extends ServiceRequest {

    public IndividualRequest(String title, String description, int karma) {
        super(title, description, karma);
    }

    public IndividualRequest() {
        super();
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "server_id", nullable = true)
    private User server;
}
