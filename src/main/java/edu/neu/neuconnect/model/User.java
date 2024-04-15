package edu.neu.neuconnect.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name="all roles", query ="FROM User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fname;
    private String lname;
    private String gender;
    private Date dob;
    private String username;
    private String password;
    private String nuid;
    private boolean isVerified;
    @Enumerated(EnumType.STRING)
    private RoleTypes role;
}
