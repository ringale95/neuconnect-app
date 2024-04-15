package edu.neu.neuconnect.model;

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
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;
    private int upvotes;
    private int downvotes;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="author_id")
    private User author;
    private Date createdAt = new Date();
    private Date updatedAt = new Date();
    @ElementCollection
    @CollectionTable(name="tags", joinColumns=@JoinColumn(name="post_id"))
    @Column(name="tag")
    private List<String> tags = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="comment_id")
    private List<Comment> comments = new ArrayList<>();
}
