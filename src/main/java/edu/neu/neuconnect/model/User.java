package edu.neu.neuconnect.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fname;
    private String lname;
    private int age;
    private String gender;
    private Date dob;
    private String username;
    private String password;
    private String nuid;
    private boolean isVerified;
    private String verificationToken;
}
