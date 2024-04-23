package edu.neu.neuconnect.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.SortComparator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "multiple_id")
public class MultipleServiceRequest extends ServiceRequest {
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "ParticipantsMSR", joinColumns = @JoinColumn(name = "msr_id"), inverseJoinColumns = @JoinColumn(name = "participant_id"))

    private List<User> participants = new ArrayList<>();

    public void addParticipant(User participant) {
        participants.add(participant);
    }

    public MultipleServiceRequest(String title, String description, int karma) {
        super(title, description, karma);
    }
}
