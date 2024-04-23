package edu.neu.neuconnect.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @Lob
    private String content;

    private int upvotes;
    private int downvotes;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private User author;

    private Date createdAt = new Date();
    private Date updatedAt = new Date();

    @ElementCollection
    @CollectionTable(name = "tags", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "tag")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<String> tags = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "PostLikes")
    private List<User> likedBy = new ArrayList<>();

    @Transient
    private boolean liked;

    public void upvote() {
        this.upvotes++;
    }

    public void addUserToLikedBy(User user) {
        this.likedBy.add(user);
    }

    public boolean isLikedBy(User loggedInUser) {
        if (likedBy.contains(loggedInUser))
            return true;
        return false;
    }

    public void downvote() {
        this.upvotes--;
    }

    public void removeUserFromLikedList(User user) {
        likedBy.remove(user);
    }

    public void addComment(Comment entity) {
        comments.add(entity);
    }

}
