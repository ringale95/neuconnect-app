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

    public IndividualRequest(String title, String description, int karma, ServiceType type) {
        super(title, description, karma, type);
    }

    public IndividualRequest() {
        // TODO Auto-generated constructor stub
    }

    @OneToOne
    @JoinColumn(name = "cert_id", nullable = true)
    private Certificate certificateAttached;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "server_id", nullable = true)
    private User server;
}
