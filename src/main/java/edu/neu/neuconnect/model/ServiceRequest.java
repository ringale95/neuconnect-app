package edu.neu.neuconnect.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ServiceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;
    private String title;

    @Enumerated(EnumType.STRING)
    private ServiceRequestStatus status;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="author_id", nullable = false)
    private User author;
    private int karma;
    @Enumerated(EnumType.STRING)
    private ServiceType type;
}
