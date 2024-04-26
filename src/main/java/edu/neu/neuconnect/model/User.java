package edu.neu.neuconnect.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "all roles", query = "FROM User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fname;
    private String lname;
    private String gender;
    private Date dob;
    private int karma = 500;
    private String username;
    private String password;
    private String nuid;
    private boolean isVerified;
    private String aboutMe;
    @Enumerated(EnumType.STRING)
    private RoleTypes role;
    private String profilepicPath = "/images/generic-image.png";

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    @OrderBy("id DESC")
    private List<Certificate> certificates = new ArrayList<>();

    public User(String fname, String lname, String gender, Date dob, String username, String password, String nuid,
            String aboutMe, RoleTypes role, String profilepicPath) {
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.dob = dob;
        this.username = username;
        this.password = password;
        this.nuid = nuid;
        this.aboutMe = aboutMe;
        this.role = role;
        this.profilepicPath = profilepicPath;
    }

    public User(String fname, String lname, String gender, Date dob, String username, String password, String nuid,
            String aboutMe, RoleTypes role) {
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.dob = dob;
        this.username = username;
        this.password = password;
        this.nuid = nuid;
        this.aboutMe = aboutMe;
        this.role = role;
    }

    public void addCertificate(Certificate certificate) {
        certificates.add(certificate);
        certificate.setUser(this);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + this.fname + '\'' +
                ", age=" + this.gender +
                '}';
    }
}
