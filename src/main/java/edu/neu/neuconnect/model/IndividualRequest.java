package edu.neu.neuconnect.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@PrimaryKeyJoinColumn(name = "individual_id")
public class IndividualRequest extends ServiceRequest{

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="server_id", nullable = false)
    private User server;
}
