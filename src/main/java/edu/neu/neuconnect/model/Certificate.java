package edu.neu.neuconnect.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean isVerified;
    private String path;
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;
    @Enumerated(EnumType.STRING)
    private ServiceType type;

    public Certificate(boolean isVerified, String path, ServiceType type) {
        this.isVerified = isVerified;
        this.path = path;
        this.user = user;
        this.type = type;
    }
}
