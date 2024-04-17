package edu.neu.neuconnect.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Certificate> certificates = new ArrayList<>();


    public User(String fname, String lname, String gender, Date dob, String username, String password, String nuid, boolean isVerified, RoleTypes role) {
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.dob = dob;
        this.username = username;
        this.password = password;
        this.nuid = nuid;
        this.isVerified = isVerified;
        this.role = role;
    }

    public void addCertificate(Certificate certificate) {
        certificates.add(certificate);
        certificate.setUser(this);
    }
}
