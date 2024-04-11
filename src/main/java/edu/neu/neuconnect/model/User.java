package edu.neu.neuconnect.model;

import lombok.Data;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String gender;
    private Date dob;
    private String email;
    private String nuId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @Enumerated(EnumType.STRING)
    private UserProfile profile;

//    @OneToMany(mappedBy = "user")
//    private List<Certificate> certificates;

}
