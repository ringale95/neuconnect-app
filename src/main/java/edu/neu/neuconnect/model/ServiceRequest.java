package edu.neu.neuconnect.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ServiceRequest {

    public ServiceRequest(String title2, String description2, int karma2) {
        this.title = title2;
        this.description = description2;
        this.karma = karma2;
    }

    public ServiceRequest() {
        // TODO Auto-generated constructor stub
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;
    private String title;

    @Enumerated(EnumType.STRING)
    private ServiceRequestStatus status;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;
    private int karma;
    @Enumerated(EnumType.STRING)
    private ServiceType type;
    private Date createdAt = new Date();
    @Transient
    private boolean showAssignedBtn;
    @Transient
    private boolean showCompleteBtn;
    @Transient
    private boolean showInProgressBtn;
    @Transient
    private boolean showEnrollBtn;

}
